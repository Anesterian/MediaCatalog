package MediaCatalog.consolecommands.commands;

import java.util.List;

import MediaCatalog.consolecommands.Operation;

public class Delete implements Operation {
    @Override
    public String getName() {
        return "delete";
    }

    @Override
    public void showHelp() {
        System.out.println("delete {path to media}");
    }


    @Override
    public void execute(List<String> params) {
        Media media= new Media();
        if (!params.isEmpty()){
            try {
                media.setPath(params.get(0));
                StorageFactory.getInstance().delete(media);
                System.out.println("media file deleted");
            } catch (StorageException e) {
                System.err.println(e.getMessage());
            }
        }else{
            System.err.println("Params empty");
        }
    }
}
