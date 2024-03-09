package backend.model.notification;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Aliabbos Ashurov
 * Date: 01/March/2024  19:58
 **/
public class NotificationMessage {
    private String id;
    private List<String> messages;

    public NotificationMessage(String id) {
        this.id = id;
        this.messages = new ArrayList<>();
    }
    public boolean add(String message){
        messages.add(message);
        return true;
    }
    public boolean clearMessage() {
        messages.clear();
        return true;
    }

    public String getId() {
        return id;
    }
    public List<String> getMessages() {
        return messages;
    }
}
