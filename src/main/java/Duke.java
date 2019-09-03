import Duke.Commands.Command;
import Duke.Exception.DukeException;
import Duke.Parser.Parser;
import Duke.Storage;
import Duke.Task.TaskList;
import Duke.Ui;

import java.io.File;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;


    public Duke(File filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.load();
    }

    public static void main(String[] args) {
        File currentDir = new File(System.getProperty("user.dir"));
        File filePath = new File(currentDir.toString() + "\\src\\main\\data\\duke.txt");
        new Duke(filePath).run();
    }

    public void run() {
        boolean isExit = false;

        ui.showWelcome();

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e){
                ui.setMessage("     Invalid Command\n");
            } finally {
                ui.showLine();
            }
        }
    }
}