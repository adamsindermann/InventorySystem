
package inventorysystem.views;

import inventorysystem.models.Part;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Adam Sindermann
 */
public class ProductController implements Initializable {

    //Text boxes
    @FXML private TextField iDfield;
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    
    //All Parts table
    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partIdColumn; 
    @FXML private TableColumn<Part, String> partNameColumn; 
    @FXML private TableColumn<Part, Integer> partInventoryColumn; 
    @FXML private TableColumn<Part, Double> partPriceColumn;
    
    //Associated Parts Table
    @FXML private TableView<Part> associatedPartTableView;
    @FXML private TableColumn<Part, Integer> associatedPartIdColumn; 
    @FXML private TableColumn<Part, String> associatedPartNameColumn; 
    @FXML private TableColumn<Part, Integer> associatedPartInventoryColumn; 
    @FXML private TableColumn<Part, Double> associatedPartPriceColumn;
    
    //Used to track state
    private boolean modifying;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
