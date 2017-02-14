import java.util.Scanner;

public class Main
{
    public static final String mainFolderPath = "C:\\Users\\calin\\Desktop\\Servers";
    public static Scanner input;

    public static void main(String[] args)
    {
        input = new Scanner(System.in);
        System.out.println("Enter profile name: ");
        String profileName = input.nextLine().replace("\n", "").replace("\r", "").trim();

    }


    //Both smaller -- current
    //Adjacent is larger, return that index
    //Choose right side if both are greater
    //Avoid Bounds Errors

    public static boolean CreateProfile(String profileName)
    {
        profileName = profileName.toLowerCase().trim().replace("\n", "").replace("\r", "");
        return false;
    }

    public static boolean CreateNewServer(String profileName, String type, String version)
    {
        //Profile names are guaranteed to only contain letters, numbers and underscores.
        profileName = profileName.toLowerCase().trim().replace("\n", "").replace("\r", "");
        String profilePath = mainFolderPath + "\\" + profileName;
        //Server newServer = new Server(type, version);
        return false;
    }
}
