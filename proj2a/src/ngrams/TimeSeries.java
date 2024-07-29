package ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        if(startYear<MIN_YEAR || endYear > MAX_YEAR)return;
        this.putAll(ts.subMap(startYear,endYear+1));//一开始没看到putALLL
        // TODO: Fill in this constructor.
    }


    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        Set<Integer> keys= keySet();
        // TODO: Fill in this method.
        return new ArrayList<>(keys);//list的创建不熟练
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        return new ArrayList<>(values());//GDP修改1 因为不知道value（）返回的和subset的顺序一样
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries New = new TimeSeries();
        List<Integer>keys2 = ts.years();//这里得到了两个整数列表 问题是怎么处理两个列表一个有一个没有的key
        if(years().isEmpty() && keys2.isEmpty())return New;
        New.putAll(this);
        for(int i : keys2){
            New.put(i , ts.get(i) + getOrDefault(i,0.0));//GDP修改2 因为不知道这个类字典用法
        }
        // TODO: Fill in this method.
        return New;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries New = new TimeSeries();
        List<Integer>keys2 = ts.years();
        if(years().isEmpty() && keys2.isEmpty())return New;
        New.putAll(this);
        for(int i:years()){
            if(!ts.containsKey(i)) throw new IllegalArgumentException();
            New.put(i,get(i)/ts.get(i));
        }
        return New;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
