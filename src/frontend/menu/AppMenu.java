package frontend.menu;

/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  22:05
 **/
public class AppMenu implements Menu{
    @Override
    public void display() {
        System.out.println("[1] - Sign in");
        System.out.println("[2] - Sign up");
        System.out.println("[0] - exit");
    }
}
