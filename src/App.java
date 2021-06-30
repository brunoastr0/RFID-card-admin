import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import controller.Controller;


public class App extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("school_card1.fxml"));

        Pane root = loader.load(); 

        Controller controller = loader.getController();

        Scene scene = new Scene(root);

  

        root.getStylesheets().add("stylesheet_school_card.css");
        stage.setTitle("School card system");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    
        
    }
    
    public static void main(String[] args){
        launch();
    }
}
