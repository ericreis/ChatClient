package Models;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable
{
	private static final long serialVersionUID = -6752038934620573853L;
	private String content;
	private String user;
	private Date date;
	private String timeFromDate;
	
	public Message(String content, String user, Date date)
	{
		this.content = content;
		this.user = user;
		this.date = date;
	}

	public String getMsg()
	{
		return this.content;
	}

	public void setMsg(String content)
	{
		this.content = content;
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
	
	public String getTimeFromDate()
	{
		StringBuilder sb = new StringBuilder(this.date.toString());
		this.timeFromDate = sb.substring(10,19);
		return this.timeFromDate;
	}
	
	public String toString()
	{
		String msg;
		msg = this.timeFromDate + " " + this.user + ": " + this.content;
		return msg;
	}
	
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		
		if (obj == null || getClass() == obj.getClass())
			return false;
		
		Message msg = (Message) obj;
		
		if (this.content == msg.content && this.user == msg.user && this.date == msg.date)
			return true;
		
		return false;
	}
}
