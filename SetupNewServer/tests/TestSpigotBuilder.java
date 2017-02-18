import java.io.IOException;

public class TestSpigotBuilder {
    public static void main (String[] args) {
        SpigotBuilder sb = new SpigotBuilder("latest");
        try {
            sb.buildLatest(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
