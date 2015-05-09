import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChatClient
{
	private final String addr = "127.0.0.1";
	private final int port = 5000;
	private final String title = "Chat Client";
	
	private JTextField outgoing;
	private JButton sendButton;
	private PrintWriter writer;
	private Socket sock;
	
	public void go()
	{
		// create GUI and register listener on send button
		// call method setUpNetworking
		
		JFrame frame = new JFrame(this.title);
		JPanel mainPanel = new JPanel();
		this.outgoing = new JTextField(20);
		this.sendButton = new JButton("Send!");
		
		this.outgoing.addActionListener(new SendListener());
		this.sendButton.addActionListener(new SendListener());
		
		mainPanel.add(this.outgoing);
		mainPanel.add(this.sendButton);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		
		this.setUpNetworking();
		
		frame.setSize(400, 500);
		frame.setVisible(true);
		
	}
	
	private void setUpNetworking()
	{
		// create Socket object and PrintWriter object
		
		try
		{
			this.sock = new Socket(this.addr, this.port);
			this.writer = new PrintWriter(this.sock.getOutputStream());
			System.out.println("Network established in address: " + this.addr + " port: " + String.valueOf(this.port));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public class SendListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent ev)
		{
			try
			{
				writer.println(outgoing.getText());
				writer.flush();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}
}
