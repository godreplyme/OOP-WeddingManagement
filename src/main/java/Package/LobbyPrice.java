/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Package;

import java.time.LocalDate;

/**
 *
 * @author Lenovo Ideapad 5 Pro
 */
enum DayOfWeek {
    THUONG(1), CUOITUAN(1.5);

    private double value;

    private DayOfWeek(double value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }

}

enum TimeOfDay {
    MORNING(0.3), AFTERNOON(0.4), EVENING(0.5);
    private double timeValue;

    private TimeOfDay(double value) {
        this.timeValue = value;
    }

    /**
     * @return the timeValue
     */
    public double getTimeValue() {
        return timeValue;
    }

    /**
     * @param timeValue the timeValue to set
     */
    public void setTimeValue(double timeValue) {
        this.timeValue = timeValue;
    }
}

public class LobbyPrice {

    private LocalDate rentDate;

    public void updateLobbyPrice(String kindDay, double newPrice) {
        if (kindDay.toUpperCase().equals("THUONG")) {
            DayOfWeek.THUONG.setValue(newPrice);
        } else if (kindDay.toUpperCase().equals("CUOI TUAN")) {
            DayOfWeek.THUONG.setValue(newPrice);
        }
    }

    public void updateLobbyPrice(String time, double newPrice, int o) {
        if (time.toUpperCase().equals("BUOI SANG")) {
            TimeOfDay.MORNING.setTimeValue(newPrice);
        } else if (time.toUpperCase().equals("BUOI CHIEU")) {
            TimeOfDay.AFTERNOON.setTimeValue(newPrice);
        } else if (time.toUpperCase().equals("BUOI TOI")) {
            TimeOfDay.EVENING.setTimeValue(newPrice);
        }
    }

    public double priceDayofWeek(LocalDate date) {
        if (date.getDayOfWeek().name().equals("MONDAY")
                || date.getDayOfWeek().name().equals("TUESDAY") 
                || date.getDayOfWeek().name().equals("WEDNESDAY")
                || date.getDayOfWeek().name().equals("THURSDAY")
                || date.getDayOfWeek().name().equals("FRIDAY")) {
            return DayOfWeek.THUONG.getValue();
        } else if (date.getDayOfWeek().name().equals("SATURDAY")
                ||date.getDayOfWeek().name().equals("SUNDAY")) {
            return DayOfWeek.CUOITUAN.getValue();
        } 
        return 0;
    }



    public double priceTimeofDay(String dayTime) {
        if (dayTime.toUpperCase().equals("SANG")) {
            return TimeOfDay.MORNING.getTimeValue();
        } else if (dayTime.toUpperCase().equals("CHIEU")) {
            return TimeOfDay.AFTERNOON.getTimeValue();
        } else if (dayTime.toUpperCase().equals("TOI")) {
            return TimeOfDay.EVENING.getTimeValue();
        }
        return 0;
    }

    public void displayLobbyPrice() {
        System.out.println("");
    }

    public String getTimeOfDay() {
        return this.rentDate.getDayOfWeek().name().toUpperCase();
    }

    /**
     * @return the rentDate
     */
    public LocalDate getRentDate() {
        return rentDate;
    }

    /**
     * @param rentDate the rentDate to set
     */
    public void setRentDate(LocalDate rentDate) {
        this.rentDate = rentDate;
    }
}
