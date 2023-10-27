package com.rms.factories;

import com.rms.dao.UserDaoImpl;

public class UserFactory extends AbstractFactory {
        public UserDaoImpl getUserDaoImpl(Class<? extends UserDaoImpl> typeUserDao) {
            if (typeUserDao == null) {
                return null;
            }

            if (typeUserDao == UserDaoImpl.class) {
                return new UserDaoImpl();
            }

            return null;
        }
}