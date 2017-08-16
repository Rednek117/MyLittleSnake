import javax.swing.*;
import java.awt.*;

public class MainField extends JFrame {
    public MainField(){
        setTitle("Snake");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(320, 320);
        setLocation(400,400);
        add(new GameField());
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        MainField mf = new MainField();
    }
}
