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
public class Singer extends Services{
    private String singerName;
    private int amountOfSongs;
    
    public Singer(int serviceId, String serviceName, double servicePrice, String singerName, int amountOfSongs) {
        super(serviceId, serviceName, servicePrice);
        this.singerName = singerName;
        this.amountOfSongs = amountOfSongs;
    }

    public Singer() {
    }

    @Override
    public void inputService() throws FileNotFoundException {
        super.inputService(); 
        Configuration.inputInfo("Ten ca si: ");
        this.singerName = Configuration.sc.nextLine();
        Configuration.inputInfo("So luong bai hat: ");
        this.amountOfSongs = Integer.parseInt(Configuration.sc.nextLine());
    }        

    @Override
    public void displayService() {

        System.out.printf("  %-17s %-30s %-12s %-25s %-17s      %-15d\n%s\n",
                this.getServiceId(), this.getServiceName(), this.getServicePrice(), "","   "+this.singerName,this.amountOfSongs,
                "------------------------------------------------------------------------------------------------------------------------------");
    
    }  
        
    /**
     * @return the singerName
     */
    public String getSingerName() {
        return singerName;
    }

    /**
     * @param singerName the singerName to set
     */
    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    /**
     * @return the amountOfSongs
     */
    public int getAmountOfSongs() {
        return amountOfSongs;
    }

    /**
     * @param amountOfSongs the amountOfSongs to set
     */
    public void setAmountOfSongs(int amountOfSongs) {
        this.amountOfSongs = amountOfSongs;
    }
    
}
