package com.tuz.coffemachine.pool;

import com.tuz.coffemachine.resources.DatabaseManager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


public final class ConnectionPool {

    private static ConnectionPool instance = null;
    private static ReentrantLock rlock = new ReentrantLock();
    private final int CONNECTIONS_NUMBER = 10;
    private BlockingQueue<Connection> connections = new ArrayBlockingQueue<>(CONNECTIONS_NUMBER);
    private final int TIME_TO_WAIT = 1000;
   // private static final Logger LOG=Logger.getLogger(ConnectionPool.class);

    private ConnectionPool(){
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
           // LOG.info("create Connection Pool");
        } catch (SQLException ex) {
            //LOG.error(ex);
            System.out.println(ex);
        }
        for(int i=0; i<CONNECTIONS_NUMBER; i++){
            try {
                connections.put(DriverManager.getConnection(DatabaseManager.getProperty("db.url"),
                                                            DatabaseManager.getProperty("db.users"),
                                                            DatabaseManager.getProperty("db.password")));
            } catch (    InterruptedException | SQLException ex) {
                //LOG.error(ex);
                System.out.println(ex);
            }
        }
    }
    public Connection getConnection(){
        Connection conn = null;
        try {
            conn = connections.take();
        } catch (InterruptedException ex) {
            //LOG.error(ex);
            System.out.println(ex);
        }
        return conn;
    }
    public void returnConnection(Connection conn){
        try {
            connections.put(conn);
        } catch (InterruptedException ex) {
            //LOG.error(ex);
            System.out.println(ex);
        }
    }
    public void releaseConnections(){
        int remainingConnection=CONNECTIONS_NUMBER;
        while(remainingConnection>0){
            try {
                connections.poll(TIME_TO_WAIT, TimeUnit.MILLISECONDS).close();
                remainingConnection--;
            } catch (SQLException | InterruptedException ex) {
                //LOG.error(ex);
                System.out.println(ex);
            }
        }
        //LOG.info("release Connections");
        connections.clear();
    }
    public static ConnectionPool getInstance(){
        if(instance==null){
            rlock.lock();
            try {
                instance = new ConnectionPool();
            } finally {
                rlock.unlock();
            }
        }
        return instance;

    }

}
