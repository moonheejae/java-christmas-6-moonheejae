package christmas;

import java.util.ArrayList;
import java.util.List;

public enum Menu {
    PIZZA("피자", 15000),
    BURGER("햄버거", 9900),
    SPAGHETTI("스파게티", 12000),
    SALAD("샐러드", 8900),
    SUSHI("스시", 20000);

    private final String name;
    private final int price;

    Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static List<String> getMenu() {
        List<String> originMenu = new ArrayList<>();

        for ( Menu menu : Menu.values() ) {

            originMenu.add( menu.getName() );
        }
        return originMenu;
    }



}
