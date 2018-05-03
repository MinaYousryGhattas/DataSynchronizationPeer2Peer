import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Listener extends Thread{
	Socket socket;
	mainScreenGUI gui;

	Listener(Socket socket , mainScreenGUI gui){
		this.socket = socket;
		this.gui = gui;
	}

	@Override
	public void run(){
		try {
			DataInputStream dis =new DataInputStream(socket.getInputStream());
			String str= (String)dis.readUTF();
			System.out.println(str);
			gui.write(str);
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
