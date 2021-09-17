import java.util.*;

import static auxiliary.Handlers.handleInput;
import static auxiliary.Init.*;
import static common.Constants.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        init(args[0], args[1]);

        System.out.println(WELCOME);
        printOptions();

        String input = scanner.nextLine();

        //read and handle user requests until exit(=option5)
        while (!input.equals(OPTION5)) {
            handleInput(input);
            input = scanner.nextLine();
        }

        System.out.println(BYE);
    }


}
