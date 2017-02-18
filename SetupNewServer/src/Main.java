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
        System.out.println("Enter profile name: ");
        String profileName = input.nextLine().replace("\n", "").replace("\r", "").trim();
        //Get vendor
        System.out.println("Enter Vendor: ");
        for (int i = 0; i < vendors.length; i++)
        {
            System.out.println(i + 1 + ") " + vendors[i] + vendorNotes[i]);
        }
        int vendorIndex = Integer.parseInt(input.nextLine()) - 1;
        String vendor = vendors[vendorIndex];
        //Get server version
        System.out.println("Enter the server version: ");
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
                System.out.println("[#][Already Found MineCraft Server Version " + version + " From " + vendor + "]");
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
        System.out.println("[+][Downloading MineCraft server version " + version + " from " + vendor + "]");
        if (vendor.equals("Mojang"))
        {
            URL website = new URL("https://s3.amazonaws.com/Minecraft.Download/versions/" + version + "/minecraft_server." + version + ".jar");
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(presetsFolderPath + vendor + "\\" + version + ".jar");
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        else
        {
            System.out.println("[!][" + vendor + " is not yet implemented]");
        }
    }
}
