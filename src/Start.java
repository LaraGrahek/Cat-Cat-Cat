import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
//import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Start extends JPanel {
    JFrame frame;
    JPanel startPanel;
    JLabel startLabel;
    String username = null;
    int n;

    Start(){
        n = 0;

        //creates frame
        frame=new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(800,600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        startPanel=new JPanel();
        startPanel.setLayout(null);
        startPanel.setSize(800,600);
        //creates and adds the start background
        startLabel=new JLabel(showImage("bg1.png",800,600));
        startLabel.setSize(800,600);
        startLabel.setLayout(null);
        startPanel.add(startLabel);
        //creates and adds start button
        JButton play=new JButton(showImage("play1.jpg",272,111));
        setButton(play);
        play.setBounds(256,300,272,111);
        //creates and adds how to play button
        JButton howTo=new JButton(showImage("instr1.jpg",309,89));
        setButton(howTo);
        howTo.setBounds(235,430,309,89);
        startLabel.add(play);
        startLabel.add(howTo);
        frame.add(startPanel);
        frame.setVisible(true);
        play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        howTo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    howToPlay();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    //sets buttons in constructor
    private void setButton(JButton button) {
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

    private ImageIcon showImage(String image, int w, int h){
        ImageIcon pic = new ImageIcon(getClass().getResource(image));
        Image newPic = pic.getImage();
        Image newNamePic = newPic.getScaledInstance(w, h, Image.SCALE_DEFAULT);
        return new ImageIcon(newNamePic);
    }

    //goes through username entering then starts rock paper scissors game
    public void startGame() {
        //start screen disappears and background for username entering appears
        startPanel.setVisible(false);

        JPanel namePanel = new JPanel();
        namePanel.setSize(800, 600);
        namePanel.setLayout(null);

        //makes background and confirm button
        JLabel nameLabel = new JLabel(showImage("choose1.PNG",800,600));
        nameLabel.setSize(800, 600);
        JButton butt =new JButton(showImage("next1.jpg",170,73));
        setButton(butt);
        butt.setBounds(600,500,170,73);

        //makes character select buttons
        JButton leftBut = new JButton(showImage("left1.jpg",73,107));
        JButton rightBut = new JButton(showImage("right1.jpg",72,106));
        setButton(leftBut);
        setButton(rightBut);
        leftBut.setBounds(170,200,73,107);
        rightBut.setBounds(555,200,72,106);

        //adds everything to name panel
        namePanel.add(leftBut);
        namePanel.add(rightBut);
        namePanel.add(butt);
        namePanel.add(nameLabel);

        //character select
        JPanel charPanel = new JPanel();
        charPanel.setBounds(257,70,285,415);
        charPanel.setLayout(null);
        String[] cats = {"kiki1.jpg","rosy1.jpg","keisha1.jpg","coco1.jpg"};
        JLabel charLabel = new JLabel(showImage(cats[n],285,415));
        charLabel.setBounds(0,0,285,415);
        charPanel.add(charLabel);
        leftBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                n-=1;
                if (n < 0){
                    n = 3;
                }
                charLabel.setIcon(showImage(cats[n],285,415));
            }
        });
        rightBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                n+=1;
                if (n > 3){
                    n = 0;
                }
                charLabel.setIcon(showImage(cats[n],285,415));
            }
        });

        //username input
        JPanel textPanel = new JPanel();
        textPanel.setBounds(260,510,280,40);
        textPanel.setLayout(null);
        JTextField inputField = new JTextField(15);
        inputField.setBounds(0,0,280,40);
        inputField.setFont(new Font("SansSerif", Font.BOLD, 20));
        textPanel.add(inputField);

        //add the background and text input panels to the frame
        frame.add(charPanel);
        frame.add(textPanel);
        frame.add(namePanel);

        butt.addActionListener(new ActionListener() { //the button from the text input is clicked
            public void actionPerformed(ActionEvent e) {
                //array stuff
                System.out.println(inputField.getText());
                username = inputField.getText();

                //set the panels with character and text to invisible
                charPanel.setVisible(false);
                namePanel.setVisible(false);
                textPanel.setVisible(false);

                //number of rounds select
                JPanel roundsPanel = new JPanel();
                roundsPanel.setBounds(0,0,800,600);
                roundsPanel.setLayout(null);
                JButton three = new JButton(showImage("three1.jpg",162,183));
                JButton five = new JButton(showImage("five1.jpg",162,183));
                JButton seven = new JButton(showImage("seven1.jpg",162,183));
                setButton(three);
                setButton(five);
                setButton(seven);
                three.setBounds(100,350,162,183);
                five.setBounds(300,350,162,183);
                seven.setBounds(500,350,162,183);
                roundsPanel.add(three);
                roundsPanel.add(five);
                roundsPanel.add(seven);
                JLabel roundLabel = new JLabel(showImage("rounds1.png",800,600));
                roundLabel.setBounds(0,0,800,600);
                roundsPanel.add(roundLabel);
                frame.add(roundsPanel);
                three.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        roundsPanel.setVisible(false);
                        startRps(3, username);
                    }
                });
                five.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        roundsPanel.setVisible(false);
                        startRps(5, username);
                    }
                });
                seven.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        roundsPanel.setVisible(false);
                        startRps(7, username);
                    }
                });
            }
        });
    }

    public void howToPlay() throws IOException {
        //dialog pop up with different panel and an image on it that is the instructions
        JPanel instructionPanel=new JPanel(new FlowLayout());
        JDialog instructionDialog = new JDialog(frame, "How To Play", false);
        instructionDialog.setPreferredSize(new Dimension(368, 618)); //size of dialog
        instructionDialog.setLayout(new BorderLayout());
        JLabel label=new JLabel(showImage("howto1.png",368,618));
        instructionPanel.add(label);
        instructionDialog.add(instructionPanel, BorderLayout.CENTER);
        instructionDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        instructionDialog.pack();
        instructionDialog.setLocationRelativeTo(null);
        instructionDialog.setVisible(true);

    }

    public void startRps(int rounds, String u){
        JPanel rpsPanel = new JPanel();
        rpsPanel.setSize(800, 600);
        rpsPanel.setLayout(null);

        //set the rock paper scissors background
        JLabel rpsLabel = new JLabel();
        switch (n){
            case 0: rpsLabel.setIcon(showImage("bgkiki.png",800,600 ));
                break;
            case 1: rpsLabel.setIcon(showImage("bgrosy.png",800,600));
                break;
            case 2: rpsLabel.setIcon(showImage("bgkeisha.png",800,600));
                break;
            case 3: rpsLabel.setIcon(showImage("bgcoco.png",800,600));
                break;
        }
        rpsLabel.setSize(800, 600);

        //instantiation a rock paper scissors object
        Rps rockPaperScissors = new Rps(frame, rpsLabel, rpsPanel, rounds, u, startPanel);
    }
}

