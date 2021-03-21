package inventorysystem;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Used to validate input and display error messages throughout the application.
 *
 * @author Adam Sindermann
 */
public class InputValidation {

    /**
     * Checks that the string only contains numbers or the period character
     *
     * @param str the string to be checked
     * @return true if only numbers or period, false otherwise
     */
    public static boolean onlyNumbersOrPeriod(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.') {
                return false;
            }
        }
        return true;
    }

    /**
     * Displays an alert popup for invalid input.
     *
     * @param message - String: message to be displayed on pop up.
     */
    public static void displayInputAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid Input");
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Displays an alert prompting the user to confirm input.
     *
     * @param headerMessage - String: Header message.
     * @param contentMessage - String: Content message.
     * @return - True if the user confirms with the alert.
     */
    public static boolean confirmationAlert(String headerMessage, String contentMessage) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(headerMessage);
        alert.setContentText(contentMessage);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Checks that strings are not empty, that they only contain numbers where
     * necessary, and that the inventory levels are logical.
     *
     * @param name - String: Name
     * @param inv - String: Inventory level
     * @param price - String: price
     * @param min - String: Minimum inventory
     * @param max - String Maximum inventory
     * @return True if all checks pass.
     */
    public static boolean inputValidation(String name, String inv, String price, String min, String max) {
        boolean invNoChar = onlyNumbersOrPeriod(inv);
        boolean priceNoChar = onlyNumbersOrPeriod(price);
        boolean maxNoChar = onlyNumbersOrPeriod(max);
        boolean minNoChar = onlyNumbersOrPeriod(min);

        if (name.isEmpty() || inv.isEmpty() || price.isEmpty()
                || max.isEmpty() || min.isEmpty()) {
            displayInputAlert("All values are required");
            return false;
        }
        if (!invNoChar || !priceNoChar || !maxNoChar || !minNoChar) {
            displayInputAlert("Inventory, Price, Max, and Min must only contain numbers");
            return false;
        }

        int minInt = Integer.parseInt(min);
        int maxInt = Integer.parseInt(max);
        int invInt = Integer.parseInt(inv);
        if (minInt >= maxInt) {
            displayInputAlert("Min can't be greater than max");
            return false;
        }
        if (minInt >= invInt || maxInt <= invInt) {
            displayInputAlert("Inventory must be less than max and greater than min");
            return false;
        }
        return true;

    }
}
