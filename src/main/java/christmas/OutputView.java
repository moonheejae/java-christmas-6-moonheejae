package christmas;

import java.util.Map;

public class OutputView {
    public int printMenu( Customer menu ) {
        int totalPrice = 0;
        System.out.println("\n<주문 메뉴>");

        for ( Map.Entry<String, Integer> entry : menu.getOrderMenu().entrySet() ) {

            String name = entry.getKey();
            Integer number = entry.getValue();

            System.out.println( name + " " + number + "개");
            totalPrice = this.sumOriginPrice( totalPrice, name, number );
        }

        System.out.println("\n<할인 전 총주문 금액>");
        String formattedNumber = String.format("%,d", totalPrice);
        System.out.println( formattedNumber + "원");

        return totalPrice;
    }

    private int sumOriginPrice ( int totalPrice, String menuName, int number ) {
        for ( Menu menu : Menu.values() ) {
            if ( menu.getName().equalsIgnoreCase( menuName ) ) {

                totalPrice += menu.getPrice() * number;
                return totalPrice;
            }
        }
        return totalPrice;
    }
}
