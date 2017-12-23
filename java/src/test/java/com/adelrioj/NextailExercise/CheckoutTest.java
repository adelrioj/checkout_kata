package com.adelrioj.NextailExercise;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Set;

import static com.adelrioj.NextailExercise.BasicPricingRule.basicPricingRuleFor;
import static com.adelrioj.NextailExercise.DiscountPricingRule.discountPricingRuleFor;
import static com.adelrioj.NextailExercise.Item.itemBuilderFor;
import static com.google.common.collect.Sets.newHashSet;

public class CheckoutTest {

    private static final Item VOUCHER = itemBuilderFor("VOUCHER").setName("Gift Card").build();
    private static final Item TSHIRT = itemBuilderFor("TSHIRT").setName("Summer T-Shirt").build();
    private static final Item PANTS = itemBuilderFor("PANTS").setName("Summer Pants").build();

    private static final BigDecimal EMPTY_PRICE = new BigDecimal("0");
    private static final BigDecimal VOUCHER_PRICE = new BigDecimal("5.00");
    private static final BigDecimal TSHIRT_PRICE = new BigDecimal("20.00");
    private static final BigDecimal PANTS_PRICE = new BigDecimal("7.50");

    private static final BigDecimal VOUCHER_DISCOUNT_PRICE = new BigDecimal("5.00");
    private static final BigDecimal TSHIRT_DISCOUNT_PRICE = new BigDecimal("19.00");

    private Set<PricingRule> pricingRules;

    @Before
    public void before(){
        pricingRules = newHashSet(
                basicPricingRuleFor(VOUCHER).withPrice(VOUCHER_PRICE).build(),
                basicPricingRuleFor(TSHIRT).withPrice(TSHIRT_PRICE).build(),
                basicPricingRuleFor(PANTS).withPrice(PANTS_PRICE).build(),
                discountPricingRuleFor(VOUCHER).withNecessaryAmmount(2).withPrice(VOUCHER_DISCOUNT_PRICE).build(),
                new BulkDiscountPricingRule(TSHIRT, 3, TSHIRT_DISCOUNT_PRICE, TSHIRT_PRICE));
    }

    @Test
    public void priceForEmptyBasket(){
        Checkout checkout = new Checkout(pricingRules);
        Assert.assertEquals(EMPTY_PRICE, checkout.checkPrice());
    }

    @Test
    public void priceForOneItem(){
        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(VOUCHER);
        Assert.assertEquals(VOUCHER_PRICE, checkout.checkPrice());
    }

    @Test
    public void priceForManyDifferentItems(){
        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(VOUCHER);
        checkout.scan(TSHIRT);
        checkout.scan(PANTS);

        BigDecimal combinedPrice = VOUCHER_PRICE.add(TSHIRT_PRICE).add(PANTS_PRICE);
        Assert.assertEquals(combinedPrice, checkout.checkPrice());
    }

    @Test
    public void priceForVoucherDiscount(){
        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(VOUCHER);
        checkout.scan(VOUCHER);

        Assert.assertEquals(VOUCHER_DISCOUNT_PRICE, checkout.checkPrice());
    }

    @Test
    public void priceForTshirtDiscount(){
        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(TSHIRT);
        checkout.scan(TSHIRT);
        checkout.scan(TSHIRT);

        Assert.assertEquals(TSHIRT_DISCOUNT_PRICE.multiply(BigDecimal.valueOf(3)), checkout.checkPrice());
    }

    @Test
    public void priceForMultipleItemsWithDiscount(){
        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(VOUCHER);
        checkout.scan(TSHIRT);
        checkout.scan(VOUCHER);

        Assert.assertEquals(new BigDecimal("25.00"), checkout.checkPrice());
    }

    @Test
    public void priceForItemsWithDiscountAddedMultipleTimes(){
        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(TSHIRT);
        checkout.scan(TSHIRT);
        checkout.scan(TSHIRT);
        checkout.scan(VOUCHER);
        checkout.scan(TSHIRT);

        Assert.assertEquals(new BigDecimal("81.00"), checkout.checkPrice());
    }

    @Test
    public void priceForItemsWithAndWithoutDiscountShuffled(){
        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(VOUCHER);
        checkout.scan(TSHIRT);
        checkout.scan(VOUCHER);
        checkout.scan(VOUCHER);
        checkout.scan(PANTS);
        checkout.scan(TSHIRT);
        checkout.scan(TSHIRT);

        Assert.assertEquals(new BigDecimal("74.50"), checkout.checkPrice());
    }
}