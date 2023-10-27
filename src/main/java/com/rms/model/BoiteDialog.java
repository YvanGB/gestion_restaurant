package com.rms.model;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BoiteDialog {

    public static void showError(String titre, String contenu) {
        afficherBoiteDialog(AlertType.ERROR, titre, contenu);
    }

    public static void showWarning(String titre, String contenu) {
        afficherBoiteDialog(AlertType.WARNING, titre, contenu);
    }

    public static void showInformation(String titre, String contenu) {
        afficherBoiteDialog(AlertType.INFORMATION, titre, contenu);
    }

    private static void afficherBoiteDialog(AlertType type, String titre, String contenu) {
        Alert alert = new Alert(type);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(contenu);
        alert.showAndWait();
    }
}
