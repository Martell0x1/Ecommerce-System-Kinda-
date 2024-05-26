import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
public class ProductWindow extends JFrame {

    final private JPanel panel = new JPanel();
    final private JPanel customerPanel = new JPanel();

    final private UserManager usermgr = new UserManager();

    private static Customer customer = null;
    int NumberOfProducts = 0;

    private JButton ShowMyCart = new JButton("My Cart "+this.NumberOfProducts);

    public ProductWindow() {
        this.setSize(1500, 800);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Welcome To The E-Commerce System !!");
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

        panel.setLayout(new GridLayout(0, 3));
        JScrollPane scrollPane = new JScrollPane(panel);
        JScrollBar verticalScroll = scrollPane.getVerticalScrollBar();
        verticalScroll.setUnitIncrement(10);

        String username = DataBaseHandle.GetCurrentUser();
        this.customer = usermgr.MakeHimLogIn(username);

        customerPanel.setLayout(null);
        customerPanel.setPreferredSize(new Dimension(300, 800));
        customerPanel.setBackground(new Color(141, 141, 103));

        JLabel user = new JLabel(new ImageIcon("user.png"));
        user.setBounds(70, 50, 128, 128);

        JLabel welcomeLabel = new JLabel("Welcome To our E-Commerce System");
        welcomeLabel.setBounds(20, 10, 300, 30);
        welcomeLabel.setForeground(new Color(22, 99, 51));
        JLabel nameLabel = new JLabel("Name: " + customer.getName());
        nameLabel.setBounds(20, 170, 300, 30);
        JLabel addressLabel = new JLabel("Address: " + customer.getAddress());
        addressLabel.setBounds(20, 210, 300, 30);
        customerPanel.revalidate();
        customerPanel.repaint();


        ShowMyCart.setBounds(40,250,100,30);

        JButton Logout = new JButton("Log out");
        Logout.setBounds(140,250,100,30);

        customerPanel.add(welcomeLabel);
        customerPanel.add(user);
        customerPanel.add(addressLabel);
        customerPanel.add(nameLabel);
        customerPanel.add(ShowMyCart);
        customerPanel.add(Logout);

        Logout.addActionListener((e)->{
            DataBaseHandle.SaveCurrentUser("");
            new LoginWindow().RUN();
            this.dispose();
        });

        ShowMyCart.addActionListener((event)->{
            JDialog dialog = new JDialog();
            dialog.setSize(300, 200);
            dialog.setLayout(null);
            dialog.setResizable(false);



            JLabel label = new JLabel(
                    "<html>"+
                            "Total Price = "+this.customer.getCustomerCart().CalculateProductsPrice()+"<br>"
                    +"Would you like to print the Order?"+"<br>"
                    +"</html>"
            );
            label.setBounds(10,10,300,30);

            dialog.add(label);

            JButton yes = new JButton("Yes");
            yes.setVisible(true);
            yes.setBounds(10,50,100,30);
            dialog.add(yes);

            JButton no = new JButton("No");
            no.setVisible(true);
            no.setBounds(150,50,100,30);
            dialog.add(no);

            dialog.setVisible(true);

            no.addActionListener((e)->dialog.dispose());
            yes.addActionListener((e)->{
                new OrderWindow().RUN();
            });

        });

        this.getContentPane().add(customerPanel, BorderLayout.WEST);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        LoadAllProducts();
    }

    public static Customer getCustomer() {
        return customer;
    }

    private void LoadAllProducts() {
        Map<String, ArrayList<Product>> allProducts = DataBaseHandle.GetAllProducts();
        JButton add;
        for (String key : allProducts.keySet()) {
            ArrayList<Product> currentProductList = allProducts.get(key);
            for (Product P : currentProductList) {
                JScrollPane productpanel = new JScrollPane();
                productpanel.setBackground(Color.white);
                JLabel productInfo = new JLabel();


                add =  new JButton("add");
                add.setBounds(200,10,100,30);


                JButton remove = new JButton("remove");
                remove.setBounds(200,50,100,30);

                AtomicInteger count = new AtomicInteger();

                add.addActionListener((event)->{
                    count.getAndIncrement();
                    this.customer.getCustomerCart().AddProduct(P);
                    productpanel.setBackground(Color.GRAY);
                    productpanel.add(remove);
                    remove.setVisible(true);
                    productpanel.revalidate();
                    productpanel.repaint();
                    ++this.NumberOfProducts;
                    ShowMyCart.setText("My Car "+this.NumberOfProducts);
                    customerPanel.revalidate();
                    customerPanel.repaint();

//                    YalaBena.Play();
                });

                remove.addActionListener((event)->{
                    this.customer.getCustomerCart().RemoveProduct(P);
                    if(count.get() == 1){
                        productpanel.setBackground(Color.white);
                        remove.setVisible(false);
                    }
                    productpanel.revalidate();
                    productpanel.repaint();
                    --this.NumberOfProducts;
                    ShowMyCart.setText("My Car " + this.NumberOfProducts);
                    customerPanel.revalidate();
                    customerPanel.repaint();
                    count.getAndDecrement();
                });



                if(P instanceof ElectronicProduct){
                    ElectronicProduct ele = (ElectronicProduct) P;
                    productInfo.setText(
                            "<html>"+
                            "Name: " + ele.getName() + "<br>"
                            + "Price: "+ ele.getPrice() + "<br>"
                            + "Brand: "+ele.getBrand() + "<br>"
                            + "WarrentyPeriod: "+ele.getWarrentyPeriod()
                            + "</html>"
                    );
                }
                else if (P instanceof BookProduct){
                    BookProduct book = (BookProduct) P;
                    productInfo.setText(
                            "<html>"+
                                    "Name: " + book.getName() + "<br>"
                                    + "Price: "+ book.getPrice() + "<br>"
                                    + "Author: "+book.getAuthor() + "<br>"
                                    + "Publisher: "+book.getPublisher()
                                    + "</html>"
                    );
                }
                else{
                    ClothingProduct clo = (ClothingProduct) P;
                    productInfo.setText(
                            "<html>"+
                                    "Name: "+clo.getName() + "<br>"
                            +"Price: "+clo.getPrice() + "<br>"
                            +"Fabric: "+clo.getFabric()+"<br>"
                            +"Size: "+clo.getSize()
                            +"</html>"
                    );
                }
                productInfo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

                productpanel.setLayout(null);

                productInfo.setBounds(0,0,200,100);
                productpanel.add(productInfo);
                productpanel.add(add);
                panel.add(productpanel);
                panel.revalidate();

            }
        }
    }

    public void RUN() {
        SwingUtilities.invokeLater(() -> {
            this.setVisible(true);
        });
    }
}
