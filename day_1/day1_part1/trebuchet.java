import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class trebuchet {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File(
            "./input.txt"
        );
        Scanner sc = new Scanner(inputFile);

        Integer res = 0;

        while(sc.hasNextLine()) {
            // System.out.println(sc.nextLine());
            String input_each = sc.nextLine();

            Integer first = -1;
            Integer last = -1;

            System.out.println("line: " + input_each);

            for (int i=0; i<input_each.length(); i++) {
                char input_sep = input_each.charAt(i);

                try {
                    // Integer exchange_check = Character.getNumericValue(input_sep);
                    Integer exchange_check = Integer.parseInt(String.valueOf(input_sep));
                    first = first == -1 ? exchange_check : first;
                    last = exchange_check;

                }

                catch (NumberFormatException e) {
                    continue;
                }
                
            }

            // System.out.println(min + " " + max);

            Integer  sub_res_sum = Integer.valueOf("" + Integer.toString(first) + Integer.toString(last));
            System.out.println(sub_res_sum);
            res += sub_res_sum;

        }
        System.out.println(res);

        sc.close();
    }
}
