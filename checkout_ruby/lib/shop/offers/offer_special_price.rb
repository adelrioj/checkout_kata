module Shop
  module Offers
    # OfferSpecialPrice allow us to assign offers with special prices
    # when buying an amount or more of items.
    class OfferSpecialPrice
      def initialize(started_at, new_price)
        @new_price = new_price
        @started_at = started_at
        validate!
      end

      def apply(price, qty)
        return 0.00 if qty < 0
        apply_offert?(qty) ? @new_price * qty : price * qty
      end

      private

      def apply_offert?(qty)
        qty >= @started_at
      end

      def validate!
        raise ArgumentError.new("You can't use a negative values for items to apply the offer") if @started_at < 0
        raise ArgumentError.new('Wrong number of products to start to apply the offer') if @started_at < 1
      end
    end
  end
end