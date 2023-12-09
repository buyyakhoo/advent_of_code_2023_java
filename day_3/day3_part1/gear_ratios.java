import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class gear_ratios {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File(
            "./input.txt"
        );

        Scanner sc = new Scanner(inputFile);
        ArrayList<String> all_es = new ArrayList<String>();

        while(sc.hasNextLine()) {
            all_es.add(sc.nextLine());
        }

        String set_of_num = "0123456789";
        String set_of_symbol = "/*!@#$%^&*()\"{}_[]|\\?/<>,+-=";

        ArrayList<Integer> res_set = new ArrayList<>();

        for (int row=0; row<all_es.size(); row++) {
            String thatLine = all_es.get(row);
            String nns_full = "";
            boolean nns = false;

            for (int pos=0; pos<thatLine.length(); pos++) {
                // is that num
                boolean chk_num = check_num(set_of_num, thatLine.charAt(pos));    
                boolean num_next;

                if (chk_num) {
                    // check num near symbol
                    nns = !nns ? num_near_symbol(set_of_symbol, row, pos, all_es) : nns;

                    // check if have num next add in temp, else, add valid_list from temp
                    num_next = num_next(set_of_num, pos, thatLine);                    
                    nns_full += thatLine.charAt(pos);
                    if (!num_next) {
                        if (nns) {
                            res_set.add(Integer.parseInt(nns_full));
                        }
                        nns = false;
                        nns_full = "";   
                    }

                    System.out.println(nns_full);
                }
                
            }
        }

        System.out.println(res_set);
        Integer res = 0;

        for (int rs_idx=0; rs_idx<res_set.size(); rs_idx++) {
            res += res_set.get(rs_idx);
        }

        System.out.println(res);
        sc.close();
    }

    public static boolean check_num(String set_of_num, char txt) {
        
        for (int i=0; i<set_of_num.length(); i++) {
            if (txt == set_of_num.charAt(i)) {
                return true;
            }
        }

        return false;
    }

    public static boolean num_near_symbol(String set_of_symbol, Integer row, Integer pos, ArrayList<String> all_es) {
    
        for (int i=row-1; i<=row+1; i++) {
            for (int j=pos-1; j<=pos+1; j++) {

                try {
                    boolean symbol_valid = find_symbol(set_of_symbol, all_es.get(i).charAt(j));
                    if (symbol_valid) {
                        System.out.println(all_es.get(row).charAt(pos) + " " + row + " " + pos + " " + all_es.get(i).charAt(j));
                        return true;
                    }

                } catch (Exception e) {
                    continue;
                }

            }
        }


        
        // demo program
        return false;
    }

    public static boolean find_symbol(String set_of_symbol, char txt) {

        for (int i=0; i<set_of_symbol.length(); i++) {
            if (txt == set_of_symbol.charAt(i)) {
                return true;
            }
        }
        
        return false;
    }

    public static boolean num_next(String set_of_num, Integer pos, String thatLine) {

        if (pos < thatLine.length()-1) {
            if (check_num(set_of_num, thatLine.charAt(pos+1))) {
                return true;
            }
        }

        return false;
    }
}