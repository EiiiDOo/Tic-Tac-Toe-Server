
package ticktacktoeserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TickTackToeServer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new ServerControlBase(stage);
//        Server server = new Server();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
