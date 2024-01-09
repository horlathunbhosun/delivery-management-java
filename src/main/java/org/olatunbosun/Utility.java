package org.olatunbosun;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Utility {



    public static JLabel createClickableLinkLabel(String text) {
        JLabel linkLabel = new JLabel();
        linkLabel.setText("<html><u>" + text + "</u></html>");
        linkLabel.setForeground(Color.BLUE);
        linkLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        return linkLabel;
    }


    public static Image getScaledImage(Image srcImg, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImg.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(srcImg, 0, 0, width, height, null);
        g2d.dispose();

        return resizedImg;
    }
}
