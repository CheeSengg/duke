//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
//import java.io.File;
//import java.io.IOException;
//
//public class Main extends Application {
//    private duke duke;
//
//    private void loadDuke(){
//        File currentDir = new File(System.getProperty("user.dir"));
//        File filePath = new File(currentDir.toString() + "\\src\\main\\data\\duke.txt");
//        duke = new duke(filePath);
//    }
//
//    @Override
//    public void start(Stage stage){
//        try{
//            loadDuke();
//            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
//            AnchorPane ap = fxmlLoader.load();
//            Scene scene = new Scene(ap);
//            stage.setScene(scene);
//            fxmlLoader.<MainWindow>getController().setDuke(duke);
//            stage.show();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    }
//}
