/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Adam Sindermann
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
    public Product(ObservableList<Part> associatedParts, int id, String name, double price, int stock, int min, int max) {
        this.associatedParts = associatedParts;
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    public int getId() {
        return id;
    }

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
         ObservableList<Part> returnList = FXCollections.observableArrayList();
         returnList.add(new InHouse(1, "1", 1, 1, 1, 1, 1));
         return returnList;
         
    }
    
    
}
