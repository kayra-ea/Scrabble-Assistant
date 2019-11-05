import javax.swing.*;
import java.io.IOException;

public class Main {
    /**
     * Main method; creates a scrabble form and a frame to put the panels in
     */
    public static void main(String[] args) throws IOException {
        ScrabbleForm scrabble = new ScrabbleForm();
        JFrame frame = new JFrame("ScrabbleAssistant Assistant");
        frame.add(scrabble.MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
