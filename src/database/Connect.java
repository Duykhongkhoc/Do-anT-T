/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class Connect {
public void truy_Xuat_CSDL() {
	try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "MANAGER_RES", "1") ;
	
		Statement st = con.createStatement();
                String sqlQuery = "select * from KHACHHANG";
		ResultSet resultSet= st.executeQuery(sqlQuery);
		while(resultSet.next())
		{
			System.out.println(resultSet.getString(1));
		}
	} 
	catch (Exception e) {
		System.out.println(e);;
	}
	
}
}
