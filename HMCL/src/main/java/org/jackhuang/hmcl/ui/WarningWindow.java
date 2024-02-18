package org.jackhuang.hmcl.ui;

import javax.sound.sampled.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class WarningWindow {
    JFrame frame;
    JLabel label, label2;
    boolean isRed = true;
    
    public static void main(String[] args) {
        create();
    }

    public static void create(){
        playSound();
        WarningWindow colorfulWindow = new WarningWindow();
        colorfulWindow.createWindow();
    }

    public static void playSound(){
        try {
            URL url = WarningWindow.class.getResource("/warn.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    private void createWindow() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 200);
        frame.setLayout(null);
        // set frame undecorated
        frame.setUndecorated(true);
        
        label = new JLabel("孩子，你的游戏崩溃了，你先别急", SwingConstants.CENTER);
        label2 = new JLabel("不要截图这个界面，你应该将日志文件发送给他人", SwingConstants.CENTER);


        label.setFont(new Font("default", Font.BOLD, 40));
        label.setBounds(0, 55, 900, 40);
        label2.setFont(new Font("default", Font.BOLD, 30));
        label2.setBounds(0, 105, 900, 40);

        frame.add(label);
        frame.add(label2);
        frame.setVisible(true);
        
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeColor();
            }
        });
        
        timer.start();
    }
    
    private void changeColor() {
        // get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth() - 600;
        int height = (int) screenSize.getHeight() - 200;
        // random
        int randomX = (int) (Math.random() * width);
        int randomY = (int) (Math.random() * height);
        frame.setBounds(randomX,randomY,900,200);

        if(isRed) {
            frame.getContentPane().setBackground(Color.RED);
            label.setForeground(Color.BLACK);
            label2.setForeground(Color.BLACK);
        } else {
            frame.getContentPane().setBackground(Color.BLACK);
            label.setForeground(Color.RED);
            label2.setForeground(Color.RED);
        }
        isRed = !isRed;
    }
}