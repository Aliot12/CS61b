package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

public class NGramMap {

    private final HashMap wordsdate;

    private final TimeSeries countdate;

    public NGramMap(String wordsFilename, String countsFilename) {
        wordsdate = new HashMap();
        countdate = new TimeSeries();
        In in = new In(wordsFilename);
        while (in.hasNextLine()){
            String[] linedate = in.readLine().split("\t");//读入数据
            String word = linedate[0];
            Integer year = Integer.parseInt(linedate[1]);
            Double num = Double.parseDouble(linedate[2]);
            if(!wordsdate.containsKey(word)){
                TimeSeries ts = new TimeSeries();
                ts.put(year,num);
                wordsdate.put(word,ts);//采用一个Hach结构来映射单词，每个单词对应一个TS
            }
            else{
                TimeSeries ts = (TimeSeries) wordsdate.get(word);
                ts.put(year,num);
            }
        }
        In count = new In(countsFilename);
        while (count.hasNextLine()){
            String[] line = count.readLine().split(",");
            countdate.put(Integer.parseInt(line[0]),Double.parseDouble(line[1]));
        }
    }


    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        return new TimeSeries((TimeSeries) wordsdate.get(word),startYear,endYear);
    }

    public TimeSeries countHistory(String word) {
        TimeSeries result = new TimeSeries();
        if(!wordsdate.containsKey(word))return result;
        result.putAll((TimeSeries)wordsdate.get(word));
        return result;
    }

    public TimeSeries totalCountHistory() {
        TimeSeries result = new TimeSeries();
        result.putAll(countdate);
        return result;
    }

    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries result =weightHistory(word);
        result = new TimeSeries(result,startYear,endYear);
        return result;
    }

    public TimeSeries weightHistory(String word) {
        if(!wordsdate.containsKey(word))return new TimeSeries();
        TimeSeries date = (TimeSeries) wordsdate.get(word);
        return date.dividedBy(countdate);
    }

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

    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries result = new TimeSeries();
        for(String i : words){
            if(!wordsdate.containsKey(i))continue;
            result = result.plus((TimeSeries) wordsdate.get(i));
        }
        result = result.dividedBy(countdate);
        return result;
    }
}
