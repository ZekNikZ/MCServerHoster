import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static final String mainFolderPath = "Servers\\";
    private static final String presetsFolderPath = mainFolderPath + "Presets\\";
    private static final String profilesFolderPath = mainFolderPath + "Profiles\\";
    private static String[] vendors = {"Mojang", "Spigot", "Mod Packs"};
    private static String[] vendorNotes = {" - In Testing", " - Not Yet Implemented", " - Not Yet Implemented"};

    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        //Get profile name
        System.out.print(Utils.makeInputTable("Enter profile name"));
        String profileName = input.nextLine().replace("\n", "").replace("\r", "").trim();
        //Get vendor
        System.out.print(Utils.makeInputTable("", "Enter a vendor", vendors));
        int vendorIndex = Integer.parseInt(input.nextLine()) - 1;
        String vendor = vendors[vendorIndex];
        //Get server version
        System.out.print("\n" + Utils.makeInputTable("Enter server version"));
        String version = input.nextLine().replace("\n", "").replace("\r", "").trim();
        verifyPresetDownload(vendor, version);
        File outputPath = new File(profilesFolderPath + "\\" + profileName + "\\" + version);
        outputPath.mkdirs();
        File outputFilePath = new File(profilesFolderPath + "\\" + profileName + "\\" + version + "\\" + version + ".jar");
        Files.copy(new File(presetsFolderPath + vendor + "\\" + version + ".jar").toPath(), outputFilePath.toPath()); //TODO: Catch already created server
    }

    //Verify that the given server version from given vendor is already downloaded; if not, do so
    public static void verifyPresetDownload(String vendor, String version) throws IOException //TODO: Get a better name
    {
        File versionList = new File(presetsFolderPath + vendor + "\\" + "versionList.txt");
        List<String> versions = Files.readAllLines(Paths.get(versionList.toString()), StandardCharsets.UTF_8);
        //Check if jar is already downloaded
        for(String str : versions)
        {
            if (str.equals(version))
            {
                //If jar is already downloaded, say so, and return
                System.out.println(Utils.makeBox("", "Already Found MineCraft Server Version " + version + " From " + vendor));
                return;
            }
        }
        //If jar is not already downloaded, download it
        downloadNewPreset(vendor, version);
        //Add new jar version to versionList.txt
        PrintWriter versionListWriter = new PrintWriter(versionList);
        versions.add(version);
        for(String str : versions)
        {
            versionListWriter.println(str);
        }
        versionListWriter.close();
    }

    public static void downloadNewPreset(String vendor, String version) throws IOException //TODO: Add downloads for non-mojang
    {
        System.out.println(Utils.makeBox("", "Downloading MineCraft server version " + version + " from " + vendor));
        if (vendor.equals("Mojang"))
        {
            URL website = new URL("https://s3.amazonaws.com/Minecraft.Download/versions/" + version + "/minecraft_server." + version + ".jar");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(presetsFolderPath + vendor + "\\" + version + ".jar");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        else if(vendor.equals("Spigot"))
        {

        }
        else
        {
            System.out.println(Utils.makeBox("", vendor + " is not yet implemented"));
        }
    }
}
