package backend.service.order;

import backend.model.cart.Cart;
import backend.model.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Aliabbos Ashurov
 * Date: 06/March/2024  12:23
 **/
public class OrderServiceImpl implements OrderService {
    List<Order> orders = new ArrayList<>();

    @Override
    public boolean add(Order order) {
        Optional<Order> orderOptional = orders.stream()
                .filter((object) -> object.getProductId().equals(order.getProductId()) && object.getCartId().equals(order.getCartId()))
                .findFirst();
        orderOptional.ifPresent((objectOrder) -> objectOrder.setCount(objectOrder.getCount() + order.getCount()));
        if (!orderOptional.isPresent()) {
            orders.add(order);
        }
        return true;
    }
    @Override
    public boolean clear(Cart cart) {
        orders.removeIf((order -> order.getCartId().equals(cart.getId())));
        return true;
    }

    @Override
    public List<Order> getAll(Cart cart) {
        return orders.stream()
                .filter((order -> order.getCartId().equals(cart.getId()) && !cart.isPaid()))
                .collect(Collectors.toList());
    }
    @Override
    public boolean remove(Cart cart, String productId) {
        return orders.removeIf(order -> Objects.equals(order.getCartId(),cart.getId()) && Objects.equals(order.getProductId(),productId));
    }

    @Override
    public List<Order> getArchive(List<Cart> list) {
        List<Order> archive = new ArrayList<>();
        for (Cart cart : list) {
            for (Order order : orders) {
                if (order.getCartId().equals(cart.getId()) && cart.isPaid()) {
                    updateOrAddToArchive(order, archive);
                }
            }
        }
        return archive;
    }

    private static void updateOrAddToArchive(Order order, List<Order> archive) {
        boolean isNotWorked = true;
        for (Order obj : archive) {
            if (obj.getProductId().equals(order.getProductId())) {
                obj.setCount(obj.getCount() + order.getCount());
                isNotWorked = false;
                break;
            }
        }
        if (isNotWorked) {
            archive.add(order);
        }
    }
}
