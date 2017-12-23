package com.adelrioj.NextailExercise;

import java.math.BigDecimal;

public class BasicPricingRule implements PricingRule{

    private final Item item;
    private final BigDecimal price;

    public static BasicPricingRuleBuilder basicPricingRuleFor(Item item) {
        return new BasicPricingRuleBuilder(item);
    }

    private BasicPricingRule(Item item, BigDecimal price) {
        this.item = item;
        this.price = price;
    }

    public BigDecimal calculatePrice(int amount) {
        return price.multiply(BigDecimal.valueOf(amount));
    }

    public int calculateRemainingItems(int amount) {
        return 0;
    }

    public int getPriority() {
        return BASIC_PRIORITY;
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasicPricingRule)) return false;

        BasicPricingRule that = (BasicPricingRule) o;

        if (getItem() != null ? !getItem().equals(that.getItem()) : that.getItem() != null) return false;
        return getPrice() != null ? getPrice().equals(that.getPrice()) : that.getPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getItem() != null ? getItem().hashCode() : 0;
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "com.adelrioj.NextailExercise.BasicPricingRule{" +
                "item=" + item +
                ", price=" + price +
                '}';
    }

    public static class BasicPricingRuleBuilder {
        private Item item;
        private BigDecimal price;

        public BasicPricingRuleBuilder(Item item) {
            this.item = item;
        }

        public BasicPricingRuleBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public BasicPricingRule build(){
            return new BasicPricingRule(this.item, this.price);
        }
    }
}
