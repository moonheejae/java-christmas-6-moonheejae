package christmas;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    Map<String, Integer> orderMenu;
    int freeGiftNum;
    String badge;

    public Customer() {
        this.orderMenu = new HashMap<>();
        freeGiftNum = 0;
        badge = null;
    }

    public void putOrderList(String menuName, int menuNum ) {

        orderMenu.put( menuName, menuNum );
    }

    public int getFreeGiftNum() {
        return freeGiftNum;
    }

    public Map<String, Integer> getOrderMenu() {
        return orderMenu;
    }
}
