import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
//import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Rps {
    JFrame frame;
    JLabel label;
    JPanel panel;
    JPanel rPanel;
    JPanel pPanel;
    JLabel rLabel;
    JLabel pLabel;
    JLabel playerdec;
    JLabel compdec;
    String username;
    int rounds;
    int points;
    int user;
    int computer;
    JLabel result;
    JPanel startPanel;
    Rps(JFrame fram, JLabel rpsLabel, JPanel rpsPanel, int n, String t, JPanel p){
        user = -1;
        computer = -1;
        points = 0;

        frame = fram;
        label = rpsLabel;
        panel = rpsPanel;
        username = t;
        rounds = n;
        startPanel = p;

        //put number of rounds in corner
        rPanel = new JPanel();
        rPanel.setBounds(580,20,190,70);
        rPanel.setLayout(null);
        rLabel = new JLabel("l", SwingConstants.CENTER);
        rLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        rLabel.setSize(190,70);
        rLabel.setText("rounds left: " + Integer.toString(rounds));
        rPanel.add(rLabel);
        frame.add(rPanel);
        //put points in other corner
        pPanel = new JPanel();
        pPanel.setBounds(20,20,120,70);
        pPanel.setLayout(null);
        pLabel = new JLabel("l", SwingConstants.CENTER);
        pLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        pLabel.setSize(120,70);
        pLabel.setText(Integer.toString(points) + " pts");
        pPanel.add(pLabel);
        frame.add(pPanel);
        // add background to the screen after the number of rounds because of layering
        panel.add(label);
        frame.add(panel);

        //label for results of each round (win, tie, or loss)
        result = new JLabel();
        result.setBounds(260,0,280,200);
        result.setLayout(null);
        result.setOpaque(false);
        panel.add(result);
        result.setVisible(false);

        playerdec = new JLabel();
        compdec = new JLabel();
        panel.add(playerdec);
        panel.add(compdec);

        JButton rock = new JButton(showImage("rock1.jpg",131,129));
        rock.setBounds(250, 400, 131, 129);
        label.add(rock);
        JButton paper = new JButton(showImage("paper1.jpg",130,128));
        paper.setBounds(430, 400, 130, 128);
        label.add(paper);
        JButton scissors = new JButton(showImage("scissors1.jpg",131,128));
        scissors.setBounds(600, 400, 131, 128);
        label.add(scissors);

        //rock, paper, or scissors are clicked
        rock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selection(1, true, playerdec);
                selection(ThreadLocalRandom.current().nextInt(1, 4), false, compdec);
            }
        });
        paper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
                selection(2, true, playerdec);
                selection(ThreadLocalRandom.current().nextInt(1, 4), false, compdec);
            }
        });
        scissors.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selection(3, true, playerdec);
                selection(ThreadLocalRandom.current().nextInt(1, 4), false, compdec);
            }
        });
    }

    private void selection(int n, boolean player, JLabel decision) {
        //assigns appropriate gif to the label based on the user's choice (rock, paper, or scissors)
        if (n == 1) {
            decision.setIcon(showImage("actualrock.png",400,600));
        } else if (n == 2){
            decision.setIcon(showImage("actualpap.png",400,600));
        } else{
            decision.setIcon(showImage("actualsciss.png",400,600));
        }
        if (player){
            decision.setBounds(0,0,400,600);
            user = n;
        } else {
            decision.setBounds(400,0,400,600);
            computer = n;
        }
        pPanel.setVisible(false);
        rPanel.setVisible(false);
        label.setVisible(false);
        decision.setVisible(true);

        if (!(user == -1 || computer == -1)){
            result.setVisible(true);
            if (user == computer){
                result.setIcon(showImage("youtie.jpg",280,134));
            } else if ((user == 1 && computer == 2) || (user == 2 && computer == 3) || (user == 3 && computer == 1)){
                result.setIcon(showImage("youlose.jpg",280,134));
                points -= 1;
            } else{
                result.setIcon(showImage("youwin.jpg",280,134));
                points+=1;
            }
            // waits 1.5 seconds until it switches back to the other label
            Timer timer = new Timer(1500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    user = -1;
                    computer = -1;
                    rounds--;

                    if (rounds == 0) { //the player has gone through all their rounds
                        rPanel.setVisible(false);
                        pPanel.setVisible(false);
                        panel.setVisible(false);
                        End endscreen = new End(frame, username, points, startPanel);
                    } else { //the player still has to finsih the rounds
                        rLabel.setText("rounds left: " + rounds);
                        pLabel.setText(points + " pts");
                        label.setVisible(true);
                        rPanel.setVisible(true);
                        pPanel.setVisible(true);
                        rPanel.revalidate();
                        rPanel.repaint();
                        pPanel.revalidate();
                        pPanel.repaint();
                        result.setVisible(false);
                        decision.setVisible(false);
                    }
                }
            });
            timer.setRepeats(false); // Ensure it only runs once
            timer.start(); // Start the timer
        }
    }

    private ImageIcon showImage(String image, int w, int h){
        ImageIcon pic = new ImageIcon(getClass().getResource(image));
        Image newPic = pic.getImage();
        Image newNamePic = newPic.getScaledInstance(w, h, Image.SCALE_FAST);
        return new ImageIcon(newNamePic);
    }

}
