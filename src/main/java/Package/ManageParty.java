/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Lenovo Ideapad 5 Pro
 */
public class ManageParty {

    private List<Party> ds = new ArrayList<>();

    public ManageParty() throws FileNotFoundException {
        this.ds = this.readFile();
    }

    public void addParty(Party... party) {
        this.ds.addAll(Arrays.asList(party));
    }

    public List<Party> readFile() throws FileNotFoundException {
        List<Party> kq = new ArrayList<>();
        Scanner sf = new Scanner(Configuration.fileParty);
        while (sf.hasNextLine()) {
            String[] line = sf.nextLine().split("#");
            kq.add(new Party(LocalDate.parse(line[0], Configuration.DATE), line[1],new Lobby(line[2]), Double.parseDouble(line[3])));
        }
        if (kq.isEmpty()) {
            System.out.println("Loi.");
            return null;
        }
        sf.close();
        return kq;
    }

    public List<Party> getYear(String year) throws FileNotFoundException {
        return this.ds.stream().filter(x -> x.getDate().getYear() == Integer.parseInt(year)).collect(Collectors.toList());
    }

    public void statisticMonth(String year) throws FileNotFoundException {
        System.out.println("Thang 1: " + this.totalMonth(year, "1"));
        System.out.println("Thang 2: " + this.totalMonth(year, "2"));
        System.out.println("Thang 3: " + this.totalMonth(year, "3"));
        System.out.println("Thang 4: " + this.totalMonth(year, "4"));
        System.out.println("Thang 5: " + this.totalMonth(year, "5"));
        System.out.println("Thang 6: " + this.totalMonth(year, "6"));
        System.out.println("Thang 7: " + this.totalMonth(year, "7"));
        System.out.println("Thang 8: " + this.totalMonth(year, "8"));
        System.out.println("Thang 9: " + this.totalMonth(year, "9"));
        System.out.println("Thang 10: " + this.totalMonth(year, "10"));
        System.out.println("Thang 11: " + this.totalMonth(year, "11"));
        System.out.println("Thang 12: " + this.totalMonth(year, "12"));
    }

    public void statisticQuater(String year) throws FileNotFoundException {
        System.out.println("Quy 1: " + this.totalQuater(year, "1", "2", "3"));
        System.out.println("Quy 2: " + this.totalQuater(year, "4", "5", "6"));
        System.out.println("Quy 3: " + this.totalQuater(year, "7", "8", "9"));
        System.out.println("Quy 4: " + this.totalQuater(year, "10", "11", "12"));
    }

    public double totalMonth(String year, String month) throws FileNotFoundException {
        List<Party> list = this.getYear(year).stream().filter(x -> x.getDate().getMonth().getValue() == Integer.parseInt(month)).collect(Collectors.toList());
        return list.stream().mapToDouble(Party::getTotal).reduce(0, Double::sum);
    }

    public double totalQuater(String year, String month1, String month2, String month3) throws FileNotFoundException {
        return this.totalMonth(year, month1) + this.totalMonth(year, month2) + this.totalMonth(year, month3);
    }

public void arrrageFrequency() throws FileNotFoundException {
        ManageLobby manageLobby = new ManageLobby();
        List<Lobby> lobbyList = manageLobby.getDs();
        int[] count = new int[lobbyList.size()];
        Arrays.fill(count, 0);
        for (int i = 0; i < lobbyList.size(); i++) {
            Lobby lobby = lobbyList.get(i);
            for (Party p : this.ds) {
                if (lobby.getLobbyName().toLowerCase().equals((p.getLobby().getLobbyName().toLowerCase()))) {
                    count[i]++;
                }
            }
        }

        List<Lobby> sortLobbyList = lobbyList;
        Collections.sort(sortLobbyList,Comparator.comparing(s -> count[lobbyList.indexOf(s)]).reversed());
        Arrays.sort(count);
        for (Lobby l : sortLobbyList) {
            System.out.println(l.getLobbyName() + ": " + count[sortLobbyList.size() - 1 - sortLobbyList.indexOf(l)] + " lan.");
        }
    }

    public void display() {
        this.ds.forEach(x -> System.out.println(x));
    }
    public void controllerParty() throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            int choice;
            do {
                Configuration.inputInfo("===WeddingRestaurant===\n");
                Configuration.inputInfo("1. Quan ly sanh.\n");
                Configuration.inputInfo("2. Quan ly thuc pham.\n");
                Configuration.inputInfo("3. Quan ly dich vu.\n");
                Configuration.inputInfo("4. Cho thue bua tiec.\n");
                Configuration.inputInfo("5. Sap xep sanh theo tan so sanh duoc cho thue.\n");
                Configuration.inputInfo("6. Tim kiem bua tiec theo nam.\n");
                Configuration.inputInfo("7. Xuat bao cao theo thang.\n");
                Configuration.inputInfo("8. Xuat bao cao theo quy.\n");
                Configuration.inputInfo("0. Thoat.\n");
                Configuration.inputInfo("Moi nhap lua chon cua ban: ");
                choice = Configuration.sc.nextInt();
                Configuration.sc.nextLine();
                
                switch(choice) {
                    case 1 -> {
                        ManageLobby ml = new ManageLobby();
                        ml.lobbyController();
                        break;
                    }
                    case 2 -> {
                        ManageProducts mp = new ManageProducts();
                        mp.controllerProducts();
                        break;
                    }
                    case 3 -> {
                        ManageServices ms = new ManageServices();
                        ms.controllerServices();
                        break;
                    }
                    case 4 -> {
                        Party p = new Party();
                        p.inputParty();
                        addParty(p);
                        p.displayParty();
                        break;
                    }
                    case 5 -> {
                        this.arrrageFrequency();
                        break;
                    }
                    case 6 -> {   
                        System.out.println("Moi nhap nam: ");
                         String year=Configuration.sc.nextLine();
                        List<Party> list=this.getYear(year);
                        list.forEach(x->System.out.println(x));
                        break;
                    }
                    case 7 -> {
                        System.out.println("Moi nhap nam: ");
                        String year=Configuration.sc.nextLine();
                        System.out.println("Bao cao theo thang: ");
                        this.statisticMonth(year);
                        break;
                    }
                    case 8 -> {
                        System.out.println("Moi nhap nam: ");
                        String year=Configuration.sc.nextLine();
                        System.out.println("Bao cao theo quy: ");
                        this.statisticQuater(year);
                        break;
                    }
                    case 0 -> {
                        Configuration.inputInfo("Thanks.Goodbye.\n");
                        break;
                    }
                    default -> Configuration.inputInfo("ERROR.\n");
                } 
            } while(choice != 0);
        }

}
