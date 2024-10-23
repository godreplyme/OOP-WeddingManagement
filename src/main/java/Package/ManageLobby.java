
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo Ideapad 5 Pro
 */
public class ManageLobby {

    private List<Lobby> ds = new ArrayList<>();

    public ManageLobby() throws FileNotFoundException {
        ds = this.readFile();
    }

    public void addLobby() throws FileNotFoundException, IOException {
        Lobby l = new Lobby();
        l.inputLobby();
        this.getDs().add(l);
    }

    public void deleteLobby(String name) throws FileNotFoundException, IOException {
        boolean check = this.getDs().removeIf(a -> a.getLobbyName().toLowerCase().equals(name.toLowerCase()));
        if (check == true) {
            System.out.println("Xoa thanh cong.");
        } else {
            System.out.println("Xoa khong thanh cong, vui long kiem tra lai ten Sanh muon xoa.");
        }
        this.getDs().forEach(a -> a.setLobbyId(getDs().indexOf(a) + 1));
    }

    public List<Lobby> searchLobby(String kw) throws FileNotFoundException {
        List<Lobby> kq = this.getDs().stream().filter(a -> a.getLobbyName().toLowerCase().contains(kw.toLowerCase())).collect(Collectors.toList());
        if (kq.isEmpty()) {
            System.out.println("Khong tim ra sanh.");
        }
        return kq;
    }

    public List<Lobby> searchLobby(int location) throws FileNotFoundException {
        List<Lobby> kq = this.getDs().stream().filter(a -> a.getLobbyLocation() == location).collect(Collectors.toList());
        if (kq.isEmpty()) {
            System.out.println("Khong tim ra sanh.");
        }
        return kq;
    }

    public List<Lobby> searchLobby(int capacity, int b) throws FileNotFoundException {
        List<Lobby> kq = this.getDs().stream().filter(a -> a.getLobbyCapacity() == capacity).collect(Collectors.toList());
        if (kq.isEmpty()) {
            System.out.println("Khong tim ra sanh.");
        }
        return kq;
    }

    public List<Lobby> readFile() throws FileNotFoundException {
        List<Lobby> ds = new ArrayList<>();
        Scanner sf = new Scanner(Configuration.file);
        while (sf.hasNextLine()) {
            String[] line = sf.nextLine().split("#");
            ds.add(new Lobby(Integer.parseInt(line[0]), line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3]), Double.parseDouble(line[4])));
        }
        sf.close();
        return ds;
    }

    public void updateLobby() throws FileNotFoundException, IOException {
        FileWriter fw = new FileWriter(Configuration.file);
        this.getDs().forEach(x -> {
            try {
                fw.write(x.getLobbyId() + "#" + x.getLobbyName() + "#" + x.getLobbyLocation() + "#" + x.getLobbyCapacity() + "#" + String.format("%.0f", x.getLobbyPrice()) + "\n");
            } catch (IOException e) {
                System.out.println("Khong the ghi.");
            }
        });
        fw.close();
    }

    public void titleLobby() {
        System.out.printf("  %-10s %-15s %-15s %-15s %10s \n%s\n",
                "MA SANH", "TÊN SANH", "VI TRI SANH", "SUC CHUA", "GIA SANH",
                "----------------------------------------------"
                + "------------------------------------------");
    }

    public void displayManageLobby() throws FileNotFoundException {
        titleLobby();
        this.getDs().forEach(a -> a.displayLobby());
    }

    public void lobbyController() throws FileNotFoundException, IOException {
        int choice;
        do {
            System.out.println("==========QUAN LI SANH==========");
            System.out.println("1. Hien thi sanh.");
            System.out.println("2. Them sanh.");
            System.out.println("3. Xoa sanh.");
            System.out.println("4. Tra cuu sanh.");
            System.out.println("5. Cap nhat sanh.");
            System.out.println("0. Thoat.");
            System.out.println("================================");
            System.out.print("Nhap lua chon cua ban: ");

            choice = Integer.parseInt(Configuration.sc.nextLine());
            while (choice < 0 || choice > 5) {
                System.out.println("Lua chon khong hop li, moi ban chon lai: ");
                choice = Integer.parseInt(Configuration.sc.nextLine());
            }

            switch (choice) {
                case 1:
                    System.out.println("==========SANH==========");
                    this.displayManageLobby();
                    break;
                case 2:
                    System.out.println("==========THEM SANH==========");
                    this.addLobby();
                    System.out.println("Danh sach sanh sau khi them:");
                    this.displayManageLobby();
                    break;
                case 3:
                    System.out.println("==========XOA SANH==========");
                    System.out.print("Moi nhap ten sanh muon xoa: ");
                    String newName = Configuration.sc.nextLine();
                    this.deleteLobby(newName);
                    System.out.println("Danh sach sanh sau khi xoa: ");
                    this.displayManageLobby();
                    break;
                case 4:
                    int choice1;
                    do {
                        System.out.println("==========TRA CUU SANH==========");
                        System.out.println("1. Tra cuu theo tu khoa.");
                        System.out.println("2. Tra cuu theo suc chua.");
                        System.out.println("3. Tra cuu theo vi tri.");
                        System.out.println("0. Thoat.");
                        System.out.println("================================");
                        System.out.print("Moi nhap lua chon:  ");
                        choice1 = Integer.parseInt(Configuration.sc.nextLine());
                        switch (choice1) {
                            case 1:
                                System.out.print("Moi nhap tu khoa tim kiem: ");
                                String kw = Configuration.sc.nextLine();
                                this.titleLobby();
                                this.searchLobby(kw).forEach(x -> x.displayLobby());
                                break;
                            case 2:
                                System.out.print("Moi nhap suc chua: ");
                                int capacity = Integer.parseInt(Configuration.sc.nextLine());
                                this.titleLobby();
                                this.searchLobby(capacity, 0).forEach(x -> x.displayLobby());
                                break;
                            case 3:
                                System.out.print("Moi nhap vi tri: ");
                                int location = Integer.parseInt(Configuration.sc.nextLine());
                                this.titleLobby();
                                this.searchLobby(location).forEach(x -> x.displayLobby());
                                break;
                            case 0:
                                break;
                        }
                    } while (choice1 != 0);
                    break;
                case 5:
                    this.updateLobby();
                    this.setDs(readFile());
                    break;
            }

        } while (choice != 0);
    }

    /**
     * @return the ds
     */
    public List<Lobby> getDs() {
        return ds;
    }

    /**
     * @param ds the ds to set
     */
    public void setDs(List<Lobby> ds) {
        this.ds = ds;
    }
}
