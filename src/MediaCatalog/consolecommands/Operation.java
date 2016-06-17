package MediaCatalog.consolecommands;

import java.util.List;


public interface Operation {
    String getName();
    void showHelp();
    void execute(List<String> params);
}
