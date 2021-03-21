package inventorysystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model for Product Object
 *
 * @author Adam Sindermann
 *
 *
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
     * Get list of associated parts
     *
     * @return - ObservableList: Parts associated with the product
     */
    public ObservableList<Part> getAssociatedParts() {
        return associatedParts;
    }

    /**
     * Set list of associated parts
     *
     * @param associatedParts - ObservableList: Parts to be associated with
     * product
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    /**
     * Gets product ID
     *
     * @return - Integer: product ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets product ID
     *
     * @param id - Integer: Product ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets Name
     *
     * @return - String: Name of the object
     */
    public String getName() {
        return name;
    }

    /**
     * Sets Name
     *
     * @param name - String: Product Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets price
     *
     * @return - Double: Price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets Price
     *
     * @param price - Double: Price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets Inventory level
     *
     * @return - Integer: Inventory level
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets inventory level
     *
     * @param stock - Integer: Inventory level
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Get minimum inventory level
     *
     * @return - Integer: Minimum inventory level
     */
    public int getMin() {
        return min;
    }

    /**
     * Set minimum inventory level
     *
     * @param min - Integer: Minimum inventory level
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Get maximum inventory level
     *
     * @return - Integer: Maximum inventory level
     */
    public int getMax() {
        return max;
    }

    /**
     * Set maximum inventory level
     *
     * @param max - Integer: maximum inventory level
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds a part to the product's associated parts list
     *
     * @param part - Part: The part to be added.
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * Removes a part from the list of associated parts
     *
     * @param selectedAssociatedPart - Part: Part to be removed
     * @return - Boolean: True
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        this.associatedParts.remove(selectedAssociatedPart);
        return true;
    }

    /**
     * Returns a list of parts associated with the Product
     *
     * @return - ObservableList: Parts associated with product
     */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;

    }

}
