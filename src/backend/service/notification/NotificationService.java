package backend.service.notification;

import backend.model.user.User;

import java.util.List;

/**
 * @author Aliabbos Ashurov
 * Date: 23/February/2024  18:18
 **/
public interface NotificationService {
    void checkData(List<?> list);
    void notificationMessage(String objName,String action,boolean isWorked);
}