//To be expanded
public class Utils
{
    //Makes a dynamically-sized table
    public static String makeTable (String title, String question, String[] items)
    {
        String createdTable = title;
        int longestStr = question.length();
        for (String item : items)
        {
            if (item.length() + 4 > longestStr)
            {
                longestStr = item.length() + 4;
            }
        }
        createdTable += (("\n+" + String.format("%-" + (longestStr + 2) + "s", "") + "+").replace(" ", "-"));
        createdTable += ("\n| " + String.format("%-" + (longestStr) + "s", question) + " |");
        createdTable += (("\n+" + String.format("%-" + (longestStr + 2) + "s", "") + "+").replace(" ", "-"));
        int itemIndex = 0;
        for (String item : items)
        {
            itemIndex += 1;
            createdTable += (String.format("%-" + (longestStr + 3) + "s", "\n| " + itemIndex + " | " + item) + " |");
        }
        createdTable += (("\n+" + String.format("%-" + (longestStr + 2) + "s", "") + "+").replace(" ", "-"));
        return createdTable;
    }
    //Makes a dynamically-sized input table
    public static String makeInputTable (String title, String question, String[] items)
    {
        return makeTable(title, question, items) + "\n[#]> ";
    }

    //Makes a dynamically-sized input table with just a question
    public static String makeInputTable (String question)
    {
        String toReturn = (("+" + String.format("%-" + (question.length() + 2) + "s", "") + "+").replace(" ", "-")) + "\n";
        toReturn = toReturn + "| " + question + " |\n" + toReturn + "[#]> ";
        return toReturn;
    }

    //Makes a dynamically-sized box win info inside
    public static String makeBox (String title, String... inBox)
    {
        String createdTable = title;
        int longestStr = 0;
        for(String item : inBox)
        {
            if(item.length() > longestStr)
            {
                longestStr = item.length();
            }
        }
        createdTable += (("\n+" + String.format("%-" + (longestStr + 2) + "s", "") + "+").replace(" ", "-"));
        for(String item : inBox)
        {
            createdTable += ("\n| " + String.format("%-" + (longestStr) + "s", item) + " |");
        }
        createdTable += (("\n+" + String.format("%-" + (longestStr + 2) + "s", "") + "+").replace(" ", "-"));
        return createdTable;
    }
}
