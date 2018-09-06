package org.jrm;

import java.util.Scanner;

// TODO: calculate average rating derived from aggregating movie_rating.csv
public class Main {

    private final static FileInput cardAccts = new FileInput("movie_cards.csv");
    private final static FileInput cardPurchases = new FileInput("movie_purchases.csv");
    private final static FileInput cardRatings = new FileInput("movie_rating.csv");

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        String line;
        String[] fields;
        int[] nums = new int[2];
        float[] average = new float[1];
        System.out.format("%8s  %-18s %6s %6s %6s\n","Account","Name", "Movies", "Points", "Average");
        while ((line = cardAccts.fileReadLine()) != null) {
            fields = line.split(",");
            findPurchases(fields[0], nums);
            findAverageRating(fields[0], average);
            System.out.format("00%6s  %-18s  %2d   %4d     %.2f\n",fields[0],fields[1], nums[0], nums[1], average[0]);
        }
    }

    public static void findPurchases(String acct, int[] nums) {
        nums[0] = 0;
        nums[1] = 0;
        String line;
        String[] fields;
        boolean done = false;
        while (((line = cardPurchases.fileReadLine()) != null) && !(done)) {
            fields = line.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            }
            else if (fields[0].equals(acct)) {
                nums[0]++;
                nums[1] += Integer.parseInt(fields[2]);
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
        while((line = cardRatings.fileReadLine()) != null && !(done))
        {
            fields = line.split(",");
            if (fields[0].compareTo(acct) > 0)
            {
                // If the first field of the card rating file doesn't match the acct
                // break out after calculating average rating...
                if (rateData[0] > 0)
                {
                    average[0] = (float)rateData[1]/rateData[0];
                }
                else
                {
                    average[0] = 0;
                }
                done = true;
            }
            else if (fields[0].equals(acct))
            {
                // If the first field of the card rating matches acct
                // increment movie nums (avgs[0]) and add to total rating
                rateData[0]++;
                rateData[1] += Integer.parseInt(fields[1]);
            }
        }

    }
}