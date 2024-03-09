package backend.service.notificationMessage;

import backend.model.notification.NotificationMessage;
import backend.model.user.User;
import backend.service.user.UserService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author Aliabbos Ashurov
 * Date: 01/March/2024  20:21
 **/
public class NotificationMessageServiceImpl implements NotificationMessageService{
    private final ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = ThreadLocal.withInitial(()->new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss"));
    private final List<NotificationMessage> notificationMessages = new ArrayList<>();

    public boolean sendNotificationToUsers(String message) {
        List<User> users = UserService.repository.getAll();
        for (User user : users) {
            addMessageToUser(user.getId(), message);
        }
        return true;
    }
    @Override
    public List<String> getUserMessages(String userId) {
        NotificationMessage notificationMessage = findOrCreateUserMessage(userId);
        return notificationMessage.getMessages();
    }
    private void addMessageToUser(String userId, String message) {
        NotificationMessage userMessage = findOrCreateUserMessage(userId);
        String dateTime = simpleDateFormatThreadLocal.get().format(new Date());
        String alert = "Send notification to user with ID: " + userId + "\nMessage: " + message + "\nSent at: " + dateTime + "\n------------------------------";
        userMessage.add(alert);
    }
    private NotificationMessage findOrCreateUserMessage(String userId) {
        return notificationMessages.stream()
                .filter(message -> message.getId().equals(userId))
                .findFirst()
                .orElseGet(() -> {
                    NotificationMessage newUserMessage = new NotificationMessage(userId);
                    notificationMessages.add(newUserMessage);
                    return newUserMessage;
                });
    }
}