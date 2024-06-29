package memorygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGamePanel extends JPanel {
    public EndGamePanel(GamePanel gamePanel, JFrame frame, int score, int moves, int timeElapsed) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#EBF4F6")); // Background color

        JLabel thankYouLabel = new JLabel("Thank you for playing Memory Matching Game!");
        thankYouLabel.setFont(new Font("Arial", Font.BOLD, 24));
        thankYouLabel.setForeground(Color.decode("#071952")); // Primary color
        thankYouLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel scoreLabel = new JLabel("Your Score: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.decode("#071952")); // Primary color
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel movesLabel = new JLabel("Total Moves: " + moves);
        movesLabel.setFont(new Font("Arial", Font.BOLD, 24));
        movesLabel.setForeground(Color.decode("#071952")); // Primary color
        movesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel timeLabel = new JLabel("Time Elapsed: " + timeElapsed + " seconds");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timeLabel.setForeground(Color.decode("#071952")); // Primary color
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.setFont(new Font("Arial", Font.PLAIN, 24));
        playAgainButton.setBackground(Color.decode("#088395")); // Secondary color
        playAgainButton.setForeground(Color.WHITE);
        playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePanel.resetGame();
                frame.getContentPane().removeAll();
                frame.add(gamePanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        JButton backToMenuButton = new JButton("Back to Main Menu");
        backToMenuButton.setFont(new Font("Arial", Font.PLAIN, 24));
        backToMenuButton.setBackground(Color.decode("#37B7C3")); // Accent color
        backToMenuButton.setForeground(Color.WHITE);
        backToMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backToMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(new MainMenuPanel(frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        exitButton.setBackground(Color.decode("#071952")); // Primary color
        exitButton.setForeground(Color.WHITE);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(Box.createVerticalGlue());
        add(thankYouLabel);
        add(Box.createVerticalStrut(20));
        add(scoreLabel);
        add(Box.createVerticalStrut(20));
        add(movesLabel);
        add(Box.createVerticalStrut(20));
        add(timeLabel);
        add(Box.createVerticalStrut(20));
        add(playAgainButton);
        add(Box.createVerticalStrut(20));
        add(backToMenuButton);
        add(Box.createVerticalStrut(20));
        add(exitButton);
        add(Box.createVerticalGlue());
    }
}
