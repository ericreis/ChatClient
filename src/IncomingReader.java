import java.io.BufferedReader;

import javax.swing.JTextArea;

public class IncomingReader implements Runnable
{	
	private BufferedReader reader;
	private JTextArea incoming;
	
	public IncomingReader(BufferedReader reader, JTextArea incoming)
	{
		this.reader = reader;
		this.incoming = incoming;
	}
	
	@Override
	public void run()
	{
		String msg;
		
		try
		{
			while ((msg = this.reader.readLine()) != null)
			{
				System.out.println("Read: " + msg);
				this.incoming.append(msg + "\n");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
