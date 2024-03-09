package backend.controller;


import backend.model.cart.Cart;
import backend.model.order.Order;
import backend.model.product.Product;
import backend.model.user.User;
import backend.utils.Utils;
import static frontend.Web.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * @author Aliabbos Ashurov
 * Date: 27/February/2024  12:03
 **/
public class UserController {
    private static final DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
    public static void userApp() {
        while (true) {
            userMenu.display();
            int selected = Utils.inputInt();
            switch (selected) {
                case 1 -> showProducts();
                case 2 -> searchProduct();
                case 3 -> addProductToCart();
                case 4 -> userBuy();
                case 5 -> removeProductFromCart();
                case 6 -> clearCart();
                case 7 -> archiveCart();
                case 8 -> viewNotifications();
                case 9 -> {if (deleteAccount()) startApp();}
                case 0 -> startApp();
            }
        }
    }

    public static void showProducts() {
        List<Product> products = productService.getAll();
        notificationService.checkData(products);
        if (Utils.checkDataForNotNull(products)) Utils.showListClearly(products);
    }

    public static void searchProduct() {
        List<Product> products = productService.getAll();
        List<Product> foundProducts = null;
        if (Utils.checkDataForNotNull(products)) {
            String query = Utils.inputStr("Enter query");
            foundProducts = productService.search(query);
            notificationService.checkData(foundProducts);
            if (Utils.checkDataForNotNull(foundProducts)) Utils.showListClearly(foundProducts);
            return;
        }
        notificationService.checkData(foundProducts);
    }

    public static boolean deleteAccount() {
        String password1 = Utils.inputStr("Enter password");
        String password2 = Utils.inputStr("Enter one more");
        User currentUser = getCurrentUser();
        boolean isworked = false;
        if (password1.equals(password2) && currentUser.getPassword().equals(password1)) {
            isworked = userService.remove(currentUser.getId());
        }
        notificationService.notificationMessage("Account", "deleted", isworked);
        return isworked;
    }
    public static void viewNotifications() {
        List<String> userMessages = notificationMessageService.getUserMessages(currentUser.getId());
        notificationService.checkData(userMessages);
        if (Utils.checkDataForNotNull(userMessages)) {
            Utils.showListClearly(userMessages);
        }
    }
    public static void clearCart() {
        boolean isWorked = orderService.clear(cartService.findOrCreate(currentUser.getId()));
        notificationService.notificationMessage("Cart","cleaned", isWorked);
    }
    public static void removeProductFromCart() {
        List<Order> orders = orderService.getAll(cartService.findOrCreate(currentUser.getId()));
        showCartProducts();
        boolean isWorked = false;
        if (Utils.checkDataForNotNull(orders) ) {
            int index = Utils.inputInt("Enter index") -1;
            Cart cart = cartService.findOrCreate(currentUser.getId());
            Order order = orderService.getAll(cart).get(index);
            isWorked = orderService.remove(cart, order.getProductId());
        }
        notificationService.notificationMessage("Product","removed from cart",isWorked);
    }
    public static void addProductToCart() {
        List<Product> products = productService.getAll();
        notificationService.checkData(products);
        boolean isWorked = false;
        if (Utils.checkDataForNotNull(products)) {
            Utils.showListClearly(products);
            String productId = productService.getProductId(Utils.inputInt("Enter index"));
            int count = Utils.inputInt("How many products");
            Cart cart = cartService.findOrCreate(currentUser.getId());
            Order order = new Order(cart.getId(),productId,count);
            isWorked = orderService.add(order);
        }
        notificationService.notificationMessage("Product","added to cart",isWorked);
    }
    private static void showCart(List<Order> orders) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            Product product = productService.get(order.getProductId());
            System.out.println((i + 1) + " " + product +" [Amount %d]".formatted(order.getCount()));
        }
    }
    private static void showCartProducts() {
        List<Order> orders = orderService.getAll(cartService.findOrCreate(currentUser.getId()));
        notificationService.checkData(orders);
        if (Utils.checkDataForNotNull(orders)) {
            showCart(orders);
        }
    }
    public static void archiveCart() {
        List<Cart> paidCarts = cartService.getAllCartPaid(currentUser.getId());
        List<Order> orders = orderService.getArchive(paidCarts);
        notificationService.checkData(paidCarts);
        if (Utils.checkDataForNotNull(orders)) {
            showCart(orders);
        }
    }
    public static void userBuy() {
        showCartProducts();
        Cart currentUserCart = cartService.findOrCreate(currentUser.getId());
        List<Order> orders = orderService.getAll(currentUserCart);
        if (Utils.checkDataForNotNull(orders)) {
            System.out.println("Do you want to buy?   [1] - YES,   [2] - NO");
            int choose = Utils.inputInt("Enter");
            if (choose == 1) {
                System.out.println(bill(orders));
                notificationService.notificationMessage("Products","bought",true);
                currentUserCart.setPaid(true);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }
    private static String bill(List<Order> list) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        StringJoiner stringJoiner = new StringJoiner("\n","----------------BILL----------------\n","------------------------------------");
        double total = 0;
        for (Order order : list) {
            Product product = productService.get(order.getProductId());
            total += product.getPrice() * order.getCount();
            stringJoiner.add(product.getProductName() + "  "+ order.getCount() + ":  " + decimalFormat.format(total));
        }
        stringJoiner.add("Total: " + decimalFormat.format(total) + "\n");
        return stringJoiner.toString();
    }
}
