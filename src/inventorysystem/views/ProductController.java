
package inventorysystem.views;

import inventorysystem.models.Inventory;
import inventorysystem.models.Part;
import inventorysystem.models.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    
    private Product thisProduct;
    
    //Buttons
    @FXML private Button cancelButton;
    
    
    public void saveProduct() {
        thisProduct.setName(nameField.getText());
        thisProduct.setStock(Integer.parseInt(invField.getText()));
        thisProduct.setPrice(Double.parseDouble(priceField.getText()));
        thisProduct.setMin(Integer.parseInt(minField.getText()));
        thisProduct.setMax(Integer.parseInt(maxField.getText()));
        if(modifying){
            int index = Inventory.getAllProducts().indexOf(thisProduct);
            Inventory.updateProduct(index, thisProduct);
        } else {
            Inventory.addProduct(thisProduct);
        }
        
        
        closeWindow();
    }
    
    public void addPart(){
        Part newPart = partTableView.getSelectionModel().getSelectedItem();
        thisProduct.getAllAssociatedParts().add(newPart);
        associatedPartTableView.setItems(thisProduct.getAllAssociatedParts());
    }
    
    public void closeWindow(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    public void modifyProductData(Product product){
        modifying = true;
        thisProduct = product;
        
        iDfield.setText(Integer.toString(thisProduct.getId()));
        nameField.setText(thisProduct.getName());
        invField.setText(Integer.toString(thisProduct.getStock()));
        priceField.setText(Double.toString(thisProduct.getPrice()));
        minField.setText(Integer.toString(thisProduct.getMin()));
        maxField.setText(Integer.toString(thisProduct.getMax()));
        
        associatedPartTableView.setItems(thisProduct.getAllAssociatedParts());
    }
    
    public void removePart(){
        Part selectedAssociatedPart = associatedPartTableView.getSelectionModel().getSelectedItem();
        thisProduct.deleteAssociatedPart(selectedAssociatedPart);
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //Initilize colums in part TableView
         modifying = false;
         
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        
        partTableView.setItems(Inventory.getAllParts());
        
        //Initialize Colums in Associated part table view
        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        associatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        associatedPartPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        
        int productId = Inventory.getAllProducts().get(Inventory.getAllProducts().size() -1 ).getId() + 1;
        
        thisProduct = new Product(productId, "product", 0, 0, 0, 0);
    }    
    
}
