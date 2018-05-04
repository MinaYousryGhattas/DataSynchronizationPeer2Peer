import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Mina_Yousry on 02/05/2018.
 */
public class ListenerManager extends Thread {
    private int port;
    private ServerSocket serverSocket;
    private queuingModel queuingModel = new queuingModel();
    private mainScreenGUI gui;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void setPort(int port) {
        this.port = port;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ListenerManager(int port ) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    ListenerManager(int port , mainScreenGUI gui) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.gui = gui;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void run(){
        boolean moreQuote = true;
        System.out.println("Listener is running");
       // if(gui != null){  gui.write("Listener is running"); }
        Socket s= null;
        while (moreQuote) {
            try {
                s = serverSocket.accept();

                new Listener(s ,this.queuingModel , this.gui).run();

                // for testing only !
                System.out.println("accepting socket");
                //if(gui != null){ gui.write("accepting socket"); }

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
