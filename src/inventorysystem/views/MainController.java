package inventorysystem.views;

import inventorysystem.models.InHouse;
import inventorysystem.models.InputValidation;
import inventorysystem.models.Inventory;
import inventorysystem.models.Outsourced;
import inventorysystem.models.Part;
import inventorysystem.models.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Adam Sindermann
 * Controls the behavior of the Main view
 */
public class MainController implements Initializable {
    
    //for part TableView
    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partIdColumn; 
    @FXML private TableColumn<Part, String> partNameColumn; 
    @FXML private TableColumn<Part, Integer> partInventoryColumn; 
    @FXML private TableColumn<Part, Double> partPriceColumn;
    
    //for product TableView
    @FXML private TableView<Product> productTableView;
    @FXML private TableColumn<Product, Integer> productIdColumn; 
    @FXML private TableColumn<Product, String> productNameColumn; 
    @FXML private TableColumn<Product, Integer> productInventoryColumn; 
    @FXML private TableColumn<Product, Double> productPriceColumn;
    
    //Buttons
    @FXML private Button exitButton;
    @FXML private Button deletePartButton;
    @FXML private Button deleteProductButton;
    
    //Search Bars
    @FXML private TextField partSearchBox;
    @FXML private TextField productSearchBox;
    
    //Warning labels
    @FXML private Label partNotFoundLabel;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        
        //Initilize colums in part TableView
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        
        partTableView.setItems(Inventory.getAllParts());
        
        //Initilize colums in part TableView
        productIdColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("price"));
        
        productTableView.setItems(Inventory.getAllProducts());
    }    
    
    /**
     * Loads initial data into the inventory 
     */
    private void loadData(){
        
        //Add parts 
        Part part1 = new InHouse(1, "Screw", 13.50, 30, 1, 50, 1222);
        Part part2 = new InHouse(2, "Nut", 11, 23, 10, 100, 222);
        Part part3 = new InHouse(3, "Chain", 0.50, 1500, 300, 5000, 322);
        Part part4 = new Outsourced(4, "Gear", 100, 15, 5, 20, "Blackwood");
        
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        
        //Add products
        
        Product product1 = new Product(12, "Product 1", 45, 345, 15, 500);
        Product product2 = new Product(123, "Product 2", 34.50, 21, 1, 35);
        Product product3 = new Product(132, "Product 3", 149.99, 600, 0, 1000);
        Product product4 = new Product(1442, "Product 4", 654.11, 20, 3, 40);
        
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);
        
        
        
    }
    
    /**
     * Closes the program when invoked by the exit button
     */
    public void exitButtonPushed(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        System.exit(0);
    }
    
    
    /**
     * Checks which delete button was pushed and deletes the corresponding Object
     * @param event used to capture which button was pushed
     */
    public void deleteButtonPushed(ActionEvent event){
        Button buttonPushed = (Button)event.getSource();
        
        if (buttonPushed.equals(deletePartButton)){
            ObservableList<Part> selectedRows;
            selectedRows = partTableView.getSelectionModel().getSelectedItems();
            for (Part part: selectedRows){
                Inventory.deletePart(part);
            }
        } else {
            ObservableList<Product> selectedRows;
            selectedRows = productTableView.getSelectionModel().getSelectedItems();
            for (Product product: selectedRows){
                Inventory.deleteProduct(product);
            }
        }
    }
    
    /**
     * 
     * FUTURE ENHANCEMENT - Would be nice if users could search by partial 
     * part ID. Currently ID Search only supports an exact match.
     */
    public void partSearch(){
        partNotFoundLabel.setText("");
        String query = partSearchBox.getText();
        ObservableList<Part> searchResults = FXCollections.observableArrayList();
        if(!query.isEmpty() && InputValidation.onlyNumbersOrPeriod(query)){
            try{
              searchResults.add(Inventory.lookupPart(Integer.parseInt(query)));
            } catch (Exception e){
              searchResults.clear();
            }
            
        } else {
            searchResults = Inventory.lookupPart(query);      
        }
        partTableView.setItems(searchResults);
        if (searchResults.isEmpty()){
            partNotFoundLabel.setText("*Part Not Found");
        }
        
    }
    
    public void modifyPart(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorysystem/views/Part.fxml"));
        Parent partViewParent = loader.load();
        
        PartController controller = loader.getController();
        controller.modifyPartData(partTableView.getSelectionModel().getSelectedItem());
        
        Scene modifyPartScene = new Scene(partViewParent);
        Stage stage = new Stage();
        
        stage.setScene(modifyPartScene);
        stage.show();
        
    }

    
    /**
     * Launches New Part Window
     * @throws IOException 
     */
    public void launchNewPartWindow() throws IOException{
        launchWindow("/inventorysystem/views/Part.fxml");
    }
    
    public void launchNewProductWindow() throws IOException{
        launchWindow("/inventorysystem/views/Product.fxml");
    }
    
    public void launchWindow(String fileLocation) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fileLocation));
        Parent partViewParent = loader.load();
   
        Scene newScene = new Scene(partViewParent);
        Stage stage = new Stage();
        
        stage.setScene(newScene);
        stage.show();
    }

    
    
}
