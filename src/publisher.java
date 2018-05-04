import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;



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
	
	static void sendData(Socket s,String data)
	{
		DataOutputStream dout = null;
		try {
			dout = new DataOutputStream(s.getOutputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			dout.writeUTF(data);
			System.out.println("tt");
			dout.flush();
			dout.close();
			s.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static Socket InitSocket(String ip,int port)
	{
		Socket s = null;
		try {
			s = new Socket(ip, port);
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return s;
	}
	
	public void run() {
		
		int cnt=0;
		String pre="a",data="";
		while (cnt!=10)
		{
			try {
				Scanner scan=new Scanner(new File("outdata.txt") );
				String flag=scan.nextLine();
				if(flag.equals(pre))
					cnt++;
				else
				{
					cnt=0;
					pre=flag;
					while(scan.hasNextLine())
						data+=scan.nextLine();
					
					Socket s= InitSocket(ip, port);
					sendData(s,data);

				}
				scan.close();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				sleep(3200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			data="";
		}
	}
}