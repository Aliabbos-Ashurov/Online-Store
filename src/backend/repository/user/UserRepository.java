package backend.repository.user;

import backend.model.user.User;
import backend.repository.BaseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  09:47
 **/
public class UserRepository implements BaseRepository<User> {
    List<User> users;
    public UserRepository() {
        this.users = new ArrayList<>();
    }
    @Override
    public boolean add(User object) {
        users.add(object);
        return true;
    }
    @Override
    public boolean remove(String id) {
        users.removeIf((user -> user.getId().equals(id)));
        return true;
    }
    @Override
    public Optional<User> findById(String id) {
        return users.stream()
                .filter((user) -> user.getId().equals(id))
                .findFirst();
    }
    @Override
    public List<User> getAll() {
        return users;
    }
}
