package christmas;

import java.util.HashMap;
import java.util.Map;

public class Customer {

    Map<String, Integer> orderMenu;

    public Customer() {
        this.orderMenu = new HashMap<>();
    }

    public void putOrderList(String menuName, int menuNum ) {

        orderMenu.put( menuName, menuNum );
    }

    public Map<String, Integer> getOrderMenu() {
        return orderMenu;
    }
}
