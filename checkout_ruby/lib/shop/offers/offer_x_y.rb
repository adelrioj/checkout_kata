module Shop
  module Offers
    # OfferXY represent offers with extra items like 2x1 or 3x2
    class OfferXY
      def initialize(each, to_pay)
        @each = each
        @to_pay = to_pay

        validate!
      end

      def apply(price, qty)
        return 0.00 if qty < 0
        group_products(qty) * price
      end

      private

      def group_products(qty)
        (qty * @to_pay / @each) + (qty % @each)
      end

      def validate!
        raise ArgumentError.new("You cant use negative values") if @each < 0 || @to_pay < 0
        raise ArgumentError.new("The number of items to pay can't be less than group of items to apply the offer") if @each < @to_pay
        raise ArgumentError.new("Wrong group of items to apply the offer") if @each < 1
      end
    end
  end
end