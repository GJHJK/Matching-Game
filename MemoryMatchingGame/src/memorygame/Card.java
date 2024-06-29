package memorygame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Card extends JButton {
    private int id;
    private boolean faceUp;
    private boolean matched;
    private ImageIcon frontImage;
    private ImageIcon backImage;

    public Card(int id) {
        this.id = id;
        this.faceUp = false;
        this.matched = false;
        this.frontImage = loadImageIcon("/images/img" + id + ".png");
        this.backImage = loadImageIcon("/images/back.png");
        hideCard();
        setPreferredSize(new Dimension(100, 100));
        setBackground(Color.decode("#EBF4F6")); // Background color
    }

    private ImageIcon loadImageIcon(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public void showCard() {
        if (!matched) {
            setIcon(frontImage);
            faceUp = true;
            animateFlip();
        }
    }

    public void hideCard() {
        if (!matched) {
            setIcon(backImage);
            faceUp = false;
            animateFlip();
        }
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
        if (matched) {
            setIcon(frontImage);
        }
    }

    public boolean isMatched() {
        return matched;
    }

    private void animateFlip() {
        Timer timer = new Timer(10, new ActionListener() {
            int scale = faceUp ? 0 : 100;

            public void actionPerformed(ActionEvent e) {
                if (faceUp) {
                    scale += 10;
                } else {
                    scale -= 10;
                }
                setSize(new Dimension(scale, getHeight()));
                revalidate();
                repaint();
                if (scale >= 100 || scale <= 0) {
                    ((Timer) e.getSource()).stop();
                    setSize(new Dimension(100, getHeight()));
                }
            }
        });
        timer.start();
    }
}
