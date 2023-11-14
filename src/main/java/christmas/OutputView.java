package christmas;

import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {
    Event event = new Event();
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
        String formattedPrice = String.format("%,d", totalPrice);
        System.out.println( formattedPrice + "원");

        return totalPrice;
    }
    void readFreeGift( Customer customer, int price ){
        System.out.println("\n<증정 메뉴>");
        if ( price >= 120000 ) {
            int number = price / 120000;
            System.out.println(event.FREEGIFT + " " + number + "개");
            customer.freeGiftNum = number;
        }
        System.out.println("없음");
    }

    int readDiscount( Customer customer, int DATE ) {
        System.out.println("\n<혜택 내역>");
        int totalDiscount = event.getDiscount(this, customer, DATE);
        if ( totalDiscount == 0){
            System.out.println("없음");
        }
        System.out.println("\n<총혜택 금액>");
        String formattedTotalPrice = formattedPrice(totalDiscount);
        System.out.println( formattedTotalPrice + "원");

        return totalDiscount;
    }
    void readBadge( Customer customer, int discountPrice ){
        System.out.println("\n<12월 이벤트 배지>");
        event.giveBadge( customer, discountPrice );
        if( customer.badge == null ) {
            System.out.println("없음");
            return;
        }
        System.out.println(customer.badge);
    }

    void readFinalPrice( int origin, int discount ){
        System.out.println("\n<할인 후 예상 결제 금액>");
        int finalPrice = origin - discount;
        String formattedPrice = String.format("%,d", finalPrice);
        System.out.println( formattedPrice + "원");
    }
    void printMsg( String type, int price ) {
        String formattedPrice = this.formattedPrice( price );
        if ( price > 0 ) {
            System.out.println(type + ": " + formattedPrice + "원");
        }
    }
    String formattedPrice( int price ) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format( price * -1 );
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
