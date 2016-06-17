package MediaCatalog.consolecommands.commands;

import java.util.ArrayList;
import java.util.List;

import MediaCatalog.Utils.RepFactory;
import MediaCatalog.consolecommands.Operation;
import MediaCatalog.services.RepException;

public class Search implements Operation {
    @Override
    public String getName() {
        return "search";
    }
    //what to do if user called help for this command
    @Override
    public void showHelp() {
        System.out.println("search { text }");
    }

    @Override
    public void execute(List<String> params) {
        List<String> files = new ArrayList<>();
        if (!params.isEmpty()){
            try {
                files = RepFactory.getInstance().search(params.get(0));
            } catch (RepException e) {
                System.err.println("Error searching: "+e.getMessage());
            }
        } else {
            System.err.println("parametr empty");
        }
        for (String file:files){
            System.out.println(file);
        }
    }
}