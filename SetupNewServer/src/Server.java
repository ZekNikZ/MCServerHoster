public class Server
{
    private String type;
    private String version;

    public Server (String type, String version)
    {
        this.type = type;
        this.version = version;
    }

    public String getVersion ()
    {
        return version;
    }

    public String getType ()
    {
        return type;
    }
}