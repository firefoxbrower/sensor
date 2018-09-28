package com.sensor.utils;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

public class SunHaoSqlCallback  implements SqlCallback {

	public  Sql sql ;
	public  ResultSet rs;
	public  Connection conn;
 
	public  SunHaoSqlCallback(Connection conn, ResultSet rs, Sql sql){
		try {
			this.invoke(conn, rs, sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	public  SunHaoSqlCallback( Sql sql){
		try {
			this.invoke(conn, rs, sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	};
	


	@Override
	public Object invoke(Connection conn, ResultSet rs, Sql sql)
			throws SQLException {
		
		ResultSetMetaData md = rs.getMetaData();
	    int columnCount = md.getColumnCount();
	    ArrayList list = new ArrayList();

	    while (rs.next()) {
	      Map rowData = new HashMap(columnCount);
	      for (int i = 1; i <= columnCount; i++) {
	        Object v = rs.getObject(i);

	        if ((v != null) && ((v.getClass() == java.util.Date.class) || (v.getClass() == java.sql.Date.class))) {
	          Timestamp ts = rs.getTimestamp(i);
	          v = new java.util.Date(ts.getTime());
	        }
	        else if ((v != null) && (v.getClass() == Clob.class)) {
	        	Clob clob = (Clob)v;
	        	v =clob != null ? clob.getSubString(1L, (int)clob.length()) : null;
	      
	        }
	        rowData.put(md.getColumnName(i), v);
	      }
	      list.add(rowData);
	    }

	    return list;
		
	}

}
