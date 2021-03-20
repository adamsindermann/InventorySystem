package inventorysystem.models;

/**
 * Model for Outsourced Part object
 *
 * @author Adam Sindermann
 */
public class Outsourced extends Part {

    private String companyName;

    /**
     *
     * @param id Part ID
     * @param name Part Name
     * @param price Price of the part
     * @param stock How many of the part are in stock?
     * @param min The minimum amount of the product that should be in stock
     * @param max the maximum amount of the product that should be in stock
     * @param companyName The name of the company that makes the part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {

        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Set the company name
     *
     * @param companyName - String
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Get the company name
     *
     * @return - String
     */
    public String getCompanyName() {
        return companyName;
    }

}
