package com.exercise45rest.services;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.exercise45rest.services.ConnectionFactory;

import com.exercise45rest.model.Customer;

@Path("/Customer")
public class DAOCustomerImpl implements DAOCustomer 
{
	Connection conn = null;
	PreparedStatement pstmnt = null;
	ResultSet rs = null;
	String sentenciaSQL = "";
	int rowsAffected = 0;

	public Connection getConnection()
	{
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		//conn = ConnectionFactory.getConnection();
		return conn;
	}
	
	@Override
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void SaveCoustomer(Customer myCustomer)
	{
		String sentenciaSQL = "INSERT INTO Customer (nameCustomer, addressCustomer, ageCustomer, heightCutomer, weightCustomer, isSingle) VALUES(?,?,?,?,?,?)";
		conn = getConnection();
		try {
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setString(1, myCustomer.getNameCustomer());
			pstmnt.setString(2, myCustomer.getAddressCustomer());
			pstmnt.setInt(3, myCustomer.getAgeCustomer());
			pstmnt.setDouble(4, myCustomer.getHeightCustomer());
			pstmnt.setDouble(5, myCustomer.getWeightCustomer());
			pstmnt.setBoolean(6, myCustomer.isSingle());
			pstmnt.executeUpdate();
			System.out.println("Record Added to Database!!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean UpdateCustomer(Customer myCustomer) {
		conn = getConnection();
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("dao.properties");
		
		try {
			props.load(in);
			if(props != null)
			{
				sentenciaSQL = props.getProperty("SQLUpdateCustomer");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			pstmnt = conn.prepareStatement(sentenciaSQL);
			pstmnt.setString(1, myCustomer.getNameCustomer());
			pstmnt.setString(2, myCustomer.getAddressCustomer());
			pstmnt.setInt(3, myCustomer.getAgeCustomer());
			pstmnt.setDouble(4, myCustomer.getHeightCustomer());
			pstmnt.setDouble(5, myCustomer.getWeightCustomer());
			pstmnt.setBoolean(6, myCustomer.isSingle());
			pstmnt.setInt(7, myCustomer.getIdCustomer());
			rowsAffected = pstmnt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				pstmnt.close();
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		
		return (rowsAffected!=0)?true:false;
	}

	@Override
	public boolean DeleteCustomer(int idCustomer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer ReadCustomer(int idCustomer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@GET
	
	public ArrayList<Customer> ReadAllCustomer() 
	{
		ArrayList<Customer> myCustomers = new ArrayList<Customer>();
		Customer myCustomer = new Customer();
		
		conn = getConnection();
		Properties props = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("dao.properties");
		
		try {
			props.load(in);
			if(props!=null)
			{
				sentenciaSQL = props.getProperty("SQLReadAllCustomer");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			pstmnt = conn.prepareStatement(sentenciaSQL);
			rs = pstmnt.executeQuery();
			while(rs.next())
			{
				myCustomer.setIdCustomer(rs.getInt("idCustomer"));
				myCustomer.setNameCustomer(rs.getString("nameCustomer"));
				myCustomer.setAddressCustomer(rs.getString("addressCuatomer"));
				myCustomer.setAgeCustomer(rs.getInt("ageCustomer"));
				myCustomer.setHeightCustomer(rs.getDouble("heightCustomer"));
				myCustomer.setWeightCustomer(rs.getDouble("weightCustomer"));
				//myCustomer.setSingle(rs.get);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
