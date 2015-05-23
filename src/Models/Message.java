package Models;

import java.util.Date;

public class Message
{
	private String msg;
	private String user;
	private Date date;
	
	public Message(String msg, String user, Date date)
	{
		this.msg = msg;
		this.user = user;
		this.date = date;
	}

	public String getMsg()
	{
		return this.msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public String getUser()
	{
		return this.user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public Date getDate()
	{
		return this.date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
	
	
}
