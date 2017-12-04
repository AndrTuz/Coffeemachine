package com.tuz.coffemachine.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tuz.coffemachine.pool.ConnectionPool;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Nadezhda
 */
public class MainServletContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicConfigurator.configure();
        //ServletContext servletContext = sce.getServletContext();
        //String log4jFile = servletContext.getRealPath("/")+servletContext.getInitParameter("log4j");
        //DOMConfigurator.configure(log4jFile);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.releaseConnections();
    }
    
}
