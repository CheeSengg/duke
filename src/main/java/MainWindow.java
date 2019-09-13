import Duke.Commands.Command;
import Duke.Exception.DukeException;
import Duke.Parser.Parser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button send;

    private Duke duke;

    @FXML
    public void initialize(){
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(new Label(duke.ui.showWelcome()));
    }

    @FXML
    private void handleUserInput(){
        try {
            Command c = Parser.parse(userInput.getText());
            c.execute(duke.tasks, duke.ui, duke.storage);
        } catch (DukeException e) {
            duke.ui.setMessage(e.getMessage());
        } catch (NumberFormatException e) {
            duke.ui.setMessage("     Invalid Command\n");
        }
        Label input = new Label(userInput.getText());
        Label response = new Label(duke.ui.showLine());
        input.setWrapText(true);
        response.setWrapText(true);
        dialogContainer.getChildren().addAll(input, response);

        userInput.clear();
    }
}
