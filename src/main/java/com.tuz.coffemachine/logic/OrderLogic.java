package com.tuz.coffemachine.logic;

import com.tuz.coffemachine.dao.OrderDao;
import com.tuz.coffemachine.dao.exceptions.DaoException;
import com.tuz.coffemachine.logic.exception.LogicException;
import com.tuz.coffemachine.models.Drink;
import com.tuz.coffemachine.models.Order;
import com.tuz.coffemachine.models.exception.IncorrectArgumentException;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderLogic {
    private static final Logger LOG= Logger.getLogger(OrderLogic.class);

    public boolean fillOrder(Order order) throws LogicException {
        OrderDao dao = new OrderDao();
        int id_order = 0;
        try{
            id_order = dao.fillOrder(order);
            order.setId_order(id_order);
            for (int i = 0; i < order.getDrinks().size(); i++) {
                Drink drink = order.getDrinks().get(i);
                dao.fillOrderDrinkTable(id_order, drink);
                dao.updateDrinksCount(drink);
            }
            return true;
        } catch (DaoException | IncorrectArgumentException ex) {
            LOG.error(ex);
            throw new LogicException(ex);
        }finally{
            dao.returnConnection();
        }
    }
    public int payForOrder(Order order) throws LogicException {
        int total_sum_order = order.getTotal_sum();
        int user_id = order.getUser().getId_user();
        int user_total_sum = order.getUser().getTotal_sum();
        int newTotalSum = user_total_sum - total_sum_order;
        OrderDao dao = new OrderDao();
        try {
            dao.changeSumAfterOrder(user_id, newTotalSum);
        }catch (DaoException ex){
            throw new LogicException(ex);
        }finally{
            dao.returnConnection();
        }
        return newTotalSum;
    }
    public ArrayList<Order> getAllOrders () throws LogicException{
        OrderDao dao = new OrderDao();
        ArrayList<Order> orders;

        try {
            orders = dao.getAllOrders();
        } catch (DaoException ex) {
            LOG.error(ex);
            throw new LogicException(ex);
        }
        for (Order x : orders){
            System.out.println(
                    x.getId_order()
                  + x.getUser().getLogin()
                   +x.getDelivery_room()+x.getDrinks().get(0).getName()
                   +x.getDrinks().get(0).getCountInOrder()
                    +x.getTotal_sum()
            );
        }
        return orders;
    }
}
