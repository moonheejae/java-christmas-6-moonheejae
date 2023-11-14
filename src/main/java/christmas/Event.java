package christmas;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Event {

    final int YEAR = 2023;
    final int MONTH = 12;


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

    int getDiscount( int originPrice, Customer customer, int DATE ) {

        int totalDiscountPrice = 0;

        // 디데이 할인
        if ( DATE < 26 ) {
            totalDiscountPrice += this.dDay( DATE );
        }

        // 평일 할인
        if ( this.isWeekday( DATE ) ) {

            totalDiscountPrice += this.day( customer, Menu.Category.DESSERT );
        }
        // 주말 할인
        if ( !this.isWeekday( DATE ) ) {

            totalDiscountPrice += this.day( customer, Menu.Category.MAIN );
        }

        // 특별 할인
        if ( this.isStar( DATE ) ) {
            totalDiscountPrice += 1000;
        }
        // 증정 이벤트
        int freeGiftNum = this.isFreeGift( originPrice );
        if ( freeGiftNum > 0 ) {
            String giftMenu = "샴페인";
            totalDiscountPrice += this.freeGift( giftMenu ) * freeGiftNum;
        }

        return totalDiscountPrice;
    }

    private int isFreeGift( int originPrice ) {
        return originPrice / 120000;
    }
    private int freeGift( String menuName ) {
        for (Menu menu : Menu.values()) {
            if ( menu.getName().equalsIgnoreCase(menuName) ) {
                return menu.getPrice();
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
