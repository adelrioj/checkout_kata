package com.adelrioj.NextailExercise;

import com.google.common.base.Function;
import com.google.common.collect.Ordering;

import java.math.BigDecimal;
import java.util.*;

public class Checkout {

    private Set<PricingRule> pricingRules;
    private Map<Item, Integer> basket = new HashMap<Item, Integer>();

    public Checkout(Set<PricingRule> pricingRules) {
        this.pricingRules = pricingRules;
    }

    public void scan(Item item){
        Integer oldAmount = basket.get(item);
        int newAmount = oldAmount != null ? oldAmount + 1 : 1;
        basket.put(item, newAmount);
    }

    public BigDecimal checkPrice(){
        List<PricingRule> sortedPricingRules = getSortedPricingRules();
        BigDecimal total = new BigDecimal("0");
        for (PricingRule pricingRule : sortedPricingRules) {
            if (basket.containsKey(pricingRule.getItem())){
                int amount = basket.get(pricingRule.getItem());
                int remainingItems = pricingRule.calculateRemainingItems(amount);
                total = total.add(pricingRule.calculatePrice(amount));
                basket.put(pricingRule.getItem(), remainingItems);
            }
        }
        return total;
    }

    private List<PricingRule> getSortedPricingRules() {
        return Ordering.natural().reverse().onResultOf(
                new Function<PricingRule, Integer>() {
                    public Integer apply(PricingRule pricingRule) {
                        return pricingRule.getPriority();
                    }
                }).sortedCopy(pricingRules);
    }
}
