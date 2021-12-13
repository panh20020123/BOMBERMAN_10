package src.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class HighScore {
    public static String highScoreFilePath = "./res/highscore/highscore.txt";

    public static List<ScoreName> setScore = new ArrayList<>();

    public static class ScoreName implements Comparable {
        public int score;
        public String name;

        public ScoreName(int s, String n) {
            score = s;
            name = n;
        }

        @Override
        public int compareTo(Object o) {
            ScoreName x = (ScoreName) o;
            if (x.score > this.score)
                return -1;
            if (x.score < this.score)
                return 1;
            return x.name.compareTo(this.name);
        }
    }

    public static void loadHighScore() {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(highScoreFilePath));

            String fromText;
            while ((fromText = in.readLine()) != null) {
                int index = 0;
                for (int t = 0; t < fromText.length(); t++) {
                    if (fromText.charAt(t) == ' ') {
                        index = t;
                        break;
                    }
                }
                int sc = Integer.parseInt(fromText.substring(0, index));
                String na = fromText.substring(index + 1).trim();

                ScoreName newS = new ScoreName(sc, na);
                setScore.add(newS);

            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveHightScore(int score, String name) {
        try {

            String str = "";
            BufferedWriter out = new BufferedWriter(new FileWriter(highScoreFilePath));
            setScore.add(new ScoreName(score, name));

            Collections.sort(setScore, Collections.reverseOrder());

            int count = 0;
            for (ScoreName i : setScore) {
                if (count + 1 <= 5) {
                    str += i.score + " " + i.name + "\n";
                }
            }
            setScore = new ArrayList<>();

            out.write(str);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
