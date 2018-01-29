package products.inventory.dao;

import products.inventory.resources.Product;
import products.inventory.exception.InventoryException;

import java.util.HashMap;

public interface ProductListDao {

    public void push(Product product);

    public boolean delete(String name);

    public void updateQuantity(String name, int newQuantity) throws InventoryException;

    public void updateSellQuantity(String name, int productQuantity)throws InventoryException;

    public HashMap<Integer,String> report();

    public void updateSellingPrice(String name, float sellingPrice);
}
