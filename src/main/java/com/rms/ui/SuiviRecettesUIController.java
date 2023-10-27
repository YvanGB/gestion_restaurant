package com.rms.ui;

import com.rms.AppLauncher;
import com.rms.dao.DAOException;
import com.rms.db.AccessDB;
import com.rms.db.RMSDBException;
import com.rms.model.Paiement;
import com.rms.model.Recette;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

import java.util.List;

public class SuiviRecettesUIController {
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> periodeComboBox;
    @FXML
    private ComboBox<String> produitComboBox;
    @FXML
    private Button quitBtn;
    @FXML
    private TableColumn<Recette, String> periodeColumn;
    @FXML
    private TableColumn<Recette, Double> montantColumn;
    @FXML
    private TableView recetteTable;

    private List<Recette> recettes;

    public void initialize() {
        AppLauncher.getInstance().setSuiviRecettesUIController(this);

        initializeComboBoxes();
        initializeTableColumns();
//        loadRecettesForSelectedPeriode();
        configureDatePicker();

        recettes = AccessDB.getInstance().getRecettes();

        // Configure recetteTypeComboBox with options

        periodeComboBox.setOnAction(event -> {
            String selectedOption = periodeComboBox.getValue();
            List<Recette> filteredRecettes = filterRecettes(selectedOption);
            // Update the UI to display filteredRecettes in recetteTableView
        });

    }

    private void initializeComboBoxes() {
        periodeComboBox.getItems().addAll("Journalier", "Hebdomadaire", "Mensuel");
        periodeComboBox.setValue("Journalier");
    }

    private void initializeTableColumns() {
        periodeColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        montantColumn.setCellValueFactory(new PropertyValueFactory<>("montantTotal"));

    }

    private List<Recette> filterRecettes(String option) {
        List<Recette> filteredRecettes = new ArrayList<>();
        LocalDate today = LocalDate.now();

        if (option.equals("Journalier")) {
            for (Recette recette : recettes) {
                if (recette.getDate().equals(today)) {
                    filteredRecettes.add(recette);
                }
            }
        } else if (option.equals("Hebdomadaire")) {
            // Calculate start and end of the week
            LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
            LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

            for (Recette recette : recettes) {
                LocalDate recetteDate = recette.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (recetteDate.isEqual(today) || (recetteDate.isAfter(startOfWeek) && recetteDate.isBefore(endOfWeek))) {
                    filteredRecettes.add(recette);
                }
            }
        } else if (option.equals("Mensuel")) {
            // Calculate start and end of the month
            LocalDate startOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate endOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());

            for (Recette recette : recettes) {
                LocalDate recetteDate = recette.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if (recetteDate.isEqual(today) || (recetteDate.isAfter(startOfMonth) && recetteDate.isBefore(endOfMonth))) {
                    filteredRecettes.add(recette);
                }
            }
        }

        return filteredRecettes;
    }


    private void configureDatePicker() {
        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        String selectedPeriode = periodeComboBox.getValue();

                        // Disable dates outside the selected period
                        if ("Hebdomadaire".equals(selectedPeriode)) {
                            LocalDate today = LocalDate.now();
                            LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
                            LocalDate endOfWeek = startOfWeek.plusDays(6);

                            setDisable(item.isBefore(startOfWeek) || item.isAfter(endOfWeek));
                        } else if ("Mensuel".equals(selectedPeriode)) {
                            // Implement logic to disable dates outside the current month
                            // ...
                        }
                    }
                };
            }
        });
    }
    private void loadRecettesForSelectedPeriode(String periode, LocalDate selectedDate) {
        Date selectedDateAsDate = java.sql.Date.valueOf(selectedDate); // Convert LocalDate to Date

        List<Recette> recettes = getRecettesForSelectedPeriode(periode, selectedDateAsDate);

        ObservableList<Recette> recetteObservableList = FXCollections.observableArrayList(recettes);

        recetteObservableList.addAll(AccessDB.getInstance().getRecette());
        recetteTable.setItems(recetteObservableList);
    }

    private List<Recette> getRecettesForSelectedPeriode(String periode, Date date) {
        List<Recette> recettes = null;
        try {
            if ("Journalier".equals(periode)) {
                recettes = AccessDB.getInstance().getRecettesForDailyPeriod(date);
            } else if ("Hebdomadaire".equals(periode)) {
                recettes = AccessDB.getInstance().getRecettesForWeeklyPeriod(date);
            } else if ("Mensuel".equals(periode)) {
                recettes = AccessDB.getInstance().getRecettesForMonthlyPeriod(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer les exceptions de manière appropriée
        }
        return recettes;
    }


    @FXML
    void onPeriodeSelected(ActionEvent event) {
        String selectedPeriode = periodeComboBox.getValue();

        if (selectedPeriode != null) {
            // Clear the date selection
            datePicker.setValue(null);
            LocalDate selectedDate = datePicker.getValue();

//                if ("Hebdomadaire".equals(selectedPeriode)) {
//                    // Set the DatePicker's date to the beginning of the week
//                    LocalDate today = LocalDate.now();
//                    LocalDate startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1);
//                    datePicker.setValue(startOfWeek);
//                } else if ("Mensuel".equals(selectedPeriode)) {
//                    // Set the DatePicker's date to the beginning of the month
//                    LocalDate today = LocalDate.now();
//                    LocalDate startOfMonth = today.withDayOfMonth(1);
//                    datePicker.setValue(startOfMonth);
//                }

                if ("Journalier".equals(selectedPeriode)) {
                    datePicker.setDisable(true);
                    loadRecettesForSelectedPeriode(selectedPeriode, LocalDate.now());
                } else if (selectedDate != null) {
                    datePicker.setDisable(false);
                    loadRecettesForSelectedPeriode(selectedPeriode, selectedDate);
                }
            }
    }

    @FXML
    void onQuit() throws IOException {
        Stage stage = (Stage) datePicker.getScene().getWindow();
//        AppLauncher.getInstance().initRestaurateurLayout();
        stage.close();
    }

}
