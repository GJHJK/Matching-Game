package memorygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private List<Card> cards;
    private Card selectedCard1;
    private Card selectedCard2;
    private int score;
    private int moves;
    private int wins;
    private JLabel scoreLabel;
    private JLabel movesLabel;
    private JLabel winsLabel;
    private JLabel playerNameLabel;
    private JLabel timerLabel;
    private Timer timer;
    private int timeElapsed;
    private JFrame frame;
    private String playerName;

    public GamePanel(JFrame frame, String playerName) {
        this.frame = frame;
        this.playerName = playerName;
        this.wins = 0;
        this.timeElapsed = 0;
        initializeGamePanel();
        startTimer();
    }

    private void initializeGamePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#EBF4F6")); // Background color

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        headerPanel.setPreferredSize(new Dimension(1920, 100));
        headerPanel.setBackground(Color.decode("#071952")); // Primary color

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);

        movesLabel = new JLabel("Moves: 0");
        movesLabel.setFont(new Font("Arial", Font.BOLD, 24));
        movesLabel.setForeground(Color.WHITE);

        winsLabel = new JLabel("Wins: " + wins);
        winsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        winsLabel.setForeground(Color.WHITE);

        playerNameLabel = new JLabel("Player: " + playerName);
        playerNameLabel.setFont(new Font("Arial", Font.BOLD, 24));
        playerNameLabel.setForeground(Color.WHITE);

        timerLabel = new JLabel("Time: 0s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.WHITE);

        JButton resetButton = new JButton("Reset Game");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 24));
        resetButton.setBackground(Color.decode("#088395")); // Secondary color
        resetButton.setForeground(Color.WHITE);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        JButton stopButton = new JButton("Stop Game");
        stopButton.setFont(new Font("Arial", Font.PLAIN, 24));
        stopButton.setBackground(Color.decode("#37B7C3")); // Accent color
        stopButton.setForeground(Color.WHITE);
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                frame.add(new MainMenuPanel(frame));
                frame.revalidate();
                frame.repaint();
            }
        });

        headerPanel.add(Box.createHorizontalGlue());
        headerPanel.add(scoreLabel);
        headerPanel.add(Box.createHorizontalStrut(20));
        headerPanel.add(movesLabel);
        headerPanel.add(Box.createHorizontalStrut(20));
        headerPanel.add(winsLabel);
        headerPanel.add(Box.createHorizontalStrut(20));
        headerPanel.add(playerNameLabel);
        headerPanel.add(Box.createHorizontalStrut(20));
        headerPanel.add(timerLabel);
        headerPanel.add(Box.createHorizontalStrut(20));
        headerPanel.add(resetButton);
        headerPanel.add(Box.createHorizontalStrut(20));
        headerPanel.add(stopButton);
        headerPanel.add(Box.createHorizontalGlue());

        JPanel gameBoard = new JPanel();
        gameBoard.setLayout(new GridLayout(4, 4));
        gameBoard.setBackground(Color.decode("#EBF4F6")); // Background color
        cards = new ArrayList<>();

        // Initialize cards with images
        for (int i = 1; i <= 8; i++) {
            cards.add(new Card(i));
            cards.add(new Card(i));
        }

        // Shuffle cards
        Collections.shuffle(cards);

        for (Card card : cards) {
            gameBoard.add(card);
            card.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    handleCardSelection(card);
                }
            });
        }

        add(headerPanel, BorderLayout.NORTH);
        add(gameBoard, BorderLayout.CENTER);
    }

    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeElapsed++;
                timerLabel.setText("Time: " + timeElapsed + "s");
            }
        });
        timer.start();
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }

    private void handleCardSelection(Card card) {
        if (card.isMatched()) {
            return;
        }

        if (selectedCard1 == null) {
            selectedCard1 = card;
            card.showCard();
        } else if (selectedCard2 == null && card != selectedCard1) {
            selectedCard2 = card;
            card.showCard();
            checkMatch();
        }
    }

    private void checkMatch() {
        if (selectedCard1.getId() == selectedCard2.getId()) {
            score++;
            scoreLabel.setText("Score: " + score);
            selectedCard1.setMatched(true);
            selectedCard2.setMatched(true);
            selectedCard1 = null;
            selectedCard2 = null;
            if (score == 8) {
                wins++;
                winsLabel.setText("Wins: " + wins);
                endGame();
            }
        } else {
            Timer timer = new Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selectedCard1.hideCard();
                    selectedCard2.hideCard();
                    selectedCard1 = null;
                    selectedCard2 = null;
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
        moves++;
        movesLabel.setText("Moves: " + moves);
    }

    private void endGame() {
        stopTimer();
        ScoreManager.saveScore(playerName, score, moves, timeElapsed);
        frame.getContentPane().removeAll();
        frame.add(new EndGamePanel(this, frame, score, moves, timeElapsed));
        frame.revalidate();
        frame.repaint();
    }

    public void resetGame() {
        score = 0;
        moves = 0;
        timeElapsed = 0;
        scoreLabel.setText("Score: " + score);
        movesLabel.setText("Moves: " + moves);
        timerLabel.setText("Time: 0s");
        startTimer();
        removeAll();
        initializeGamePanel();
        revalidate();
        repaint();
    }
}
