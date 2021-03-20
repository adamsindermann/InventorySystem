/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.models;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Adam Sindermann
 */
public class InputValidation {
        /**
     * Checks that the string only contains numbers or the period character
     * @param str the string to be checked 
     * @return true if only numbers or period, false otherwise
     */
    public static boolean onlyNumbersOrPeriod(String str){
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.' ){
                return false;
            }  
        }
        return true;
    }
    
    /**
     * Displays an alert popup for invalid input
     * @param message 
     */
    public static void displayInputAlert(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Input");
        alert.setContentText(message);
        
        alert.showAndWait();
    }
    
    public static boolean confirmationAlert(String headerMessage, String contentMessage){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(headerMessage);
        alert.setContentText(contentMessage);
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            return true;
        } else {
            return false;
        }
    }
}
