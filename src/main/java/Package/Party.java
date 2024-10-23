/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo Ideapad 5 Pro
 */
public class Party {

    private String partyName;
    private Lobby lobby;
    private LocalDate date;
    private String timeOfDay;
    private ManageLobby manageLobby;
    private ManageServices manageServices;
    private ManageProducts manageProducts;
    private LobbyPrice lobbyPrice;
    private List<Products> productList;
    private List<Services> servicesList;
    private double total;

    public Party() throws FileNotFoundException {
        this.partyName = new String();
        this.lobby = new Lobby();
        this.timeOfDay = new String();
        this.manageLobby = new ManageLobby();
        this.manageProducts = new ManageProducts();
        this.manageServices = new ManageServices();
        this.lobbyPrice = new LobbyPrice();
        this.productList = new ArrayList<>();
        this.servicesList = new ArrayList<>();
    }

    public Party(LocalDate date, String partyName, Lobby lobby, double price) {
        this.date = date;
        this.lobby = lobby;
        this.total = price;
        this.partyName = partyName;
    }

    public void inputParty() throws FileNotFoundException, IOException {
        System.out.print("Moi nhap ten bua tiec: ");
        this.setPartyName(Configuration.sc.nextLine());
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.print("Moi nhap ngay thue(dd/MM/yyyy): ");
        this.setDate(LocalDate.parse(Configuration.sc.nextLine(), Configuration.DATE));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.print("Moi chon thoi diem(sang, chieu, toi): ");
        this.setTimeOfDay(Configuration.sc.nextLine());
        while (!this.timeOfDay.toLowerCase().equals("sang")
                && !this.timeOfDay.toLowerCase().equals("chieu")
                && !this.timeOfDay.toLowerCase().equals("toi")) {
            System.out.print("Thoi diem khong hop le, moi nhap lai: ");
            this.setTimeOfDay(Configuration.sc.nextLine());
        }
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("---------------------------------DANH SACH SANH-----------------------------------------");
        this.getManageLobby().displayManageLobby();
        System.out.print("Moi chon sanh muon thue: ");
        this.setLobby(this.getManageLobby().searchLobby(Configuration.sc.nextLine()).get(0));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("------------------------------DANH SACH THUC PHAM---------------------------------------");
        this.getManageProducts().displayManageProduct();
        System.out.println("Moi chon thuc pham vao thuc don(Nhap \"xong\" khi ban da chon xong): ");
        String add = "";
        do {
            add = Configuration.sc.nextLine();
            if (add.toLowerCase().equals("xong")) {
                break;
            }
            this.getProductList().add(getManageProducts().findProducts(add).get(0));
        } while (!add.toLowerCase().equals("xong"));
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("-------------------------------DANH SACH DICH VU----------------------------------------");
        this.getManageServices().displayManageService();
        int check = 1;
        System.out.println("Moi chon ma dich vu vao thuc don(Nhap \"0\" khi ban da chon xong): ");
        while (check != 0) {
            check = Integer.parseInt(Configuration.sc.nextLine());
            if (check == 0) {
                break;
            }
            this.getServicesList().add(getManageServices().findIdService(check).get(0));
        }
        this.writeFile();
    }

    public double totalPrice() {
        double totalProductsPrice = 0, totalServicesPrice = 0;
        for (Products x : this.getProductList()) {
            totalProductsPrice += x.getProductPrice();
        }
        for (Services x : this.getServicesList()) {
            totalServicesPrice += x.getServicePrice();
        }
        this.setTotal(this.getLobby().getLobbyPrice() * (getLobbyPrice().priceDayofWeek(getDate()) + getLobbyPrice().priceTimeofDay(this.getTimeOfDay())) + totalProductsPrice + totalServicesPrice);
        return this.getTotal();
    }

    public void displayParty() {
        System.out.println("==================== HOA DON =====================");
        System.out.println("Ten bua tiec: " + this.partyName);
        System.out.println("Thoi gian: " + this.date.format(Configuration.DATE));
        System.out.println("\t\t\t" + String.format("%.1f", this.lobbyPrice.priceDayofWeek(date)));
        System.out.println("Thoi diem: " + this.timeOfDay);
        System.out.println("\t\t\t" + String.format("%.1f", this.lobbyPrice.priceTimeofDay(this.timeOfDay)));
        System.out.println(this.lobby.getLobbyName());
        System.out.println("\t\t\t" + this.lobby.getLobbyPrice());
        System.out.println("Thuc don:");
        this.productList.forEach(x -> {
            System.out.println("\t" + x.getProductName());
            System.out.println("\t\t\t" + x.getProductPrice());
        });
        System.out.println("Dich vu:");
        this.servicesList.forEach(x -> {
            System.out.println("\t" + x.getServiceName());
            System.out.println("\t\t\t" + x.getServicePrice());
        });
        System.out.println("Tong hoa don:");
        System.out.println("\t\t\t"+this.totalPrice());
    }

    public void writeFile() throws IOException {
        FileWriter fw = new FileWriter(Configuration.fileParty, true);
        try {
            fw.write(this.date.format(Configuration.DATE) + "#" + this.partyName + "#" + this.lobby.getLobbyName() + "#" + this.totalPrice() + "\n");
        } catch (IOException e) {
            System.out.println("Khong the ghi.");
        }
        fw.close();
    }

    @Override
    public String toString() {
        return String.format("%s\n%-10s%s\n%-10s%s\n%-10s%s\n%-10s%.0f\n%s",
                "-------------------------",
                "Ngay thue: ", this.date.format(Configuration.DATE),
                "Ten tiec: ", this.partyName,
                "Ten sanh: ", this.lobby.getLobbyName(),
                "Doanh thu: ", this.total,
                "-------------------------");
    }

    /**
     * @return the partyName
     */
    public String getPartyName() {
        return partyName;
    }

    /**
     * @param partyName the partyName to set
     */
    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    /**
     * @return the lobby
     */
    public Lobby getLobby() {
        return lobby;
    }

    /**
     * @param lobby the lobby to set
     */
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }

    /**
     * @return the date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return the timeOfDay
     */
    public String getTimeOfDay() {
        return timeOfDay;
    }

    /**
     * @param timeOfDay the timeOfDay to set
     */
    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    /**
     * @return the manageLobby
     */
    public ManageLobby getManageLobby() {
        return manageLobby;
    }

    /**
     * @param manageLobby the manageLobby to set
     */
    public void setManageLobby(ManageLobby manageLobby) {
        this.manageLobby = manageLobby;
    }

    /**
     * @return the manageServices
     */
    public ManageServices getManageServices() {
        return manageServices;
    }

    /**
     * @param manageServices the manageServices to set
     */
    public void setManageServices(ManageServices manageServices) {
        this.manageServices = manageServices;
    }

    /**
     * @return the manageProducts
     */
    public ManageProducts getManageProducts() {
        return manageProducts;
    }

    /**
     * @param manageProducts the manageProducts to set
     */
    public void setManageProducts(ManageProducts manageProducts) {
        this.manageProducts = manageProducts;
    }

    /**
     * @return the lobbyPrice
     */
    public LobbyPrice getLobbyPrice() {
        return lobbyPrice;
    }

    /**
     * @param lobbyPrice the lobbyPrice to set
     */
    public void setLobbyPrice(LobbyPrice lobbyPrice) {
        this.lobbyPrice = lobbyPrice;
    }

    /**
     * @return the productList
     */
    public List<Products> getProductList() {
        return productList;
    }

    /**
     * @param productList the productList to set
     */
    public void setProductList(List<Products> productList) {
        this.productList = productList;
    }

    /**
     * @return the servicesList
     */
    public List<Services> getServicesList() {
        return servicesList;
    }

    /**
     * @param servicesList the servicesList to set
     */
    public void setServicesList(List<Services> servicesList) {
        this.servicesList = servicesList;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }
}
