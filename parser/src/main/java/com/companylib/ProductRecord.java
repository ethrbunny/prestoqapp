package com.companylib;

import java.math.BigDecimal;

public class ProductRecord {
    private int id;
    private String description;
    private Double priceRegularSingular;
    private Double pricePromoSingular;
    private Double priceRegularSplit;
    private Double pricePromoSplit;
    private Double priceRegularX;
    private Double pricePromoX;
    private String flags;
    private String productSize;

    private static final Double TAXRATE = 0.07775;
    private static final String WEIGHT = "Pound";
    private static final String EACH = "Each";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPriceRegularSingular() {
        return priceRegularSingular;
    }

    public void setPriceRegularSingular(Double priceRegularSingular) {
        this.priceRegularSingular = priceRegularSingular;
    }

    public Double getPricePromoSingular() {
        return pricePromoSingular;
    }

    public void setPricePromoSingular(Double pricePromoSingular) {
        this.pricePromoSingular = pricePromoSingular;
    }

    public Double getPriceRegularSplit() {
        return priceRegularSplit;
    }

    public void setPriceRegularSplit(Double priceRegularSplit) {
        this.priceRegularSplit = priceRegularSplit;
    }

    public Double getPricePromoSplit() {
        return pricePromoSplit;
    }

    public void setPricePromoSplit(Double pricePromoSplit) {
        this.pricePromoSplit = pricePromoSplit;
    }

    public Double getPriceRegularX() {
        return priceRegularX;
    }

    public void setPriceRegularX(Double priceRegularX) {
        this.priceRegularX = priceRegularX;
    }

    public Double getPricePromoX() {
        return pricePromoX;
    }

    public void setPricePromoX(Double pricePromoX) {
        this.pricePromoX = pricePromoX;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Double getPriceRegularCalculated() {
        if(priceRegularX != 0.0) {
            Double splitValue = getPriceRegularSplit() / getPriceRegularX();

            return new BigDecimal(splitValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return getPriceRegularSingular();
    }

    public Double getPricePromoCalculated() {
        if(getPricePromoX() != 0.0) {
            Double splitValue = getPricePromoSplit() / getPricePromoX();

            return new BigDecimal(splitValue).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        }
        return getPricePromoSingular();
    }

    public String getUnits() {
        if(flags.charAt(2) == 'Y') {
            return WEIGHT;
        } else {
            return EACH;
        }
    }

    public Double getTaxRate() {
        if (flags.charAt(4) == 'Y') {
            return TAXRATE;
        } else {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return "ProductRecord{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", priceRegularSingular=" + priceRegularSingular +
                ", pricePromoSingular=" + pricePromoSingular +
                ", priceRegularSplit=" + priceRegularSplit +
                ", pricePromoSplit=" + pricePromoSplit +
                ", priceRegularX=" + priceRegularX +
                ", pricePromoX=" + pricePromoX +
                ", flags='" + flags + '\'' +
                ", productSize='" + productSize + '\'' +
                ", regularCalc='" + getPriceRegularCalculated() + "\'" +
                ", promoCalc='" + getPricePromoCalculated() + "\'" +
                ", units='" + getUnits() + "\'" +
                ", tax='" + getTaxRate() + "\'" +
                '}';
    }
}
