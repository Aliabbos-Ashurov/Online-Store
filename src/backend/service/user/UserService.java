package backend.service.user;

import backend.model.user.User;
import backend.repository.user.UserRepository;
import backend.service.BaseService;
import backend.service.support.GetAll;
import backend.service.support.Remove;
import backend.service.support.Search;

/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  09:51
 **/
public interface UserService extends BaseService<User>, Search<User>, Remove, GetAll {
    UserRepository repository = new UserRepository();
    String getUserId(int index);
    boolean block(String id);
    boolean unBlock(String id);
    boolean addNewAdmin(String id);
    boolean checkUserFromList(String username, String password);
    User getUserByUsername(String username);
}
