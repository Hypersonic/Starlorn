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

    public Score add(String name, long score, Date date) {
        Score scoreObj = new Score(name, score, date);
        _scores.add(scoreObj);
        return scoreObj;
    }

    public Score add(String name, long score) {
        return this.add(name, score, new Date());
    }

    public int count() {
        return _scores.size();
    }

    public long getLowest() {
        if (count() == 0)
            return 0;
        return _scores.first().getScore();
    }

    public Score popLowest() {
        return _scores.pollFirst();
    }

    public boolean extraScores() {
        return count() > MAX_SCORES;
    }

    public boolean displaces(long score) {
        return count() < MAX_SCORES || getLowest() < score;
    }

    public Iterator<Score> iterator() {
        return _scores.descendingIterator();
    }

    public void save(String filename) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter(filename));
            for (Score score : _scores) {
                w.write(score.getName().replace(":", "\\:").replace("\n", " ")); //Escape colons, remove newlines
                w.write(" : "); // separator
                w.write("" + score.getScore());
                w.write(" : ");
                w.write("" + score.getDate().getTime());
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
                String[] data = b.readLine().split(" : ");
                if (data.length != 3) {
                    System.out.println("Some line in the scores file isn't formatted right. I'll ignore it");
                    break;
                }
                String name = data[0].replace("\\:", ":"); // We'll escape their colons whilst saving, so unescape them whilst loading
                long score = Long.parseLong(data[1]);
                long time = Long.parseLong(data[2]);
                add(name, score, new Date(time));
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
