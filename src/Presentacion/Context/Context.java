package Presentacion.Context;

public class Context {
	private int event;
	private Object data;
	
	public Context() {};
	
	public Context(int event, Object data)
	{
		this.event = event;
		this.data = data;
	}
	
	public void setEvent(int event)
	{
		this.event = event;
	}
	
	public void setData(Object data)
	{
		this.data = data;
	}
	
	public int getEvent()
	{
		return this.event;
	}
	
	public Object getData()
	{
		return this.data;
	}

}
