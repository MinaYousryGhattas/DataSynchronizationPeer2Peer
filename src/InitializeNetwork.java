import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * Created by Mina_Yousry on 02/05/2018.
 */
public class InitializeNetwork extends Thread {
    private MulticastSocket socket;
    private String addressname = "239.255.255.250";
    private String ipAddress;
    private InetAddress group;
    private int port = 1234;
    private mainScreenGUI gui;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    InitializeNetwork(String ip) throws IOException {
        socket = new MulticastSocket();
//        socket.setLoopbackMode(true);
        group = InetAddress.getByName(addressname);
        socket.joinGroup(group);
        ipAddress = ip;
    }

    InitializeNetwork(String ip , mainScreenGUI gui) throws IOException {
        socket = new MulticastSocket();
        group = InetAddress.getByName(addressname);
        socket.joinGroup(group);
        ipAddress = ip;
        this.gui = gui;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String getAddressname() {
        return addressname;
    }

    public void setAddressname(String addressname) {
        this.addressname = addressname;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void run(){
        while (true) {
            byte[] buf = new byte[256];
            String dString = ipAddress;

            buf = dString.getBytes();
            DatagramPacket packet;
            packet = new DatagramPacket(buf, buf.length, group, port);

            try {
                socket.send(packet);
                System.out.println("Sent my Ip Address : " + dString);
//                if(gui != null ){gui.write("Sent my Ip Address : ");}
//                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                sleep((long)6 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public String findAddress(String addressname){
        if (addressname.length() < 1)
            addressname = "Ralink RT3290 802.11bgn Wi-Fi Adapter";
       // String ip;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    // *EDIT*
                    if (addr instanceof Inet6Address) continue;
//                    if (iface.)

                    System.out.println(iface.getDisplayName()+" "+addr.getHostAddress());

                    if (iface.getDisplayName().equals(addressname))
                        return addr.getHostAddress();
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
