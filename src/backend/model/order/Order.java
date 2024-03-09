package backend.model.order;

import backend.model.BaseModel;
/**
 * @author Aliabbos Ashurov
 * Date: 06/March/2024  12:07
 **/
public class Order extends BaseModel {

    String cartId;
    String productId;
    int count;

    public Order(String cartId, String productId, int count) {
        this.cartId = cartId;
        this.productId = productId;
        this.count = count;
    }
    public String getCartId() {
        return cartId;
    }
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
