package org.jrm.org.jrm.data;

/**
 * Class model for a movie rating record
 * @author Jared Mallas
 * @version 1.0
 */
public class RatingRecord
{
    String account;
    Integer rating;

    /**
     * Constructor for a movie rating record
     * @param account Account which submitted review
     * @param rating Rating of movie
     */
    public RatingRecord(String account, Integer rating)
    {
        this.account = account;
        this.rating = rating;
    }

    /* getters and setters */

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
