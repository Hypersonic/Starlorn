package edu.stuy.starlorn.highscores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

public class HighScores implements Iterable<Score> {

    protected static final int MAX_SCORES = 20;
    protected TreeSet<Score> _scores;

    public HighScores() {
        _scores = new TreeSet<Score>();
    }

    public Score add(Score score) {
        _scores.add(score);
        return score;
    }

    public Score add(String name, long score, int level, int wave, Date date) {
        return add(new Score(name, score, level, wave, date));
    }

    public int count() {
        return _scores.size();
    }

    public Score getHighest() {
        if (_scores.size() == 0)
            return null;
        return _scores.last();
    }

    public Score getLowest() {
        if (_scores.size() == 0)
            return null;
        return _scores.first();
    }

    public Score popLowest() {
        return _scores.pollFirst();
    }

    public boolean extraScores() {
        return count() > MAX_SCORES;
    }

    public boolean displaces(long score) {
        return count() < MAX_SCORES || getLowest().getScore() < score;
    }

    @Override
    public Iterator<Score> iterator() {
        return _scores.descendingIterator();
    }

    public void save(String filename) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(filename));
            for (Score score : this) {
                w.write(score.serialize());
                w.newLine();
            }
            w.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        save("scores.txt");
    }

    public void load(String filename) {
        try {
            BufferedReader b = new BufferedReader(new FileReader(filename));
            while (b.ready()) {
                Score score = Score.deserialize(b.readLine());
                if (score != null)
                    add(score);
            }
            b.close();
        } catch (java.io.IOException e) {
            System.out.println("No highscores file exists, so we'll make one later.");
        }
    }

    public void load() {
        load("scores.txt");
    }
}
