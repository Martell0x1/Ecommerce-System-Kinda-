public class ClothingProduct extends Product{
    private String Size;
    private String fabric;


    public ClothingProduct(int ProductId, String Name, float Price, String size, String fabric) {
        super(ProductId, Name, Price);
        Size = size;
        this.fabric = fabric;
    }

    public ClothingProduct(String size, String fabric) {
        Size = size;
        this.fabric = fabric;
    }
    public ClothingProduct(){}

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    @Override
    protected void PrintAdditionalInfo(){
        System.out.println("Size : "+this.getSize());
        System.out.println("Fabric: "+this.getFabric());
    }

}
