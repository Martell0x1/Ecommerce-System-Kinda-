public class Order {
    private int OrderId;
    private float TotalCost;
    private Customer customer;

    private void setTotalCost() {
        TotalCost =customer.getCustomerCart().CalculateProductsPrice();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order(int orderId , float totalCost , final Customer customer ) {
        OrderId = orderId;
        TotalCost = totalCost;
        this.customer = customer;
    }

    public Order(int orderId,final Customer customer){
        this.OrderId = orderId;
        this.customer = customer;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }


    public float getTotalCost() {
        setTotalCost();
        return TotalCost;
    }


    public void PrintInfo(){
        setTotalCost();
        System.out.println("Customer ID: "+customer.getCustomerId());
        System.out.println("Order ID: "+getOrderId());
        System.out.println("Products: ");
        for(Product x : customer.getCustomerCart().getProducts()){
            x.PrintProductInfo();
        }
        System.out.println("Total Price: "+getTotalCost());
    }

    public StringBuilder PrintInfo2(){
        setTotalCost();
        StringBuilder str = new StringBuilder();
        str.append("Customer ID: "+customer.getCustomerId());
        str.append("<br>Order ID: "+getOrderId());
        str.append("<br>Products: ");

        for(Product x : customer.getCustomerCart().getProducts()){
            str.append("<br>Name: "+x.getName());
            str.append("<br>Price: "+x.getPrice());

            if(x instanceof ElectronicProduct){
                str.append("<br>Brand: "+((ElectronicProduct) x).getBrand());
                str.append("<br>Warrenty Period: "+((ElectronicProduct) x).getWarrentyPeriod());
            }

            else if(x instanceof BookProduct){
                str.append("<br>Author: "+((BookProduct) x).getAuthor());
                str.append("<br>Publisher: "+((BookProduct) x).getPublisher());
            }
            else{
                str.append("<br>Size: "+ ((ClothingProduct) x).getSize());
                str.append("<br>Fabric: "+ ((ClothingProduct) x).getFabric());
            }
            str.append("<br>------------------------------------------------------------<br>");
        }
        str.append("<br>Total Price: "+getTotalCost());
        return str;
    }
}
