package backend.service.user;

import backend.db.DeafultData;
import backend.enums.Role;
import backend.model.user.User;
import java.util.*;
import java.util.stream.Collectors;
/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  09:53
 **/
public class UserServiceImpl implements UserService{
    public UserServiceImpl() {
        DeafultData.addDefaultUser(repository);
    }
    @Override
    public boolean add(User object) {
        List<User> usersExists = repository.getAll();
        boolean checkUser = usersExists.stream()
                .anyMatch(user -> user.getPassword().equals(object.getPassword()) && user.getUsername().equals(object.getUsername()) && user.isActive());
        if (!checkUser) {
            repository.add(object);
        }
        return checkUser;
    }
    @Override
    public boolean checkUserFromList(String username, String password) {
        List<User> users = repository.getAll();
        boolean userFound = users.stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password) && user.isActive());
        return userFound;
    }

    @Override
    public User getUserByUsername(String username) {
        List<User> users = repository.getAll();
        Optional<User> userOptional = users.stream()
                .filter((user -> user.getUsername().equals(username)))
                .findFirst();
        return userOptional.orElse(null);
    }

    @Override
    public boolean remove(String id) {
        Optional<User> userOptional = repository.getAll().stream()
                .filter((user) -> user.getId().equals(id))
                .findFirst();
        userOptional.ifPresent(user -> repository.remove(user.getId()));
        return userOptional.isPresent();
    }

    @Override
    public List<User> search(String query) {
        return repository.getAll().stream()
                .filter((user -> userMatchesQuery(user,query)))
                .collect(Collectors.toList());
    }
    private boolean userMatchesQuery(User user, String query) {
        String fullname = user.getFullname();
        String queryUpperCase = query.toUpperCase();
        return fullname.equalsIgnoreCase(query) || fullname.toUpperCase().startsWith(queryUpperCase);
    }

    @Override
    public boolean addNewAdmin(String id) {
        Optional<User> userOptional = repository.getAll().stream()
                .filter(user -> user.getId().equals(id) && !user.getRole().equals(Role.ADMIN) && user.isActive())
                .findFirst();
        userOptional.ifPresent(user -> user.setRole(Role.ADMIN));
        return userOptional.isPresent();
    }
    @Override
    public boolean block(String id) {
        List<User> users = repository.getAll();
        Optional<User> userOptional = users.stream()
                .filter((user -> user.getId().equals(id) && !user.getRole().equals(Role.ADMIN) && user.isActive()))
                .findFirst();
        userOptional.ifPresent((user -> user.setActive(false)));
        return userOptional.isPresent();
    }
    @Override
    public boolean unBlock(String id) {
        List<User> users = repository.getAll();
        Optional<User> userOptional = users.stream()
                .filter((user -> user.getId().equals(id) && !user.getRole().equals(Role.ADMIN) && !user.isActive()))
                .findFirst();
        userOptional.ifPresent(user -> user.setActive(true));
        return userOptional.isPresent();
    }
    @Override
    public User findById(String id) {
        Optional<User> userOptional = repository.getAll().stream()
                .filter((user -> user.getId().equals(id)))
                .findFirst();
        return userOptional.orElse(null);
    }
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }
    @Override
    public String getUserId(int index) {
        List<User> allUsers = repository.getAll();
        if (index >= 1 && index <= allUsers.size()) {
            return allUsers.get(index - 1).getId();
        }
        return null;
    }
}
