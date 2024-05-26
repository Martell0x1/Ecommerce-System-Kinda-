public class Product{
    private int ProductId;
    private String Name;
    private float Price;

    private String imgpath;

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    protected Product(int ProductId , String Name , float Price){
        this.setProductId(ProductId);
        this.setPrice(Price);
        this.Name = Name;
        this.imgpath = imgpath;

    }

    protected Product(){
        ProductId= 0;
        Price =0;
        Name = "";
    }

    protected int getProductId() {
        return ProductId;
    }

    protected void setProductId(int productId) {
        if(productId < 0)
            ProductId = Math.abs(productId);
        ProductId = productId;
    }

    protected String getName() {
        return Name;
    }

    protected void setName(String name) {
        Name = name;
    }

    protected float getPrice() {
        return Price;
    }

    protected void setPrice(float price) {
        if(price < 0)
            Price = Math.abs(price);
        Price = price;
    }

    protected void PrintAdditionalInfo(){}
    protected void PrintProductInfo(){
        System.out.println("---------------------------------------------");
        System.out.println("Product: "+this.getName());
        System.out.println("ProductId: "+this.getProductId());
        System.out.println("Price: "+this.getPrice());
        this.PrintAdditionalInfo();

    }

}
