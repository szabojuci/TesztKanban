import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PathGeneratorDisplay extends JPanel {

    private List<Point> pathPoints;
    private final int NUM_POINTS = 50; // A pálya pontjainak száma
    private final int MAX_STEP = 30;    // Maximális lépésméret egy pont generálásakor

    public PathGeneratorDisplay() {
        setPreferredSize(new Dimension(800, 600)); // Az ablak preferált mérete
        setBackground(Color.LIGHT_GRAY);
        generatePath();
    }

    private void generatePath() {
        pathPoints = new ArrayList<>();
        Random rand = new Random();

        // Kezdőpont a panel közepén
        int currentX = getPreferredSize().width / 2;
        int currentY = getPreferredSize().height / 2;

        pathPoints.add(new Point(currentX, currentY));

        for (int i = 1; i < NUM_POINTS; i++) {
            // Véletlenszerű elmozdulás
            int dx = rand.nextInt(2 * MAX_STEP) - MAX_STEP; // -MAX_STEP és +MAX_STEP között
            int dy = rand.nextInt(2 * MAX_STEP) - MAX_STEP;

            currentX += dx;
            currentY += dy;

            // Koordináták korlátozása, hogy a panelen belül maradjanak
            currentX = Math.max(0, Math.min(currentX, getPreferredSize().width));
            currentY = Math.max(0, Math.min(currentY, getPreferredSize().height));

            pathPoints.add(new Point(currentX, currentY));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Antialiasing a simább vonalakért
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(3)); // Vastagabb vonal

        if (pathPoints != null && pathPoints.size() > 1) {
            // A pontok összekötése vonalakkal
            for (int i = 0; i < pathPoints.size() - 1; i++) {
                Point p1 = pathPoints.get(i);
                Point p2 = pathPoints.get(i + 1);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }

            // Pontok megjelenítése (opcionális)
            g2d.setColor(Color.RED);
            for (Point p : pathPoints) {
                g2d.fillOval(p.x - 4, p.y - 4, 8, 8); // Kis körök a pontoknál
            }
        }
    }

    public static void main(String[] args) {
        // Swing GUI létrehozása az Event Dispatch Thread-en
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pályagenerálás és megjelenítés");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // A generált pályát megjelenítő panelt hozzáadjuk az ablakhoz
            PathGeneratorDisplay displayPanel = new PathGeneratorDisplay();
            frame.add(displayPanel);
            
            frame.pack(); // Az ablak méretét a tartalomhoz igazítja
            frame.setLocationRelativeTo(null); // Középre helyezi az ablakot
            frame.setVisible(true); // Láthatóvá teszi az ablakot
        });
    }
}
