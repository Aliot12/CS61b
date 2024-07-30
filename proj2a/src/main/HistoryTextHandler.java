package main;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;

import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler{

    NGramMap date;
    public HistoryTextHandler(NGramMap map){
        date = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        String response = "";
        for(String i : words){
            TimeSeries da =  date.weightHistory(i,startYear,endYear);
            response += i +": ";
            response += da.toString()+ "\n";
        }
        return response;
    }
}
