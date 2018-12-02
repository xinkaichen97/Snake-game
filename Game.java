import javafx.scene.media.AudioClip;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener{

    private int[] snakeX = new int[750];
    private int[] snakeY = new int[750];

    private boolean right = false;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private ImageIcon rightHead;
    private ImageIcon leftHead;
    private ImageIcon upHead;
    private ImageIcon downHead;

    private int[] cakeX = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475,
            500, 525, 550, 575, 600, 625, 650, 675, 700, 725, 750, 775, 800, 825, 850};
    private int[] cakeY = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 450, 475,
            500, 525, 550, 575, 600, 625};

    private ImageIcon cakeImg;

    private Random random = new Random();

    private int xPos = random.nextInt(34);
    private int yPos = random.nextInt(23);

    private Timer timer;
    private int delay = 100;

    private int moves = 0;

    private int score = 0;

    private boolean isOver = false;

    private int snakelength = 3;

    // sound
    //private AudioInputStream inputStream;
    //private Clip clip;

    private ImageIcon snakeImg;
    private ImageIcon titleImg;

    public Game(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
        //URL url = SoundTest.class.getResource("back.wav");
        //AudioClip music = Applet.newAudioClip("D:\Java\snake.wav");
        //music.play();
        //URL url = getClass().getResource("back.wav");
        //inputStream = AudioSystem.getAudioInputStream(url);
        //clip.open(inputStream);
        //clip.loop(10);


    }

    public void paint(Graphics graf){

        if(moves == 0){
            snakeX[0] = 100;
            snakeX[1] = 75;
            snakeX[2] = 50;

            snakeY[0] = 100;
            snakeY[1] = 100;
            snakeY[2] = 100;
        }

        graf.setColor(Color.WHITE);
        graf.drawRect(24, 10, 851, 55);

        titleImg = new ImageIcon("title2.jpg");
        titleImg.paintIcon(this, graf, 25,11);

        graf.setColor(Color.WHITE);
        graf.drawRect(24, 74, 851, 577);

        graf.setColor(Color.BLACK);
        graf.fillRect(25, 75, 850, 575);

        // draw the score
        graf.setColor(Color.WHITE);
        graf.setFont(new Font("Georgia Bold", Font.BOLD, 12));
        graf.drawString("Kashish has done", 758, 30);
        graf.setFont(new Font("Georgia Bold", Font.BOLD, 16));
        if(score == 0 || score == 1){
            graf.drawString("" + score + "    cake!!", 785, 53);
        }
        if(score >= 2 && score < 10)
        {
            graf.drawString("" + score + "    cakes!", 785, 53);
        }
        if(score >= 10 && score < 50){
            graf.drawString("" + score + "   cakes!", 785, 53);
        }
        if(score >= 50){
            graf.setColor(Color.ORANGE);
            graf.setFont(new Font("Georgia Bold", Font.BOLD, 18));
            graf.drawString("" + score + "   cakes!", 775, 53);
        }

        rightHead = new ImageIcon("kas_right1.png");
        rightHead.paintIcon(this, graf, snakeX[0], snakeY[0]);

        for(int i = 0; i < snakelength; i++){
            if(i == 0){
                if(right){
                    rightHead = new ImageIcon("kas_right1.png");
                    rightHead.paintIcon(this, graf, snakeX[i], snakeY[i]);
                }
                if(left){
                    leftHead = new ImageIcon("kas_left1.png");
                    leftHead.paintIcon(this, graf, snakeX[i], snakeY[i]);
                }
                if(up){
                    upHead = new ImageIcon("kas_up1.png");
                    upHead.paintIcon(this, graf, snakeX[i], snakeY[i]);
                }
                if(down){
                    downHead = new ImageIcon("kas_down1.png");
                    downHead.paintIcon(this, graf, snakeX[i], snakeY[i]);
                }
            }

            if(i != 0){
                snakeImg = new ImageIcon("snakebody0.png");
                snakeImg.paintIcon(this, graf, snakeX[i], snakeY[i]);
            }
        }

        cakeImg = new ImageIcon("cake.png");

        if(cakeX[xPos] == snakeX[0] && cakeY[yPos] == snakeY[0]){
            snakelength ++;
            score += 1;
            xPos = random.nextInt(34);
            yPos = random.nextInt(23);
        }

        cakeImg.paintIcon(this, graf, cakeX[xPos], cakeY[yPos]);

        for(int pos = 1; pos < snakelength; pos++){
            if(snakeX[pos] == snakeX[0] && snakeY[pos] == snakeY[0]){
                right = false;
                left = false;
                up = false;
                down = false;
                isOver = true;

                graf.setColor(Color.GRAY);
                graf.setFont(new Font("Book Antiqua Bold", Font.BOLD, 80));
                graf.drawString("Game Over", 250, 300);

                graf.setColor(Color.CYAN);
                graf.setFont(new Font("Gill Sans MT Bold", Font.BOLD, 30));
                if(score < 10){
                    graf.drawString("No! Kashish is starving!", 318, 360);
                }
                if(score >= 10 && score < 50){
                    graf.drawString("Come on! Kashish is still hungry!", 260, 360);
                }
                if(score >= 50 && score < 70){
                    graf.drawString("Well done! Kashish is 60% full!", 275, 360);
                }
                if(score >= 70 && score < 100){
                    graf.drawString("Wonderful! Kashish is almost full!", 256, 360);
                }
                if(score >= 100) {
                    graf.drawString("Fabulous! Kashish is full AF!! ", 290, 360);
                }
                graf.drawString("Enter to restart", 357, 400);
            }
        }

        graf.dispose();
    }

    public void actionPerformed(ActionEvent event){
        if(score >= 10){
            timer.setDelay(60);
        }
        if(score >= 20){
            timer.setDelay(80);
        }
        if(score >= 25){
            timer.setDelay(40);
        }
        if(score >= 30){
            timer.setDelay(60);
        }
        if(score >= 50){
            timer.setDelay(70);
        }
        if(score >= 55){
            timer.setDelay(50);
        }
        if(score >= 70){
            timer.setDelay(40);
        }
        if(score >= 80){
            timer.setDelay(60);
        }
        timer.start();



        if(right){
            for(int r = snakelength - 1; r >= 0; r--){
                snakeY[r + 1] = snakeY[r];
            }
            for(int r = snakelength; r >= 0; r--){
                if(r == 0){
                    snakeX[r] += 25;
                }
                else{
                    snakeX[r] = snakeX[r - 1];
                }
                if(snakeX[r] > 850){
                   snakeX[r] = 25;
                }

            }
            repaint();
        }

        if(left){
            for(int r = snakelength - 1; r >= 0; r--){
                snakeY[r + 1] = snakeY[r];
            }
            for(int r = snakelength; r >= 0; r--){
                if(r == 0){
                    snakeX[r] -= 25;
                }
                else{
                    snakeX[r] = snakeX[r - 1];
                }
                if(snakeX[r] < 25){
                    snakeX[r] = 850;
                }

            }
            repaint();
        }

        if(up){
            for(int r = snakelength - 1; r >= 0; r--){
                snakeX[r + 1] = snakeX[r];
            }
            for(int r = snakelength; r >= 0; r--){
                if(r == 0){
                    snakeY[r] -= 25;
                }
                else{
                    snakeY[r] = snakeY[r - 1];
                }
                if(snakeY[r] < 75){
                    snakeY[r] = 625;
                }

            }
            repaint();
        }

        if(down){
            for(int r = snakelength - 1; r >= 0; r--){
                snakeX[r + 1] = snakeX[r];
            }
            for(int r = snakelength; r >= 0; r--){
                if(r == 0){
                    snakeY[r] += 25;
                }
                else{
                    snakeY[r] = snakeY[r - 1];
                }
                if(snakeY[r] > 625){
                    snakeY[r] = 75;
                }

            }
            repaint();
        }

    }

    public void keyTyped(KeyEvent event){
    }

    public void keyReleased(KeyEvent event){
    }

    public void keyPressed(KeyEvent event){

        if(event.getKeyCode() == KeyEvent.VK_SPACE){
            right = false;
            left = false;
            up = false;
            down = false;
        }

        if(event.getKeyCode() == KeyEvent.VK_ENTER){
            moves = 0;
            score = 0;
            snakelength = 3;
            isOver = false;
            repaint();
            timer.setDelay(100);
            xPos = random.nextInt(34);
            yPos = random.nextInt(23);
        }

        if(event.getKeyCode() == KeyEvent.VK_RIGHT && !isOver){
            moves ++;
            if(!left){
                right = true;
            }
            else{
                right = false;
                left = true;
            }

            up = false;
            down = false;
        }

        if(event.getKeyCode() == KeyEvent.VK_LEFT && !isOver){
            moves ++;
            if(!right){
                left = true;
            }
            else{
                left = false;
                right = true;
            }

            up = false;
            down = false;
        }

        if(event.getKeyCode() == KeyEvent.VK_UP && !isOver){
            moves++ ;
            if(!down){
                up = true;
            }
            else{
                up = false;
                down = true;
            }

            right = false;
            left = false;
        }

        if(event.getKeyCode() == KeyEvent.VK_DOWN && !isOver){
            moves ++;
            if(!up){
                down = true;
            }
            else{
                down = false;
                up = true;
            }

            right = false;
            left = false;
        }
    }
}
