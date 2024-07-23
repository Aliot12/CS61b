import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int sum = 0;
        for (Integer i : L) {sum += i;}
        // TODO: Fill in this function.
        return sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> evens = new ArrayList<Integer>();
        for (Integer i : L) {if (i % 2 == 0) evens.add(i);}
        // TODO: Fill in this function.
        return evens;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> commons = new ArrayList<Integer>();
        for (Integer i : L1) {for (Integer j : L2) {if (i.equals(j)) {commons.add(i);break;}}}
        // TODO: Fill in this function.
        return commons;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
        for (String word : words) {for(int i = 0;i<word.length();i++){
            if(word.charAt(i) == c){count++;}
        }}
        // TODO: Fill in this function.
        return count;
    }
}
