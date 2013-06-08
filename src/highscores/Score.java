package edu.stuy.starlorn.highscores;

import java.text.DecimalFormat;
import java.util.Date;

public class Score implements Comparable {

    private String _name;
    private long _score;
    private int _level, _wave;
    private Date _date;

    public Score(String name, long score, int level, int wave, Date date) {
        _name = name;
        _score = score;
        _level = level;
        _wave = wave;
        _date = date;
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

    public String getFormattedScore() {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(_score);
    }

    public void setLevel(int level) {
        _level = level;
    }

    public int getLevel() {
        return _level;
    }

    public void setWave(int wave) {
        _wave = wave;
    }

    public int getWave() {
        return _wave;
    }

    public void setDate(Date date) {
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
