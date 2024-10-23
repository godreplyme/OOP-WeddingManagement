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
//public abstract class Products {
//
//    private int productId;
//    private String productName;
//    private double productPrice;
//
//    public Products() {
//    }
//
//    public Products(int id, String name, double price) {
//        this.productId = id;
//        this.productName = name;
//        this.productPrice = price;
//    }
//
//    public void inputProduct() {
//        System.out.print("Moi nhap ten " + this.name() + ": ");
//        this.productName = Configuration.sc.nextLine();
//        System.out.print("Moi nhap gia " + this.name() + ": ");
//        this.productPrice = Double.parseDouble(Configuration.sc.nextLine());
//    }
//
//    public abstract void displayProduct();
//
//    public abstract String name();
//
//    /**
//     * @return the productId
//     */
//    public int getProductId() {
//        return productId;
//    }
//
//    /**
//     * @param productId the productId to set
//     */
//    public void setProductId(int productId) {
//        this.productId = productId;
//    }
//
//    /**
//     * @return the productName
//     */
//    public String getProductName() {
//        return productName;
//    }
//
//    /**
//     * @param productName the productName to set
//     */
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    /**
//     * @return the productPrice
//     */
//    public double getProductPrice() {
//        return productPrice;
//    }
//
//    /**
//     * @param productPrice the productPrice to set
//     */
//    public void setProductPrice(double productPrice) {
//        this.productPrice = productPrice;
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
public abstract class Products {
    protected String productId;
    private String productName;
    private double productPrice;

    public Products(String productName, double productPrice) {
        
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Products() {
    }
    
    public abstract void inputProduct();
    
    public abstract void displayProduct();
    

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productPrice
     */
    public double getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice the productPrice to set
     */
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }   
}
