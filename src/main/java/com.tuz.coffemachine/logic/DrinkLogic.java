package com.tuz.coffemachine.logic;

import com.tuz.coffemachine.dao.DrinkDao;
import com.tuz.coffemachine.dao.exceptions.DaoException;
import com.tuz.coffemachine.logic.exception.LogicException;
import com.tuz.coffemachine.models.Drink;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class DrinkLogic {
    private static final Logger LOG= Logger.getLogger(DrinkLogic.class);

    public ArrayList<Drink> getAllDrinks() throws LogicException {
        DrinkDao dao = new DrinkDao();
        ArrayList<Drink> drinks = new ArrayList<>();
        try {
            drinks = dao.getAllDrinks();
        }catch (DaoException ex) {
            LOG.error(ex);
            throw new LogicException(ex);
        }finally{
            dao.returnConnection();
        }
        return drinks;
    }
    public ArrayList<Drink> fillingDrinks() throws LogicException {
        DrinkDao dao = new DrinkDao();
        ArrayList<Drink> drinks = new ArrayList<>();
        try {
            if(dao.fillingDrinks())drinks = dao.getAllDrinks();

        }catch (DaoException ex) {
            LOG.error(ex);
            throw new LogicException(ex);
        }finally{
            dao.returnConnection();
        }
        return drinks;
    }
}
