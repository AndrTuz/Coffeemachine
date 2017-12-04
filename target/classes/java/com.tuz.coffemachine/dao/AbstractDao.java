package com.tuz.coffemachine.dao;

import com.tuz.coffemachine.pool.ConnectionPool;

import java.sql.Connection;

public class AbstractDao {

    protected Connection connection;


    public AbstractDao(){
        connection = ConnectionPool.getInstance().getConnection();
        System.out.println("connection");
    }
    public void returnConnection(){
        ConnectionPool.getInstance().returnConnection(connection);
    }
}