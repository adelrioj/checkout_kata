require "spec_helper"

describe Shop::Price do
  let(:price) { Shop::Price.new(5.0, Shop::Offers::OfferXY.new(2, 1)) }
  let(:price_without_offer) { Shop::Price.new(7.5) }

  describe 'calculate final price' do
    it 'price with offer' do
      expect(price.price_for(0)).to eq(0.00)
      expect(price.price_for(1)).to eq(5.00)
      expect(price.price_for(3)).to eq(10.00)
    end

    it 'price without offer' do
      expect(price_without_offer.price_for(0)).to eq(0.00)
      expect(price_without_offer.price_for(1)).to eq(7.50)
      expect(price_without_offer.price_for(3)).to eq(22.50)
    end
  end

  describe 'errors' do
    it 'negative price value' do
      expect { price = Shop::Price.new(-15.5) }
        .to raise_error("You can't use a negative values for price")
    end
  end
end
