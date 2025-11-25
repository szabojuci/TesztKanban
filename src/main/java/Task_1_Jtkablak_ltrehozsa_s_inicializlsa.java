import javax.swing.JFrame;

public class GameWindow {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Játékablak");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Középre igazítás
        frame.setVisible(true);
    }
}
