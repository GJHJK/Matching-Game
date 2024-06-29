package memorygame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreManager {
    private static final String SCORE_FILE = "scores.txt";

    public static void saveScore(String playerName, int score, int moves, int timeElapsed) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE, true))) {
            writer.write(playerName + "," + score + "," + moves + "," + timeElapsed);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getScores() {
        List<String> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores;
    }

    public static List<String[]> getSortedScores() {
        List<String> rawScores = getScores();
        List<String[]> sortedScores = new ArrayList<>();

        for (String score : rawScores) {
            sortedScores.add(score.split(","));
        }

        sortedScores.sort((a, b) -> Integer.compare(Integer.parseInt(b[1]), Integer.parseInt(a[1])));
        return sortedScores;
    }
}
