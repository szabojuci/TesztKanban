import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;

public class AssetManager {

    private final Map<String, BufferedImage> images = new HashMap<>();
    private final Map<String, Clip> audioClips = new HashMap<>();

    public void loadImage(String path, String name) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            images.put(name, image);
            System.out.println("Kép betöltve: " + name + " (" + path + ")");
        } catch (IOException e) {
            System.err.println("Hiba a kép betöltésekor " + name + " (" + path + "): " + e.getMessage());
        }
    }

    public BufferedImage getImage(String name) {
        return images.get(name);
    }

    public void loadAudio(String path, String name) {
        try {
            File audioFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            audioClips.put(name, clip);
            System.out.println("Hanganyag betöltve: " + name + " (" + path + ")");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Hiba a hanganyag betöltésekor " + name + " (" + path + "): " + e.getMessage());
        }
    }

    public void playAudio(String name) {
        Clip clip = audioClips.get(name);
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0); // Vissza az elejére
            clip.start();
            System.out.println("Hanganyag lejátszása: " + name);
        } else {
            System.err.println("Nem található hanganyag: " + name);
        }
    }

    public void stopAudio(String name) {
        Clip clip = audioClips.get(name);
        if (clip != null && clip.isRunning()) {
            clip.stop();
            System.out.println("Hanganyag megállítva: " + name);
        }
    }
}
