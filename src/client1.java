import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class client1 {

	public static void main(String[]args) throws IOException
	{
		int a=1;
		ServerSocket ss=new ServerSocket(1234);
		while(a!=0) {
		try
		{
			Socket s=ss.accept();
			DataInputStream dis =new DataInputStream(s.getInputStream());
			//  DataOutputStream dout=new DataOutput
			String str= (String)dis.readUTF();
			System.out.println(str);
		}
		catch(Exception e)
		{
			
		}
		}
		ss.close();

	}
}
