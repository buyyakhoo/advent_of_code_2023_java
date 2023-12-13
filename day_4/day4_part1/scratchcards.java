import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class scratchcards {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File (
            "./input.txt"
        );
        Scanner sc = new Scanner(inputFile);
        Integer res = 0;

        while (sc.hasNext()) {
            // System.out.println(sc.nextLine());
            ArrayList<Integer> win_num = new ArrayList<Integer>();
            ArrayList<Integer> all_num = new ArrayList<Integer>();
            String input_txt = sc.nextLine();
            String num_full = "";
            boolean all_mode = false;

            // separate the win and all number you have in each arraylist
            // e.g., separate(win : ArrayList, all_num : ArrayList, sc.nextLine() : String) : void => nothing (not use this at now)
            // loop over the pos (col)
            for (int pos=10; pos < input_txt.length(); pos++) {
                // skip 11 pos (already skipped)
                // check is this num
                boolean check_num = check_num("0123456789", input_txt.charAt(pos));

                if (check_num) {
                    num_full += input_txt.charAt(pos);
                }

                boolean num_next = pos+1 != input_txt.length() ? check_num("0123456789", input_txt.charAt(pos+1)) : false;
                all_mode = !all_mode ? check_all_mode('|', input_txt.charAt(pos)) : true;

                try {
                    if (!num_next && !all_mode) {
                        win_num.add(Integer.parseInt(num_full));
                        num_full = "";
                    } else if (!num_next && all_mode) {
                        all_num.add(Integer.parseInt(num_full));
                        num_full = "";
                    }
                } catch (NumberFormatException e) {
                    if (!num_next)
                        num_full = "";
                }

            }

            Integer inc_points = 1;
            Integer match = 0;

            for (Integer win_each : win_num) {
                for (Integer all_each : all_num) {
                    System.out.println(win_each + " " + all_each);
                    if (win_each == all_each) {
                        System.out.println("Winner: " + win_each + " " + all_each);
                        inc_points *= ++match >= 3 ? 2 : 1;
                        res += inc_points;
                    }
                }
            }
            all_mode = false;
        }

        System.out.println(res);
        sc.close();
    }

    public static boolean check_num(String set_of_num, char txt) {

        for (int i=0; i<set_of_num.length(); i++) {
            if (set_of_num.charAt(i) == txt) {
                return true;
            }
        }

        return false;
    }

    public static boolean check_all_mode(char check, char txt) {
        
        if (check == txt) {
            return true;
        }

        return false;
    }
}