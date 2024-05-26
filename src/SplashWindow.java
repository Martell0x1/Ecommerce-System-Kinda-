import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SplashWindow extends JFrame {
    final private JProgressBar progressBar = new JProgressBar(0,100);
    final private JLabel Loading = new JLabel("Loading.....");

    public SplashWindow(){
        this.setSize(1000,571);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Welcom To My E-Commerce System !!");
        this.setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(null);


        ImageIcon backgroundImageIcon = new ImageIcon("2503.jpg");

        JLabel backgroundLabel = new JLabel(backgroundImageIcon);

        backgroundLabel.setBounds(0, 0, 1000, 571);

        Loading.setFont(new Font("Arial",Font.BOLD,20));
        Loading.setForeground(Color.WHITE);
        Loading.setBounds(0,490,1000,50);

        progressBar.setStringPainted(true);
        progressBar.setSize(300,50);
        progressBar.setBounds(0,532,1000,4);
        progressBar.setString("");
        progressBar.setForeground(Color.red);

        backgroundLabel.add(Loading);
        backgroundLabel.add(progressBar);
        panel.add(backgroundLabel);


        this.getContentPane().add(panel);

    }


    private void updateProgressBar(){
        new Thread(
                ()->{
                    for(int x = 0 ; x <= 100 ; x+=20){
                        int finalX = x;
                        SwingUtilities.invokeLater(()-> {
                            progressBar.setValue(finalX);
                            if(finalX == 40) Loading.setText("Initialing Users Databse...");
                            if(finalX == 60) Loading.setText("Initializing Products Database...");
                            try {
                                DataBaseHandle.INIT_DATABASE();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                            if(finalX == 80) Loading.setText("Setting Things For you....");
                            if(finalX > 80) Loading.setText("Almost there.....");
                        });
                        try{
                            Thread.sleep(1000);
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    SwingUtilities.invokeLater(()-> {
                        this.dispose();
                        new LoginWindow().RUN();

                    });
                }).start();
    }

    public void RUN() {
        SwingUtilities.invokeLater(() -> {
            this.setVisible(true);
            updateProgressBar();
        });
    }
}
