require "spec_helper"

describe Shop::Offers::OfferXY do
  let(:offer) { Shop::Offers::OfferXY.new(2, 1) }

  describe 'apply offer' do
    it 'empty list of items' do
      expect(offer.apply(25, 0)).to eq(0.00)
    end

    it 'apply offer' do
      expect(offer.apply(25, 5)).to eq(75.00)
    end

    it 'offer doesnt apply' do
      expect(offer.apply(25, 1)).to eq(25.00)
    end

    it 'negative quantity' do
      expect(offer.apply(25, -1)).to eq(0.00)
    end
  end

  describe 'errors' do
    it 'offer with negative values' do
      expect { offer = Shop::Offers::OfferXY.new(1, 2) }
        .to raise_error("The number of items to pay can't be less than group of items to apply the offer")
      expect { offer = Shop::Offers::OfferXY.new(0, 0) }
        .to raise_error("Wrong group of items to apply the offer")
    end
  end
end
