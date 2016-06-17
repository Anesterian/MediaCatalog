package MediaCatalog.Utils;

import java.util.ArrayList;
import java.util.List;

public class CommandUtils {
    public static List<String> extractParametrs(String command){
        List<String> params = new ArrayList<String>();

        String tmp[] = command.split(" ");
        for (int i = 1; i<tmp.length;i++){
            params.add(tmp[i]);
        }

        return  params;
    };
    public static String extractName(String command){
        if (command.indexOf(' ')==-1)
            return command;
        return command.substring(0,command.indexOf(' '));
    }
}