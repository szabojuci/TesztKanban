import javax.swing.*;
import java.awt.*;

public class UISketchGenerator {
    public static void createAndShowUISketch() {
        JFrame frame = new JFrame("UI Vázlat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JLabel titleLabel = new JLabel("Alkalmazás Címe");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        panel.add(titleLabel);

        JTextField inputField = new JTextField(20);
        inputField.setText("Írj ide valamit...");
        panel.add(inputField);

        JButton actionButton = new JButton("Művelet Végrehajtása");
        panel.add(actionButton);

        JLabel infoLabel = new JLabel("Ez egy információs szöveg.");
        infoLabel.setForeground(Color.GRAY);
        panel.add(infoLabel);
        
        frame.add(panel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UISketchGenerator::createAndShowUISketch);
    }
}
