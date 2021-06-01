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
    public static String formatNameToUsername(String name){
        String[] nameTokens = name.split(" ");
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < nameTokens.length - 1; i++)
            builder.append(nameTokens[i].substring(0, 1).toLowerCase());
        builder.append(nameTokens[nameTokens.length - 1].toLowerCase());
        return VNCharacterUtils.removeAccent(builder.toString());
    }
    public static void changeDaysInMonth(JComboBox month, int days){
        month.removeAllItems();
        for(int i = 1; i <= days; i++)
            month.addItem(i);
    }

    public static void main(String[] args){
        System.out.println(formatNameToUsername("Thoi Hai Duc"));
    }
}
