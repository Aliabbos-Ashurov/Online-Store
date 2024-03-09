package backend.service.support;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * Date: 08/March/2024  11:08
 **/
@FunctionalInterface
public interface GetAll<E> {
    List<E> getAll();
}
