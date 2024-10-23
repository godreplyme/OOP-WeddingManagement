/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Dell 5490-HD
 */
public class ManageServices {

    private List<Services> listSer = new ArrayList<>();

    public ManageServices() throws FileNotFoundException {
        this.listSer = readListService();
    }

    public void addService(String path) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, FileNotFoundException {
        Class c = Class.forName(path);
        Services s = (Services) c.getConstructor().newInstance();
        s.inputService();
        this.listSer.add(s);
    }

    public void deleteService(String kw) throws FileNotFoundException {
        this.listSer.removeIf(s -> s.getServiceName().toLowerCase().equals(kw.toLowerCase()));
    }

    public List<Services> findService(String kw) throws FileNotFoundException {
        List<Services> kq = new ArrayList<>();
        kq = this.getListSer().stream().filter(s -> s.getServiceName().toLowerCase().contains(kw.toLowerCase())).collect(Collectors.toList());
        if (kq.isEmpty()) {
            Configuration.inputInfo("NOT FIND!\n");
        }
        return kq;
    }
    public List<Services> findIdService(int id)
    {
        List<Services> kq =new ArrayList<>();
        kq=this.listSer.stream().filter(s->s.getServiceId()==id).collect(Collectors.toList());
        if(kq.isEmpty())
        {
            Configuration.inputInfo("Khong tim thay dich vu.\n");
        }
        return kq;
    }

    public List<Services> readListService() throws FileNotFoundException {
        File f = new File("src/main/sources/services");
        try (Scanner scf = new Scanner(f)) {
            while (scf.hasNextLine()) {
                String line = scf.nextLine();
                if (line.startsWith("*")) {
                    String[] parts = line.split("_");
                    this.listSer.add(new Services(Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3])));
                } else if (line.startsWith("=")) {
                    String[] parts = line.split("_");
                    this.listSer.add(new Singer(Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]), parts[4], Integer.parseInt(parts[5])));
                } else if (line.startsWith("+")) {
                    String[] parts = line.split("_");
                    this.listSer.add(new Decor(Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3])));
                } else if (line.startsWith("-")) {
                    String[] parts = line.split("_");
                    this.listSer.add(new Karaoke(Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]), Integer.parseInt(parts[1])));
                }
            }
            scf.close();
        }
        return this.listSer;
    }

    public void updateService() throws IOException {
        FileWriter fws = new FileWriter("src/main/sources/services");
        this.listSer.forEach(s -> {
            try {
                if (s instanceof Singer) {
                    fws.write("=" + "_" + s.getServiceId() + "_" + s.getServiceName() + "_" + s.getServicePrice() + "_" + ((Singer) s).getSingerName() + "_" + ((Singer) s).getAmountOfSongs() + "\n");
                } else if (s instanceof Decor) {
                    fws.write("+" + "_" + s.getServiceId() + "_" + s.getServiceName() + "_" + s.getServicePrice() + "\n");
                } else if (s instanceof Karaoke) {
                    fws.write("-" + "_" + s.getServiceId() + "_" + s.getServiceName() + "_" + s.getServicePrice() + "_" + ((Karaoke) s).getRentTime() + "\n");
                }else if (s instanceof Services) {
                    fws.write("*" + "_" + s.getServiceId() + "_" + s.getServiceName() + "_" + s.getServicePrice() + "\n");
                }
            } catch (IOException w) {
                System.out.println("");
            }
        });
        fws.close();
    }

    public void titleServices(){
        System.out.printf("  %-17s %-30s %-12s %-25s %-17s %-20s\n%s\n",
                "MA DICH VU", "TEN DICH VU", "GIA", "THOI LUONG(PHUT)","TEN CA SI","SO LUONG BAI HAT",
                "----------------------------------------------------------------------------------------------------------------------------");
    }
    public void displayManageService() throws FileNotFoundException {
        this.titleServices();
        this.listSer.forEach(s -> s.displayService());
    }

    /**
     * @return the listSer
     */
    public List<Services> getListSer() {
        return listSer;
    }

    /**
     * @param listSer the listSer to set
     */
    public void setListSer(List<Services> listSer) {
        this.listSer = listSer;
    }

    public void controllerServices() throws FileNotFoundException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {

        int choice;
        do {
            Configuration.inputInfo("===SERVICE===\n");
            Configuration.inputInfo("1. Them dich vu.\n");
            Configuration.inputInfo("2. Xoa dich vu.\n");
            Configuration.inputInfo("3. Tim dich vu.\n");
            Configuration.inputInfo("4. Cap nhat dich vu.\n");
            Configuration.inputInfo("5. Hien thi danh sach dich vu.\n");
            Configuration.inputInfo("0. Thoat.\n");
            Configuration.inputInfo("Moi nhap lua chon cua ban: ");
            choice = Configuration.sc.nextInt();
            Configuration.sc.nextLine();

            switch (choice) {
                case 1 -> {
                    Configuration.inputInfo("Moi Chon loai dich vu(Decor, Karaoke, Singer): ");
                    String path = Configuration.classPath + Configuration.sc.nextLine();
                    addService(path);
                    break;
                }
                case 2 -> {
                    Configuration.inputInfo("Moi nhap ten dich vu muon xoa: ");
                    String kw = Configuration.sc.nextLine();
                    deleteService(kw);
                    Configuration.inputInfo("Danh sach dich vu sau khi xoa: \n");
                    displayManageService();
                    break;
                }
                case 3 -> {
                    Configuration.inputInfo("Moi nhap tu khoa muon tim kiem: ");
                    String kw = Configuration.sc.nextLine();
                    Configuration.inputInfo("Ket qua:\n");
                    this.titleServices();
                    findService(kw).forEach(s -> s.displayService());
                    break;
                }
                case 4 -> {
                    updateService();
                    break;
                }
                case 5 -> {
                    Configuration.inputInfo("Danh sach dich vu cua nha hang: \n");
                    displayManageService();
                    break;
                }
                case 0 -> {
                    Configuration.inputInfo("....\n");
                    break;
                }
                default ->
                    Configuration.inputInfo("Error.\n");
            }
        } while (choice != 0);
    }
}
