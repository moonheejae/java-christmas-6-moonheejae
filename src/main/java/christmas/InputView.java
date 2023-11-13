package christmas;

import camp.nextstep.edu.missionutils.Console;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.core.AbstractMasterDetailListProcessor;

import java.util.*;

public class InputView {

    final private int MONTH = 12;
    public void welcomeMsg() {
        System.out.println("안녕하세요! 우테코 식당 " + MONTH + "월 이벤트 플래너입니다.");
    }
    public int readDate() {
        System.out.println(MONTH + "월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        return this.validDate( input );
    }

    public void readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();

        Set<String> duplicateTest = new HashSet<>();
        List<String> inputMenu = List.of( input.trim().split(",") );
        for ( String in : inputMenu ) {

            this.validMenu( duplicateTest, in );
        }
    }

    public void inputCompleteMsg( int date ) {
        System.out.println(MONTH + "월 "+ date +"일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    private void validMenu( Set<String> duplicateTest, String input ) {
        try {
            List<String> menu = List.of( input.trim().split("-") );

            String menuName = menu.get(0).trim();
            int menuNum = Integer.parseInt( menu.get(1) );

            this.checkForm( menuName, menuNum );
            this.checkAvailableMenu( menuName );
            this.checkDuplicateMenu( duplicateTest, menuName );

        } catch ( NumberFormatException e ) {
            throw new NumberFormatException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."); // 메뉴 갯수의 숫자 형식 아닐 경우
        } catch ( IndexOutOfBoundsException e ) {
            throw new IndexOutOfBoundsException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."); // 메뉴 입력 형식이 다른 경우
        } catch ( IllegalArgumentException e ) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."); // 메뉴가 빈 값 이거나 갯수가 1개 이상이 아닌 경우
        }
    }

    private void checkDuplicateMenu ( Set<String> testSet, String name ) {
        if ( !testSet.add( name ) ) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."); // 주문한 메뉴에 중복 메뉴가 존재하는 경우
        }
    }
    private void checkAvailableMenu( String name ) {
        if ( !Menu.getMenu().contains( name ) ) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
    private void checkForm( String name, int number ) {
        if ( name.isEmpty() ) {
            throw new IllegalArgumentException();
        }

        if ( number < 1 ) {
            throw new IllegalArgumentException();
        }
    }
    private int validDate( String input ) {
        int date = 0;

        try {
            date = Integer.parseInt( input.trim() );

        } catch ( NumberFormatException e ) {
            throw new NumberFormatException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."); // 날짜가 숫자 이외의 입력 값인 경우
        }

        if ( 1 > date || date > 31 ) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."); // 1~31 이외의 입력 값인 경우
        }

        return date;
    }

}
