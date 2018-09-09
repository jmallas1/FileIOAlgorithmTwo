package org.jrm.org.jrm.data;

import java.util.Objects;

/**
 * Class model for a row of report data
 * @author Jared Mallas
 * @version 1.0
 */
public class ReportRecord implements Comparable<ReportRecord>
{
    private String account;
    private String name;
    private Integer movieCount;
    private Integer points;
    private Float aveScore;

    /**
     * Constructor for a report record
     * @param account account number
     * @param name account name
     * @param movieCount number of movies seen
     * @param points "points"
     * @param aveScore average review from this account
     */
    public ReportRecord(String account, String name, Integer movieCount, Integer points, Float aveScore) {
        this.account = account;
        this.name = name;
        this.movieCount = movieCount;
        this.points = points;
        this.aveScore = aveScore;
    }

    /**
     * Convert this record to CSV format
     * @return string of instance vars in CSV format
     */
    public String toCsv()
    {
        return account + "," + name + "," + movieCount + "," + points + "," + aveScore;
    }

    /* getters and setters */

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMovieCount() {
        return movieCount;
    }

    public void setMovieCount(Integer movieCount) {
        this.movieCount = movieCount;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Float getAveScore() {
        return aveScore;
    }

    public void setAveScore(Float aveScore) {
        this.aveScore = aveScore;
    }

    /* toString, equals, hashcode */

    @Override
    public String toString() {
        return "ReportRecord{" +
                "account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", movieCount=" + movieCount +
                ", points=" + points +
                ", aveScore=" + aveScore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportRecord)) return false;
        ReportRecord that = (ReportRecord) o;
        return getMovieCount() == that.getMovieCount() &&
                getPoints() == that.getPoints() &&
                Float.compare(that.getAveScore(), getAveScore()) == 0 &&
                Objects.equals(getAccount(), that.getAccount()) &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccount(), getName(), getMovieCount(), getPoints(), getAveScore());
    }

    @Override
    public int compareTo(ReportRecord o) {
        return this.getAveScore().compareTo(o.getAveScore());
    }
}
