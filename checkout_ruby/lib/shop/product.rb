module Shop
  # Product entity represent a product with code, name and calculate the price.
  class Product
    # For tests
    attr_reader :code, :name, :price

    def initialize(code, price, name='')
      @code = code
      @price = price
      @name = name
      validate!
    end

    def price_of(qty)
      price.price_for(qty)
    end

    private

    def validate!
      raise ArgumentError.new('Wrong code') if @code.empty?
      raise ArgumentError.new('Invalid price') unless @price.is_a?(Shop::Price)
    end
  end
end