package backend.model.cart;
import backend.model.BaseModel;
/**
 * @author Aliabbos Ashurov
 * Date: 01/March/2024  12:21
 **/
public class Cart extends BaseModel {
    private String userId;
    boolean isPaid;
    public Cart(String userId) {
        this.userId = userId;
        this.isPaid = false;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
