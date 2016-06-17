package MediaCatalog.consolecommands;

import java.util.List;

//interface needed for commands to work properly
public interface Operation {
    String getName();
    void showHelp();
    void execute(List<String> params);
}
