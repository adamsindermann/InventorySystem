/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model for the inventory object
 * @author Adam Sindermann
 */
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();;
    
    /**
     * Adds a part to the inventory
     * @param newPart Part object to be added to inventory 
     */
    public static void addPart(Part newPart){
        
        allParts.add(newPart);
    }
    
    
    /**
     * Adds a new product to the inventory
     * @param newProduct The Product object to be added to the inventory
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    
    public static Part lookupPart(int partId){
        for (Part part: allParts){
            if (part.getId() == partId){
                return part;
            }
        }
        return allParts.get(1);
    }
    
    public static Boolean deletePart(Part selectedPart){
        allParts.remove(selectedPart);
        return true;
    }
    
    public static Boolean deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
        return true;
    }
    
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
