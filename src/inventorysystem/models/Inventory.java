package inventorysystem.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model for Inventory. Keeps track of Parts and Products.
 *
 * @author Adam Sindermann
 */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    ;
    
    /**
     * Adds a part to the inventory
     * @param newPart - Part object to be added to inventory 
     */
    public static void addPart(Part newPart) {

        allParts.add(newPart);

    }

    /**
     * Adds a new product to the inventory
     *
     * @param newProduct - The Product object to be added to the inventory
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Searches the Part inventory and returns a Part
     *
     * @param partId - The ID of the part being looked up
     * @return - Part that was found
     * @throws Exception - If the part is not found an exception is thrown,
     * otherwise the method would need to return a new part signifying that no
     * part was found.
     */
    public static Part lookupPart(int partId) throws Exception {
        for (Part part : allParts) {
            if (part.getId() == partId) {
                return part;
            }
        }
        throw new Exception("Part not found");
    }

    /**
     * Searches the product inventory and returns a Product
     *
     * @param productId - The ID of the product being looked up
     * @return - Product that was found
     * @throws Exception - Throws exception if the product is not found.
     */
    public static Product lookupProduct(int productId) throws Exception {
        for (Product product : allProducts) {
            if (product.getId() == productId) {
                return product;
            }
        }
        throw new Exception("Product not found");
    }

    /**
     * Searches the inventory and returns a list of parts that have names
     * containing the string that is passed in
     *
     * @param partName - The search query
     * @return - A list of parts that match the search query. Returns an empty
     * list if no parts are found.
     */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                matchingParts.add(part);
            }
        }
        return matchingParts;
    }

    /**
     * Searches the inventory and returns a list of products that have names
     * containing the string that is passed in
     *
     * @param productName - The search query
     * @return - A list of products that match the search query. Returns an
     * empty list if no products are found.
     */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> matchingProducts = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    /**
     * Replaces the part at the specified index with a new part.
     *
     * @param index - The index of the part being replaced
     * @param selectedPart - The new part
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /**
     * Replaces the product at the specified index with a new product.
     *
     * @param index - The index of the product being replaced
     * @param selectedProduct - The new Product
     */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /**
     * Removes a part from the inventory if the part is not associated with a
     * product.
     *
     * @param selectedPart - The part to be removed
     * @return - True if the part is successfully removed
     */
    public static Boolean deletePart(Part selectedPart) {
        allParts.remove(selectedPart);
        return true;
    }

    /**
     * Removes a product from the inventory if the product is not associated
     * with any parts.
     *
     * @param selectedProduct - The product to be removed.
     * @return - True if the product is successfully removed.
     */
    public static Boolean deleteProduct(Product selectedProduct) {
        if (selectedProduct.getAllAssociatedParts().isEmpty()) {
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets a list of parts in the inventory.
     *
     * @return - ObservableList of all parts in inventory
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Gets a list of products in the inventory.
     *
     * @return - ObservableList of all products in inventory.
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
