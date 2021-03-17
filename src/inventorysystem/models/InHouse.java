/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorysystem.models;

/**
 *
 * @author Adam Sindermann
 */
public class InHouse extends Part {
    private  int machineId;

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
     * 
     * @param machineId 
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    /**
     * 
     * @return 
     */
    public int getMachineId() {
        return machineId;
    }
    
    
}
