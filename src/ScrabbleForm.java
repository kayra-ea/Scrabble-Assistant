import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ScrabbleForm{
    JPanel MainPanel;
    private JPanel OutputPanel;
    private JTextField InputFld;
    private JLabel WordsLbl;
    private JPanel InputPanel;
    private JLabel InputLbl;
    private JLabel LettersLbl;
    private JLabel WarningLbl;
    private JScrollPane WordsPane;
    private JRadioButton HSRBtn;
    private JButton GenBtn;
    private JRadioButton LengthRBtn;
    private JRadioButton AlphaRBtn;
    private JButton ClearBtn;
    private JTextArea OutputArea;

    public static ArrayList<String> letters, empty;
    public static ArrayList<Word> sorted = new ArrayList<Word>();
    ScrabbleAssistant s = new ScrabbleAssistant();
    SortingAssistant sorter = new SortingAssistant();

    boolean printed = false;

    public ScrabbleForm() throws IOException{
        //Initializing the arrays
        letters = new ArrayList<String>();
        empty = new ArrayList<String>();
        WarningLbl.setVisible(false);

        //JInputField: takes in letters entered by the user and sets them to the array "letters" to be checked by the ScrabbleAssistant.
        InputFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = (InputFld.getText());

                for(int i = 0; i < input.length(); i++) {
                    char letterChar = input.charAt(i);
                    String letterString = Character.toString(letterChar);

                    //Making sure the input is acceptable, otherwise set the warning lable visible
                    if (Character.isLetter(letterChar) || letterString.compareTo("*") == 0) {
                        letters.add(letterString);
                        InputFld.setText("");
                    } else {
                        WarningLbl.setVisible(true);
                        InputFld.setText("");
                    }
                }
                LettersLbl.setText("Your Letters Are: " + letters);
            }
        });

        //JButton: this generates the words with the letters entered, at first in no specified order.
        GenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(printed == false){
                    s.transferList();
                    s.setAlphabet1();
                    s.getPossibleWords(empty, letters);

                    //printing to the text area.
                    for(int i = 0; i < s.realWords.size(); i++) {
                        OutputArea.append(s.realWords.get(i).word + " : " + s.realWords.get(i).findPoints() +"\n");
                    }
                    printed = true;
                }
            }
        });

        //JButton: clears the text area and the array of letters so new letters can be entered.
        ClearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                letters.clear();
                LettersLbl.setText("Your Letters Are: " + letters);
                InputFld.setText("");
                OutputArea.setText("");
                s.realWords.clear();
                printed = false;
            }
        });
        //RadioButton: sorts by highest points using the SortingAssistant
        HSRBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Sorting by highest point
                sorted = sorter.PointsSort(s.realWords);

                //Setting the text accordingly
                OutputArea.setText("");
                for(int i = 0; i < sorted.size(); i++) {
                    OutputArea.append(sorted.get(i).getWord() + " : " + sorted.get(i).findPoints() +"\n");
                }
                printed = true;

            }
        });
        //RadioButton: sorts by highest length using the SortingAssistant
        LengthRBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Sorting by length
                sorted = sorter.LengthSort(s.realWords);

                //Setting the text accordingly
                OutputArea.setText("");
                for(int i = 0; i < sorted.size(); i++) {
                    OutputArea.append(sorted.get(i).getWord() + " : " + sorted.get(i).findPoints() +"\n");
                }
                printed = true;
            }
        });
        //RadioButton: sorts alphabetically using the SortingAssistant
        AlphaRBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Sorting alphabetically
                sorted = sorter.AlphabeticalSort(s.realWords);

                //Setting the text accordingly
                OutputArea.setText("");
                for(int i = 0; i < sorted.size(); i++) {
                    OutputArea.append(sorted.get(i).getWord() + " : " + sorted.get(i).findPoints() + "\n");
                }
                printed = true;
            }
        });
    }

    /*
     * creating a custom panel.
     */
    private void createUIComponents() {
        // TODO: place custom component creation code here
        OutputPanel = new OutputPanel();
    }
}
