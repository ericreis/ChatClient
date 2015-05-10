import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

public class ChatClient
{
	private final String addr = "127.0.0.1";
	private final int port = 5000;
	private final String title = "Chat Client";
	
	private JTextArea incoming;
	private JScrollPane qScroll;
	private JTextField outgoing;
	private JButton sendButton;
	private BufferedReader reader;
	private PrintWriter writer;
	private Socket sock;
	
	public void go()
	{
		// create GUI and register listener on send button
		// call method setUpNetworking
		
		JFrame frame = new JFrame(this.title);
		JPanel mainPanel = new JPanel();
		
		this.incoming = new JTextArea(15,50);
		this.outgoing = new JTextField(40);
		this.sendButton = new JButton("Send!");
		this.qScroll = new JScrollPane(this.incoming);
		
		DefaultCaret caret = (DefaultCaret) this.incoming.getCaret();
		caret.setUpdatePolicy(DefaultCaret.OUT_BOTTOM);
		
		this.incoming.setLineWrap(true);
		this.incoming.setWrapStyleWord(true);
		this.incoming.setEditable(false);
		this.qScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.qScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.outgoing.addActionListener(new SendListener());
		this.sendButton.addActionListener(new SendListener());
		
		mainPanel.add(this.qScroll);
		mainPanel.add(this.outgoing);
		mainPanel.add(this.sendButton);
		
		this.setUpNetworking();
		
		// Starts thread to receive messages from server
		Thread t = new Thread(new IncomingReader(this.reader, this.incoming));
		t.start();
		
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(650, 500);
		frame.setVisible(true);
	}
	
	private void setUpNetworking()
	{
		// create Socket object and PrintWriter object
		
		try
		{
			this.sock = new Socket(this.addr, this.port);
			InputStreamReader streamReader = new InputStreamReader(this.sock.getInputStream());
			this.reader = new BufferedReader(streamReader);
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
				if (!outgoing.getText().trim().equals(""))
				{
					writer.println(outgoing.getText().trim());
					writer.flush();
				}
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
