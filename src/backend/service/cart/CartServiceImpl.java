package backend.service.cart;

import backend.model.cart.Cart;
import backend.service.product.data.ProductData;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Aliabbos Ashurov
 * Date: 01/March/2024  17:43
 **/
public class CartServiceImpl implements CartService, ProductData {
    List<Cart> carts = new ArrayList<>();
    @Override
    public boolean add(Cart object) {
        carts.add(object);
        return true;
    }
    @Override
    public Cart findOrCreate(String userId) {
        Optional<Cart> optionalCart = carts.stream()
                .filter((cart -> cart.getUserId().equals(userId) && !cart.isPaid()))
                .findFirst();
        return optionalCart.orElseGet(()->{
            Cart cart = new Cart(userId);
            carts.add(cart);
            return cart;
        });

    }
    @Override
    public Cart getCartByUserid(String userId) {
        return carts.stream()
                .filter((cart -> cart.getUserId().equals(userId) && cart.isPaid()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Cart> getAllCartPaid(String userId) {
        return carts.stream()
                .filter((cart -> cart.getUserId().equals(userId)))
                .collect(Collectors.toList());
    }
}
