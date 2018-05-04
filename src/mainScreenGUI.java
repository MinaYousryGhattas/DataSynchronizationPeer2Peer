
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
import java.io.IOException;

public class mainScreenGUI extends JFrame  {

    JButton ConnectToNetwork = new JButton("Connect To Network");

    // listener information
    JTextField ipAddress=new JTextField("192.168.1.5",50);
    JTextField PortNumber =new JTextField("1234" , 50);

    JTextArea Chat = new JTextArea(10 , 50);
    JScrollPane scroll = new JScrollPane(Chat);

    public mainScreenGUI() throws IOException {

        setTitle(" My Chat ");
        setSize(600,600);;
        ConnectToNetwork.addActionListener( new action());
        ipAddress.addActionListener(new action());

        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(ConnectToNetwork);
        getContentPane().add(ipAddress);
        getContentPane().add(PortNumber);
        getContentPane().add(scroll);

        Timer timer = new Timer(1000 ,new fileAction()); // Execute task each 1000 mileSeconds
        timer.setRepeats(true);
        timer.start();

        //getContentPane().setBackground(Color.GRAY);
    }


    InitializeNetwork initializeNetwork = new InitializeNetwork(ipAddress.getText(), this);
    ListenerManager listenerManager = new ListenerManager(1234 , this);
    NetworkConfig netConfig=new NetworkConfig("0");
    WordGenerator generate=new WordGenerator();

    public void write(String s) {
        Chat.append(s + "\n");
    }

    private class action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object buttonPressed=e.getSource();

            if(buttonPressed.equals(ConnectToNetwork)){
                // if user change the default values !!
                listenerManager.setPort(Integer.parseInt(PortNumber.getText()));
                initializeNetwork.setAddressname(ipAddress.getText());

                initializeNetwork.start();
                listenerManager.start();
                netConfig.start();
                generate.start();
            }

            // Refresh to see new messages
            if (buttonPressed.equals(Refresh)){
                Chat.setText("");
                queuingModel.loadFromFile();
            }
        }
    }
    private class fileAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Chat.setText("");
            queuingModel.loadFromFile();

        }
    }
}
