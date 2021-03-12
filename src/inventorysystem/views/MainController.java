package inventorysystem.views;

import inventorysystem.models.InHouse;
import inventorysystem.models.Inventory;
import inventorysystem.models.Outsourced;
import inventorysystem.models.Part;
import inventorysystem.models.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
        Part part1 = new InHouse(1, "Part 1", 13.50, 30, 1, 50, 1234);
        Part part2 = new InHouse(156, "Part 2", 11, 23, 10, 100, 1237);
        Part part3 = new InHouse(123, "Part 3", 0.50, 1500, 300, 5000, 1235);
        Part part4 = new Outsourced(247, "Part 4", 100, 15, 5, 20, "Blackwood");
        
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
     * @param event 
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
}
