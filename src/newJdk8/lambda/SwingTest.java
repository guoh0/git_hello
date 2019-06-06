package newJdk8.lambda;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingTest {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("my JFrame");
        JButton jButton = new JButton("my JButton");

//        jButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Button Pressed");
//            }
//        });

        // -> 箭头符号,分割左边和右边
        //参数:(param1 , param2 ,param3) -> {执行体}
//        jButton.addActionListener((ActionEvent event)-> {
        jButton.addActionListener(event -> {
            System.out.println("Button Pressed");
            System.out.println("hello word");
        });

        jFrame.add(jButton);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
