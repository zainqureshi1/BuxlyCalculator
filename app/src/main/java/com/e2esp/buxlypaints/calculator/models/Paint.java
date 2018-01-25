package com.e2esp.buxlypaints.calculator.models;

/**
 *
 * Created by Zain on 12/15/2017.
 */

public class Paint {
    /*
    * Paint Class all Variables
    * */
    private String name;
    private String recommend_cots;
    private int image_id;
    private int square_feet;
    private double metre_square;
    private double liter;
    private double p_liters;

    /*
    * Create All Getter setter
    * */

    public Paint() {
        name = "";
        image_id = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public int getSquare_feet() {
        return square_feet;
    }

    public void setSquare_feet(int square_feet) {
        this.square_feet = square_feet;
    }

    public double getMetre_square() {
        return metre_square;
    }

    public void setMetre_square(double metre_square) {
        this.metre_square = metre_square;
    }

    public double getP_p_liter() {
        return p_liters;
    }

    public void setP_p_liter(double p_p_liter) {
        this.p_liters = p_p_liter;
    }

    public double getLiter() {
        return liter;
    }

    public void setLiter(double liter) {
        this.liter = liter;
    }

    public String getRecommend_cots() {
        return recommend_cots;
    }

    public void setRecommend_cots(String recommend_cots) {
        this.recommend_cots = recommend_cots;
    }

}
