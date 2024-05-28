public class Customer {
    private int CustomerId;
    private String Name;
    private String Address;
    private boolean isadmin;

    private Cart customerCart = new Cart();

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        if(customerId < 0 )
            CustomerId = Math.abs(customerId);
        CustomerId = customerId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public Cart getCustomerCart() {
        return customerCart;
    }

    public void setCustomerCart(Cart customerCart) {
        this.customerCart = customerCart;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setIsAdmin(boolean id){this.isadmin = id;}
    public boolean isAdmin(){return this.isadmin;}

    public Customer(int customerId, String name, String address , boolean admin) {
        this.setCustomerId(customerId);
        Name = name;
        Address = address;
        this.isadmin = admin;
    }

    public Customer(){
        this.setCustomerId(0);
        Name = "";
        Address = "";
    }
}
