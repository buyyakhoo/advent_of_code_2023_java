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
                    String[] numeric_list = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
                    Integer num_real = -1;
                    // for (String numeric_each : numeric_list) {
                    for (int ne_idx=0; ne_idx < numeric_list.length; ne_idx++) {

                        boolean check = true;

                        if (numeric_list[ne_idx].charAt(0) == input_sep) {
                            try {
                                for (int j=0; j<numeric_list[ne_idx].length(); j++) {
                                    // if (i+j < input_each.length()) {
                                        if (numeric_list[ne_idx].charAt(j) != input_each.charAt(i+j)) {
                                            check = false;
                                            break;
                                        }
                                    // } else {
                                    //     check = false;
                                    //     break;
                                    // }


                                }
                            } 
                            
                            catch (StringIndexOutOfBoundsException ex) {
                                continue;
                            }


                            if (check) {
                                num_real = ne_idx+1;
                                break;
                            } 

                            // break;
                        }                        

                    }

                    first = first == -1 && num_real != -1 ? num_real : first;
                    last = num_real != -1 ? num_real : last;

                    // continue;
                }

                catch (StringIndexOutOfBoundsException e) {
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
