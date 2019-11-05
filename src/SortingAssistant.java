import java.util.ArrayList;

public class SortingAssistant{
    /**
     * Sort by highest points: uses a merge sort to do this.
     */
    public ArrayList<Word> PointsSort(ArrayList<Word> arr){
        //If the array has a length of one, the array is sorted, therefore we can return the array right away.

        if(arr.size() == 1){
            return arr;
        }

        int m = arr.size()/2;

        //create the two sub-arrays of the original array
        ArrayList<Word> tempL = new ArrayList<Word>();
        ArrayList<Word> tempR = new ArrayList<Word>();

        //Copying the elements into the temporary arrays
        for(int i = 0; i < m; i++){
            tempL.add(arr.get(i));
        }
        for(int y = m; y < arr.size(); y++){
            tempR.add(arr.get(y));
        }

        //Calling mergeSort on the temporary arrays. This will happen until they have a length of 1, which means they are sorted.
        tempL = PointsSort(tempL);
        tempR = PointsSort(tempR);

        ArrayList<Word> Sorted = new ArrayList<Word>();

        int Lcount = 0;
        int Rcount = 0;

        //Now, merge the arrays
        for(int z = 0; z < arr.size(); z++){
            //Catching if the count is larger than the array size
            if(Lcount >= tempL.size()){
                for(int i = Rcount; i < tempR.size(); i++){
                    Sorted.add(z, tempR.get(i));
                    z++;
                }
                break;
            }
            if(Rcount >= tempR.size()){
                for(int i = Lcount; i < tempL.size(); i++){
                    //Sorted[z] = tempL[i];
                    Sorted.add(z, tempL.get(i));
                    z++;
                }
                break;
            }
            //Compare
            if(tempL.get(Lcount).value < tempR.get(Rcount).value){
                Sorted.add(z, tempR.get(Rcount));
                Rcount++;
            } else if(tempL.get(Lcount).value > tempR.get(Rcount).value){
                Sorted.add(z, tempL.get(Lcount));
                Lcount++;
            } else if(tempL.get(Lcount).value == tempR.get(Rcount).value){
                Sorted.add(z, tempL.get(Lcount));
                Sorted.add(z+1, tempR.get(Rcount));
                Lcount++;
                Rcount++;
                z++;
            }
        }
        return Sorted;
    }

    /**
     * This sorts the array by longest length to shortest length. I chose to use a bubble sort for this simply to
     * try it out and see how well it works. Also, using a bubbleSort should not be too large of a drawbacks because the
     * arrayLists being sorted are relatively small.
     * @param arr
     * @return
     */
    public ArrayList<Word> LengthSort(ArrayList<Word> arr){
        //loops through the array
        for(int i = 0; i < arr.size()-1; i++){
            //loops through again, ensuring that the chosen letter can be shifter over.
            for(int y = 0; y < arr.size()-1-i; y++){
                if(arr.get(y).word.length() < arr.get(y+1).word.length()){
                    //interchange the two letters being checked. Thus shifting the letters to the appropriate spots.
                    Word stored = arr.get(y);
                    arr.set(y, arr.get(y+1));
                    arr.set(y+1, stored);
                }
            }
        }
        return arr;
    }

    /**
     * This sorts the array in alphabetical order. I chose to use a bubble sort for this simply to
     * try it out and see how well it works. Also, using a bubbleSort should not be too large of a drawbacks because the
     * arrayLists being sorted are relatively small.
     * @param arr
     * @return
     */
    public ArrayList<Word> AlphabeticalSort(ArrayList<Word> arr){
        //loops through the array
        for(int i = 0; i < arr.size()-1; i++){
            //loops through again, ensuring that the chosen letter can be shifter over.
            for(int y = 0; y < arr.size()-1-i; y++){
                if(arr.get(y).word.compareTo(arr.get(y+1).word) > 0){
                    //interchange the two letters being checked. Thus shifting the letters to the appropriate spots.
                    Word stored = arr.get(y);
                    arr.set(y, arr.get(y+1));
                    arr.set(y+1, stored);
                }
            }
        }
        return arr;
    }
}