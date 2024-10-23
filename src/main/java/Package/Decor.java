/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

import java.io.FileNotFoundException;

/**
 *
 * @author Dell 5490-HD
 */
public class Decor extends Services{
    public Decor(int serviceId, String serviceName, double servicePrice) {
        super(serviceId, serviceName, servicePrice);
    }

    public Decor() {
    }

    @Override
    public void inputService() throws FileNotFoundException {
        super.inputService(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public void displayService() {
        super.displayService();
    }
    
    
}
