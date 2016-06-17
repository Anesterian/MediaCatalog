package MediaCatalog.consolecommands.commands;

import java.util.List;

import MediaCatalog.consolecommands.Operation;

public class Help implements Operation {
    private List<Operation> operations;
    @Override
    public  String getName() {
        return "help";
    }
    public Help(List<Operation> operations){
        this.operations=operations;
    }
    @Override
    public void showHelp() {
        System.out.println("Show help info");
    }


    @Override
    public void execute(List<String> params) {
        System.out.println("input \"{name command} help\" to show info");
        System.out.print("Operations: ");
        for (Operation operation : operations){
            System.out.print(operation.getName());
            System.out.print(" ");
        }



    }


}