package com.tuz.coffemachine.dao;

import com.tuz.coffemachine.dao.exceptions.DaoException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationDao extends AbstractDao {

    private static final Logger LOG= Logger.getLogger(RegistrationDao.class);
    private static final String ADD_USER = "INSERT INTO `users` (`login`, `password`, `id_role`, `total_sum`, `pin`) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_ROLE_ID = "SELECT `id_role` FROM `roles` WHERE `name`= ?";

    public boolean addInDatabase(String user_login, String user_password, int role_id, String cardpin) throws DaoException {
        PreparedStatement statement = null;
        int total_sum = 0;
        try {
            statement = connection.prepareStatement(ADD_USER);
            statement.setString(1, user_login);
            statement.setString(2, user_password);
            statement.setInt(3, role_id);
            statement.setInt(4, total_sum);
            statement.setString(5, cardpin);
            statement.executeUpdate();
            return true;
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
    public int getRoleID (String role) throws DaoException {
        PreparedStatement statement = null;
        int id=0;
        try {
            System.out.println(role);
            statement = connection.prepareStatement(GET_ROLE_ID);
            statement.setString(1, role);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                id = rs.getInt("id_role");
                return id;
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
        return 0;
    }
}
