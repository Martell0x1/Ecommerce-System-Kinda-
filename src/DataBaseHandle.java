import java.io.*;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataBaseHandle {
    private final static String DATABASE = "DataBase.json";
    private final static String USERSDATABASE = "Users.json";
    private final static String UsersDataBaseKey = "Users";
    private  static Customer customer = new Customer();
    private static JSONObject MainObject = null;
    private static JSONObject UsersSobject = null;
    private static JSONObject CurrentUser = null;

    private static Set<String> GetAllProductsType() {
        return (Set<String>) MainObject.keySet();
    }


    public static void INIT_DATABASE() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        MainObject = (JSONObject) parser.parse(new FileReader(DATABASE));
        UsersSobject = (JSONObject) parser.parse(new FileReader(USERSDATABASE));
        CurrentUser = (JSONObject) parser.parse(new FileReader("currentuser.json"));

    }

    public static ArrayList<JSONObject> GetAllObjectsOf(String Key) throws IOException, ParseException {

        ArrayList<JSONObject> list = new ArrayList<>();
        JSONArray Arr = (JSONArray) MainObject.get(Key);

        for (Object obj : Arr) {
            JSONObject CurrentObject = (JSONObject) obj;
            list.add(CurrentObject);
        }
        return list;
    }


    public static Map<String, ArrayList<Product>> GetAllProducts(){
        Set<String> AllProductsType = DataBaseHandle.GetAllProductsType();

        Map<String, ArrayList<Product>> ProductMap = new HashMap<>();
        try{
            for (String Type : AllProductsType) {

                ArrayList<JSONObject> CurrentType = DataBaseHandle.GetAllObjectsOf(Type);
                ArrayList<Product> CurrentProducts = new ArrayList<>();


                for (JSONObject CurrentProduct : CurrentType) {

                    switch (Type) {
                        case "ElectronicProducts" -> {
                            ElectronicProduct E_Product = new ElectronicProduct(
                                    ((Long) CurrentProduct.get("productId")).intValue(),
                                    (String) CurrentProduct.get("name"),
                                    ((Double) CurrentProduct.get("price")).floatValue(),
                                    (String) CurrentProduct.get("brand"),
                                    ((Long) CurrentProduct.get("warrantyPeriod")).intValue()
                            );

                            CurrentProducts.add(E_Product);
                        }
                        case "BookProducts" -> {
                            BookProduct B_Product = new BookProduct(
                                    ((Long) CurrentProduct.get("productId")).intValue(),
                                    (String) CurrentProduct.get("name"),
                                    ((Double) CurrentProduct.get("price")).floatValue(),
                                    (String) CurrentProduct.get("author"),
                                    (String) CurrentProduct.get("publisher")
                            );
                            CurrentProducts.add(B_Product);
                        }
                        case "ClothingProduct" -> {
                            ClothingProduct C_Product = new ClothingProduct(
                                    ((Long) CurrentProduct.get("productId")).intValue(),
                                    (String) CurrentProduct.get("name"),
                                    ((Double) CurrentProduct.get("price")).floatValue(),
                                    (String) CurrentProduct.get("size"),
                                    (String) CurrentProduct.get("fabric")
                            );
                            CurrentProducts.add(C_Product);
                        }
                        default -> throw new UnknownServiceException("The Product is UnSupported Yet");
                    }
                }

                ProductMap.put(Type, CurrentProducts);

            }
        } catch (IOException e){
            e.printStackTrace();
        } catch(ParseException e){
            e.printStackTrace();
        }


        return ProductMap;
    }

    public static ArrayList<User> LoadAllUsers() throws IOException,ParseException {
        JSONArray UsersObjects = (JSONArray) UsersSobject.get(UsersDataBaseKey);
        ArrayList<User> Users = new ArrayList<>();

        for (Object obj : UsersObjects) {
            JSONObject CurrentObject = (JSONObject) obj;
            User user = new User(
                (String) CurrentObject.get("username"),
                (String) CurrentObject.get("password"),
                ((Boolean) CurrentObject.get("isAdmin")).booleanValue(),
                0,
                (String) CurrentObject.get("address")
            );

            Object idValue = CurrentObject.get("id");
            int CustomerId;
            if(idValue instanceof Integer){
                CustomerId = (Integer) idValue;
            }
            else if(idValue instanceof Long){
                CustomerId = ((Long) idValue).intValue();
            }
            else throw new IllegalArgumentException("UnExpected Datatype for the id .");
            user.setCustomerId(CustomerId);

            Users.add(user);
        }
        return Users;

    }



    public static ArrayList<String> LoadAllUserNames() throws IOException, ParseException {
        ArrayList<User> AllUsers = DataBaseHandle.LoadAllUsers();
        ArrayList<String> AllUserNames = new ArrayList<>(AllUsers.size());

        for (User user : AllUsers) {
            AllUserNames.add(user.getUsername());
        }
        return AllUserNames;
    }

    public static Boolean IsExist(String Username){
        boolean flag = false;
        try {
            ArrayList<String> AllUserNames = DataBaseHandle.LoadAllUserNames();
            for (String username : AllUserNames) {
                if (Username.equals(username)) {
                    flag = true;
                    break;
                }
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (ParseException pe){
            System.out.println(pe.getMessage());
        }
        return flag;

    }

    public static void SaveNewUser(ArrayList<?> Data) {
        JSONArray arr = (JSONArray) UsersSobject.get(UsersDataBaseKey);
        if (arr == null)  arr = new JSONArray();

        JSONObject newObj = new JSONObject();
        ArrayList<String> Templates = new ArrayList<>(){
            {
                add("username");
                add("password");
                add("id");
                add("isAdmin");
                add("address");
            }
        };

        for (int x = 0; x < Templates.size(); x++) {
            newObj.put(Templates.get(x), Data.get(x));
        }

        arr.add(newObj);
        UsersSobject.put(UsersDataBaseKey, arr);

        try (FileWriter fileToWrite = new FileWriter(USERSDATABASE, false)) {
            fileToWrite.write(UsersSobject.toJSONString());
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void SaveCurrentUser(String username){
        CurrentUser.put("username",username);
        try{
            FileWriter file = new FileWriter("currentuser.json",false);
            file.write(CurrentUser.toJSONString());
            file.flush();
            file.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String GetCurrentUser(){
        return (String) CurrentUser.get("username");
    }

    public static int GetMaxId() {
        int max = 0;
        try{
            ArrayList<User> UsersObjects = DataBaseHandle.LoadAllUsers();

            for(User user : UsersObjects){
                max = Math.max(user.getCustomerId(),max);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch(ParseException e){
            e.printStackTrace();
        }

        return max;
    }


}
