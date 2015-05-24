import java.io.ObjectInputStream;

import javax.swing.JTextArea;

import Models.Message;

public class IncomingReader implements Runnable
{	
	private ObjectInputStream inputStream;
	private JTextArea incoming;
	
	public IncomingReader(ObjectInputStream inputStream, JTextArea incoming)
	{
		this.inputStream = inputStream;
		this.incoming = incoming;
	}
	
	@Override
	public void run()
	{
		Message msg;
		
		try
		{
			while ((msg = (Message) this.inputStream.readObject()) != null)
			{
				System.out.println("Read: " + msg.getTimeFromDate());
				this.incoming.append(msg + "\n");
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
