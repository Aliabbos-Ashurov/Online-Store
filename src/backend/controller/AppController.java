package backend.controller;

import backend.enums.Role;
import backend.model.user.User;
import backend.utils.Utils;
import static frontend.Web.*;
/**
 * @author Aliabbos Ashurov
 * Date: 26/February/2024  09:32
 **/
public class AppController {
    public static final boolean singIn() {
        String username = Utils.inputStr("Enter username");
        String password = Utils.inputStr("Enter password");
        boolean isUserFound = userService.checkUserFromList(username, password);
        notificationService.notificationMessage("User", "find", isUserFound);
        if (isUserFound)
            setCurrentUser(userService.getUserByUsername(username));
        return isUserFound;
    }
    public static final boolean singUp() {
        String fullname = Utils.inputStr("Enter fullname");
        String username = Utils.inputStr("Enter username");
        String password = Utils.inputStr("Enter password");
        User newUser = new User(fullname, username, password, Role.USER, true);
        boolean isUserAdded = userService.add(newUser);
        notificationService.notificationMessage("User", "sing up", isUserAdded);
        if (isUserAdded) setCurrentUser(newUser);
        return isUserAdded ? true : false;
    }
}
