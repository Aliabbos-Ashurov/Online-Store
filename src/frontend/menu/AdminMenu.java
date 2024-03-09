package frontend.menu;

/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  21:57
 **/
public class AdminMenu implements Menu{
    @Override
    public void display() {
        System.out.println("[1]  - Show products");
        System.out.println("[2]  - Create product");
        System.out.println("[3]  - Update product");
        System.out.println("[4]  - Delete product");
        System.out.println("[5]  - Put product on discount");
        System.out.println("[6]  - Show products on discount");
        System.out.println("[7]  - Remove product from discount");
        System.out.println("[8]  - Show users");
        System.out.println("[9]  - Add new admin");
        System.out.println("[10] - Send notification to users");
        System.out.println("[11] - User block");
        System.out.println("[12] - User unblock");
        System.out.println("[13] - Search user");
        System.out.println("[0]  - back");
    }
}
