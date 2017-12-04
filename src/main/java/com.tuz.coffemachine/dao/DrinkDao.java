package com.tuz.coffemachine.dao;

import com.tuz.coffemachine.dao.exceptions.DaoException;
import com.tuz.coffemachine.models.Drink;
import com.tuz.coffemachine.models.exception.IncorrectArgumentException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DrinkDao extends AbstractDao {
    private static final Logger LOG= Logger.getLogger(DrinkDao.class);
    private static final String GET_ALL_DRINKS = "SELECT * FROM drinks";
    private static final String FILLING_DRINKS = "UPDATE drinks SET availiable_sum = capacity WHERE id_drink>0";

    public ArrayList<Drink> getAllDrinks() throws DaoException {
        ArrayList<Drink> drinks = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(GET_ALL_DRINKS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Drink drink = new Drink();
                drink.setId_drink(rs.getInt("id_drink"));
                drink.setName(rs.getString("name"));
                drink.setCost(rs.getInt("cost"));
                drink.setAvailiable_sum(rs.getInt("availiable_sum"));
                drink.setCapacity(rs.getInt("capacity"));
                drinks.add(drink);
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
        return drinks;
    }
    public boolean fillingDrinks() throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(FILLING_DRINKS);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
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
    }
}
