package edu.stuy.starlorn.util;

public class HighScore {
    private String _name;
    private long _score;
    public HighScore(String name, long score) {
        _name = name;
        _score = score;
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

    public int compareTo(Object o) {
        if (o instanceof HighScore) {
            long otherScore = ((HighScore) o)._score;
            if (otherScore > _score) return -1;
            else if (otherScore < _score) return 1;
            else return 0;
        } else throw new java.lang.IllegalArgumentException("Expected HighScore");
    }
}
