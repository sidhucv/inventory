package products.inventory.resources;

public class Product {
    String Name;
    float costPrice;
    float sellingPrice;
    int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(String name,float costPrice, float sellingPrice, int quantity) {
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.quantity=quantity;
        Name = name;
    }

    public Product(){

    }

    @Override
    public String toString() {
        return "Product{" +
                "Name='" + Name + '\'' +
                ", costPrice=" + costPrice +
                ", sellingPrice=" + sellingPrice +
                ", quantity=" + quantity +
                '}';
    }

    public float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
