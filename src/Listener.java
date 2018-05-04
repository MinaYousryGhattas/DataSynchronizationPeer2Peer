import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Listener extends Thread{
	Socket socket;
	queuingModel queuingModel ;
	mainScreenGUI gui ;

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	Listener(Socket socket ,queuingModel queuingModel , mainScreenGUI gui){
		this.socket = socket;
		this.queuingModel = queuingModel;
		this.gui = gui;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void run(){
		try {
			DataInputStream dis =new DataInputStream(socket.getInputStream());
			String str= (String)dis.readUTF();

			queuingModel.addToQueue(str);

			if(queuingModel.getSize() == 10 ){
				queuingModel.saveToFile();
			}

			// for testing only !
			System.out.println(str);

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
