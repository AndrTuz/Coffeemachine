package com.tuz.coffemachine.logic;

import com.tuz.coffemachine.dao.UserDao;
import com.tuz.coffemachine.dao.exceptions.DaoException;
import com.tuz.coffemachine.logic.exception.LogicException;
import com.tuz.coffemachine.models.User;
import org.apache.log4j.Logger;


public class LoginLogic {
    private static final Logger LOG= Logger.getLogger(DrinkLogic.class);

    public boolean checkLogin(String login, String password) throws LogicException {
        UserDao dao = new UserDao();
        try{
            return dao.checkLogin(login) && (password.equals(dao.takePassword(login)));
        } catch (DaoException ex) {
            LOG.error(ex);
            throw new LogicException(ex);
        }finally{
            dao.returnConnection();
        }
    }
    public boolean checkPIN(String login, String pin) throws LogicException {
        UserDao dao = new UserDao();
        try{
            return dao.checkPIN(login, pin);
        } catch (DaoException ex) {
            LOG.error(ex);
            throw new LogicException(ex);
        }finally{
            dao.returnConnection();
        }
    }
    public void refillSum (String login, int refill_sum) throws LogicException {
        UserDao dao = new UserDao();
        try{
            int total_sum = refill_sum + dao.getBalance(login);
            dao.refillSum(login, total_sum);
        } catch (DaoException ex) {
            LOG.error(ex);
            throw new LogicException(ex);
        }finally{
            dao.returnConnection();
        }
    }
    public User getUserByLogin(String login) throws LogicException {
        UserDao dao = new UserDao();
        User user = new User();
        try {
            user = dao.getUser(login);
        }catch (DaoException ex) {
            LOG.error(ex);
            throw new LogicException(ex);
        }finally{
            dao.returnConnection();
        }
        return user;
    }
}
