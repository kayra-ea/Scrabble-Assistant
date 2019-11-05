public class Word {
    String word = "";
    int value;
    /*
     * The Word Class: Is used to create a string that can determine its own point value
     */
    public Word(String word){
        this.word = word;
    }

    /**
     * Uses a switch statement to gather all the points, only gets the points for lowercase letters, thus ignoring blanks.
     * @return value: the value of the word
     */
    public int findPoints(){
        value = 0;
        for(int i = 0; i < word.length(); i++){
            //If the letter being checked is not a blank, then proceed
            if(Character.isLowerCase(word.charAt(i))){
                switch (word.charAt(i)) {
                    case 'e':
                    case 'a':
                    case 'i':
                    case 'o':
                    case 'n':
                    case 'r':
                    case 't':
                    case 'l':
                    case 's':
                    case 'u':
                        this.value += 1;
                        break;
                    case 'd':
                    case 'g':
                        this.value += 2;
                        break;
                    case 'b':
                    case 'c':
                    case 'm':
                    case 'p':
                        this.value += 3;
                        break;
                    case 'f':
                    case 'h':
                    case 'v':
                    case 'w':
                    case 'y':
                        this.value += 4;
                        break;
                    case 'k':
                        this.value += 5;
                        break;
                    case 'j':
                    case 'x':
                        this.value += 8;
                        break;
                    case 'q':
                    case 'z':
                        this.value += 10;
                        break;
                    default:
                        this.value += 0;
                }
            }
        }
        return value;
    }

    //returns the String value of the word
    public String getWord(){
        return word;
    }
}
