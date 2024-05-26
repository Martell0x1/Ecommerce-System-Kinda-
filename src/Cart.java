public class Cart {
    private int NumberOfproducts;
    private Product[] Products;

    public Cart(int numberOfproducts, Product[] Products) {

        this.Products = new Product[this.NumberOfproducts];

        setNumberOfproducts(numberOfproducts);

        setProducts(Products);
    }

    public Cart(){
        setNumberOfproducts(0);
        this.Products = new Product[this.NumberOfproducts];
    }

    public int getNumberOfproducts() {
        return NumberOfproducts;
    }

    public void setNumberOfproducts(int numberOfproducts) {
        if(numberOfproducts < 0 )
            NumberOfproducts = Math.abs(numberOfproducts);
        NumberOfproducts = numberOfproducts;
    }

    public Product[] getProducts() {
        return Products.clone();
    }

    public void setProducts(Product[] products) {
        for(int x = 0 ; x < this.Products.length ; x++){
            this.Products[x] = products[x];
        }
    }

    public void AddProduct(final Product product){


        Product[] NewProductList = new Product[this.getNumberOfproducts()+1];

        for(int x = 0 ;x < NumberOfproducts ; x++){
            NewProductList[x] = this.Products[x];
        }

        NewProductList[this.NumberOfproducts] = product;

        this.Products = NewProductList.clone();

        this.setNumberOfproducts(this.getNumberOfproducts() + 1);


    }

    public void RemoveProduct(final Product product){
        if(NumberOfproducts == 0) return;
        int index = -1;

        for(int x = 0 ; x < NumberOfproducts ; x++){//1
            if(this.Products[x].equals(product)){
                index = x;
                break;
            }
        }

        if(index == -1) return;

        Product[] Temp = new Product[NumberOfproducts-1];

        int k = 0;
        for(int x = 0 ; x < NumberOfproducts ; x++){
            if(index == x) continue;//x =3 , k = 2
            Temp[k] = this.Products[x];
            k++;
        }

        this.Products = Temp.clone();
        this.setNumberOfproducts(--this.NumberOfproducts);

    }

    public float CalculateProductsPrice(){
        float price =0;
        for(Product p : Products){
            price += p.getPrice();
        }
        return price;
    }

    public void placeholder(int yesorno){
        System.out.println("Your total price is "+CalculateProductsPrice());
        if(yesorno == 1){
            for(Product x : getProducts()){
                x.PrintProductInfo();
            }
            System.out.println("Total: "+CalculateProductsPrice());
        }
        else System.exit(1);
    }
}
