import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class Reciever extends Thread {
	
	Socket socket;
	
	public Reciever(Socket s) {
		this.socket = s;
	}
	
	public void displayMessage(Text window, String message) {
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				window.append("\n");
				window.append(message);
			}
		});
	}
	
	@Override
	public void run() {
			try {
				InputStreamReader in = new InputStreamReader(socket.getInputStream());
				BufferedReader br = new BufferedReader(in);
				while (true) {
					String message = br.readLine();
					displayMessage(clientWindow.chatWindow, message);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
