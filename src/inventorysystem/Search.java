/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem;

import inventorysystem.models.Inventory;
import inventorysystem.models.Part;
import inventorysystem.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Provides search functionality throughout the application.
 *
 * @author Adam Sindermann
 *
 *
 */
public class Search {

    /**
     * RUNTIME ERROR - When I first wrote this method I was getting a
     * NumberFormatException from the Integer.parseInt() method when the search
     * box was set to empty. Fixed by checking if the input string was empty
     * before calling the lookupPart(int) method.
     *
     * Searches for a part using either the name or the ID
     *
     * @param query - String: name or ID
     * @return - ObservableList of matching parts
     */
    public static ObservableList<Part> partSearch(String query) {
        ObservableList<Part> searchResults = FXCollections.observableArrayList();
        if (!query.isEmpty() && InputValidation.onlyNumbersOrPeriod(query)) {
            try {
                searchResults.add(Inventory.lookupPart(Integer.parseInt(query)));
            } catch (Exception e) {
                searchResults.clear();
            }

        } else {
            searchResults = Inventory.lookupPart(query);
        }
        return searchResults;
    }

    /**
     * Searches for a product using name or ID
     *
     * @param query - String: Name or ID
     * @return - ObservableList of matching products.
     */
    public static ObservableList<Product> productSearch(String query) {
        ObservableList<Product> searchResults = FXCollections.observableArrayList();
        if (!query.isEmpty() && InputValidation.onlyNumbersOrPeriod(query)) {
            try {
                searchResults.add(Inventory.lookupProduct(Integer.parseInt(query)));
            } catch (Exception e) {
                searchResults.clear();
            }

        } else {
            searchResults = Inventory.lookupProduct(query);
        }
        return searchResults;
    }
}
