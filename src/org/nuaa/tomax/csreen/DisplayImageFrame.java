package org.nuaa.tomax.csreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2018/5/28 11:20
 */
public class DisplayImageFrame extends JFrame {

    private static Integer FRAME_WIDTH = 1000;
    private static Integer FRAME_HEIGHT = 800;

    public DisplayImageFrame() throws HeadlessException {
    }

    public DisplayImageFrame(CaptureScreen screen){
        this.setLayout(new GridLayout(2,1,10,30));
        JLabel imageBox = new JLabel();
        ImageIcon image = screen.getPickedIcon();
        imageBox.setIcon(image);
        imageBox.setSize(300*image.getIconWidth()/FRAME_WIDTH,300*image.getIconHeight()/FRAME_HEIGHT);
        this.add(imageBox);
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(new GridLayout(2,5,10,30));
        List<String> labelList = LoadProperties.getLabels();
        for (String in : labelList){
            JButton btn = new JButton(in);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(e.getActionCommand());
                    outputImage(screen,e.getActionCommand());
                }
            });
            btn.setSize(40,20);
            panel.add(btn);
        }
        Button cancel = new Button("exit");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disposeThis();
            }
        });
        panel.add(cancel);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    public void disposeThis(){
        this.dispose();
    }
    public void outputImage(CaptureScreen screen, String labelName){
        String path = LoadProperties.getSavePath();
        File file = new File(path+"/"+labelName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        if (!file.exists()){
            file.mkdir();
        }
        int num = file.listFiles().length + 1;
        path += "/"+labelName+"/"+num+".png";
        screen.save(path);
        dispose();
    }
}
