/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Dell 5490-HD
 */
public class Services {
    
    private int serviceId;
    private String serviceName;
    private double servicePrice;
    
    public Services(int serviceId, String serviceName, double servicePrice) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }
    
    public Services() {
        
    }
    
    public void inputService() throws FileNotFoundException {
        File f = new File("src/main/sources/services");
        Configuration.inputInfo("---Please enter the Service---\n");
        Configuration.inputInfo("Service Id: ");
        this.serviceId = Integer.parseInt(Configuration.sc.nextLine());
        try (Scanner scf = new Scanner(f)) {
            boolean check = false;
            while (!check) {
                Configuration.inputInfo("Service name: ");
                this.setServiceName(Configuration.sc.nextLine());
                while (scf.hasNextLine()) {
                    String[] parts = scf.nextLine().split("_");                    
                    if (parts[2].toLowerCase().equals(getServiceName().toLowerCase())) {
                        Configuration.inputInfo("The Service name already exists. Please enter another name.\n");
                        this.setServiceName(Configuration.sc.nextLine());
                    }
                    check = true;
                }
            }
            scf.close();
        }
        Configuration.inputInfo("Service price: ");
        this.servicePrice = Double.parseDouble(Configuration.sc.nextLine());
    }
    
    
    public void displayService(){
        System.out.printf("  %-17s %-30s %-12s %-25s %-17s %-20s\n%s\n",
                this.serviceId, this.serviceName, this.servicePrice, "","","",
                "------------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * @return the serviceId
     */
    public int getServiceId() {
        return serviceId;
    }

    /**
     * @param serviceId the serviceId to set
     */
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * @return the serviceName
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * @param serviceName the serviceName to set
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * @return the servicePrice
     */
    public double getServicePrice() {
        return servicePrice;
    }

    /**
     * @param servicePrice the servicePrice to set
     */
    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }
    
}
