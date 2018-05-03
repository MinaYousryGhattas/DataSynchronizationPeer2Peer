import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Mina_Yousry on 02/05/2018.
 */
public class ListenerManager extends Thread {
    private ServerSocket serverSocket;
    private mainScreenGUI gui;



    ListenerManager(int port , mainScreenGUI gui) throws IOException {
        serverSocket = new ServerSocket(port);
        this.gui = gui;
    }

    public void run(){
        boolean moreQuote = true;
        //System.out.println("Listener is running");
        gui.write("Listener is running");
        Socket s= null;
        while (moreQuote) {
            try {
                s = serverSocket.accept();
                new Listener(s , this.gui).run();
               // System.out.println("accepting socket");
                gui.write("accepting socket");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
