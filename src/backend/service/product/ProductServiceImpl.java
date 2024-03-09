package backend.service.product;

import backend.db.DeafultData;
import backend.model.product.Product;
import backend.model.user.User;
import backend.service.user.UserService;
import backend.utils.Utils;
import jdk.jfr.DataAmount;


import java.io.*;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  21:49
 **/
public class ProductServiceImpl implements ProductService {
    public ProductServiceImpl() {
        DeafultData.addDefaultProduct(repository);
    }

    @Override
    public String getProductId(int index) {
        List<Product> products = repository.getAll();
        if (index >= 1 && index <= products.size()) {
            return products.get(index - 1).getId();
        }
        return null;
    }
    public String getProductId(int index, List<Product> list) {
        if (index > 0 && index <= list.size()) {
            Product product = list.get(index - 1);
            return product.getId();
        } else {
            return null;
        }
    }
    @Override
    public boolean putProductOnDiscount(String id) {
        return updateDiscountStatus(id, true);
    }

    @Override
    public boolean removeDiscountProduct(String id) {
        return updateDiscountStatus(id, false);
    }

    private boolean updateDiscountStatus(String id, boolean newStatus) {
        List<Product> products = repository.getAll();
        Optional<Product> optionalProduct = products.stream()
                .filter(product -> product.getId().equals(id) && newStatus == !product.isOnDiscount())
                .findFirst();
        optionalProduct.ifPresent(product -> product.setOnDiscount(newStatus));
        return optionalProduct.isPresent();
    }

    @Override
    public List<Product> showProductOnDiscount() {
        List<Product> products = repository.getAll();
        return products.stream()
                .filter((Product::isOnDiscount))
                .collect(Collectors.toList());
    }
    @Override
    public boolean add(Product object) {
        List<Product> products = repository.getAll();
        Optional<Product> existingProduct = products.stream()
                .filter(product -> product.equals(object))
                .findFirst();
        repository.add(object);
        return true;
    }
    @Override
    public boolean update(Product product) {
        List<Product> products = repository.getAll();
        return products.stream()
                .filter((object) -> object.equals(product))
                .findFirst()
                .map((object) ->{
                    object.setProductName(product.getProductName());
                    object.setCategory(product.getCategory());
                    object.setPrice(product.getPrice());
                    object.setOnDiscount(product.isOnDiscount());
                    return true;
                }).orElse(false);
    }
    @Override
    public boolean remove(String id) {
        Optional<Product> productExists = repository.getAll().stream()
                .filter((product) -> product.getId().equals(id))
                .findFirst();
        productExists.ifPresent(product -> repository.remove(product.getId()));
        return productExists.isPresent();
    }
    @Override
    public Product findById(String id) {
        List<Product> products = repository.getAll();
        Optional<Product> productOptional = products.stream()
                .filter((product -> product.getId().equals(id)))
                .findFirst();
        return productOptional.orElse(null);
    }
    @Override
    public List<Product> search(String query) {
        List<Product> products = repository.getAll();
        return products.stream()
                .filter((product -> productMatchesQuery(product, query)))
                .collect(Collectors.toList());
    }
    private boolean productMatchesQuery(Product product, String query) {
        String productName = product.getProductName();
        return productName.equalsIgnoreCase(query) || productName.toUpperCase().startsWith(query.toUpperCase());
    }
    @Override
    public List<Product> getAll() {
        return repository.getAll();
    }

    @Override
    public Product get(String productID) {
        List<Product> products = repository.getAll();
        Optional<Product> optionalProduct = products.stream()
                .filter((product -> product.getId().equals(productID)))
                .findFirst();
        return optionalProduct.orElseGet(null);
    }
}
