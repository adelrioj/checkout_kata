require "spec_helper"

describe Shop::Inventory do
  let(:voucher) { Shop::Product.new('VOUCHER', Shop::Price.new(5.0, Shop::Offers::OfferXY.new(2, 1))) }
  let(:mug) { Shop::Product.new('MUG', Shop::Price.new(7.5)) }

  let(:inventory) { Shop::Inventory.new([voucher, mug]) }

  describe 'create inventory' do
    it 'adding products' do
      expect(inventory.products.length).to eq(2)
    end

    it 'checking content' do
      expect(inventory.search('VOUCHER')).to eq(voucher)
    end

    it 'checking content' do
      expect(inventory.exist?('VOUCHER')).to be true
    end
  end

  describe 'errors' do
    it 'passing list of products' do
      expect { inventory = Shop::Inventory.new(1) }
        .to raise_error('You have to pass a list of products')
    end

    it 'all elements are products' do
      expect { inventory = Shop::Inventory.new([voucher, 'a']) }
        .to raise_error('You have to pass only products')
    end
  end
end