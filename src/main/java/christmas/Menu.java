package christmas;

import java.util.ArrayList;
import java.util.List;

public enum Menu {

    APPETIZER_YANGSONGSU_SOUP("양송이수프", 6000, Category.APPETIZER),
    APPETIZER_TAPAS("타파스", 5500, Category.APPETIZER),
    APPETIZER_CAESAR_SALAD("시저샐러드", 8000, Category.APPETIZER),

    MAIN_T_BONE_STEAK("티본스테이크", 55000, Category.MAIN),
    MAIN_BBQ_RIB("바비큐립", 54000, Category.MAIN),
    MAIN_SEAFOOD_PASTA("해산물파스타", 35000, Category.MAIN),
    MAIN_CHRISTMAS_PASTA("크리스마스파스타", 25000, Category.MAIN),

    DESSERT_CHOCO_CAKE("초코케이크", 15000, Category.DESSERT),
    DESSERT_ICE_CREAM("아이스크림", 5000, Category.DESSERT),

    DRINK_ZERO_COLA("제로콜라", 3000, Category.DRINK),
    DRINK_RED_WINE("레드와인", 60000, Category.DRINK),
    DRINK_CHAMPAGNE("샴페인", 25000, Category.DRINK);

    private final String name;
    private final int price;
    private final Category category;

    Menu(String name, int price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public Category getCategory() {
        return category;
    }

    public enum Category {
        APPETIZER,
        MAIN,
        DESSERT,
        DRINK
    }

    public static List<String> getMenu() {
        List<String> originMenu = new ArrayList<>();

        for ( Menu menu : Menu.values() ) {

            originMenu.add( menu.getName() );
        }
        return originMenu;
    }
}
