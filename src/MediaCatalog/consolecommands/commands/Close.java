package MediaCatalog.consolecommands.commands;

import java.util.List;

import MediaCatalog.consolecommands.Operation;

public class Close  implements Operation {
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public void showHelp() {
        System.out.println("exit from app");
    }


    @Override
    public void execute(List<String> params) {
        System.exit(0);
    }

}
