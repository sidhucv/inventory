package products.inventory.maintainance;


import products.inventory.dao.ProductListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import products.inventory.resources.Product;
import products.inventory.exception.InventoryException;
import org.json.JSONObject;

import java.util.HashMap;

@Controller
@RequestMapping("/Inventory")
public class InventoryService {

    @Autowired
    ProductListDao productListDao;

    @RequestMapping( method = RequestMethod.POST, params = "type=add")
    @ResponseBody
    public String add( @RequestParam String name,@RequestParam String costPrice, @RequestParam String sellingPrice)
    {

        //setting Default quantity 1
        int quantity =1;
        Product newProduct = new Product(name,Float.parseFloat(costPrice),Float.parseFloat(sellingPrice),quantity);
        productListDao.push(newProduct);
        return "success";
    }

    @RequestMapping( method = RequestMethod.POST,params = "type=delete")
    @ResponseBody
    public String delete(@RequestParam String name)
    {
        productListDao.delete(name);
        return "success";

    }
    @RequestMapping( method = RequestMethod.POST, params = "type=updateQuantity")
    @ResponseBody
    public String updateQuantity(@RequestParam String name,@RequestParam int quantity) throws InventoryException{
        productListDao.updateQuantity(name,quantity);
        return "success";
    }
    @RequestMapping(method = RequestMethod.POST, params = "type=sell")
    @ResponseBody
    public String updateSell(@RequestParam String name,@RequestParam int quantity)throws InventoryException {

        productListDao.updateSellQuantity(name, quantity);
        return "Success";

    }
    @RequestMapping(method = RequestMethod.POST, params = "type=report")
    @ResponseBody
    public String report(){
        HashMap<Integer,String> report = productListDao.report();
        String JsonResponse = new JSONObject(report).toString();
        return JsonResponse;
    }

    @RequestMapping( method = RequestMethod.POST, params = "type=updateSellingPrice")
    public void updateSellingPrice(@RequestParam String name,@RequestParam String newSellingPrice){

        productListDao.updateSellingPrice(name,Float.parseFloat(newSellingPrice));
    }
}
