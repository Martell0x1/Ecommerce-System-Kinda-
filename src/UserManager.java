import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class UserManager {
    private static User user = new User();


    public UserManager() {

    }

    public boolean DoLogin(String Username, String Password) {
        boolean state = false;
        try {
            ArrayList<User> AllUsers = DataBaseHandle.LoadAllUsers();
            for(User user : AllUsers){
                if(user.getUsername().equals(Username) && user.getPassword().equals(Password)) {
                    state = true;
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return state;
    }

    public  Customer MakeHimLogIn(String name){
        Customer customer = new Customer();
        try {
            ArrayList<User> x =DataBaseHandle.LoadAllUsers();
            for(User user : x){
                if(user.getUsername().equals(name)){
                    customer = new Customer(
                            user.getCustomerId(),
                            user.getUsername(),
                            user.getAddress(),
                            user.isAdmin()
                    );
                    break;
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }
        return customer;
    }


}
