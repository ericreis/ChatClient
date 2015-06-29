import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LoginWindow
{
	private final String title = "Chat Client";
	
	private JFrame frame;
	
	private JLabel usernameLbl, ipAddrLbl;
	private JTextField username, ipAddr;
	private JButton login;
	
	public void go()
	{
		this.frame = new JFrame(this.title);
		JPanel mainPanel = new JPanel();
		
		this.usernameLbl = new JLabel("Username:");
		this.username = new JTextField(40);
		this.ipAddrLbl = new JLabel("IP Address:");
		this.ipAddr = new JTextField(40);
		this.login = new JButton("Login!");
		
		this.ipAddr.setText("127.0.0.1");
	
		mainPanel.add(this.usernameLbl);
		mainPanel.add(this.username);
		mainPanel.add(this.ipAddrLbl);
		mainPanel.add(this.ipAddr);
		mainPanel.add(this.login);
		
		this.login.addActionListener(new LoginListener());
		
		this.frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		this.frame.setSize(600, 130);
		this.frame.setVisible(true);
	}
	
	public void close()
	{
		this.frame.setVisible(false);
	}
	
	public class LoginListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			if (username.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(null, "Username field must be filled");
			}
			else if (ipAddr.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(null, "IP Address field must be filled");
			}
			else
			{
				ChatClient cc = new ChatClient(username.getText().trim(), ipAddr.getText().trim());
				cc.go();
				close();
			}
		}
	}
}
