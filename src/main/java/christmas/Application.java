package christmas;

import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {

        InputView inputView = new InputView();
        int DATE = 0;

        while(true) {
            try{
                inputView.welcomeMsg();
                DATE = inputView.readDate();
                break;

            } catch ( IllegalArgumentException e ) {
                System.out.println( e.getMessage() );
            }
        }

        Customer customer = new Customer();
        while(true) {
            try{
                inputView.orderMenu( customer );
                inputView.completeOrderMsg( DATE );
                break;

            } catch ( IllegalArgumentException | IndexOutOfBoundsException e ) {
                System.out.println( e.getMessage() );
            }
        }

        OutputView outputView = new OutputView();

        int originPrice = outputView.printMenu( customer );

        outputView.readFreeGift( customer, originPrice );

        int discountPrice = outputView.readDiscount( customer, DATE );
        outputView.readFinalPrice( originPrice, discountPrice );

        outputView.readBadge( customer, discountPrice );

    }
}
