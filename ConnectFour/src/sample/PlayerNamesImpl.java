package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayerNamesImpl implements PlayerNames {

    public static String player1;
    public static String player2;

    BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));
    @Override
    public String capturePlayer1Name() {
        try {
            //The message welcome Player 1, please enter your name. I'm inside a serviceImpl class!
            System.out.println("Player 1, please enter your name:");
            player1 = reader.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return player1;
    }

    @Override
    public String capturePlayer2Name() {
       try {
           System.out.println("Player 2, please enter your name:");
           player2 = reader.readLine();
       }   catch(IOException e){
           e.printStackTrace();
       }
        return player2;
    }
}
