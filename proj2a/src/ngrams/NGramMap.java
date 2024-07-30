package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    private final HashMap wordsdate;

    private final TimeSeries countdate;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */

    public NGramMap(String wordsFilename, String countsFilename) {
        wordsdate = new HashMap();
        countdate = new TimeSeries();
        In in = new In(wordsFilename);
        while (!in.isEmpty()){
            String[] linedate = in.readLine().split("\t");
            String word = linedate[0];
            Integer year = Integer.parseInt(linedate[1]);
            Double num = Double.parseDouble(linedate[2]);
            if(!wordsdate.containsKey(word)){
                TimeSeries ts = new TimeSeries();
                ts.put(year,num);
                wordsdate.put(word,ts);
            }
            else{
                TimeSeries ts = (TimeSeries) wordsdate.get(word);
                ts.put(year,num);
            }
        }
        In count = new In(countsFilename);
        while (!count.isEmpty()){
            String[] line = count.readLine().split(",");
            countdate.put(Integer.parseInt(line[0]),Double.parseDouble(line[1]));
        }
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        return new TimeSeries((TimeSeries) wordsdate.get(word),startYear,endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        TimeSeries result = new TimeSeries();
        if(!wordsdate.containsKey(word))return result;
        result.putAll((TimeSeries)wordsdate.get(word));
        return result;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries result = new TimeSeries();
        result.putAll(countdate);
        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries result =weightHistory(word);
        result = new TimeSeries(result,startYear,endYear);
        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if(!wordsdate.containsKey(word))return new TimeSeries();
        TimeSeries date = (TimeSeries) wordsdate.get(word);
        return date.dividedBy(countdate);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries result = new TimeSeries();
        for(String i : words){
            if(!wordsdate.containsKey(i))continue;
            result = result.plus((TimeSeries) wordsdate.get(i));
        }
        result = new TimeSeries(result,startYear,endYear);
        result = result.dividedBy(countdate);
        // TODO: Fill in this method.
        return result;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries result = new TimeSeries();
        for(String i : words){
            if(!wordsdate.containsKey(i))continue;
            result.plus((TimeSeries) wordsdate.get(i));
        }
        result = result.dividedBy(countdate);
        // TODO: Fill in this method.
        return result;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
