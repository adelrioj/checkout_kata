require "spec_helper"

describe Shop::Offers::OfferSpecialPrice do
  let(:offer) { Shop::Offers::OfferSpecialPrice.new(3, 10) }

  describe 'apply offer' do
    it 'empty list of items' do
      expect(offer.apply(25, 0)).to eq(0.00)
    end

    it 'apply offer' do
      expect(offer.apply(25, 5)).to eq(50.00)
    end

    it 'offer doesnt apply' do
      expect(offer.apply(25, 2)).to eq(50.00)
    end

    it 'negative quantity' do
      expect(offer.apply(25, -1)).to eq(0.00)
    end
  end

  describe 'errors' do
    it 'offer wrong number of items to apply the offer' do
      expect { offer = Shop::Offers::OfferSpecialPrice.new(0, 10) }
        .to raise_error('Wrong number of products to start to apply the offer')
    end

    it 'negative values for items' do
      expect { offer = Shop::Offers::OfferSpecialPrice.new(-1, 2) }
        .to raise_error("You can't use a negative values for items to apply the offer")
    end
  end
end
