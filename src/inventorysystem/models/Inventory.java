
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
    
    
    /**
     * 
     * FUTURE ENHANCEMENT - Could create a more descriptive exception. 
     * I.e. partNotFoundException.
     * @param partId - The ID 
     * @return
     * @throws Exception - If the part is not found an exception is thrown, otherwise 
     * the method would need to return a new part signifying that no part was found. 
     */
    public static Part lookupPart(int partId) throws Exception{
        for (Part part: allParts){
            if (part.getId() == partId){
                return part;
            }
        }
        throw new Exception("Part not found");
    }
    
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();
        for (Part part: allParts){
            if(part.getName().toLowerCase().contains(partName.toLowerCase())){
                matchingParts.add(part);
            }
        }
        return matchingParts;
    }
    
    public static void updatePart(int index, Part selectedPart){
         allParts.set(index, selectedPart);
    }
    
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
    
    public static Boolean deletePart(Part selectedPart){
        for (Product product: allProducts){
            if(product.getAllAssociatedParts().contains(selectedPart)){
                return false;
            }
        }
        allParts.remove(selectedPart);
        return true;
    }
    
    public static Boolean deleteProduct(Product selectedProduct){
        if(selectedProduct.getAllAssociatedParts().isEmpty()){
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }
    
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
