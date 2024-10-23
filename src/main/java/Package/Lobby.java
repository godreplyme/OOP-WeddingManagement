/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Lenovo Ideapad 5 Pro
 */
public class Lobby {

    private int lobbyId;
    private String lobbyName;
    private int lobbyLocation;
    private int lobbyCapacity;
    private double lobbyPrice;

    public Lobby(int id, String name, int location, int capacity,double price) {
        this.lobbyId = id;
        this.lobbyName = name;
        this.lobbyLocation = location;
        this.lobbyCapacity = capacity;
        this.lobbyPrice=price;
    }
    
    public Lobby(String name)
    {
        this.lobbyName=name;
    }

    public Lobby() {
    }

    public void inputLobby() throws FileNotFoundException {
        System.out.print("Moi nhap ten Sanh: ");
        this.setLobbyName(Configuration.sc.nextLine());
        File file = new File("src/main/sources/lobby");
        Scanner sc = null;
        String last=new String();
        //--------------------check ten sanh xem no co trung hay ko----------------------
        try {
            sc = new Scanner(file);
            boolean flag = false;
            while (flag == false) {
                int dem = 1;
                while (sc.hasNextLine()) {
                    String[] line = sc.nextLine().split("#");    
                    if (line[1].toLowerCase().equals(this.getLobbyName().toLowerCase())) {
                        System.out.println(line[1]);
                        System.out.printf("Ten sanh da ton tai, vui long nhap lai: ");
                        this.setLobbyName(Configuration.sc.nextLine());
                    }
                    this.setLobbyId(++dem);
                } 
                flag = true;
            }
        } catch (IOException e) {
            System.out.println("Loi doc file.");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        //-------------------------------------------------------------------------------
        System.out.print("Moi nhap tang: ");
        this.setLobbyLocation(Integer.parseInt(Configuration.sc.nextLine()));
        System.out.print("Moi nhap suc chua: ");
        this.setLobbyCapacity(Integer.parseInt(Configuration.sc.nextLine()));
        System.out.print("Moi nhap gia: ");
        this.setLobbyPrice(Double.parseDouble(Configuration.sc.nextLine()));
    }
    public void displayLobby()
    {
        System.out.printf("  %s%03d        %-15s %-15s %-10s %15.0f \n%s\n",
						"S",this.lobbyId, this.lobbyName.toUpperCase(),
                                                this.lobbyLocation,
						this.lobbyCapacity, this.getLobbyPrice(), "----------------" +
								"----------------------------------------------" +
								"--------------------------");
    }

    @Override
    public String toString() {
        return this.getLobbyName();
    }

    /**
     * @return the lobbyId
     */
    public int getLobbyId() {
        return lobbyId;
    }

    /**
     * @param lobbyId the lobbyId to set
     */
    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    /**
     * @return the lobbyName
     */
    public String getLobbyName() {
        return lobbyName;
    }

    /**
     * @param lobbyName the lobbyName to set
     */
    public void setLobbyName(String lobbyName) {
        this.lobbyName = lobbyName;
    }

    /**
     * @return the lobbyLocation
     */
    public int getLobbyLocation() {
        return lobbyLocation;
    }

    /**
     * @param lobbyLocation the lobbyLocation to set
     */
    public void setLobbyLocation(int lobbyLocation) {
        this.lobbyLocation = lobbyLocation;
    }

    /**
     * @return the lobbyCapacity
     */
    public int getLobbyCapacity() {
        return lobbyCapacity;
    }

    /**
     * @param lobbyCapacity the lobbyCapacity to set
     */
    public void setLobbyCapacity(int lobbyCapacity) {
        this.lobbyCapacity = lobbyCapacity;
    }

    /**
     * @return the lobbyPrice
     */
    public double getLobbyPrice() {
        return lobbyPrice;
    }

    /**
     * @param lobbyPrice the lobbyPrice to set
     */
    public void setLobbyPrice(double lobbyPrice) {
        this.lobbyPrice = lobbyPrice;
    }
    
}
