package backend.service.support;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * Date: 08/March/2024  10:56
 **/
public interface Search<E> {
    List<E> search(String query);
    E findById(String id);
}
