package com.exercise45rest.services;

import com.exercise45rest.model.Product;
import java.util.ArrayList;

public interface DAOProducts 
{
	public void SaveProduct(Product myProduct);
	public boolean UpdateProduct(int idProduct);
	public boolean DeleteProduct(int diProduct);
	public Product ReadProduct(int myProduct);
	public ArrayList<Product> ReadAllProduct();
	
}
