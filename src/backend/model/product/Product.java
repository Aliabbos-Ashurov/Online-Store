package backend.model.product;

import backend.enums.Category;
import backend.model.BaseModel;
import java.util.Objects;
/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  21:10
 **/
public class Product extends BaseModel implements Cloneable {
    private String productName;
    private Category category;
    private double price;
    private boolean onDiscount;

    public Product(String productName, Category category, double price, boolean onDiscount) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.onDiscount = onDiscount;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public boolean isOnDiscount() {
        return onDiscount;
    }
    public void setOnDiscount(boolean onDiscount) {
        this.onDiscount = onDiscount;
    }

    @Override
    public String toString() {
        return String.format("Product -> [ProductName: %s, Category: %s, Price: %.2f, OnDiscount: %b]",
                productName, category, price,onDiscount);
    }
    public String toStringWithId() {
        return String.format("Product -> [ProductName: %s, Category: %s, Price: %.2f, OnDiscount: %b, ID: %s]",
                productName, category, price,onDiscount,super.getId());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 &&  onDiscount == product.onDiscount && Objects.equals(productName, product.productName) && category == product.category;
    }
    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, category, price,onDiscount);
    }
}
