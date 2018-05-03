import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Vector;

public class NetworkConfig extends Thread {

    private MulticastSocket socket ;
    private Vector<String> IPs;
	private InetAddress group;
    String addressname = "239.255.255.250";
    int port = 1234;
    protected byte[] buf;
    boolean morequotes = true;

    /////////////////////////////////////////////

    public NetworkConfig()
    {
        socket=null;
        IPs=new Vector<String>();
        addressname = "239.255.255.250";
        port=1234;
        buf = new byte[256];
    }

    public NetworkConfig(String ip)
    {
        socket=null;
        IPs=new Vector<String>();
        addressname = "239.255.255.250";
        IPs.add(ip);
        port=1234;
        buf = new byte[256];
    }


    //////////////////////////////////////////////
        
    public void run() {
        try {
            group = InetAddress.getByName(addressname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            socket = new MulticastSocket(port);
            socket.setLoopbackMode(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.joinGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DatagramPacket packet;
        while (morequotes){
            byte[] buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            try {
                System.out.println("Waiting for IP...");
                socket.receive(packet);
                String IP=new String(packet.getData());
                System.out.println("IP is: " + IP);
                if(!IPs.contains(IP))
                {
                	IPs.add(IP);
                	new publisher(IP,1234).start();
                }
            
            } catch (IOException e) {
                morequotes = false;
                e.printStackTrace();
            }
        }

        try {
            socket.leaveGroup(group);
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket.close();
    }
    
    public static void main(String[]args)
    {
    	NetworkConfig net=new NetworkConfig();
    	net.run();
    }

}
