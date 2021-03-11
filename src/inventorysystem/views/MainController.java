/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.views;

import inventorysystem.models.InHouse;
import inventorysystem.models.Inventory;
import inventorysystem.models.Outsourced;
import inventorysystem.models.Part;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author AdamS
 */
public class MainController implements Initializable {
    
    //for part TableView
    @FXML private TableView<Part> partTableView;
    @FXML private TableColumn<Part, Integer> partIdColumn; 
    @FXML private TableColumn<Part, String> partNameColumn; 
    @FXML private TableColumn<Part, Integer> partInventoryColumn; 
    @FXML private TableColumn<Part, Double> partPriceColumn; 

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Part part1 = new InHouse(1, "Part 1", 13.50, 30, 1, 50, 1234);
        Part part2 = new InHouse(1, "Part 2", 11, 23, 10, 100, 1237);
        Part part3 = new InHouse(1, "Part 3", 0.50, 1500, 300, 5000, 1235);
        Part part4 = new Outsourced(1, "Part 4", 100, 15, 5, 20, "Blackwood");
        
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        
        
        //Initilize colums in part TableView
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        
        partTableView.setItems(Inventory.getAllParts());
    }    
    
}
