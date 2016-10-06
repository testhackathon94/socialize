package com.amazon.netty.database;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDatabaseManager {
	
	public Connection openConnection() throws ClassNotFoundException, SQLException{
		Connection conn = DatabaseConnectionMgr.getInstance().getConnection();
		return conn;
	}
	
	public void endConnection(Connection conn) throws SQLException{
		conn.close();
	}
	
	public void commit(Connection conn) throws SQLException{
		conn.commit();
	}
	
	public void rollBack(Connection conn) throws SQLException{
		conn.rollback();
	}
	
}
