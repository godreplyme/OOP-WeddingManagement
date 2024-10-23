///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Package;
//
///**
// *
// * @author Lenovo Ideapad 5 Pro
// */
//public class Drinks extends Products{
//    private String manufacturer;
//
//    public Drinks() {
//    }
//
//    public Drinks(int id, String name, double price,String manufacturer) {
//        super(id, name, price);
//        this.manufacturer = manufacturer;
//    }
//    
//    @Override
//    public void displayProduct(){
//        System.out.printf("   %-20s %s%-10s %-32s %-12.0f %-15s %-17s  \n%s\n","NUOC UONG","D",String.format("%03d", this.getProductId()),this.getProductName(),
//                this.getProductPrice(),"",this.manufacturer,"------------------------------------------------------------------------------------------------------------------");
//    }
//    @Override
//    public String name(){
//        return "nuoc uong";
//    }
//
//    /**
//     * @return the manufacturer
//     */
//    public String getManufacturer() {
//        return manufacturer;
//    }
//
//    /**
//     * @param manufacturer the manufacturer to set
//     */
//    public void setManufacturer(String manufacturer) {
//        this.manufacturer = manufacturer;
//    }
//}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

/**
 *
 * @author Dell 5490-HD
 */
public class Drinks extends Products{
    private static int dem = 0;
    private String manufacturer;

    public Drinks(String drinkName, double drinkPrice, String manufacturer) {
        super(drinkName, drinkPrice);
        this.productId = "D" + String.format("%02d", ++dem);
        this.manufacturer = manufacturer;
    }
    
    public Drinks() {
    }

    @Override
    public void inputProduct() {
        Configuration.inputInfo("---Nhap nuoc uong--- \n");
        this.setProductId("D" + String.format("%02d", ++dem));
        Configuration.inputInfo("Ten nuoc uong: ");
        this.setProductName(Configuration.sc.nextLine());
        Configuration.inputInfo("Gia nuoc uong: ");
        this.setProductPrice(Double.parseDouble(Configuration.sc.nextLine()));
        Configuration.inputInfo("Nha san xuat: ");
        this.manufacturer = Configuration.sc.nextLine();
    }

    @Override
    public void displayProduct() {
        System.out.printf("  %-17s %-17s %-30s %-12s %-17s %-17s \n%s\n",
                "NUOC UONG", this.getProductId(), this.getProductName(), this.getProductPrice(), "",this.manufacturer,
                "------------------------------------------------------------------------------------------------------------------");
    }   
    
    /**
     * @return the manufacturer
     */
    public String getManufactuer() {
        return manufacturer;
    }

    /**
     * @param manufactuer the manufacturer to set
     */
    public void setManufactuer(String manufactuer) {
        this.manufacturer = manufactuer;
    }
    
}
