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
public class Outsourced extends Part{
    
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }
    
    
    
}
