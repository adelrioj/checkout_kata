require 'checkout'

pricing_rules = Shop::Inventory.new([
  Shop::Product.new('VOUCHER', Shop::Price.new(5.0, Shop::Offers::OfferXY.new(2, 1)), 'Cabify Voucher'),
  Shop::Product.new('TSHIRT', Shop::Price.new(20.0, Shop::Offers::OfferSpecialPrice.new(3, 19.00)), 'Cabify T-Shirt'),
  Shop::Product.new('MUG', Shop::Price.new(7.5), 'Cafify Coffee Mug')
])

# Example 1
co = Checkout.new(pricing_rules)
co.scan('VOUCHER')
co.scan('TSHIRT')
co.scan('MUG')
p "Items: #{co.items}"
p "Price: #{co.total}€"

# Example 2
co = Checkout.new(pricing_rules)
co.scan('VOUCHER')
co.scan('TSHIRT')
co.scan('VOUCHER')
p "Items: #{co.items}"
p "Price: #{co.total}€"

# Example 3
co = Checkout.new(pricing_rules)
co.scan('TSHIRT')
co.scan('TSHIRT')
co.scan('TSHIRT')
co.scan('VOUCHER')
co.scan('TSHIRT')
p "Items: #{co.items}"
p "Price: #{co.total}€"

# Example 4
co = Checkout.new(pricing_rules)
co.scan('VOUCHER')
co.scan('TSHIRT')
co.scan('VOUCHER')
co.scan('VOUCHER')
co.scan('MUG')
co.scan('TSHIRT')
co.scan('TSHIRT')
p "Items: #{co.items}"
p "Price: #{co.total}€"
