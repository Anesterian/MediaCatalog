package MediaCatalog.consolecommands.commands;

import java.util.List;

import MediaCatalog.MediaFiles;
import MediaCatalog.Utils.RepFactory;
import MediaCatalog.consolecommands.Operation;
import MediaCatalog.services.RepException;

public class Favorite implements Operation{
    @Override
    public String getName() {
        return "favorite";
    }

    @Override
    public void showHelp() {
        System.out.println("favorite {add or remove} {path to media}");
    }

    @Override
    public void execute(List<String> params) {
        if (!params.isEmpty()&&params.size()==2){
            MediaFiles media = new MediaFiles();
            media.setPath(params.get(1));
            try {
                if (params.get(0).equals("add")){
                    RepFactory.getInstance().addToFavorite(media);
                }else if (params.get(0).equals("remove")){
                    RepFactory.getInstance().removeFromFavorite(media);
                } else {
                    showHelp();
                }
            } catch (RepException e) {
                    System.err.println(e.getMessage());
            }
        }else {
            System.err.println("not found parametrs");
        }
    }
}
