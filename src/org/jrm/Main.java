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
        System.out.format("%8s  %-18s %6s %6s %6s\n","Account","Name", "Movies", "Points", "Average Review Rating");
        while ((line = cardAccts.fileReadLine()) != null) {
            fields = line.split(",");
            findPurchases(fields[0], nums);
            findAverageRating(fields[0], average);
            System.out.format("00%6s  %-18s  %2d   %4d     %.2f\n",fields[0],fields[1], nums[0], nums[1], average[0]);
        }
        String someString = new String("you should be done now");
        System.out.println(someString);
    }

    public static void findPurchases(String acct, int[] nums) {
        nums[0] = 0;
        nums[1] = 0;
        String line;
        String[] fields;
        boolean done = false;
        while (done != true)
        {
            while((line = cardPurchases.fileReadLine()) != null)
            {
                System.out.println(line);
                fields = line.split(",");
                if (fields[0].equals(acct))
                {
                    nums[0]++;
                    nums[1] += Integer.parseInt(fields[2]);
                }
                else
                {
                    cardPurchases.backUpOneLine();
                    done = true;
                    break; // yes it's cheap, ugly, and downright undignified...
                }
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
        }
    }
}