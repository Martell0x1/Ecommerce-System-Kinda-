import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends JFrame{

    final private JPanel panel;

    final private JTextField Username = new JTextField();
    final private JPasswordField Password = new JPasswordField();
    final private RegisterWindow registerWindow = new RegisterWindow();
    final private UserManager usermngr = new UserManager();
    private static Customer customer = new Customer();

    final private JButton Register = new JButton("Register");

    public static Customer getCustomer() {
        return customer;
    }

    public LoginWindow(){
        this.setSize(500,400);
        this.setTitle("Login");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        this.panel = new JPanel();

        panel.setLayout(null);

        JButton login = new JButton("Login");
        login.setBounds(100,300,100,30);

        JButton cancel = new JButton("Cancel");
        cancel.setBounds(300,300,100,30);

        JLabel UserNameLabel = new JLabel("Username: ");
        JLabel PasswordLabel = new JLabel("Password: ");
        UserNameLabel.setBounds(50,50,100,30);
        PasswordLabel.setBounds(50,150,100,30);



        Username.setBounds(130,50,320,30);
        Password.setBounds(130,150,320,30);
        Register.setBounds(200,300,100,30);


        panel.add(UserNameLabel);
        panel.add(PasswordLabel);
        panel.add(Username);
        panel.add(Password);
        panel.add(login);
        panel.add(cancel);
        panel.add(Register);

        this.getContentPane().add(panel);

        cancel.addActionListener(this::OnCancel);
        login.addActionListener(this::OnLogin);
        Register.addActionListener(this::OnRegister);

    }

    private void OnCancel(ActionEvent event){
        this.dispose();
    }

    private void OnLogin(ActionEvent event){
        String username = Username.getText();
        String passwd = new String(Password.getPassword());


        if(usermngr.DoLogin(username,passwd)){
            DataBaseHandle.SaveCurrentUser(username);
            this.dispose();
            ProductWindow productWindow = new ProductWindow();
            productWindow.RUN();

        }else {
            JLabel ErrorLabel = new JLabel("Invalid Username or Password");
            ErrorLabel.setBounds(130,200,300,20);
            ErrorLabel.setForeground(Color.RED);

            panel.add(ErrorLabel);
            panel.revalidate();
            panel.repaint();

        }
    }


    private void OnRegister(ActionEvent event){
        this.dispose();
        registerWindow.RUN();
    }


    public void RUN(){
        SwingUtilities.invokeLater(()->{
            this.setVisible(true);
        });
    }
}
