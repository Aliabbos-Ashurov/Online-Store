package backend.service.order;

import backend.model.cart.Cart;
import backend.model.order.Order;
import backend.service.BaseService;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * Date: 06/March/2024  13:07
 **/
public interface OrderService extends BaseService<Order> {
    boolean add(Order order);
    boolean remove(Cart cart,String productId);
    boolean clear(Cart cart);
    List<Order>getAll(Cart cart);
    List<Order> getArchive(List<Cart> list);
}
