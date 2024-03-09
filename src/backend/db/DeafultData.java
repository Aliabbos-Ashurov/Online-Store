package backend.db;

import backend.enums.Category;
import backend.enums.Role;
import backend.model.product.Product;
import backend.model.user.User;
import backend.repository.product.ProductRepository;
import backend.repository.user.UserRepository;

/**
 * @author Aliabbos Ashurov
 * Date: 26/February/2024  11:22
 **/
public interface DeafultData{
    static void addDefaultUser(UserRepository userRepository) {
        userRepository.add(new User("Aliabbos Ashurov","a","a", Role.ADMIN,true));
        userRepository.add(new User("Akbarov Alisher","u","u", Role.USER,true));
        userRepository.add(new User("Rustamov Otabek","c","c", Role.USER,true));
        userRepository.add(new User("Baxtiyorov Ali","baxtiyorovA","mypass", Role.USER,true));
        userRepository.add(new User("Timur Abdullaev","timurAdmin","timur999", Role.ADMIN,true));
        userRepository.add(new User("Jasurbekov Sanjar","jSanjar","sanjar2000", Role.USER,true));
        userRepository.add(new User("Dilshodov Feruz","feruzUzb","ff0000", Role.USER,true));
    }
    static void addDefaultProduct(ProductRepository productRepository) {
        productRepository.add(new Product("iPhone 15 Pro Max", Category.ELECTRONICS,14000000.00,false));
        productRepository.add(new Product("iPhone 14 pro", Category.ELECTRONICS,11000000.00,false));
        productRepository.add(new Product("Samsung SmartTV 54QEO99", Category.ELECTRONICS,9880000.00,false));
        productRepository.add(new Product("Samsung S24 ultra ", Category.ELECTRONICS,13550000.00,true));
        productRepository.add(new Product("Nike Air Jordan",Category.CLOTHING,546000,true));
        productRepository.add(new Product("Nike Air Max",Category.CLOTHING,772000,true));
    }
}
