package backend.service.notificationMessage;

import backend.model.user.User;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * Date: 01/March/2024  20:20
 **/
public interface NotificationMessageService {
    boolean sendNotificationToUsers(String message);
    List<String> getUserMessages(String userId);
}
