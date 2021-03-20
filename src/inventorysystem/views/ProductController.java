package inventorysystem.views;

import inventorysystem.InputValidation;
import inventorysystem.Search;
import inventorysystem.models.Inventory;
import inventorysystem.models.Part;
import inventorysystem.models.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Provides logic for Product View.
 *
 * @author Adam Sindermann
 */
public class ProductController implements Initializable {

    //Text boxes
    @FXML
    private TextField iDfield;
    @FXML
    private TextField nameField;
    @FXML
    private TextField invField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField minField;

    //Search 
    @FXML
    private TextField partSearch;
    @FXML
    private Label partSearchLabel;

    //All Parts table
    @FXML
    private TableView<Part> partTableView;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    //Associated Parts Table
    @FXML
    private TableView<Part> associatedPartTableView;
    @FXML
    private TableColumn<Part, Integer> associatedPartIdColumn;
    @FXML
    private TableColumn<Part, String> associatedPartNameColumn;
    @FXML
    private TableColumn<Part, Integer> associatedPartInventoryColumn;
    @FXML
    private TableColumn<Part, Double> associatedPartPriceColumn;

    //Used to track state
    private boolean modifying;

    private Product thisProduct;

    //Buttons
    @FXML
    private Button cancelButton;

    /**
     * Validates input and saves the product.
     */
    public void saveProduct() {
        String name = nameField.getText();
        String inv = invField.getText();
        String price = priceField.getText();
        String max = maxField.getText();
        String min = minField.getText();
        if (InputValidation.inputValidation(name, inv, price, min, max)) {

            thisProduct.setName(name);
            thisProduct.setStock(Integer.parseInt(inv));
            thisProduct.setPrice(Double.parseDouble(price));
            thisProduct.setMin(Integer.parseInt(min));
            thisProduct.setMax(Integer.parseInt(max));
            if (modifying) {
                int index = Inventory.getAllProducts().indexOf(thisProduct);
                Inventory.updateProduct(index, thisProduct);
            } else {
                Inventory.addProduct(thisProduct);
            }

            closeWindow();
        }
    }

    /**
     * Adds a part to the product.
     */
    public void addPart() {
        Part newPart = partTableView.getSelectionModel().getSelectedItem();
        thisProduct.addAssociatedPart(newPart);
        associatedPartTableView.setItems(thisProduct.getAllAssociatedParts());
    }

    /**
     * Closes the window.
     */
    public void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Loads product data if a product is being modified.
     *
     * @param product - Selected product being modified.
     */
    public void modifyProductData(Product product) {
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

    /**
     * Remove a part from the product.
     */
    public void removePart() {
        if (InputValidation.confirmationAlert("Remove", "Remove part?")) {

            Part selectedAssociatedPart = associatedPartTableView.getSelectionModel().getSelectedItem();
            thisProduct.deleteAssociatedPart(selectedAssociatedPart);
        }
    }

    /**
     * Searches for a part and updates the tableView.
     */
    public void search() {
        partSearchLabel.setText("");
        String query = partSearch.getText();
        ObservableList<Part> searchResults = Search.partSearch(query);
        partTableView.setItems(searchResults);
        if (searchResults.isEmpty()) {
            partSearchLabel.setText("*Part Not Found");
        }
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

        int productId = Inventory.getAllProducts().get(Inventory.getAllProducts().size() - 1).getId() + 1;
        iDfield.setText(Integer.toString(productId));
        thisProduct = new Product(productId, "product", 0, 0, 0, 0);
    }

}
