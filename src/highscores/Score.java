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

    public String serialize() {
        String serial = "%s : %d : %d : %d : %d";
        String name = getName().replace(":", "\\:").replace("\n", " ");
        return String.format(serial, name, getScore(), getLevel(), getWave(),
                             getDate().getTime());
    }

    public static Score deserialize(String input) {
        String[] data = input.split(" : ");
        if (data.length != 5) {
            System.out.println("Some line in the scores file isn't formatted right. I'll ignore it");
            return null;
        }
        // We escape their colons when saving, so unescape them when loading
        String name = data[0].replace("\\:", ":");
        long score = Long.parseLong(data[1]);
        int level = Integer.parseInt(data[2]);
        int wave = Integer.parseInt(data[3]);
        long time = Long.parseLong(data[4]);
        return new Score(name, score, level, wave, new Date(time));
    }

    @Override
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
