package edu.stuy.util;

import java.util.TreeMap;

public class HighScores {
    protected ScoreTree _scores;

    public HighScores() {
        _scores = new ScoreTree();
    }

    public void addScore(String name, long score) {
        _scores.add(new HighScore(name, score));
    }

    public HighScore[] getScoresSorted() {
        return _scores.getScoresSorted();
    }

    private class ScoreTree {
        private HighScore _val;
        private ScoreTree _left, _right;
        private int _size;

        public ScoreTree(HighScore score) {
            _val = score;
            _size = 1;
        }
        public ScoreTree() {
            this(null);
            _size = 0;
        }

        public void add(HighScore score) {
            _size++;
            if (_val == null) _val = score;
            else {
                if (score.compareTo(_val) > 0) {
                    if (_right == null) _right = new ScoreTree(score);
                    else _right.add(score);
                } else {
                    if (_left == null) _left = new ScoreTree(score);
                    else _left.add(score);
                }
            }
        }

        /*
         * Returns an array with the scores, sorted from lowest to highest
         */
        public HighScore[] getScoresSorted() {
            HighScore[] sortedScores = new HighScore[_size];
            int index = 0;
            if (_left != null) {
                HighScore[] leftScores = _left.getScoresSorted();
                for (int j = 0; j < leftScores.length; j++) {
                    sortedScores[index] = leftScores[j];
                    index++;
                }
            }
            if (_val != null) {
                sortedScores[index] = _val;
                index++;
            }
            if (_right != null) {
                HighScore[] rightScores = _right.getScoresSorted();
                for (int j = 0; j < rightScores.length; j++) {
                    sortedScores[index] = rightScores[j];
                    index++;
                }
            }
            return sortedScores;
        }
    }

}
