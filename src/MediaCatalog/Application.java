package MediaCatalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import MediaCatalog.Utils.CommandUtils;
import MediaCatalog.consolecommands.Operation;
import MediaCatalog.consolecommands.commands.Close;
import MediaCatalog.consolecommands.commands.Delete;
import MediaCatalog.consolecommands.commands.Favorite;
import MediaCatalog.consolecommands.commands.Get;
import MediaCatalog.consolecommands.commands.Help;
import MediaCatalog.consolecommands.commands.Info;
import MediaCatalog.consolecommands.commands.Move;
import MediaCatalog.consolecommands.commands.Put;
import MediaCatalog.consolecommands.commands.Search;

public class Application {
    private static List<Operation> init(){
        List<Operation> operations = new ArrayList<Operation>(); //initializing list of operations
        operations.add(new Put());
        operations.add(new Get());
        operations.add(new Delete());
        operations.add(new Info());
        operations.add(new Search());
        operations.add(new Move());
        operations.add(new Favorite());
        operations.add(new Close());
        operations.add(new Help(operations));
        return operations;       //operations

    }
    public static void main(String[] args) {

        List<Operation> operations = init();       //initializing List of operations
        Scanner scanner = new Scanner(System.in);  //creating scanner for commands
        while (true){
            System.out.print("\n>:");
            String inputedCommand = scanner.nextLine();      //scanning input string
            for (Operation operation : operations){
                if (operation.getName().equals(CommandUtils.extractName(inputedCommand))){
                    List<String> params = CommandUtils.extractParametrs(inputedCommand);
                    if (params.size()>0&&params.get(0).equals("help"))
                        operation.showHelp();
                    else
                        operation.execute(params);
                }
            }
        }
    }
}