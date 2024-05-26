import javax.swing.*;
import java.awt.*;

public class OrderWindow extends JFrame {

    private final Customer customer = ProductWindow.getCustomer();
    private final Order order = new Order(1,customer);
    private final Product[] cart = order.getCustomer().getCustomerCart().getProducts();

    private final JPanel panel = new JPanel();
    public OrderWindow(){
        this.setSize(500, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Welcome To The E-Commerce System !!");
        this.setLocationRelativeTo(null);


        JScrollPane scrollPane = new JScrollPane(panel);
        JScrollBar verticalScroll = scrollPane.getVerticalScrollBar();
        verticalScroll.setUnitIncrement(10);

        JButton Ok = new JButton("Ok");
        Ok.addActionListener((e) -> this.dispose());

        AddProductsToList(panel);
        panel.add(Ok);

        this.getContentPane().add(scrollPane);

    }

    private void AddProductsToList(JPanel panel){
        StringBuilder str = order.PrintInfo2();
        JLabel product = new JLabel(
                "<html>" + str.toString() +"</html>"
        );
        panel.add(product);
    }

    public void RUN(){
        SwingUtilities.invokeLater(()->this.setVisible(true));
    }
}
