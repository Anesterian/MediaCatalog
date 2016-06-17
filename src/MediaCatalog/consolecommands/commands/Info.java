package MediaCatalog.consolecommands.commands;

import java.util.ArrayList;
import java.util.List;

import MediaCatalog.Utils.RepFactory;
import MediaCatalog.consolecommands.Operation;
import MediaCatalog.services.RepException;

public class Info implements Operation {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public void showHelp() {
        System.out.println("write 'info' or 'info favorite' if you want see list media files");
    }

    @Override
    public void execute(List<String> params) {
        List<String> files = new ArrayList<>();
        try {
            if (!params.isEmpty()&&params.get(0).equals("favorite")){
                files = RepFactory.getInstance().getInfoOfFavoriteMedia();
            } else {
                files = RepFactory.getInstance().getInfoOfAllMedia();
            }
        } catch (RepException e) {
            System.err.println(e.getMessage());
        }
        for (String file:files){
            System.out.println(file);
        }
    }
}
