package inventorysystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Adam Sindermann
 * Model for Product Object
 */
public class Product {
    
    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * 
     * @param associatedParts An ObservableList of parts used to make the product
     * @param id product identification number
     * @param name product name
     * @param price product price
     * @param stock amount of product in stock 
     * @param min the minimum amount of the product that should be in stock
     * @param max the maximum amount of the product that should be in stock
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.associatedParts = FXCollections.observableArrayList();
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * 
     * @return ObservableList of parts associated with the product 
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    /**
     * 
     * @param associatedParts ObservableList of parts associated with the product 
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     * 
     * @return the product identification number as an integer 
     */
    public int getId() {
        return id;
    }

    /**
     * the product identification number as an integer
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    public static void addAssociatedPart(Part part){
        
    }
    
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        return true;
    }
    
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
         
    }
    
    
}
