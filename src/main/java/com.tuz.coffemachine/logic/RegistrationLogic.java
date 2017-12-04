package com.tuz.coffemachine.logic;

import com.tuz.coffemachine.dao.RegistrationDao;
import com.tuz.coffemachine.dao.UserDao;
import com.tuz.coffemachine.dao.exceptions.DaoException;
import com.tuz.coffemachine.logic.exception.LogicException;
import org.apache.log4j.Logger;

public class RegistrationLogic {
    private static final Logger LOG= Logger.getLogger(OrderLogic.class);

    public boolean checkLogin(String login) throws LogicException {
        UserDao dao = new UserDao();
        try {
            return dao.checkLogin(login);
        } catch (DaoException ex) {
            LOG.error(ex);
            throw new LogicException(ex);
        } finally {
            dao.returnConnection();
        }
    }
    public boolean createNewUser(String user_name, String user_password, String cardpin, String role) throws LogicException {
        RegistrationDao dao = new RegistrationDao();
        try{
            int role_id = dao.getRoleID(role);
            if(dao.addInDatabase(user_name, user_password, role_id, cardpin)) {
                return true;
            }
        } catch (DaoException ex) {
            LOG.error(ex);
            throw new LogicException(ex);
        }finally{
            dao.returnConnection();
        }
        return false;
    }
}
