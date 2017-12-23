package com.adelrioj.NextailExercise;

import java.math.BigDecimal;

public interface PricingRule {

    int DISCOUNT_PRIORITY = 1;
    int BASIC_PRIORITY = 0;

    Item getItem();

    BigDecimal calculatePrice(int amount);

    int calculateRemainingItems(int amount);

    int getPriority();
}
