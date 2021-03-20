package inventorysystem.views;

import inventorysystem.models.InHouse;
import inventorysystem.InputValidation;
import inventorysystem.models.Inventory;
import inventorysystem.models.Outsourced;
import inventorysystem.models.Part;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * Provides logic for part view.
 *
 * @author Adam Sindermann
 */
public class PartController implements Initializable {

    //Radio Buttons 
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outsourcedRadio;
    private ToggleGroup radioToggleGroup;

    //Used to track state of part form
    private boolean inHouseSelected;
    private boolean modifyingPart;

    //Label that changes depending on radio button selection 
    @FXML
    private Label machineOrCompanyLabel;

    //Text Boxes
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
    @FXML
    private TextField machineOrCompanyField;

    //Buttons
    @FXML
    private Button cancelButton;

    /**
     * Updates the view when the toggle switch is changed. Defaults to In-House.
     */
    public void toggleSwitch() {
        if (this.radioToggleGroup.getSelectedToggle().equals(this.inHouseRadio)) {
            machineOrCompanyLabel.setText("Machine ID");
            inHouseSelected = true;
        } else {
            machineOrCompanyLabel.setText("Company Name");
            inHouseSelected = false;
        }
    }

    /**
     * Checks whether a new or existing part is being saved and calls the
     * corresponding method
     */
    public void save() {
        if (inputValidation()) {
            if (modifyingPart) {
                saveExisting();
            } else {
                saveNew();
            }
            closeWindow();
        }

    }

    /**
     * Updates an existing part with new values
     */
    public void saveExisting() {
        Part modifiedPart = null;
        for (Part part : Inventory.getAllParts()) {
            if (Integer.parseInt(iDfield.getText()) == part.getId()) {
                modifiedPart = part;
            }
        }
        if (modifiedPart instanceof Outsourced && inHouseSelected) {
            replacePart(modifiedPart);

        } else if (modifiedPart instanceof InHouse && !inHouseSelected) {
            replacePart(modifiedPart);
        } else {
            modifiedPart.setName(nameField.getText());
            modifiedPart.setStock(Integer.parseInt(invField.getText()));
            modifiedPart.setPrice(Double.parseDouble(priceField.getText()));
            modifiedPart.setMax(Integer.parseInt(maxField.getText()));
            modifiedPart.setMin(Integer.parseInt(minField.getText()));
            if (inHouseSelected) {
                ((InHouse) modifiedPart).setMachineId(Integer.parseInt(machineOrCompanyField.getText()));
            } else {
                ((Outsourced) modifiedPart).setCompanyName(machineOrCompanyField.getText());
            }

            Inventory.updatePart(Inventory.getAllParts().indexOf(modifiedPart), modifiedPart);
        }

    }

    /**
     * Captures Data from text boxes and adds a new part to the inventory
     */
    public void saveNew() {
        String name, compName;
        double price;
        int id, inv, max, min, machId;
        //Gets the ID of the last part in the inventory and adds 1 to it 
        id = Inventory.getAllParts().get(Inventory.getAllParts().size() - 1).getId() + 1;
        name = nameField.getText();
        inv = Integer.parseInt(invField.getText());
        price = Double.parseDouble(priceField.getText());
        max = Integer.parseInt(maxField.getText());
        min = Integer.parseInt(minField.getText());

        if (inHouseSelected) {
            machId = Integer.parseInt(machineOrCompanyField.getText());
            Part newPart = new InHouse(id, name, price, inv, min, max, machId);
            Inventory.addPart(newPart);
        } else {
            compName = machineOrCompanyField.getText();
            Part newPart = new Outsourced(id, name, price, inv, min, max, compName);
            Inventory.addPart(newPart);
        }

    }

    /**
     * Creates a new part and replaces old part. For use when changing InHouse
     * to Outsources or vice versa.
     *
     * @param modifiedPart the part to be replaced
     */
    public void replacePart(Part modifiedPart) {
        Part newPart;
        String name, compName;
        double price;
        int id, inv, max, min, machId;
        //Gets the ID of the last part in the inventory and adds 1 to it 
        id = Inventory.getAllParts().get(Inventory.getAllParts().size() - 1).getId() + 1;
        name = nameField.getText();
        inv = Integer.parseInt(invField.getText());
        price = Double.parseDouble(priceField.getText());
        max = Integer.parseInt(maxField.getText());
        min = Integer.parseInt(minField.getText());

        if (inHouseSelected) {
            machId = Integer.parseInt(machineOrCompanyField.getText());
            newPart = new InHouse(id, name, price, inv, min, max, machId);
        } else {
            compName = machineOrCompanyField.getText();
            newPart = new Outsourced(id, name, price, inv, min, max, compName);
        }
        newPart.setId(modifiedPart.getId());
        Inventory.updatePart(Inventory.getAllParts().indexOf(modifiedPart), newPart);
    }

    /**
     * Captures the part selected in main view and loads corresponding data
     *
     * @param part the selected part to be modified
     */
    public void modifyPartData(Part part) {
        modifyingPart = true;
        nameField.setText(part.getName());
        iDfield.setText(Integer.toString(part.getId()));
        invField.setText(Integer.toString(part.getStock()));
        priceField.setText(Double.toString(part.getPrice()));
        maxField.setText(Integer.toString(part.getMax()));
        minField.setText(Integer.toString(part.getMin()));
        if (part instanceof InHouse) {
            machineOrCompanyField.setText(Integer.toString(((InHouse) part).getMachineId()));
            inHouseSelected = true;
        } else if (part instanceof Outsourced) {
            inHouseSelected = false;
            machineOrCompanyField.setText(((Outsourced) part).getCompanyName());
            radioToggleGroup.selectToggle(outsourcedRadio);
            toggleSwitch();
        }
    }

    /**
     * Validates input.
     *
     * @return true if all checks pass.
     */
    public boolean inputValidation() {
        String name = nameField.getText();
        String inv = invField.getText();
        String price = priceField.getText();
        String max = maxField.getText();
        String min = minField.getText();
        String machIdOrCompName = machineOrCompanyField.getText();

        if (!InputValidation.inputValidation(name, inv, price, min, max)) {
            return false;
        }

        if (machIdOrCompName.isEmpty()) {
            InputValidation.displayInputAlert("All values are required");
            return false;
        }

        if (inHouseSelected) {
            if (!InputValidation.onlyNumbersOrPeriod(machIdOrCompName)) {
                InputValidation.displayInputAlert("Machine ID must only contain numbers");
                return false;
            }
        }

        return true;
    }

    /**
     * Closes the new part window
     */
    public void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radioToggleGroup = new ToggleGroup();
        this.inHouseRadio.setToggleGroup(radioToggleGroup);
        this.outsourcedRadio.setToggleGroup(radioToggleGroup);
        inHouseSelected = true;
        modifyingPart = false;
        iDfield.setText(Integer.toString(Inventory.getAllParts().get(Inventory.getAllParts().size() - 1).getId() + 1));
    }

}
