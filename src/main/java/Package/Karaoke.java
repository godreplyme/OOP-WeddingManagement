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
public class Karaoke extends Services{
    private int rentTime;
    public Karaoke(int serviceId, String serviceName, double servicePrice, int rentTime) {
        super(serviceId, serviceName, servicePrice);
        this.rentTime = rentTime;
    }

    public Karaoke() {
    }   
    
    @Override
    public void inputService() throws FileNotFoundException {
        super.inputService(); 
        Configuration.inputInfo("Rent time(minute): ");
        this.rentTime = Integer.parseInt(Configuration.sc.nextLine());
    }    

    @Override
    public void displayService() {

        System.out.printf("  %-17s %-30s %-12s      %-20s %-17s %-20s\n%s\n",
                this.getServiceId(), this.getServiceName(),this.getServicePrice(),this.rentTime,"","",
                "-----------------------------------------------------------------------------------------------------------------------------");
    
    }
        
    /**
     * @return the rentTime
     */
    public int getRentTime() {
        return rentTime;
    }

    /**
     * @param rentTime the rentTime to set
     */
    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }
}
