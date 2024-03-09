package frontend.menu;

/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  22:05
 **/
public class UserMenu implements Menu{
    @Override
    public void display() {
        System.out.println("[1] - Show products");
        System.out.println("[2] - Search product");
        System.out.println("[3] - Add product to cart");
        System.out.println("[4] - Show cart");
        System.out.println("[5] - Remove product from cart");
        System.out.println("[6] - Clear Cart");
        System.out.println("[7] - Archive");
        System.out.println("[8] - View notifications");
        System.out.println("[9] - Delete account");
        System.out.println("[0] - back");
    }
}
