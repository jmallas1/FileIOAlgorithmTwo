package org.jrm;

import org.jrm.org.jrm.data.RatingRecord;
import org.jrm.org.jrm.data.ReportRecord;

import java.util.*;

public class Main {

    private final static FileInput cardAccts = new FileInput("movie_cards.csv");
    private final static FileInput cardPurchases = new FileInput("movie_purchases.csv");
    private final static FileInput cardRatings = new FileInput("movie_rating.csv");
    private final static FileOutput reportOut = new FileOutput("report.out.csv");
    private final static FileOutput reportOutSorted = new FileOutput("report.out.sorted.csv");

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        String line;
        String[] fields;
        String reportString = new String();
        ArrayList<ReportRecord> aReportList = new ArrayList();
        ArrayList<RatingRecord> aRatingList = new ArrayList();
        int[] nums = new int[2];
        float[] average = new float[1];
        System.out.format("%8s  %-18s %6s %6s %6s\n","Account","Name", "Movies", "Points", "Average");
        while ((line = cardAccts.fileReadLine()) != null) {
            fields = line.split(",");
            findPurchases(fields[0], nums);
            findAverageRating(fields[0], average);
            aReportList.add(new ReportRecord(fields[0], fields[1], nums[0], nums[1], average[0]));
            System.out.format("00%6s  %-18s  %2d   %4d     %.2f\n",fields[0],fields[1], nums[0], nums[1], average[0]);
            reportString = reportString + fields[0] + "," + fields[1] + "," + nums[0] + "," + nums[1] + "," + average[0]+"\n";

        }

        reportString = reportString.substring(0, reportString.length() -1); // hack off trailing carriage return
        reportOut.fileWrite(reportString);

        Collections.sort(aReportList);

        reportString = new String();
        for( ReportRecord record : aReportList)
        {
            reportString = reportString + record.toCsv() + "\n";
        }
        reportString = reportString.substring(0, reportString.length() -1); // hack off trailing carriage return
        reportOutSorted.fileWrite(reportString);

        reportOut.fileClose();
        cardAccts.fileClose();
        cardPurchases.fileClose();
        cardRatings.fileClose();
        reportOutSorted.fileClose();

        FileInput moreRatings = new FileInput("movie_rating.csv");
        HashMap<String, Integer> aggregateRatings = genAggregateRatings(moreRatings);

        for (String key : aggregateRatings.keySet())
        {
            System.out.println(key + " ==> " + aggregateRatings.get(key));
        }
    }

    private static HashMap genAggregateRatings(FileInput moreRatings)
    {
        String line;
        String[] fields;
        HashMap<String, Integer> rMap = new HashMap();
        Integer workingInt = 0;

        while((line = moreRatings.fileReadLine()) != null)
        {
            fields = line.split(",");
            if(rMap.containsKey(fields[1]))
            {
                workingInt = rMap.get(fields[1]) +1;
                rMap.put(fields[1], workingInt);
            }
            else
            {
                rMap.put(fields[1], 1);
            }
        }

        return rMap;
    }

    public static void findPurchases(String acct, int[] nums) {
        nums[0] = 0;
        nums[1] = 0;
        String line;
        String[] fields;
        boolean done = false;
        while (done != true)
        {
            while(((line = cardPurchases.fileReadLine()) != null))
            {
                // System.out.println(line);
                fields = line.split(",");
                if (fields[0].equals(acct))
                {
                    nums[0]++;
                    nums[1] += Integer.parseInt(fields[2]);
                }
                else if (!fields[0].equals(acct))
                {
                    cardPurchases.backUpOneLine();
                    done = true;
                    break; // yes it's cheap, ugly, and downright undignified...
                }
            }

            if (line == null)
            {
                done = true;
            }
        }
    }

    public static void findAverageRating(String acct, float[] average) {
        int[] rateData = new int[2];
        rateData[0] = 0;
        rateData[1] = 0;
        average[0] = 0;
        String line;
        String[] fields;
        boolean done = false;

        while(done != true)
        {
            while((line = cardRatings.fileReadLine()) != null)
            {
                fields = line.split(",");
                if(fields[0].equals(acct))
                {
                    // if the account matches, increment the total reviews and add score
                    rateData[0]++;
                    rateData[1] += Integer.parseInt(fields[1]);
                }
                else
                {
                    // if it doesn't, calculate the average review score
                    cardRatings.backUpOneLine();
                    if(rateData[0] > 0) {
                        average[0] = (float) rateData[1] / rateData[0];
                    }
                    else
                    {
                        average[0] = 0;
                    }
                    done = true;
                    break;
                }
            }

            if (line == null)
            {
                done = true;
            }
        }
    }
}