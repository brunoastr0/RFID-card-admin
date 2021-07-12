import java.io.IOException;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class App extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("app/fxml/main.fxml"));

        Pane root = loader.load(); 
       

        Scene scene = new Scene(root);
  

        stage.setTitle("School card system");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show(); 
    }


    
    
    public static void main(String[] args){
        launch();
    }
}
