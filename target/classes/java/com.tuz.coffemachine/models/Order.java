package com.tuz.coffemachine.models;

import com.tuz.coffemachine.models.exception.IncorrectArgumentException;

import java.util.ArrayList;

public class Order {
    private int id_order;
    private User user;
    private ArrayList<Drink> drinks;
    private int delivery_room;
    private int total_sum;

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) throws IncorrectArgumentException {
        if(id_order <= 0){
            throw new IncorrectArgumentException("Id_order must be positive number");
        }
        this.id_order = id_order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) throws IncorrectArgumentException {
        if(user==null) {
            throw new IncorrectArgumentException("User can't be null");
        }
        this.user = user;
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(ArrayList<Drink> drinks) throws IncorrectArgumentException {
        if(drinks==null) {
            throw new IncorrectArgumentException("Drinks can't be null");
        }
        this.drinks = drinks;
    }

    public int getDelivery_room() {
        return delivery_room;
    }

    public void setDelivery_room(int delivery_room) throws IncorrectArgumentException {
        if(delivery_room <= 0){
            throw new IncorrectArgumentException("Roomr must be positive number");
        }
        this.delivery_room = delivery_room;
    }

    public int getTotal_sum() {
        return total_sum;
    }

    public void setTotal_sum(int total_sum) throws IncorrectArgumentException {
        if(total_sum < 0){
            throw new IncorrectArgumentException("Total_sum must be positive number");
        }
        this.total_sum = total_sum;
    }
}
