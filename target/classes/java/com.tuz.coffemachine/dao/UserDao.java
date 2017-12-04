package com.tuz.coffemachine.dao;

import com.tuz.coffemachine.dao.exceptions.DaoException;
import com.tuz.coffemachine.models.User;
import com.tuz.coffemachine.models.exception.IncorrectArgumentException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends AbstractDao{

    private static final Logger LOG= Logger.getLogger(UserDao.class);
    private static final String CHECK_LOGIN = "SELECT `id_user` FROM `users` WHERE `login`= ?";
    private static final String TAKE_PASSWORD_BY_LOGIN = "SELECT `password` FROM `users` WHERE `login`= ?";
    private static final String TAKE_BALANCE_BY_LOGIN = "SELECT `total_sum` FROM `users` WHERE `login`= ?";
    private static final String TAKE_ROLE_BY_LOGIN = "SELECT roles.name FROM roles INNER JOIN users ON users.id_role = roles.id_role WHERE users.login = ?";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM users INNER JOIN roles ON users.id_role = roles.id_role WHERE login = ?";
    private static final String CHECK_PIN = "SELECT `id_user` FROM `users` WHERE `login`= ? AND `pin` = ?";
    private static final String REFILL_TOTAL_SUM= "UPDATE users SET total_sum = ? WHERE login = ?";

    public boolean checkLogin(String login) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHECK_LOGIN);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            return rs.first();
        } catch (SQLException ex) {
            LOG.error(ex);
            throw new DaoException(ex);
        }finally{
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
    }
    public String takePassword(String login) throws DaoException{
        String password = "";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(TAKE_PASSWORD_BY_LOGIN);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if(rs.first()){
                password = rs.getString("password");
            }
        } catch (SQLException ex) {
            LOG.error(ex);
            throw new DaoException(ex);
        }finally{
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
        return password;
    }
    public  int getBalance(String login) throws DaoException {
        int balance = 0;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(TAKE_BALANCE_BY_LOGIN);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                balance = rs.getInt("total_sum");
            }
        } catch (SQLException ex) {
            LOG.error(ex);
            throw new DaoException(ex);
        }finally{
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
        return balance;
    }
    public User getUser(String login) throws DaoException {
        PreparedStatement statement = null;
        User user = new User();
        try {
            statement = connection.prepareStatement(GET_USER_BY_LOGIN);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                user.setId_user(rs.getInt("id_user"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("name"));
                user.setTotal_sum(rs.getInt("total_sum"));
                user.setPin(rs.getString("pin"));
            }
        } catch (SQLException | IncorrectArgumentException ex) {
            LOG.error(ex);
            throw new DaoException(ex);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
        return user;
    }
    public boolean checkPIN(String login, String pin) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CHECK_PIN);
            statement.setString(1, login);
            statement.setString(2, pin);
            ResultSet rs = statement.executeQuery();
            return rs.first();
        } catch (SQLException ex) {
            LOG.error(ex);
            throw new DaoException(ex);
        }finally{
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                    System.out.println(ex);
                }
            }
        }
    }
    public void refillSum(String login, int sum) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(REFILL_TOTAL_SUM);
            statement.setInt(1, sum);
            statement.setString(2, login);
            statement.executeUpdate();
        } catch (SQLException ex) {
            LOG.error(ex);
            throw new DaoException(ex);
        }finally{
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException ex) {
                    LOG.error(ex);
                }
            }
        }
    }
}
