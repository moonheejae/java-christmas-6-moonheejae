package christmas;

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

        while(true) {
            try{
                inputView.readMenu();
                inputView.inputCompleteMsg( DATE );
                break;

            } catch ( IllegalArgumentException | IndexOutOfBoundsException e ) {
                System.out.println( e.getMessage() );
            }
        }
    }
}
