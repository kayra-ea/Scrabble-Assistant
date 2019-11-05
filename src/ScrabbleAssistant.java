import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ScrabbleAssistant {

    //Necessary arrayLists
    ArrayList<String> dictionaryList, InptList, possibleWords, empty, alphabet;
    ArrayList<Word> realWords, realWordsCopy;

    //necesarry variables for the binary Search.
    int lowWord, highWord;
    String keyCopy;

    public ScrabbleAssistant() throws IOException{
        //Initializing the arrays
        dictionaryList = new ArrayList<String>();
        empty = new ArrayList<String>();
        InptList = new ArrayList<String>();
        alphabet = new ArrayList<String>();
        realWords = new ArrayList<Word>();
        realWordsCopy = new ArrayList<Word>();
    }

    /**
     * Recursive method that finds all the permutations of a string and checks if it in the dictionary using a binary search.
     * Uses two arrays and moves over a different character from the Original to the Check array in order to find all of the
     * permutations. Also finds the permutations for words with blanks, and displays them as CAPITALIZED so the user can identify
     * which tiles are blanks.
     * @param Check : this array will be checked with the BinarySearch
     * @param Original: Contains the inputted letters
     * @return
     */
    public ArrayList getPossibleWords(ArrayList<String> Check, ArrayList<String> Original){
        ArrayList<String> alphabet = returnAlphabet();
        String WordstoCheck = "";

        //making a string from the Check array
        for(String s:Check) {
            WordstoCheck += s;
        }

        System.out.println(Check + " : " + Original);

        //using the binary search to check if the word is in the dictionary
        BinarySearch(WordstoCheck);

        // Generate all permutations
        for(int y = 0; y < Original.size(); y++){
            // Check for wild cards
            if(Original.get(y).equals("*")){
                Check.add(Original.get(y));
                Original.remove(y);
                //Looping through all of the letters of the alphabet.
                for(int i = 0; i < alphabet.size(); i++){
                    //Setting the letter to a letter in the alphabet, and making it UPPERCASE
                    Check.set(Check.size()-1, alphabet.get(i).toUpperCase());
                    //recursive call
                    getPossibleWords(Check, Original);
                }
                //Resetting the last word to a '*' in order to find the new permutations.
                Original.add(y,"*");
                Check.remove(Check.size()-1);
            } else { //If there is no blank card
                //Move one letter over to the Original array
                Check.add(Original.get(y));
                Original.remove(y);

                //Recursive call
                getPossibleWords(Check, Original);

                //Reset
                Original.add(y, Check.get(Check.size() - 1));
                Check.remove(Check.size() - 1);
            }
        }
        return possibleWords;
    }

    /**
     * Binary Search Method: uses a binary search to look through the dictionary and check if the word is there. If it is, it will be
     * added to an arrayList.
     * @param key
     */
    public void BinarySearch(String key){
        //The collins Scrabble dictionary as an arrayList.
        ArrayList<String> dictionary = dictionaryList;

        //setting up the low, high and mid points
        highWord = dictionary.size()-1;
        lowWord = 1;
        int middleWord;

        //making a copy that will be added to the array if found
        keyCopy = key;
        key = key.toLowerCase();

        //Looping through the dictionary
        while(lowWord <= highWord){
            middleWord = (lowWord + highWord) / 2;
            //if the word is found; ie: the word being checked is the middle word.
            if(key.toLowerCase().compareTo(dictionary.get(middleWord).toLowerCase()) == 0){
                realWords.add(new Word(keyCopy));
                break;
            }else if(key.toLowerCase().compareTo(dictionary.get(middleWord).toLowerCase()) > 0){
                lowWord = middleWord+1;
            }else{
                highWord = middleWord-1;
            }
        }
    }

    //transfers the dictionary text file to an arrayList.
    public void transferList(){
        try{
            File f = new File("D.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                dictionaryList.add(line);
                line = br.readLine();
            }
        }catch(Exception e){
        }
    }

    //returns the alphabet array.
    public ArrayList<String> returnAlphabet(){
        return alphabet;
    }

    //Creates the alphabet and stores it as an arrayList.
    public void setAlphabet1(){
        alphabet.add("a");
        alphabet.add("b");
        alphabet.add("c");
        alphabet.add("d");
        alphabet.add("e");
        alphabet.add("f");
        alphabet.add("g");
        alphabet.add("h");
        alphabet.add("i");
        alphabet.add("j");
        alphabet.add("k");
        alphabet.add("l");
        alphabet.add("m");
        alphabet.add("n");
        alphabet.add("o");
        alphabet.add("p");
        alphabet.add("q");
        alphabet.add("r");
        alphabet.add("s");
        alphabet.add("t");
        alphabet.add("u");
        alphabet.add("v");
        alphabet.add("w");
        alphabet.add("x");
        alphabet.add("y");
        alphabet.add("z");
    }
}
