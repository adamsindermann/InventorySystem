package inventorysystem.views;

import inventorysystem.models.InHouse;
import inventorysystem.models.Inventory;
import inventorysystem.models.Outsourced;
import inventorysystem.models.Part;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Adam Sindermann
 */
public class PartController implements Initializable {

    //Radio Buttons 
    @FXML private RadioButton inHouseRadio;
    @FXML private RadioButton outsourcedRadio;
    private ToggleGroup radioToggleGroup;
    
    private boolean inHouseSelected;
    private boolean modifyingPart;
    
    //Label that changes depending on radio button selection 
    @FXML private Label machineOrCompanyLabel;
    
    //Text Boxes
    @FXML private TextField iDfield;
    @FXML private TextField nameField;
    @FXML private TextField invField;
    @FXML private TextField priceField;
    @FXML private TextField maxField;
    @FXML private TextField minField;
    @FXML private TextField machineOrCompanyField;
    
    //Buttons
    @FXML private Button cancelButton;
    
    /**
     * Updates the view when the toggle switch is changed. Defaults to In-House
     */
    public void toggleSwitch(){
        if (this.radioToggleGroup.getSelectedToggle().equals(this.inHouseRadio)){
            machineOrCompanyLabel.setText("Machine ID");
            inHouseSelected = true;
        } else {
            machineOrCompanyLabel.setText("Company Name");
            inHouseSelected = false;
        }
    }
    
    public void save(){
        if (modifyingPart){
            saveExisting();
        } else {
            saveNew();
        }
        closeWindow();
        
    }
    
    public void saveExisting(){
        Part modifiedPart = null;
        for (Part part: Inventory.getAllParts()){
            if (Integer.parseInt(iDfield.getText()) == part.getId()){
                modifiedPart = part;
            }
        }
        modifiedPart.setName(nameField.getText());
        modifiedPart.setStock(Integer.parseInt(invField.getText()));
        modifiedPart.setPrice(Double.parseDouble(priceField.getText()));
        modifiedPart.setMax(Integer.parseInt(maxField.getText()));
        modifiedPart.setMin(Integer.parseInt(minField.getText()));
        if (inHouseSelected){
            ((InHouse) modifiedPart).setMachineId(Integer.parseInt(machineOrCompanyField.getText()));
        } else {
            ((Outsourced) modifiedPart).setCompanyName(machineOrCompanyField.getText());
        }
        
        Inventory.updatePart(Inventory.getAllParts().indexOf(modifiedPart), modifiedPart);
    }
    /**
     * Captures Data from text boxes and adds a new part to the inventory
     */
    public void saveNew(){
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
        
        if (inHouseSelected){
            machId = Integer.parseInt(machineOrCompanyField.getText());
            Part newPart = new InHouse(id, name, price, inv, min, max, machId);
            Inventory.addPart(newPart);
        } else {
            compName = machineOrCompanyField.getText();
            Part newPart = new Outsourced(id, name, price, inv, min, max, compName);
            Inventory.addPart(newPart);
        }
        
         
        
    }
    
    public void modifyPartData(Part part){
        modifyingPart = true;
        nameField.setText(part.getName());
        iDfield.setText(Integer.toString(part.getId()));
        invField.setText(Integer.toString(part.getStock()));
        priceField.setText(Double.toString(part.getPrice()));
        maxField.setText(Integer.toString(part.getMax()));
        minField.setText(Integer.toString(part.getMin()));
        if (part instanceof InHouse){
           machineOrCompanyField.setText(Integer.toString(((InHouse) part).getMachineId()));
            inHouseSelected = true;
        } else if (part instanceof Outsourced) {
            machineOrCompanyField.setText(((Outsourced) part).getCompanyName());
            radioToggleGroup.selectToggle(outsourcedRadio);
            toggleSwitch();
        }
    }
    
    /**
     * Closes the new part window
     */
    public void closeWindow(){
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
    }    
    
}
