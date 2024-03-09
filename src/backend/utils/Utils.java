package backend.utils;

import backend.model.order.Order;
import backend.model.product.Product;

import java.util.*;

/**
 * @author Aliabbos Ashurov
 * Date: 23/February/2024  19:08
 **/
public interface Utils {
    Scanner scanner = new Scanner(System.in);
    static String generateUUID() {
        return UUID.randomUUID().toString();
    }
    static <E> void showListClearly(List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(((i + 1) +" "+list.get(i)));
        }
    }
    static boolean checkDataForNotNull(List<?> list) {
        return list != null && !list.isEmpty();
    }
    static int inputInt(){
        System.out.print("-> ");
        while (!scanner.hasNextInt()) {
            System.out.print("-> ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
    static double inputDouble(String message){
        System.out.print(message + ": ");
        double num = scanner.nextDouble();
        scanner.nextLine();
        return num;
    }
    static int inputInt(String message){
        System.out.print(message + ": ");
        while (!scanner.hasNextInt()) {
            System.out.print(message + ": ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
    static String inputStr(String message) {
        System.out.print(message + ": ");
        return scanner.nextLine();
    }
}
