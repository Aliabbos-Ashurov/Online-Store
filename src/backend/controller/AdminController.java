package backend.controller;

import backend.enums.Category;
import backend.model.product.Product;
import backend.model.user.User;
import backend.utils.Utils;
import static frontend.Web.*;
import java.util.List;
/**
 * @author Aliabbos Ashurov
 * Date: 26/February/2024  10:18
 **/
public class AdminController {
    public static void adminApp() {
        while (true) {
            adminMenu.display();
            int selected = Utils.inputInt();
            switch (selected) {
                case 1 -> showProducts();
                case 2 -> createProduct();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> putProductOnDiscount();
                case 6 -> showProductsOnDiscount();
                case 7 -> removeFromDiscount();
                case 8 -> showUsers();
                case 9 -> addNewAdmin();
                case 10 -> sendNotificationToUsers();
                case 11 -> userBlock();
                case 12 -> userUnBlock();
                case 13 -> searchUser();
                case 0 -> startApp();
            }
        }
    }
    public static void showProducts() {
        List<Product> products = productService.getAll();
        notificationService.checkData(products);
        if (Utils.checkDataForNotNull(products)) Utils.showListClearly(products);
    }
    public static void createProduct() {
        String productName = Utils.inputStr("Enter new product name");
        Category.showCategories();
        int categoryProduct = Utils.inputInt("Enter product category");
        Category category = Category.getCategoryByIndex(categoryProduct);
        Double price = Utils.inputDouble("Enter product price");
        System.out.println("ON DISCOUNT: [1] - YES, [2] - NO");
        int isOnDiscount = Utils.inputInt();
        boolean onDiscount = isOnDiscount == 1;
        boolean isProductAdded = productService.add(new Product(productName, category, price,onDiscount));
        notificationService.notificationMessage("Product", "added", isProductAdded);
    }
    public static void deleteProduct() {
        List<Product> products = productService.getAll();
        notificationService.checkData(products);
        boolean isWorked = false;
        if (Utils.checkDataForNotNull(products)) {
            Utils.showListClearly(products);
            int chooseProduct = Utils.inputInt("Enter index");
            isWorked = productService.remove(productService.getProductId(chooseProduct));
        }
        notificationService.notificationMessage("Product", "deleted", isWorked);
    }

    public static void removeFromDiscount() {
        List<Product> products = productService.getAll();
        notificationService.checkData(products);
        boolean isWorked = false;
        if (Utils.checkDataForNotNull(products)) {
            Utils.showListClearly(products);
            int chooseProduct = Utils.inputInt("Enter index");
            isWorked = productService.removeDiscountProduct(productService.getProductId(chooseProduct));
        }
        notificationService.notificationMessage("Product", "removed from Discount", isWorked);
    }

    public static void putProductOnDiscount() {
        List<Product> products = productService.getAll();
        notificationService.checkData(products);
        boolean isWorked = false;
        if (Utils.checkDataForNotNull(products)) {
            Utils.showListClearly(products);
            int chooseProduct = Utils.inputInt("Enter index");
            isWorked = productService.putProductOnDiscount(productService.getProductId(chooseProduct));
        }
        notificationService.notificationMessage("Product", "on discount", isWorked);
    }

    public static void showProductsOnDiscount() {
        List<Product> products = productService.showProductOnDiscount();
        notificationService.checkData(products);
        if (Utils.checkDataForNotNull(products)) Utils.showListClearly(products);
    }

    public static void showUsers() {
        List<User> products = userService.getAll();
        notificationService.checkData(products);
        if (Utils.checkDataForNotNull(products)) Utils.showListClearly(userService.getAll());
    }

    public static void addNewAdmin() {
        List<User> users = userService.getAll();
        notificationService.checkData(users);
        boolean isWorked = false;
        if (Utils.checkDataForNotNull(users)) {
            Utils.showListClearly(users);
            int choose = Utils.inputInt("Ender index");
            isWorked = userService.addNewAdmin(userService.getUserId(choose));
        }
        notificationService.notificationMessage("New Admin", "added", isWorked);
    }
    public static void sendNotificationToUsers() {
        String message = Utils.inputStr("Enter message");
        boolean isWorked = notificationMessageService.sendNotificationToUsers(message);
        notificationService.notificationMessage("Notification","send to users",isWorked);
    }

    public static void searchUser() {
        String query = Utils.inputStr("Enter fullname");
        List<User> users = userService.search(query);
        boolean isWorked = false;
        if (Utils.checkDataForNotNull(users)) {
            Utils.showListClearly(users);
            isWorked = true;
        }
        notificationService.notificationMessage("User", "found", isWorked);
    }

    public static void userBlock() {
        List<User> users = userService.getAll();
        notificationService.checkData(users);
        boolean isWorked = false;
        if (Utils.checkDataForNotNull(users)) {
            Utils.showListClearly(users);
            int choose = Utils.inputInt("Enter index");
            isWorked = userService.block(userService.getUserId(choose));
        }
        notificationService.notificationMessage("User", "blocked", isWorked);
    }

    public static void userUnBlock() {
        List<User> users = userService.getAll();
        notificationService.checkData(users);
        boolean isWorked = false;
        if (Utils.checkDataForNotNull(users)) {
            Utils.showListClearly(users);
            int choose = Utils.inputInt("Enter index");
            isWorked = userService.unBlock(userService.getUserId(choose));
        }
        notificationService.notificationMessage("User", "unblocked", isWorked);
    }

    public static void updateProduct() {
        showProducts();
        List<Product> products = productService.getAll();
        if (Utils.checkDataForNotNull(products)) {
            int productIndex = Utils.inputInt("Enter index");

            if (productIndex < 1 || productIndex > products.size()) {
                System.out.println("Invalid product index.");
                return;
            }
            Product productToUpdate = products.get(productIndex - 1);
            while (true) {
                System.out.println("Choose what to update:");
                System.out.println("[1] - Product name");
                System.out.println("[2] - Category");
                System.out.println("[3] - Price");
                System.out.println("[4] - On discount");
                System.out.println("[0] - Finish updating");
                int choice = Utils.inputInt("Enter");
                switch (choice) {
                    case 0 -> notificationService.notificationMessage("Product", "updated", true);
                    case 1 -> updateProductName(productToUpdate);
                    case 2 -> updateCategory(productToUpdate);
                    case 3 -> updatePrice(productToUpdate);
                    case 4 -> updateOnDiscount(productToUpdate);
                    default -> System.out.println("Invalid choice.");
                }
            }
        }
    }

    private static void updateProductName(Product product) {
        String newName = Utils.inputStr("Enter new product name");
        product.setProductName(newName);
        notificationService.notificationMessage("Product", "updated", productService.update(product));
    }

    private static void updateCategory(Product product) {
        Category.showCategories();
        int categoryIndex = Utils.inputInt("Enter new category index");
        Category newCategory = Category.getCategoryByIndex(categoryIndex);
        product.setCategory(newCategory);
        notificationService.notificationMessage("Product", "updated", productService.update(product));
    }

    private static void updatePrice(Product product) {
        double newPrice = Utils.inputDouble("Enter new price");
        product.setPrice(newPrice);
        notificationService.notificationMessage("Product", "updated", productService.update(product));
    }
    private static void updateOnDiscount(Product product) {
        boolean newOnDiscount = Utils.inputInt("On Discount: [1] - YES, [2] - NO") == 1;
        product.setOnDiscount(newOnDiscount);
        notificationService.notificationMessage("Product", "updated", productService.update(product));
    }
}
