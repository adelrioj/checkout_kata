require "spec_helper"

describe Shop::Product do
  describe 'create product' do
    it 'product name' do
      product_price = Shop::Price.new(5.0, Shop::Offers::OfferXY.new(2, 1))
      expect(Shop::Product.new('MUG', product_price).code).to eq('MUG')
    end
  end
  describe 'create product' do
    it 'Product price without offer' do
      product_price = Shop::Price.new(5.0)
      product = Shop::Product.new('MUG', product_price)
      expect(product.price_of(1)).to eq(5.0)
    end

    it 'Product price with offer' do
      product_price = Shop::Price.new(5.0, Shop::Offers::OfferXY.new(2, 1))
      product = Shop::Product.new('MUG', product_price)
      expect(product.price_of(2)).to eq(5.0)
    end
  end

  describe 'errors' do
    it 'product without name' do
      expect { product = Shop::Product.new('', Shop::Price.new(5.0)) }
        .to raise_error('Wrong code')
    end

    it 'product without price' do
      expect { product = Shop::Product.new('MUG', 5.0) }
        .to raise_error('Invalid price')
    end
  end
end
