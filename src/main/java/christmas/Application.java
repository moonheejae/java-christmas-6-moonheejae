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

        Customer menu = new Customer();
        while(true) {
            try{
                inputView.orderMenu( menu );
                inputView.completeOrderMsg( DATE );
                break;

            } catch ( IllegalArgumentException | IndexOutOfBoundsException e ) {
                System.out.println( e.getMessage() );
            }
        }

        OutputView outputView = new OutputView();
        outputView.printMenu( menu );
    }
}
