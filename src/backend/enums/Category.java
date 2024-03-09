package backend.enums;

/**
 * @author Aliabbos Ashurov
 * Date: 25/February/2024  21:12
 **/
public enum Category {
    ELECTRONICS,
    CLOTHING,
    FURNITURE,
    HOME,
    HEALTH,
    BEAUTY,
    TOYS,
    GAMES,
    OUTDOORS,
    AUTOMOTIVE,
    FOOD;
    public static Category getCategoryByIndex(int index) {
        Category[] categories = Category.values();
        if (index >= 1 && index <= categories.length) {
            return categories[index - 1];
        }
        return null;
    }
    public static void showCategories() {
        Category[] categories = Category.values();
        for (int i = 0; i < categories.length; i++) {
            System.out.println((i + 1) + " " + categories[i]);
        }
    }
}
