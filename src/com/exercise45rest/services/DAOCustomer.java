package com.exercise45rest.services;


import java.util.ArrayList;
import com.exercise45rest.model.Customer;;

// DAO its a design pattern
public interface DAOCustomer 
{
	
	public void SaveCoustomer(Customer myCustomer);
	public boolean UpdateCustomer(Customer myCustomer);
	public boolean DeleteCustomer(int idCustomer);
	public Customer ReadCustomer(int idCustomer);
	public ArrayList <Customer> ReadAllCustomer();
}
