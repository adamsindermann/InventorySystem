package inventorysystem;


import inventorysystem.models.InHouse;
import inventorysystem.models.Inventory;
import inventorysystem.models.Outsourced;
import inventorysystem.models.Part;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point of the application
 * @author Adam Sindermann
 * FUTURE ENHANCEMENT - Currently, users can add one part to a product multiple times.
 * Future implementation should check if the part is already associated with the product 
 * and display an error message or remove the part from the available parts list. 
 * 
 * FUTURE ENHANCEMENT - Would be nice if users could search by partial 
 * part or product ID. Currently ID Search only supports an exact match.
 * 
 * FUTURE ENHANCEMENT - Search functionality should be moved to it's own class.
 */
public class InventorySystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(InventorySystem.class.getResource("/inventorysystem/views/Main.fxml"));
        Parent root = loader.load();
   
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }
    
}
