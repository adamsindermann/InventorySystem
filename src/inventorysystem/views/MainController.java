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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Adam Sindermann Controls the behavior of the Main view
 */
public class MainController implements Initializable {

    //for part TableView
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

    //for product TableView
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TableColumn<Product, Integer> productIdColumn;
    @FXML
    private TableColumn<Product, String> productNameColumn;
    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;
    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    //Buttons
    @FXML
    private Button exitButton;
    @FXML
    private Button deletePartButton;
    @FXML
    private Button deleteProductButton;

    //Search Bars
    @FXML
    private TextField partSearchBox;
    @FXML
    private TextField productSearchBox;

    //Warning labels
    @FXML
    private Label partNotFoundLabel;

    /**
     * Loads initial data into the inventory
     */
    private void loadData() {

        //Add parts 
        Part part1 = new InHouse(1, "Screw", 1, 40, 35, 2000, 1231);
        Part part2 = new InHouse(2, "Nut", .25, 3000, 200, 10000, 1235);
        Part part3 = new InHouse(3, "Chain", 5, 65, 15, 100, 1234);
        Part part4 = new Outsourced(4, "Gear", 10, 25, 5, 40, "Gear Maker Association");
        Part part5 = new Outsourced(5, "Nail", .50, 1000, 200, 5000, "Nails Inc");

        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);

        //Add products
        Product product1 = new Product(1, "Table", 299, 10, 5, 50);
        Product product2 = new Product(2, "Desk", 99.99, 15, 5, 60);
        Product product3 = new Product(3, "Bike", 149.99, 30, 0, 75);
        Product product4 = new Product(4, "Skateboard", 149.50, 20, 5, 40);

        product2.getAllAssociatedParts().add(part1);
        product2.getAllAssociatedParts().add(part2);
        product3.getAllAssociatedParts().add(part2);
        product3.getAllAssociatedParts().add(part3);
        product3.getAllAssociatedParts().add(part4);

        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

    }

    /**
     * Closes the program when invoked by the exit button
     */
    public void exitButtonPushed() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
        System.exit(0);
    }

    /**
     * Checks which delete button was pushed and deletes the corresponding
     * Object
     *
     * @param event used to capture which button was pushed
     */
    public void deleteButtonPushed(ActionEvent event) {
        Button buttonPushed = (Button) event.getSource();
        if (InputValidation.confirmationAlert("Delete", "Are you sure you want to delete this item?")) {
            if (buttonPushed.equals(deletePartButton)) {
                ObservableList<Part> selectedRows;
                selectedRows = partTableView.getSelectionModel().getSelectedItems();
                for (Part part : selectedRows) {
                    if (!Inventory.deletePart(part)){
                        InputValidation.displayInputAlert("Cannot delete a part associated with a product");
                    };
                }
            } else if (buttonPushed.equals(deleteProductButton)){
                ObservableList<Product> selectedRows;
                selectedRows = productTableView.getSelectionModel().getSelectedItems();
                for (Product product : selectedRows) {
                    if (!Inventory.deleteProduct(product)) {
                        InputValidation.displayInputAlert("Cannot delete a product with associated parts");
                    }

                }
            }
        }

    }

    /**
     *
     *
     * RUNTIME ERROR - When I first wrote this method I was getting a
     * NumberFormatException from the Integer.parseInt() method when the search
     * box was set to empty. Fixed by checking if the input string was empty
     * before calling the lookupPart(int) method.
     */
    public void partSearch() {
        partNotFoundLabel.setText("");
        String query = partSearchBox.getText();
        ObservableList<Part> searchResults = FXCollections.observableArrayList();
        if (!query.isEmpty() && InputValidation.onlyNumbersOrPeriod(query)) {
            try {
                searchResults.add(Inventory.lookupPart(Integer.parseInt(query)));
            } catch (Exception e) {
                searchResults.clear();
            }

        } else {
            searchResults = Inventory.lookupPart(query);
        }
        partTableView.setItems(searchResults);
        if (searchResults.isEmpty()) {
            partNotFoundLabel.setText("*Part Not Found");
        }

    }

    public void modifyPart(ActionEvent event) throws IOException {
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

    public void modifyProduct(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/inventorysystem/views/Product.fxml"));
        Parent partViewParent = loader.load();

        ProductController controller = loader.getController();
        controller.modifyProductData(productTableView.getSelectionModel().getSelectedItem());

        Scene modifyProductScene = new Scene(partViewParent);
        Stage stage = new Stage();

        stage.setScene(modifyProductScene);
        stage.show();
    }

    /**
     * Launches New Part Window
     *
     * @throws IOException
     */
    public void launchNewPartWindow() throws IOException {
        launchWindow("/inventorysystem/views/Part.fxml");
    }

    public void launchNewProductWindow() throws IOException {
        launchWindow("/inventorysystem/views/Product.fxml");
    }

    public void launchWindow(String fileLocation) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(fileLocation));
        Parent partViewParent = loader.load();

        Scene newScene = new Scene(partViewParent);
        Stage stage = new Stage();

        stage.setScene(newScene);
        stage.show();
    }

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

}
