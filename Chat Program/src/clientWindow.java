import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class clientWindow {

	static Text chatWindow;
	
	public static void sendMessage(Socket socket, String message) throws IOException {
		PrintWriter pr = new PrintWriter(socket.getOutputStream());
		pr.println("Ammar: " + message);
		pr.flush();
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("10.0.0.141", 4500);
		Display display = new Display();
		Shell clientWindow = new Shell(display);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		clientWindow.setLayout(layout);
		
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		GridData data1 = new GridData(GridData.FILL_BOTH);

		chatWindow = new Text(clientWindow, SWT.MULTI | SWT.V_SCROLL | SWT.READ_ONLY);
		chatWindow.setLayoutData(data1);
		Text messageBox = new Text(clientWindow, SWT.SINGLE);
		messageBox.setLayoutData(data);
		Button send = new Button(clientWindow, 0);
		send.setText("Send");
		send.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				try {
					sendMessage(s, messageBox.getText());
					messageBox.setText("");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
		clientWindow.open();
		
		while (!clientWindow.isDisposed()) {
			if (!display.readAndDispatch()) {
			              display.sleep();
				}
			
	}	
	}

}
