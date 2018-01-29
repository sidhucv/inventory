package products.inventory.dao;

import org.springframework.stereotype.Component;
import products.inventory.exception.InventoryException;
import products.inventory.resources.Product;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Component
public class ProductListDaoImpl implements ProductListDao {


    static Map<String,Product> productist = new TreeMap<String,Product> ();

     static Double profit =0.00;


    public void push(Product product){
        productist.put(product.getName(),product);
    }

     public boolean delete(String name){
        if(productist.get(name)!=null) {
            productist.remove(name);
            return true;
        }
            return false;
     }

     public void updateQuantity(String name, int newQuantity) throws InventoryException {
        Product updateProduct = productist.get(name);
        if(updateProduct== null)
        {
            throw new InventoryException("product not existing "+name);
        }
        int existingQuanity = updateProduct.getQuantity();
         updateProduct.setQuantity(existingQuanity+newQuantity);
         productist.put(name,updateProduct);
     }

     public void updateSellQuantity(String name, int productQuantity)throws InventoryException{
        Product updateProductSold = productist.get(name);
        int quantity = updateProductSold.getQuantity();
        if(quantity>=productQuantity){
            float sellingPrice = updateProductSold.getSellingPrice();
            float costPrice = updateProductSold.getCostPrice();
            profit = profit + (sellingPrice - costPrice)*productQuantity;
            int newQuantity = quantity-productQuantity;
            updateProductSold.setQuantity(newQuantity);
            productist.put(updateProductSold.getName(),updateProductSold);
        }else{
            throw new InventoryException("selling quantity for product "+name+"is:"+productQuantity+" stock in the Inventory is "+ quantity);
        }

     }

     public HashMap<Integer,String> report(){

         HashMap<Integer,String> report = new HashMap<Integer, String>();
         Integer count = 1;
         for (Map.Entry<String,Product> products : productist.entrySet()) {
             Product product = products.getValue();
             report.put(count++,product.toString());
         }
         report.put(count,"total amount of profit after sales:"+profit);
         return report;
     }

     public void updateSellingPrice(String name, float newSellingPrice){
        Product product = productist.get(name);
        product.setSellingPrice(newSellingPrice);
        productist.put(product.getName(),product);
     }
}
