package backend.repository.product;

import backend.db.DeafultData;
import backend.model.product.Product;
import backend.repository.BaseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  21:33
 **/
public class ProductRepository implements BaseRepository<Product> {
    List<Product> products;
    public ProductRepository() {
        this.products = new ArrayList<>();
    }
    @Override
    public boolean add(Product object) {
        products.add(object);
        return true;
    }
    @Override
    public boolean remove(String id) {
        products.removeIf(product -> product.getId().equals(id));
        return true;
    }
    @Override
    public Optional<Product> findById(String id) {
        return products.stream()
                .filter((product) -> product.getId().equals(id))
                .findFirst();
    }
    @Override
    public List<Product> getAll() {
        return products;
    }
}
