package com.adelrioj.NextailExercise;

import java.math.BigDecimal;

public class DiscountPricingRule implements PricingRule{

    private final Item item;
    private final int necessaryAmmount;
    private final BigDecimal price;

    public static DiscountPricingRuleBuilder discountPricingRuleFor(Item item) {
        return new DiscountPricingRuleBuilder(item);
    }

    private DiscountPricingRule(Item item, int necessaryAmmount, BigDecimal price) {
        this.item = item;
        this.necessaryAmmount = necessaryAmmount;
        this.price = price;
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal calculatePrice(int amount) {
        long appliedTimes = amount / necessaryAmmount;
        return price.multiply(BigDecimal.valueOf(appliedTimes));
    }

    public int calculateRemainingItems(int amount) {
        long appliedTimes = amount / necessaryAmmount;
        return (int) (amount - (necessaryAmmount * appliedTimes));
    }

    public int getPriority() {
        return DISCOUNT_PRIORITY;
    }

    public int getNecessaryAmmount() {
        return necessaryAmmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiscountPricingRule)) return false;

        DiscountPricingRule that = (DiscountPricingRule) o;

        if (getNecessaryAmmount() != that.getNecessaryAmmount()) return false;
        if (getItem() != null ? !getItem().equals(that.getItem()) : that.getItem() != null) return false;
        return getPrice() != null ? getPrice().equals(that.getPrice()) : that.getPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getItem() != null ? getItem().hashCode() : 0;
        result = 31 * result + getNecessaryAmmount();
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "com.adelrioj.NextailExercise.DiscountPricingRule{" +
                "item=" + item +
                ", necessaryAmmount=" + necessaryAmmount +
                ", price=" + price +
                '}';
    }

    public static class DiscountPricingRuleBuilder {
        private Item item;
        private int necessaryAmmount;
        private BigDecimal price;

        public DiscountPricingRuleBuilder(Item item) {
            this.item = item;
        }

        public DiscountPricingRuleBuilder withNecessaryAmmount(int necessaryAmmount) {
            this.necessaryAmmount = necessaryAmmount;
            return this;
        }

        public DiscountPricingRuleBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public DiscountPricingRule build() {
            return new DiscountPricingRule(item, necessaryAmmount, price);
        }
    }
}
