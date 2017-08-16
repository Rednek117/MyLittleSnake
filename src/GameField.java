

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener{
    private final int size = 320;
    private final int dot_size = 16;
    private final int all_dots = 400;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[all_dots - 1];
    private int[] y = new int[all_dots - 1 ];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean alive = true;



    public GameField(){
        setBackground(Color.black);
        imagesLoad();
        initGame();
       addKeyListener( new KeyGameField());
       setFocusable(true);
    }

    public void initGame(){
        dots = 3;
        for(int i = 0; i < dots; i++){
            x[i] = 48 - i * dot_size;
            y[i] = 48;
        }
        timer = new Timer(250, this);
        timer.start();
        makeApples();
    }

    public void makeApples(){
        appleX = new Random().nextInt(20) * dot_size;
        appleY = new Random().nextInt(20) * dot_size;
    }

    public void imagesLoad(){
        ImageIcon imageApple = new ImageIcon("apple.png");
        apple = imageApple.getImage();
        ImageIcon imageDot = new ImageIcon("dot.png");
        dot = imageDot.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(alive){
            g.drawImage(apple,appleX,appleY,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot,x[i],y[i],this);

            }
        }else{
            String gameover = "Wasted";

            g.setColor(Color.white);
            g.drawString(gameover, 130,size/2);
        }
    }

    public void moving(){
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
            if(right) {
                x[0] += dot_size;
            }
            if(left) {
                x[0] -= dot_size;
            }
            if(up) {
                y[0] -= dot_size;
            }
            if(down) {
                y[0] += dot_size;
            }
    }

    public void cheakApple(){
        if(x[0]==appleX && y[0] ==appleY){
            dots++;
            makeApples();
        }
    }

    public void cheakCollision(){
        for (int i = dots; i > 0; i--) {
            if(i > 4 && x[0] == x[i] && y[0] == y[i]){
                 alive = false;
            }
        }
        if(x[0] > size){
            alive = false;
        }
        if(x[0] < 0){
            alive = false;
        }
        if(y[0] > size){
            alive = false;
        }
        if(y[0] < 0){
            alive = false;
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(alive){
            moving();
            cheakApple();
            cheakCollision();
        }
        repaint();
    }

    class KeyGameField extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_UP && !down){
                left = false;
                right = false;
                up = true;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                left = false;
                right = false;
                down = true;
            }
        }
    }
}
