package memorygame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LeaderboardPanel extends JPanel {
    public LeaderboardPanel(JFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Leaderboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(20));

        List<String[]> scores = ScoreManager.getSortedScores();

        for (String[] score : scores) {
            JLabel scoreLabel = new JLabel("Player: " + score[0] + ", Score: " + score[1] + ", Moves: " + score[2] + ", Time: " + score[3] + "s");
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
            scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(scoreLabel);
            add(Box.createVerticalStrut(10));
        }

        JButton backButton = new JButton("Back to Menu");
        backButton.setFont(new Font("Arial", Font.PLAIN, 24));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            frame.getContentPane().removeAll();
            frame.add(new MainMenuPanel(frame));
            frame.revalidate();
            frame.repaint();
        });

        add(Box.createVerticalGlue());
        add(backButton);
        add(Box.createVerticalGlue());
    }
}
