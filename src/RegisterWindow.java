import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
public class RegisterWindow extends JFrame {

    final private JPanel panel = new JPanel();
    final private JTextField Name = new JTextField();
    final private JTextField Pass1 = new JTextField();
    final private JTextField Pass2 = new JTextField();

    final private JTextField Addrr = new JTextField();

    public RegisterWindow(){
        this.setSize(500,500);
        this.setTitle("Register");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        panel.setLayout(null);
        JLabel NameL = new JLabel("Name: ");
        JLabel Passwd1 = new JLabel("Password: ");
        JLabel Passwd2 = new JLabel("Confirm Passowrd: ");
        JLabel Address = new JLabel("Address:: ");

        JButton Register = new JButton("Register");
        JButton Cancel = new JButton("Cancel");

        NameL.setBounds(50,50,100,30);
        Passwd1.setBounds(50,150,100,30);
        Passwd2.setBounds(50,250,150,30);
        Address.setBounds(50,350,100,30);

        Name.setBounds(150,50,250,30);
        Pass1.setBounds(150,150,250,30);
        Pass2.setBounds(200,250,250,30);
        Addrr.setBounds(150,350,250,30);

        Register.setBounds(120,400,100,30);
        Cancel.setBounds(270,400,100,30);

        panel.add(NameL);
        panel.add(Passwd1);
        panel.add(Passwd2);
        panel.add(Address);
        panel.add(Name);
        panel.add(Pass1);
        panel.add(Pass2);
        panel.add(Addrr);
        panel.add(Register);
        panel.add(Cancel);

        this.getContentPane().add(panel);

        Register.addActionListener(this::OnRegister);
        Cancel.addActionListener((ActionEvent event) ->{
            this.dispose();
            new LoginWindow().RUN();
        });


    }

    private void OnRegister(ActionEvent event){
        JLabel ErrorLable = new JLabel();
        StringBuilder error = new StringBuilder();
        ErrorLable.setBounds(100,280,400,100);

        String Name_T = Name.getText();
        String Pass1_T = Pass1.getText();
        String Pass2_T = Pass2.getText();
        String Address_T = Addrr.getText();

        if(!(Pass1_T.equals(Pass2_T)) || DataBaseHandle.IsExist(Name_T)){
            if(!(Pass1_T.equals(Pass2_T)))
                error.append("The tow Passwords Must Be the Same");
            if(DataBaseHandle.IsExist(Name_T)){
                error.append("<br><br>The Username Already Exists");
            }
            ErrorLable.setForeground(Color.RED);
            ErrorLable.setText("<html>"+error.toString()+"</html>");
            panel.add(ErrorLable);
            panel.revalidate();
            panel.repaint();
        }
        else{
            ErrorLable.setForeground(Color.green);
            ErrorLable.setText("Registeration Completed Hit Cancle to back to login");
            panel.add(ErrorLable);
            panel.revalidate();
            panel.repaint();
            int id = DataBaseHandle.GetMaxId();
            int finalId = ++id;
            ArrayList<?> Data = new ArrayList<>(){
                {
                    add(Name_T);
                    add(Pass1_T);
                    add(finalId);
                    add(false);
                    add(Address_T);
                }};
            DataBaseHandle.SaveNewUser(Data);
        }

    }

    public void RUN(){
        SwingUtilities.invokeLater(()->{
            this.setVisible(true);
        });
    }
}
