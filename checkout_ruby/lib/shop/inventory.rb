module Shop
  # Inventory represents the list of products available with the rules of
  # each one to get the final price.
  class Inventory
    # Only for tests
    attr_reader :products

    def initialize(products)
      @products = products
      validate!
    end

    def search(product_code)
      products.find{ |product| product.code == product_code }
    end

    def exist?(product_code)
      !search(product_code).nil?
    end

    private

    def validate!
      unless @products.is_a?(Array)
        raise ArgumentError.new("You have to pass a list of products")
        return
      end
      @products.each do |product|
        raise ArgumentError.new("You have to pass only products") unless product.is_a?(Shop::Product)
      end
    end
  end
end