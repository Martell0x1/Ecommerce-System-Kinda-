public class ElectronicProduct extends Product{
    private String brand;
    private int warrantyPeriod;


    public ElectronicProduct(int ProductId, String Name, float Price, String brand, int warrentyPeriod) {
        super(ProductId, Name, Price);
        this.brand = brand;
        this.setWarrentyPeriod(warrentyPeriod);

    }

    public ElectronicProduct(String brand , int warrentyPeriod){
        this.brand = brand;
        this.setWarrentyPeriod(warrentyPeriod);
    }

    public ElectronicProduct(){}

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrentyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrentyPeriod(int warrentyPeriod) {
        if(warrentyPeriod < 0)
            this.warrantyPeriod = Math.abs(warrentyPeriod);
        this.warrantyPeriod = warrentyPeriod;
    }

    @Override
    protected void PrintAdditionalInfo(){
        System.out.println("warrenty Period : "+this.getWarrentyPeriod());
        System.out.println("Brandc: "+this.getBrand());
    }
}
