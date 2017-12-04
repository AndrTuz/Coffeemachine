package com.tuz.coffemachine.dao;

import com.tuz.coffemachine.dao.exceptions.DaoException;
import com.tuz.coffemachine.models.Drink;
import com.tuz.coffemachine.models.Order;
import com.tuz.coffemachine.models.User;
import com.tuz.coffemachine.models.exception.IncorrectArgumentException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDao extends AbstractDao {
    private static final Logger LOG= Logger.getLogger(OrderDao.class);
    private static final String GET_ALL_ORDERS =
            "SELECT orders_drinks.id_order, users.login, orders.delivery_room, drinks.name, orders_drinks.count, orders.total_sum \n" +
            "FROM orders_drinks \n" +
            "INNER JOIN orders ON orders_drinks.id_order =  orders.id_order \n" +
            "INNER JOIN drinks ON orders_drinks.id_drink = drinks.id_drink\n" +
            "INNER JOIN users ON orders.id_user = users.id_user\n" +
            "ORDER BY id_order";
    private static final String ADD_ORDER = "INSERT INTO `orders` (`id_user`, `delivery_room`, `total_sum`) VALUES (?, ?, ?)";
    private static final String ADD_ORDER_DRINK_TABLE = "INSERT INTO `orders_drinks` (`id_order`, `id_drink`, `count`) VALUES (?, ?, ?)";
    private static final String GET_ID_ORDER = "SELECT MAX(id_order) FROM orders";
    private static final String UPDATE_USER_SUM= "UPDATE users SET total_sum = ? WHERE id_user = ?";
    private static final String UPDATE_DRINKS_COUNT= "UPDATE drinks SET availiable_sum = ? WHERE id_drink = ?";

    public ArrayList<Order> getAllOrders() throws DaoException {
        ArrayList<Order> orders = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(GET_ALL_ORDERS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Order order = new Order();
                User user = new User();
                ArrayList<Drink> drinks = new ArrayList<>();
                Drink drink = new Drink();
                order.setId_order(rs.getInt("id_order"));
                user.setLogin(rs.getString("login"));
                order.setUser(user);
                order.setDelivery_room(rs.getInt("delivery_room"));
                drink.setName(rs.getString("name"));
                drink.setCountInOrder(rs.getInt("count"));
                drinks.add(drink);
                order.setDrinks(drinks);
                order.setTotal_sum(rs.getInt("total_sum"));
                orders.add(order);
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
        return orders;
    }
    public int fillOrder(Order order) throws DaoException {
        int id_order = 0;
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;
        try {
            statement = connection.prepareStatement(ADD_ORDER);
            statement.setInt(1, order.getUser().getId_user());
            //statement.setInt(1, 6);
            statement.setInt(2, order.getDelivery_room());
            statement.setInt(3, order.getTotal_sum());
            statement.executeUpdate();
            statement1 = connection.prepareStatement(GET_ID_ORDER);
            ResultSet rs = statement1.executeQuery();
            if(rs.next()) {
                id_order = rs.getInt(1);
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
        return id_order;
    }
    public void fillOrderDrinkTable(int id_order, Drink drink) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(ADD_ORDER_DRINK_TABLE);
            statement.setInt(1, id_order);
            statement.setInt(2, drink.getId_drink());
            statement.setInt(3, drink.getCountInOrder());
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
    public void changeSumAfterOrder(int id_user, int newTotalSum) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_USER_SUM);
            statement.setInt(1, newTotalSum);
            statement.setInt(2, id_user);
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
    public void updateDrinksCount(Drink drink) throws DaoException {
        int id_drink = drink.getId_drink();
        int new_AvailiableCount = drink.getAvailiable_sum() - drink.getCountInOrder();
        System.out.println(drink.getName()+" "+new_AvailiableCount);
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_DRINKS_COUNT);
            statement.setInt(1, new_AvailiableCount);
            statement.setInt(2, id_drink);
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
