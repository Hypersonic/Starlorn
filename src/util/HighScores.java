package edu.stuy.starlorn.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;
import java.util.TreeSet;

public class HighScores {

    public static final int MAX_SCORES = 20;

    protected TreeSet<Score> _scores;

    public HighScores() {
        _scores = new TreeSet<Score>();
    }

    public void add(String name, long score, Date date) {
        _scores.add(new Score(name, score, date));
    }

    public void add(String name, long score) {
        _scores.add(new Score(name, score));
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
}
