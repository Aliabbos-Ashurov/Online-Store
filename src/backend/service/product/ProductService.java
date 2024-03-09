package backend.service.product;

import backend.model.product.Product;
import backend.service.BaseService;
import backend.service.support.GetAll;
import backend.service.support.Remove;
import backend.service.support.Search;
import backend.service.product.data.ProductData;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  21:49
 * @since
 **/
public interface ProductService extends BaseService<Product>, ProductData, Search<Product>, Remove, GetAll {
    String getProductId(int index);
    String getProductId(int index,List<Product> list);
    boolean putProductOnDiscount(String id);
    List<Product> showProductOnDiscount();
    boolean removeDiscountProduct(String id);
    boolean update(Product product);
    Product get(String productID);
}
