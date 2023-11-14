package christmas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Event {

    final int YEAR = 2023;
    final int MONTH = 12;
    final String FREEGIFT = "샴페인";

    void giveBadge( Customer customer, OutputView outputView, int discount ){
        if ( discount >= 20000 ) {
            customer.badge = "산타";
        } else if ( discount >= 10000 ) {
            customer.badge = "트리";
        } else if ( discount >= 5000 ) {
            customer.badge = "별";
        }
        outputView.printBadge( customer );
    }
    private boolean isWeekday( int DATE ) {
        DayOfWeek dayOfWeek = this.today( DATE );
        return (dayOfWeek != DayOfWeek.FRIDAY) && (dayOfWeek != DayOfWeek.SATURDAY);
    }
    private boolean isStar( int DATE ) {
        DayOfWeek dayOfWeek = this.today( DATE );
        return dayOfWeek == DayOfWeek.SUNDAY || DATE == 25;
    }

    private DayOfWeek today( int DATE ) {
        LocalDate today = LocalDate.of( YEAR, MONTH, DATE );
        return today.getDayOfWeek();
    }

    int getDiscount( OutputView outputView, Customer customer, int DATE ) {

        int totalDiscountPrice = 0;

        if ( DATE < 26 ) {
            int dDay = this.dDay( DATE );
            outputView.printMsg( "크리스마스 디데이 할인", dDay);
            totalDiscountPrice += dDay;
        }
        if ( this.isWeekday( DATE ) ) {
            int weekday = this.day( customer, Menu.Category.DESSERT );
            outputView.printMsg( "평일 할인", weekday);
            totalDiscountPrice += weekday;
        }
        if ( !this.isWeekday( DATE ) ) {
            int weekend = this.day( customer, Menu.Category.MAIN );
            outputView.printMsg( "주말 할인", weekend);
            totalDiscountPrice += weekend;
        }
        if ( this.isStar( DATE ) ) {
            int special = 1000;
            outputView.printMsg("특별 할인", special);
            totalDiscountPrice += special;
        }
        if ( customer.getFreeGiftNum() > 0 ) {
            int gift = this.freeGift( customer.getFreeGiftNum(), FREEGIFT );
            outputView.printMsg("증정 이벤트", gift);
            totalDiscountPrice += gift;
        }

        return totalDiscountPrice;
    }

    void isFreeGift( Customer customer, int freeGiftNum ) {
        customer.freeGiftNum = freeGiftNum;
    }
    private int freeGift( int giftNum, String menuName ) {
        for (Menu menu : Menu.values()) {
            if ( menu.getName().equalsIgnoreCase(menuName) ) {
                return menu.getPrice() * giftNum;
            }
        }
        return 0;
    }

    private int day( Customer customer, Menu.Category category ) {
        List<String> categoryName = new ArrayList<>();
        int discountPrice = 0;

        for ( Menu menu : Menu.values() ) {
            if ( menu.getCategory() == category ) {
                categoryName.add( menu.getName() );
            }
        }

        for ( String name : categoryName ){
            if ( null != customer.getOrderMenu().get( name ) ) {
                discountPrice += customer.getOrderMenu().get( name ) * YEAR;
            }
        }
        return discountPrice;
    }
    private int dDay( int DATE ){
        int discountPrice = 1000;

        for ( int i = 0; i < DATE; i++ ) {
            discountPrice += 100;
        }
        return discountPrice;
    }
}
