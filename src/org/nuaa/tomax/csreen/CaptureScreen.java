package org.nuaa.tomax.csreen;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2018/5/27 22:58
 */
public class CaptureScreen {
    private Robot robot = null;

    private static CaptureScreen captureScreen = null;

    private BackgroundImage fullScreenBackground;
    private JDialog dialog;

    private BufferedImage fullScreenImage;
    private BufferedImage pickedImage;

    private int x1, y1, x2, y2;
    private int recX, recY, recH, recW;
    private boolean isFirstPoint;

    private CaptureScreen(){
        try {
            robot = new Robot();
            fullScreenBackground = new BackgroundImage();
            dialog = new JDialog();
            isFirstPoint = true;
        } catch (AWTException e) {
            e.printStackTrace();
        }
        JPanel cp = (JPanel) dialog.getContentPane();
        cp.setLayout(new BorderLayout());


        fullScreenBackground.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evn) {
                isFirstPoint = true;
                pickedImage = fullScreenImage.getSubimage(recX, recY, recW,
                        recH);
                dialog.setVisible(false);
            }
        });
        fullScreenBackground.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent evn) {
                if (isFirstPoint){
                    x1 = evn.getX();
                    y1 = evn.getY();
                    isFirstPoint = false;
                } else {
                    x2 = evn.getX();
                    y2 = evn.getY();
                    int maxX = Math.max(x1, x2);
                    int maxY = Math.max(y1, y2);
                    int minX = Math.min(x1, x2);
                    int minY = Math.min(y1, y2);
                    recX = minX;
                    recY = minY;
                    recW = maxX - minX;
                    recH = maxY - minY;
                    fullScreenBackground.drawRectangle(recX, recY, recH, recW);
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                fullScreenBackground.drawCross(e.getX(), e.getY());
            }
        });
        cp.add(BorderLayout.CENTER, fullScreenBackground);
        dialog.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        dialog.setAlwaysOnTop(true);
        dialog.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        dialog.setUndecorated(true);
        dialog.setSize(dialog.getMaximumSize());
        dialog.setModal(true);
    }
    public void initialize(){
        x1 = 0;
        x2 = 0;
        y1 = 0;
        y2 = 0;
        recX = 0;
        recY = 0;
        recW = 0;
        recH = 0;
        if (fullScreenBackground != null){
            fullScreenBackground.initialize();
        }
    }

    public static CaptureScreen getInstance(){
        if (captureScreen == null){
            captureScreen = new CaptureScreen();
        }
        return captureScreen;
    }

    /**
     * 捕捉屏幕
     * @return
     */
    public Icon captureScreenImage(){
        fullScreenImage = robot.createScreenCapture(new Rectangle(Toolkit
                .getDefaultToolkit().getScreenSize()));
        ImageIcon icon = new ImageIcon(fullScreenImage);
        return icon;
    }

    /**
     * 捕捉区域
     */
    public void captureImage() {
        fullScreenImage = robot.createScreenCapture(new Rectangle(Toolkit
                .getDefaultToolkit().getScreenSize()));
        ImageIcon icon = new ImageIcon(fullScreenImage);
        fullScreenBackground.setIcon(icon);
        dialog.setVisible(true);
    }


    public BufferedImage getPickedImage() {
        return pickedImage;
    }
    public ImageIcon getPickedIcon() {
        return new ImageIcon(getPickedImage());
    }
    public void save(String path){
        File file = new File(path);
        try {
            ImageIO.write(getPickedImage(),"png",file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void process(){
        CaptureScreen capture = CaptureScreen.getInstance();
        capture.captureImage();
        DisplayImageFrame frame = new DisplayImageFrame(capture);
        capture.initialize();
    }

}
