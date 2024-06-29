package memorygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    private JTextField nameField;

    public MainMenuPanel(JFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#EBF4F6")); // Background color

        JLabel welcomeLabel = new JLabel("Welcome to Memory Game");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        welcomeLabel.setForeground(Color.decode("#071952")); // Primary color
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel("Enter your name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        nameLabel.setForeground(Color.decode("#071952")); // Primary color
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 24));
        nameField.setMaximumSize(new Dimension(400, 50));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.setBackground(Color.decode("#088395")); // Secondary color
        startButton.setForeground(Color.WHITE);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton leaderboardButton = new JButton("View Leaderboard");
        leaderboardButton.setFont(new Font("Arial", Font.PLAIN, 24));
        leaderboardButton.setBackground(Color.decode("#37B7C3")); // Accent color
        leaderboardButton.setForeground(Color.WHITE);
        leaderboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        exitButton.setBackground(Color.decode("#071952")); // Primary color
        exitButton.setForeground(Color.WHITE);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String playerName = nameField.getText().trim();
                if (!playerName.isEmpty()) {
                    frame.getContentPane().removeAll();
                    frame.add(new GamePanel(frame, playerName));
                    frame.revalidate();
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please enter your name.");
                }
            }
        });

        leaderboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(new LeaderboardPanel(frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(Box.createVerticalGlue());
        add(welcomeLabel);
        add(Box.createVerticalStrut(20));
        add(nameLabel);
        add(Box.createVerticalStrut(10));
        add(nameField);
        add(Box.createVerticalStrut(20));
        add(startButton);
        add(Box.createVerticalStrut(10));
        add(leaderboardButton);
        add(Box.createVerticalStrut(10));
        add(exitButton);
        add(Box.createVerticalGlue());
    }
}
