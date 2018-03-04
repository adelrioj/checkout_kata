require "spec_helper"

describe Checkout do
  let(:inventory) do
    Shop::Inventory.new([
      Shop::Product.new('VOUCHER', Shop::Price.new(5.0, Shop::Offers::OfferXY.new(2,1))),
      Shop::Product.new('TSHIRT', Shop::Price.new(20.0, Shop::Offers::OfferSpecialPrice.new(3, 19.0))),
      Shop::Product.new('MUG', Shop::Price.new(7.5))
    ])
  end

  describe 'scan' do
    it 'adding product' do
      co = Checkout.new(inventory)
      expect(co.scan('VOUCHER')).to be(1)
      expect(co.scan('VOUCHER')).to be(2)
      expect(co.scan('TSHIRT')).to be(1)
    end

    it 'product doesnt exist' do
      co = Checkout.new(inventory)
      expect{co.scan('STICKER')}.to raise_error("The product doesn't exist")
    end
  end

  describe 'examples of use' do
    it 'scenario 1' do
    	co = Checkout.new(inventory)
      co.scan("VOUCHER")
      co.scan("TSHIRT")
      co.scan('MUG')

      expect(co.total).to eq("32.50")
    end

    it 'scenario 2' do
    	co = Checkout.new(inventory)
      co.scan('VOUCHER')
      co.scan('TSHIRT')
      co.scan('VOUCHER')

      expect(co.total).to eq("25.00")
    end

    it 'scenario 3' do
    	co = Checkout.new(inventory)
      co.scan('TSHIRT')
      co.scan('TSHIRT')
      co.scan('TSHIRT')
      co.scan('VOUCHER')
      co.scan('TSHIRT')
      expect(co.total).to eq("81.00")
    end

    it 'scenario 4' do
    	co = Checkout.new(inventory)
      co.scan('VOUCHER')
      co.scan('TSHIRT')
      co.scan('VOUCHER')
      co.scan('VOUCHER')
      co.scan('MUG')
      co.scan('TSHIRT')
      co.scan('TSHIRT')

      expect(co.total).to eq("74.50")
    end
  end

  describe 'items' do
    it 'listing items' do
      co = Checkout.new(inventory)
      co.scan("VOUCHER")
      co.scan("TSHIRT")
      co.scan("TSHIRT")
      co.scan('MUG')
      co.scan('MUG')
      co.scan('MUG')
      expect(co.items).to eq("VOUCHER, TSHIRT, TSHIRT, MUG, MUG, MUG")
    end
  end

  describe 'errors' do
    it 'invalid inventory' do
      expect { checkout = Checkout.new(['VOUCHER', 'TSHIRT']) }
        .to raise_error("Invalid inventory of products")
    end
  end
end
