package backend.repository;

import java.util.List;
import java.util.Optional;
/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  09:42
 **/
public interface BaseRepository<E> {
    boolean add(E object);
    boolean remove(String id);
    Optional<E> findById(String id);
    List<E> getAll();
}