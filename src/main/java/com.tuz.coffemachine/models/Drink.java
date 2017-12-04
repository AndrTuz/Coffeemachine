package com.tuz.coffemachine.models;

import com.tuz.coffemachine.models.exception.IncorrectArgumentException;

public class Drink {
    private int id_drink;
    private String name;
    private int cost;
    private int availiable_sum;
    private int capacity;
    private int countInOrder;

    public int getCountInOrder() {
        return countInOrder;
    }

    public void setCountInOrder(int countInOrder) throws IncorrectArgumentException {
        if(countInOrder <= 0){
            throw new IncorrectArgumentException("countInOrder must be positive number");
        }
        this.countInOrder = countInOrder;
    }

    public int getId_drink() {
        return id_drink;
    }

    public void setId_drink(int id_drink) throws IncorrectArgumentException {
        if(id_drink <= 0){
            throw new IncorrectArgumentException("Id_drink must be positive number");
        }
        this.id_drink = id_drink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IncorrectArgumentException {
        if(name==null || name.isEmpty()){
            throw new IncorrectArgumentException("Name can't be empty or null");
        }
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) throws IncorrectArgumentException {
        if(cost <= 0){
            throw new IncorrectArgumentException("Cost must be positive number");
        }
        this.cost = cost;
    }

    public int getAvailiable_sum() {
        return availiable_sum;
    }

    public void setAvailiable_sum(int availiable_sum) throws IncorrectArgumentException {
        if(availiable_sum < 0){
            throw new IncorrectArgumentException("availiable_sum must be positive number");
        }
        this.availiable_sum = availiable_sum;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacty) throws IncorrectArgumentException {
        if(capacty <= 0){
            throw new IncorrectArgumentException("Capacity must be positive number");
        }
        this.capacity = capacty;
    }
}
