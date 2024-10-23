/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

/**
 *
 * @author Dell 5490-HD
 */
public class Foods extends Products{
    private static int dem = 0;
    private boolean isChay;
    
    public Foods(String foodName, double foodPrice, boolean isChay) {
        super(foodName, foodPrice);
        this.productId = "F" + String.format("%02d", ++dem);
        this.isChay = isChay;
    }
    public Foods() {
    }

    @Override
    public void inputProduct() {
        Configuration.inputInfo("---Nhap thuc an--- \n");
        this.setProductId("F" + String.format("%02d", ++dem));
        Configuration.inputInfo("Ten thuc an: ");
        this.setProductName(Configuration.sc.nextLine());
        Configuration.inputInfo("Gia thuc an: ");
        this.setProductPrice(Double.parseDouble(Configuration.sc.nextLine()));
        Configuration.inputInfo("Chay(true) hay man(false)?\n");
        this.isChay = Boolean.parseBoolean(Configuration.sc.nextLine());
    }

    @Override
    public void displayProduct() {
        System.out.printf("  %-17s %-17s %-30s %-12s %-17s %-17s \n%s\n",
                "THUC AN", this.getProductId(),this.getProductName(), this.getProductPrice(), this.convert(this.isChay),"",
                "------------------------------------------------------------------------------------------------------------------");
    }
    
    public String convert(boolean b)
    {
        if(b==true) return "CHAY";
        else return "MAN";
    }
    
    /**
     * @return the isChay
     */
    public boolean isIsChay() {
        return isChay;
    }

    /**
     * @param isChay the isChay to set
     */
    public void setIsChay(boolean isChay) {
        this.isChay = isChay;
    }    
}