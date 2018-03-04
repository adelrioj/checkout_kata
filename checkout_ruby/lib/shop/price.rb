module Shop
  # Price is used to calculate the price of a product depending of the quantity.
  class Price
    def initialize(value, offer = nil)
      @value = value
      @offer = offer
      validate!
    end

    def price_for(qty)
      return (@value * qty) unless @offer
      @offer.apply(@value, qty)
    end

    private

    def validate!
      raise ArgumentError.new("You can't use a negative values for price") if @value < 0
    end
  end
end
