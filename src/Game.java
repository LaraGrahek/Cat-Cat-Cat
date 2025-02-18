import javax.swing.*;
import java.awt.*;

public class Game  {


    protected ImageIcon showImage(String image, int w, int h){
        ImageIcon pic = new ImageIcon(getClass().getResource(image));
        Image newPic = pic.getImage();
        Image newNamePic = newPic.getScaledInstance(w, h, Image.SCALE_FAST);
        return new ImageIcon(newNamePic);
    }

    //sets buttons in constructor
    protected void setButton(JButton button) {
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
    }

}
