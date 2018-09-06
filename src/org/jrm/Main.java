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
        System.out.format("%8s  %-18s %6s %6s\n","Account","Name", "Movies", "Points");
        while ((line = cardAccts.fileReadLine()) != null) {
            fields = line.split(",");
            findPurchases(fields[0], nums);
            System.out.format("00%6s  %-18s  %2d   %4d\n",fields[0],fields[1], nums[0], nums[1]);
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

    public static void findAverageRating(String acct, int[] nums) {
        nums[0] = 0;
        nums[1] = 0;
        String line;
        String[] fields;
        boolean done = false;
        while((line = cardRatings.fileReadLine()) != null)
        {
            fields = line.split(",");
            if (fields[0].compareTo(acct) > 0)
            {
                // If the first field of the card rating file doesn't match the acct
                // break out after calculating average rating...
            }
            else if ()
            {
                // If the first field of the card rating matches acct
                // add to total rating
            }
        }
        /* while (((line = cardPurchases.fileReadLine()) != null) && !(done)) {
            fields = line.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            }
            else if (fields[0].equals(acct)) {
                nums[0]++;
                nums[1] += Integer.parseInt(fields[2]);
            }

        }*/
    }
}