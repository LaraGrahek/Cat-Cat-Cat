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
    int n = 0;

    Start(){
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
        ImageIcon image=new ImageIcon(getClass().getResource("bg.png"));
        Image pic = image.getImage();
        Image newPic = pic.getScaledInstance(800, 600,Image.SCALE_DEFAULT);
        image = new ImageIcon(newPic);
        startLabel=new JLabel(image);
        startLabel.setSize(800,600);
        startLabel.setLayout(null);
        startPanel.add(startLabel);
        //creates and adds start button
        JButton play=new JButton(new ImageIcon(getClass().getResource("IMG_7154.GIF")));
        setButton(play);
        play.setBounds(256,230,287,111);
        //creates and adds how to play button
        JButton howTo=new JButton(new ImageIcon(getClass().getResource("IMG_7155.GIF")));
        setButton(howTo);
        howTo.setBounds(148,350,504,123);
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

    private ImageIcon showImage(String image){
        ImageIcon pic = new ImageIcon(getClass().getResource(image));
        Image newPic = pic.getImage();
        Image newNamePic = newPic.getScaledInstance(800, 600, Image.SCALE_DEFAULT);
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
        JLabel nameLabel = new JLabel(showImage("bg.png"));
        nameLabel.setSize(800, 600);
        JButton butt =new JButton(new ImageIcon(getClass().getResource("IMG_7154.GIF")));
        setButton(butt);
        butt.setBounds(300,490,200,50);

        //makes character select buttons
        JButton leftBut = new JButton(new ImageIcon(getClass().getResource("arrowLef.png")));
        JButton rightBut = new JButton(new ImageIcon(getClass().getResource("arrowRigh.png")));
        setButton(leftBut);
        setButton(rightBut);
        leftBut.setBounds(200,200,90,100);
        rightBut.setBounds(500,200,70,100);

        //adds everything to name panel
        namePanel.add(leftBut);
        namePanel.add(rightBut);
        namePanel.add(butt);
        namePanel.add(nameLabel);

        //character select
        JPanel charPanel = new JPanel();
        charPanel.setBounds(300,30,190,350);
        charPanel.setLayout(null);
        String[] cats = {"kiki.png","brown.png","greay.png","green.png"};
        JLabel charLabel = new JLabel(showImage(cats[n]));
        charLabel.setBounds(0,0,150,300);
        charPanel.add(charLabel);
        leftBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                n-=1;
                if (n < 0){
                    n = 3;
                }
                charLabel.setIcon(showImage(cats[n]));
            }
        });
        rightBut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                n+=1;
                if (n > 3){
                    n = 0;
                }
                charLabel.setIcon(showImage(cats[n]));
            }
        });

        //username input
        JPanel textPanel = new JPanel();
        textPanel.setBounds(200,400,350,75);
        textPanel.setLayout(null);
        JTextField inputField = new JTextField(15);
        inputField.setBounds(20,10,300,50);
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
                JButton three = new JButton(new ImageIcon(getClass().getResource("three.png")));
                JButton five = new JButton(new ImageIcon(getClass().getResource("five.png")));
                JButton seven = new JButton(new ImageIcon(getClass().getResource("seven.png")));
                setButton(three);
                setButton(five);
                setButton(seven);
                three.setBounds(100,400,90,90);
                five.setBounds(300,400,90,90);
                seven.setBounds(500,400,90,90);
                roundsPanel.add(three);
                roundsPanel.add(five);
                roundsPanel.add(seven);
                JLabel roundLabel = new JLabel(showImage("arrowRigh.png"));
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
        instructionDialog.setPreferredSize(new Dimension(450, 260)); //size of dialog
        instructionDialog.setLayout(new BorderLayout());
        JLabel label=new JLabel(showImage("bg.png"));
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
            case 0: rpsLabel.setIcon(showImage("kikBg.png"));
                break;
            case 1: rpsLabel.setIcon(showImage("brownBg.png"));
                break;
            case 2: rpsLabel.setIcon(showImage("greyBg.png"));
                break;
            case 3: rpsLabel.setIcon(showImage("greenBg.png"));
                break;
        }
        rpsLabel.setSize(800, 600);

        //instantiation a rock paper scissors object
        Rps rockPaperScissors = new Rps(frame, rpsLabel, rpsPanel, rounds, u);
    }
}

