public class User {
    private String username;
    private String password;
    private boolean isAdmin;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    private int customerId;
    private String Address;

    public User(String username, String password, boolean isAdmin , int cst , String addrr) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.customerId = cst;
        this.Address = addrr;
    }

    public User() {
        this.isAdmin = false;
        this.password = "";
        this.username = "";
    }


    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}
