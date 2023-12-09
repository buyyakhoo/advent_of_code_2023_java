import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class cube_conundrum {
    public static void main(String[] args) throws FileNotFoundException {
        File inputFile = new File(
            "./input.txt"
        );
        Scanner sc = new Scanner(inputFile);
        Integer game_idx = 1;
        ArrayList<Integer> valid_game = new ArrayList<Integer>();

        while(sc.hasNextLine()) {
            String input_game = sc.nextLine();
            Integer pos = 5;
            // skip_game_init(pos, input_game) : Integer => current_pos
            pos = skip_game_initial(pos, input_game);
            // read_rgb(color, input_game) : Boolean => valid
            boolean valid = read_rgb(pos, input_game);

            if (valid) {
                valid_game.add(game_idx);
            }
            game_idx++;
        }

        System.out.println(valid_game);

        Integer res = 0;

        for (int i=0; i<valid_game.size(); i++) {
            res += valid_game.get(i);
        }

        System.out.println(res);
    }

    public static Integer skip_game_initial(Integer pos_i, String input_game) {

        while(input_game.charAt(pos_i) != ':') {
            pos_i++;
        }

        return pos_i+2;

    }

    public static boolean read_rgb(Integer pos_rgb, String input_game) {
        // Character[] rgb = {'r', 'g', 'b'};
        boolean not_found_sc = false;

        for (int color_t=0; !not_found_sc; color_t++) {
            Integer red = 0;
            Integer blue = 0;
            Integer green = 0;
            String numeric = "123456789";

            while(input_game.charAt(pos_rgb) != ';') {

                // 2 condition: 1 digit and 2 digits
                // if 1 digit
                System.out.print(input_game.charAt(pos_rgb+2));
                if (input_game.charAt(pos_rgb+2) == 'b') {
                    blue = read_num_color(pos_rgb, numeric, input_game);
                    pos_rgb += 5;
                } else if (input_game.charAt(pos_rgb+2) == 'r') {
                    red = read_num_color(pos_rgb, numeric, input_game);
                    pos_rgb += 4;
                } else if (input_game.charAt(pos_rgb+2) == 'g') {
                    green = read_num_color(pos_rgb, numeric, input_game);
                    pos_rgb += 6;
                }
                pos_rgb++;

                if (pos_rgb >= input_game.length()) {
                    not_found_sc = true;
                    break;
                }

                System.out.println(red + " " + green + " " + blue);

            }
            System.out.println("finalize round " + color_t + ": " + red + " " + green + " " + blue);
            
            if (red > 12 || blue > 14 || green > 13) {
                return false;
            }
            pos_rgb++;

        }

        return true;
    }

    public static Integer read_num_color(Integer pos, String numeric, String input_game) {

        boolean havePre = false;
        Integer num_game;

        for (int num_pos=0; num_pos<numeric.length(); num_pos++) {
            if (input_game.charAt(pos-1) == numeric.charAt(num_pos)) {
                havePre = true;
            }
        }
        
        if (havePre) {
            // then read as 2 digits
            num_game = Integer.parseInt("" + input_game.charAt(pos-1) + input_game.charAt(pos));
        } else {
            // then read as 1 digits
            num_game = Integer.parseInt("" + input_game.charAt(pos));
        }

        return num_game;

    }
}