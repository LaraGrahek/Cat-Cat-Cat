import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
//import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
public class End {
    JFrame frame;
    JPanel endPanel;
    JLabel endLabel;
    String username;
    String t;
    String u;
    int points;
    int place;
    JPanel startPanel;
    End(JFrame f, String s, int p, JPanel c){
        frame = f;
        username = s;
        points = p;
        startPanel = c;

        place = -1;
        String[] oldUsers = new String[10];
        String[] newUsers = new String[10];

        endPanel = new JPanel();
        endPanel.setBounds(0,0,800,600);
        endPanel.setLayout(null);
        ImageIcon pic = new ImageIcon(getClass().getResource("startbut.png"));
        Image newPic = pic.getImage();
        Image newNamePic = newPic.getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        pic = new ImageIcon(newNamePic);
        endLabel = new JLabel(pic);

        JLabel resultLabel = new JLabel(username+":  "+points+" pts");
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        resultLabel.setBounds(300,50,250,90);

        JButton restart = new JButton(new ImageIcon(getClass().getResource("bg.png")));
        restart.setBounds(400, 100, 100, 75);
        endPanel.add(restart);

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startPanel.setVisible(true);
                endPanel.setVisible(false);
            }
        });

        JLabel nameLabel = new JLabel("p", SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        nameLabel.setBounds(250,300,100,150);
        JLabel pointLabel = new JLabel("p", SwingConstants.CENTER);
        pointLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        pointLabel.setBounds(400,300,50,150);

        StringBuilder nameText = new StringBuilder("<html>");
        StringBuilder pointText = new StringBuilder("<html>");

        //puts current top 5 players (username and points) in the array
        try {
            FileReader fr = new FileReader("Leaderboard");
            BufferedReader br = new BufferedReader(fr);
            for (int n = 0; n<10; n++){
                t = br.readLine();
                oldUsers[n] = t;
                if ((n % 2 != 0) && (place == -1)) {
                    if (!t.equals("null")) {
                        if (points > Integer.parseInt(t)) {
                            place = n;
                        }
                    }
                    else{
                        place = n;
                    }
                }
            }
            br.close();
        }
        catch (IOException i){
            System.out.print("error "+i);
        }
        System.out.println(place);

        //the player got on the leaderboard/is in the top 5
        if (place != -1) {
            for (int d = 0; d<place-1; d++){
                newUsers[d] = oldUsers[d];
            }
            newUsers[place-1] = username;
            newUsers[place] = String.valueOf(points);
            for (int d = (place+1); d<10; d++){
                newUsers[d] = oldUsers[d-2];
            }
            //put the top 5 into the leaderboard file to save it
            try {
                // input the (modified) file content to the StringBuffer "input"
                BufferedReader file = new BufferedReader(new FileReader("Leaderboard"));
                StringBuffer inputBuffer = new StringBuffer();
                String line;

                for (int j = 0; j<10; j++){
                    line = newUsers[j];
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                }
                file.close();

                // write the new string with the replaced line OVER the same file
                FileOutputStream fileOut = new FileOutputStream("Leaderboard");
                fileOut.write(inputBuffer.toString().getBytes());
                fileOut.close();
            } catch (IOException i) {
                System.err.println("Error: " + i);
            }
            for (int l = 0; l < newUsers.length; l ++) {
                if (newUsers[l] != null && !newUsers[l].equals("null")) {
                    if (l%2==0) {
                        nameText.append("<br/>").append(newUsers[l]);
                    } else{
                        pointText.append("<br/>").append(newUsers[l]);
                    }
                }
            }
        }
        else{
            for (int l = 0; l < oldUsers.length; l ++) {
                if (oldUsers[l] != null && !oldUsers[l].equals("null")) {
                    if (l%2==0) {
                        nameText.append("<br/>").append(oldUsers[l]);
                    } else{
                        pointText.append("<br/>").append(oldUsers[l]);
                    }
                }
            }
        }
        nameText.append("</html>");
        pointText.append("</html>");

        // Update the labels after the loop
        nameLabel.setText(nameText.toString());
        pointLabel.setText(pointText.toString());

        endPanel.add(resultLabel);
        endPanel.add(nameLabel);
        endPanel.add(pointLabel);
        endPanel.add(endLabel);
        endPanel.setVisible(true);

        frame.add(endPanel);

    }
}
