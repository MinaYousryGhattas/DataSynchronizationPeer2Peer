import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;



public class publisher extends Thread{
	
	String ip;
	int port;
	Generator g;
	public publisher() {}
	public publisher(String ip,int port) throws IOException {
		this.ip=ip;
		this.port=port;
		g = new WordGenerator();
	}
	
	public void run() {

		for (int i = 0; i < 50; i++) {
			Socket s = null;
			try {
				s = new Socket(ip, port);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			DataOutputStream dout = null;
			try {
				dout = new DataOutputStream(s.getOutputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {

				dout.writeUTF(g.generateData());
				dout.flush();
				dout.close();
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
//    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
//    	publisher c=new publisher("192.168.43.54",1234);
//    	c.run();
//    }
}