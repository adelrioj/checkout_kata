# coding: utf-8
require 'shop/version'
# Shop:
require 'shop/price'
require 'shop/inventory'
require 'shop/product'
# Offers:
require 'shop/offers/offer_x_y'
require 'shop/offers/offer_special_price'

# Checkout class allow us simulate the process to scan products, get a final amount and
# list the products in the shopping cart.
class Checkout
  def initialize(pricing_rules)
    @pricing_rules = pricing_rules
    @cart = Hash.new(0)
    validate!
  end

  def scan(product)
    raise "The product doesn't exist" unless @pricing_rules.exist?(product)
    @cart[product] = @cart[product] + 1
  end

  # Return the total amount of the shopping cart with two decimals.
  def total
    '%.2f' % calculate_total
  end

  # List the items in the shopping cart separated by comma.
  def items
    @cart.each_pair.reduce([]) do |list, (product, qty)|
      list + Array.new(qty, product)
    end.join(', ')
  end

  private

  def calculate_total
    @cart.each_pair.reduce(0.0) do |amount, (product, qty)|
      amount + @pricing_rules.search(product).price_of(qty)
    end
  end

  def validate!
    raise ArgumentError.new("Invalid inventory of products") unless @pricing_rules.is_a?(Shop::Inventory)
  end
end
