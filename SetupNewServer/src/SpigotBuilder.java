import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpigotBuilder {
    private String version;

    public SpigotBuilder(String version) {
        this.version = version;
    }

    public void buildLatest() throws IOException{
        buildLatest(false);
    }

    public void buildLatest(boolean debugMode) throws IOException{
        Runtime rt = Runtime.getRuntime();
        Process pr = rt.exec("java -jar Servers/BuildTools/BuildTools.jar --rev " + this.version);
        if (debugMode) {
            new Thread(() -> {
                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line = null;

                try {
                    while ((line = input.readLine()) != null)
                        System.out.println(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            try {
                pr.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
