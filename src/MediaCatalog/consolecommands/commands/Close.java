package MediaCatalog.consolecommands.commands;

import java.util.List;

import MediaCatalog.consolecommands.Operation;

public class Close  implements Operation {
    @Override
    public String getName() {
        return "close";
    }
    //what to do if user called help for this command
    @Override
    public void showHelp() {
        System.out.println("close the app");
    }


    @Override
    public void execute(List<String> params) {
        System.exit(0);
    }

}
