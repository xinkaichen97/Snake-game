import javax.swing.*;
import java.awt.*;

public class main {

    public static void main(String[] args){
        JFrame win = new JFrame();
        Game gameplay = new Game();

        win.setBounds(0 ,0, 905, 700);
        win.setResizable(false);
        win.setBackground(Color.lightGray);
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.add(gameplay);


    }
}
