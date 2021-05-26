package utils;

import javax.swing.*;
import java.awt.*;

public class Utils {
    public static ImageIcon transformImg(ImageIcon imgIcon, int width, int heigth){
        Image image = imgIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(width, heigth,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imgIcon = new ImageIcon(newimg);
        return imgIcon;
    }

}
