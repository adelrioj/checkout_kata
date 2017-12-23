package com.adelrioj.NextailExercise;

import java.math.BigDecimal;

public class BulkDiscountPricingRule implements PricingRule {

    private Item item;
    private int necessaryAmmount;
    private BigDecimal price;
    private BigDecimal singlePrice;

    public BulkDiscountPricingRule(Item item, int necessaryAmmount, BigDecimal price, BigDecimal singlePrice) {
        this.item = item;
        this.necessaryAmmount = necessaryAmmount;
        this.price = price;
        this.singlePrice = singlePrice;
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal calculatePrice(int amount) {

        if (amount >= necessaryAmmount){
            return price.multiply(BigDecimal.valueOf(amount));
        } else {
            return singlePrice.multiply(BigDecimal.valueOf(amount));
        }
    }

    public int calculateRemainingItems(int amount) {
        return 0;
    }

    public int getPriority() {
        return 1;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getNecessaryAmmount() {
        return necessaryAmmount;
    }

    public void setNecessaryAmmount(int necessaryAmmount) {
        this.necessaryAmmount = necessaryAmmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(BigDecimal singlePrice) {
        this.singlePrice = singlePrice;
    }
}
