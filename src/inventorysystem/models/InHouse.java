package inventorysystem.models;

/**
 *
 * @author Adam Sindermann Model for the InHouse part
 */
public class InHouse extends Part {

    private int machineId;

    /**
     *
     * @param id Part ID
     * @param name Part Name
     * @param price Price of the part
     * @param stock How many of the part are in stock?
     * @param min The minimum amount of the product that should be in stock
     * @param max the maximum amount of the product that should be in stock
     * @param machineId the ID of the machine that was used to make the part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Sets the Machine ID
     *
     * @param machineId - Integer: Machine ID
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * Gets the Machine ID
     *
     * @return - Integer: Machine ID
     */
    public int getMachineId() {
        return machineId;
    }

}
