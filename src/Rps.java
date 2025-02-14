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
    String username;
    int rounds;
    int user = -1;
    int computer = -1;
    JLabel result;
    Rps(JFrame fram, JLabel rpsLabel, JPanel rpsPanel, int n, String t){
        frame = fram;
        label = rpsLabel;
        panel = rpsPanel;
        username = t;
        rounds = n;

        //put number of rounds in corner
        JPanel rPanel = new JPanel();
        rPanel.setBounds(600,0,200,70);
        rPanel.setLayout(null);
        JLabel rLabel = new JLabel();
        rLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        rLabel.setSize(200,70);
        rPanel.add(rLabel);
        frame.add(rPanel);
        // add background to the screen after the number of rounds because of layering
        panel.add(label);
        frame.add(panel);

        //label for results of each round (win, tie, or loss)
        result = new JLabel();
        result.setBounds(300,100,300,150);
        result.setLayout(null);
        panel.add(result);
        result.setVisible(false);

        JButton rock = new JButton(new ImageIcon(getClass().getResource("startbut.png")));
        rock.setBounds(400, 500, 90, 75);
        label.add(rock);
        JButton paper = new JButton(new ImageIcon(getClass().getResource("startbut.png")));
        paper.setBounds(500, 500, 90, 75);
        label.add(paper);
        JButton scissors = new JButton(new ImageIcon(getClass().getResource("startbut.png")));
        scissors.setBounds(600, 500, 90, 75);
        label.add(scissors);
        System.out.println("ow");
        rLabel.setText("rounds left: " + Integer.toString(rounds));

        //generates random number from 1-3, the number correspond with rock, paper, and scissors
        int randNum = ThreadLocalRandom.current().nextInt(1, 4);

        //rock, paper, or scissors are clicked
        rock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selection(1, true);
                System.out.println("between");
                selection(randNum, false);
            }
        });
        paper.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setVisible(false);
                selection(2, true);
                System.out.println("between");
                selection(randNum, false);
            }
        });
        scissors.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selection(3, true);
                System.out.println("between");
                selection(randNum, false);
            }
        });
    }

    private void selection(int n, boolean player) {
        JLabel decision = new JLabel();
        //assigns appropriate gif to the label based on the user's choice (rock, paper, or scissors)
        if (n == 1) {
            decision.setIcon(showImage("bg.png",400,600));
        } else if (n == 2){
            decision.setIcon(showImage("bg.png",400,600));
        } else{
            decision.setIcon(showImage("bg.png",400,600));
        }
        if (player){
            decision.setBounds(0,0,400,600);
            user = n;
        } else {
            decision.setBounds(400,0,400,600);
            computer = n;
        }
        label.setVisible(false);
        decision.setVisible(true);
        panel.add(decision);

        System.out.println("before if in method");
        if (!(user == -1 || computer == -1)){
            JButton continueBut = new JButton (new ImageIcon(getClass().getResource("green.png")));
            continueBut.setBounds(50,100,100,100);
            result.add(continueBut);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("decisisiison");
            result.setVisible(true);
            if (user == computer){
                result.setIcon(showImage("tiez.png",300,150));
            } else if ((user == 1 && computer == 2) || (user == 2 && computer == 3) || (user == 3 && computer == 1)){
                result.setIcon(showImage("losez.png",300,150));
            } else{
                result.setIcon(showImage("winz.png",300,150));
            }
            continueBut.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    user = -1;
                    computer = -1;
                    rounds--;
                }
            });
        }
        System.out.println("after if in method");
    }

    private ImageIcon showImage(String image, int w, int h){
        ImageIcon pic = new ImageIcon(getClass().getResource(image));
        Image newPic = pic.getImage();
        Image newNamePic = newPic.getScaledInstance(w, h, Image.SCALE_DEFAULT);
        return new ImageIcon(newNamePic);
    }

}
