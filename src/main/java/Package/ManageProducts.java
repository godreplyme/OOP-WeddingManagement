///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Package;
//
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
///**
// *
// * @author Lenovo Ideapad 5 Pro
// */
//public class ManageProducts {
//
//    public List<Foods> readFood() throws FileNotFoundException {
//        List<Foods> ds = new ArrayList<>();
//        Scanner sf = new Scanner(Configuration.fileFood);
//        while (sf.hasNextLine()) {
//            String[] line = sf.nextLine().split("#");
//            ds.add(new Foods(Integer.parseInt(line[0]), line[1], Double.parseDouble(line[2]), Boolean.parseBoolean(line[3])));
//        }
//        sf.close();
//        return ds;
//    }
//
//    public List<Drinks> readDrink() throws FileNotFoundException {
//        List<Drinks> ds = new ArrayList<>();
//        Scanner sf = new Scanner(Configuration.fileDrink);
//        while (sf.hasNextLine()) {
//            String[] line = sf.nextLine().split("#");
//            ds.add(new Drinks(Integer.parseInt(line[0]), line[1], Double.parseDouble(line[2]), line[3]));
//        }
//        sf.close();
//        return ds;
//    }
//
//    public void writeFood(Foods f) {
//        try {
//            FileWriter fw = new FileWriter(Configuration.fileFood, true);
//            fw.write(f.getProductId() + "#" + f.getProductName() + "#" + String.format("%.0f", f.getProductPrice()) + "#" + f.isKindFood() + "\n");
//            fw.close();
//        } catch (IOException e) {
//            System.out.println("Khong the ghi.");
//        }
//    }
//
//    public void writeDrink(Drinks d) {
//        try {
//            FileWriter fw = new FileWriter(Configuration.fileDrink, true);
//            fw.write(d.getProductId() + "#" + d.getProductName() + "#" + String.format("%.0f", d.getProductPrice()) + "#" + d.getManufacturer() + "\n");
//            fw.close();
//        } catch (IOException e) {
//            System.out.println("Khong the ghi.");
//        }
//    }
//
//    public void addFood() {
//        Products f = new Foods();
//        f.inputProduct();
//        writeFood((Foods) f);
//    }
//    
//    public void titleProduct(){
//        System.out.printf("  %-17s %-17s %-30s %-12s %-17s %-17s \n%s\n",
//                "LOAI THUC PHAM", "MA THUC PHAM", "TEN THUC PHAM", "GIA", "LOAI MON AN","NHA SAN XUAT",
//                "------------------------------------------------------------------------------------------------------------------");
//    }
//    
//    public void displayManageProduct() throws FileNotFoundException{
//        titleProduct();
//        List<Foods> flist= readFood();
//        List<Drinks> dlist=readDrink();
//        flist.forEach(x->x.displayProduct());
//        dlist.forEach(x->x.displayProduct());
//    }
//
//    public void productController() {
//        int choice;
//        do{
//            System.out.println("==========QUAN LI THUC PHAM==========");
//            System.out.println("1. Hien thi thuc pham.");
//            System.out.println("2. Them thuc an.");
//            System.out.println("3. Them nuoc uong.");
//            System.out.println("4. Xoa thuc an.");
//            System.out.println("5. Xoa nuoc uong.");
//            System.out.println("6. Cap nhat thuc an.");
//            System.out.println("7. Cap nhat nuoc uong.");
//            System.out.println("8. Tra cuu thuc pham.");
//            System.out.println("0. Thoat.");
//            System.out.println("=====================================");
//            System.out.print("Moi nhap lua chọn: ");
//            choice=Integer.parseInt(Configuration.sc.nextLine());
//            while(choice<0||choice>8)
//            {
//                System.out.print("Lua chon khong hop le, moi nhap lai: ");
//                choice=Integer.parseInt(Configuration.sc.nextLine());
//            }
//            switch(choice)
//            {
//                
//            }
//        }while(choice!=0);
//
//    }
//}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Dell 5490-HD
 */
public class ManageProducts {

    private List<Products> listPro = new ArrayList<>();

    public ManageProducts() throws FileNotFoundException {
        this.listPro = readFileFoods();
        this.listPro = readFileDrinks();
    }

    public void addProducts(Products p) {
        this.listPro.add(p);
    }

    public void deleteProducts(String kw) {
        this.listPro.removeIf(p -> p.getProductName().toLowerCase().equals(kw.toLowerCase()));
    }

    public List<Products> findProducts(String kw) {
        List<Products> kq = new ArrayList<>();
        kq = this.listPro.stream().filter(p -> p.getProductName().toLowerCase().equals(kw.toLowerCase())).collect(Collectors.toList());
        if (kq.isEmpty()) {
            Configuration.inputInfo("NOT FIND.\n");
        }
        return kq;
    }

    public List<Products> readFileFoods() throws FileNotFoundException {
        File f = new File("src/main/sources/food");
        try (Scanner scf = new Scanner(f)) {
            while (scf.hasNextLine()) {
                String[] parts = scf.nextLine().split("_");
                this.listPro.add(new Foods(parts[0], Double.parseDouble(parts[1]), Boolean.parseBoolean(parts[2])));
            }
        }
        return this.listPro;
    }

    public List<Products> readFileDrinks() throws FileNotFoundException {
        File f = new File("src/main/sources/drink");
        try (Scanner scf = new Scanner(f)) {
            while (scf.hasNextLine()) {
                String[] parts = scf.nextLine().split("_");
                this.listPro.add(new Drinks(parts[0], Double.parseDouble(parts[1]), parts[2]));
            }
            scf.close();
        }
        return this.listPro;
    }

    public void updateFoods() throws IOException {
        FileWriter fwp = new FileWriter("src/main/sources/food");
        this.listPro.forEach(p -> {
            try {
                if (p instanceof Foods) {
                    fwp.write(p.getProductName() + "_" + p.getProductPrice() + "_" + ((Foods) p).isIsChay() + "\n");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        });
        fwp.close();
    }

    public void updateDrinks() throws IOException {
        FileWriter fwp = new FileWriter("src/main/sources/drink");
        this.listPro.forEach(p -> {
            try {
                if (p instanceof Drinks) {
                    fwp.write(p.getProductName() + "_" + p.getProductPrice() + "_" + ((Drinks) p).getManufactuer() + "\n");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
            }
        });
        fwp.close();
    }

    public void titleProduct(){
        System.out.printf("  %-17s %-17s %-30s %-12s %-17s %-17s \n%s\n",
                "LOAI THUC PHAM", "MA THUC PHAM", "TEN THUC PHAM", "GIA", "LOAI MON AN","NHA SAN XUAT",
                "------------------------------------------------------------------------------------------------------------------");
    }
    public void displayManageProduct() throws FileNotFoundException{
        titleProduct();
        this.listPro.forEach(x->x.displayProduct());
    }

    public void controllerProducts() throws FileNotFoundException, IOException {
        int choice;
        do {
            Configuration.inputInfo("===THUC PHAM===\n");
            Configuration.inputInfo("1. Them thuc pham.\n");
            Configuration.inputInfo("2. Xoa thuc pham.\n");
            Configuration.inputInfo("3. Tim thuc pham.\n");
            Configuration.inputInfo("4. Cap nhat thuc pham.\n");
            Configuration.inputInfo("5. Hien thi thuc pham.\n");
            Configuration.inputInfo("0. Thoat.\n");
            Configuration.inputInfo("Moi nhap lua chon: ");
            choice = Configuration.sc.nextInt();
            Configuration.sc.nextLine();


            switch (choice) {
                case 1 -> {
                    do {
                        Configuration.inputInfo("===Them thuc pham===\n");
                        Configuration.inputInfo("6. Them thuc an.\n");
                        Configuration.inputInfo("7. Them nuoc uong.\n");
                        Configuration.inputInfo("8. Thoat.\n");
                        Configuration.inputInfo("Moi nhap lua chon cua ban: ");
                        choice = Configuration.sc.nextInt();
                        Configuration.sc.nextLine();

                        switch (choice) {
                            case 6 -> {
                                Foods f = new Foods();
                                Configuration.inputInfo("Moi nhap thuc an:\n");
                                f.inputProduct();
                                addProducts(f);
                                Configuration.inputInfo("Them thanh cong.\n");
                                break;
                            }
                            case 7 -> {
                                Drinks d = new Drinks();
                                Configuration.inputInfo("Moi nhap nuoc uong:\n");
                                d.inputProduct();
                                addProducts(d);
                                Configuration.inputInfo("Them thanh cong.\n");
                                break;
                            }
                            case 8 -> {
                                break;
                            }
                            default ->
                                Configuration.inputInfo("ERROR!!!\n");
                        }
                    } while (choice != 8);
                    break;
                }
                case 2 -> {
                    Configuration.inputInfo("Nhap ten thuc pham ban muon xoa: ");
                    String kw = Configuration.sc.nextLine();
                    deleteProducts(kw);
                    Configuration.inputInfo("Danh sach thuc pham sau khi xoa: \n");
                    displayManageProduct();
                    break;
                }
                case 3 -> {
                    Configuration.inputInfo("Nhap ten Product ban muon tim: ");
                    String kw = Configuration.sc.nextLine();
                    Configuration.inputInfo("Product ma ban tim: \n");
                    this.titleProduct();
                    findProducts(kw).forEach(p -> p.displayProduct());
                    break;
                }
                case 4 -> {
                    updateFoods();
                    updateDrinks();
                    break;
                }
                case 5 -> {
                    Configuration.inputInfo("Danh sach thuc pham dang co\n");
                    displayManageProduct();
                    break;
                }
                case 0 -> {
                    Configuration.inputInfo("Goodbye!");
                    break;
                }
                default ->
                    Configuration.inputInfo("ERROR");
            }
        } while (choice != 0);
    }

    /**
     * @return the listPro
     */
    public List<Products> getListPro() {
        return listPro;
    }

    /**
     * @param listPro the listPro to set
     */
    public void setListPro(List<Products> listPro) {
        this.listPro = listPro;
    }
}
