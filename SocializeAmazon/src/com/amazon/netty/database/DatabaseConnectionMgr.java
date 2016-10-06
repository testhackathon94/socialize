package com.amazon.netty.database;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.netty.controller.AnnotatedPathManager;


public class DatabaseConnectionMgr {
	private static DatabaseConnectionMgr instance = null;
	Logger log = LogManager.getLogger(AnnotatedPathManager.class);
	private GenericObjectPool  connectionPool  = null;
	private DataSource dataSource = null;
	public static final String DRIVER = "com.orientechnologies.orient.jdbc.OrientJdbcDriver";
    public static final String URL = "jdbc:orient:remote:192.168.1.120/mio";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "gms123";
    
        
	private DatabaseConnectionMgr () {
		try {
			init();
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			log.error("Database Connection error"+ e);
		}
	}
	
	public static DatabaseConnectionMgr getInstance(){
		if(instance == null){
			synchronized (DatabaseConnectionMgr.class) {
				if(instance == null){
					instance = new DatabaseConnectionMgr();
				}
			}
		}
		return instance;
	}
	
	
	private void init() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
		
		 Class.forName(DatabaseConnectionMgr.DRIVER).newInstance();
		 
		  // Creates an instance of GenericObjectPool that holds our
	      // pool of connections object.
	      connectionPool = new GenericObjectPool();
	      connectionPool.setMaxActive(10);
	      
      	// Creates a connection factory object which will be use by
        // the pool to create the connection object. We passes the
        // JDBC url info, username and password.
        ConnectionFactory cf = new DriverManagerConnectionFactory(
        		DatabaseConnectionMgr.URL,
        		DatabaseConnectionMgr.USERNAME,
        		DatabaseConnectionMgr.PASSWORD);

        // Creates a PoolableConnectionFactory that will wraps the
        // connection object created by the ConnectionFactory to add
        // object pooling functionality.
        PoolableConnectionFactory pcf =
                new PoolableConnectionFactory(cf, connectionPool,
                        null, null, false, true);
        
         dataSource = new PoolingDataSource(connectionPool);
			
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		return dataSource.getConnection();
	}

	
}
