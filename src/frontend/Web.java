package frontend;

import backend.controller.AdminController;
import backend.controller.AppController;
import backend.controller.UserController;
import backend.enums.Role;
import backend.model.user.User;
import backend.service.cart.CartService;
import backend.service.cart.CartServiceImpl;
import frontend.menu.AdminMenu;
import frontend.menu.AppMenu;
import frontend.menu.UserMenu;
import backend.service.notification.*;
import backend.service.notificationMessage.*;
import backend.service.order.OrderService;
import backend.service.order.OrderServiceImpl;
import backend.service.product.ProductService;
import backend.service.product.ProductServiceImpl;
import backend.service.user.UserService;
import backend.service.user.UserServiceImpl;
import backend.utils.Utils;
import java.util.*;
/**
 * @author Aliabbos Ashurov
 * Date: 23/February/2024  18:10
 **/
public class Web {
    public static final UserService userService = new UserServiceImpl();
    public static final ProductService productService = new ProductServiceImpl();
    public static final NotificationService notificationService = new NotificationServiceImpl();
    public static final Scanner scanner = new Scanner(System.in);
    public static final AdminMenu adminMenu = new AdminMenu();
    public static final UserMenu userMenu = new UserMenu();
    public static final AppMenu appMenu = new AppMenu();
    public static final CartService cartService = new CartServiceImpl();
    public static final NotificationMessageService notificationMessageService = new NotificationMessageServiceImpl();
    public static final OrderService orderService = new OrderServiceImpl();
    public static User currentUser = null;
    public static void main(String[] args) {
        startApp();
    }
    public static void startApp() {
        appMenu.display();
        int choose = Utils.inputInt();
        while (true) {
            switch (choose) {
                case 1 -> signIn();
                case 2 -> signUp();
                case 0 -> exit();
            }
        }
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static User setCurrentUser(User user) {
        currentUser = user;
        return user;
    }
    public static void exit() {
        scanner.close();
        System.exit(0);
    }
    public static void signIn() {
        if (AppController.singIn()) {
            if (currentUser.getRole().equals(Role.ADMIN)) {
                AdminController.adminApp();
            } else if (currentUser.getRole().equals(Role.USER)) {
                UserController.userApp();
            }
        } else {
            startApp();
        }
    }
    public static void signUp() {
        if (AppController.singUp()) {
            if (currentUser.getRole().equals(Role.ADMIN)) {
                AdminController.adminApp();
            } else if (currentUser.getRole().equals(Role.USER)) {
                UserController.userApp();
            }
        } else {
            startApp();
        }
    }
}
