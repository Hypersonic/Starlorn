package edu.stuy.starlorn.highscores;

import java.util.Date;

public class Score implements Comparable {

    private String _name;
    private long _score;
    private Date _date;

    public Score(String name, long score, Date date) {
        _name = name;
        _score = score;
        _date = date;
    }

    public Score(String name, long score) {
        this(name, score, new Date());
    }

    public void setName(String name) {
        _name = name;
    }

    public String getName() {
        return _name;
    }

    public void setScore(long score) {
        _score = score;
    }

    public long getScore() {
        return _score;
    }

    public void setDate (Date date) {
        _date = date;
    }

    public Date getDate() {
        return _date;
    }

    public int compareTo(Object o) {
        if (o instanceof Score) {
            long otherScore = ((Score) o).getScore();
            if (otherScore > _score)
                return -1;
            else if (otherScore < _score)
                return 1;
            else {
                return -_date.compareTo(((Score) o).getDate());
            }
        } else
            throw new IllegalArgumentException("Expected Score");
    }
}
