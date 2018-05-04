import javax.swing.*;
import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * Created by Mina_Yousry on 02/05/2018.
 */

public class Main {
    public static void main(String[] args) throws IOException {
        mainScreenGUI mainScreenGUI = new mainScreenGUI();
        mainScreenGUI.setVisible(true);
        mainScreenGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        // testing the queuing model
//        int counter = 0;
//        generate generate = new generate();
//        queuingModel queuingModel = new queuingModel();
//
//        for (int i = 0; i <= 100; i++) {
//
//            if(queuingModel.getSize() == 20 ){
//                System.out.println(counter++);
//                queuingModel.saveToFile();
//
//                try {
//                    sleep(10000);
//                    System.out.println("i am sleeping now xD  ");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            queuingModel.addToQueue(generate.generateData());
//        }
//        System.out.println("finished");


//        InitializeNetwork initializeNetwork = new InitializeNetwork("192.168.1.3");
//        initializeNetwork.start();
//        ListenerManager listenerManager = new ListenerManager(1234);
//        listenerManager.start();
//
//        NetworkConfig netConfig=new NetworkConfig("0");
//        netConfig.start();

    }
}
