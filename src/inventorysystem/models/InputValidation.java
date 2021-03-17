/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.models;

import javafx.scene.control.Alert;

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
}
