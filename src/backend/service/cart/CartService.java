package backend.service.cart;

import backend.model.cart.Cart;
import backend.service.BaseService;
import java.util.List;
/**
 * @author Aliabbos Ashurov
 * Date: 01/March/2024  17:41
 **/
public interface CartService extends BaseService<Cart> {
    Cart findOrCreate(String userId);
    Cart getCartByUserid(String userId);
    List<Cart> getAllCartPaid(String userId);
}
