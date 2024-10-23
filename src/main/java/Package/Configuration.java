/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Lenovo Ideapad 5 Pro
 */
public class Configuration {

    public static void inputInfo(String info) {
        System.out.print(info);
    }
    public static final Scanner sc = new Scanner(System.in);
    public static final String classPath = "Package.";
    public static final File file = new File("src/main/sources/lobby");
    public static final File fileFood = new File("src/main/sources/food");
    public static final File fileDrink = new File("src/main/sources/drink");
    public static final File fileParty = new File("src/main/sources/party");
    public static final DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static final void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
