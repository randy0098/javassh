package command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;

import com.jcraft.jsch.Channel;

public class Command {
	private long timeout = 10000;
	private String commandContent;
	private String correctReturnValue;
	private String actualReturnValue;
	private Channel channel;
	
	public String getCorrectReturnValue()
    {
    	return correctReturnValue;
    }
	public void setCorrectReturnValue(String correctReturnValue)
    {
    	this.correctReturnValue = correctReturnValue;
    }
	public long getTimeout()
    {
    	return timeout;
    }
	public void setTimeout(long timeout)
    {
    	this.timeout = timeout;
    }
	public String getCommandContent()
    {
    	return commandContent;
    }
	public void setCommandContent(String commandContent)
    {
    	this.commandContent = commandContent;
    }
	
	public String getActualReturnValue()
    {
    	return actualReturnValue;
    }
	public void setActualReturnValue(String actualReturnValue)
    {
    	this.actualReturnValue = actualReturnValue;
    }
	public Channel getChannel()
    {
    	return channel;
    }
	public void setChannel(Channel channel)
    {
    	this.channel = channel;
    }
	
	/**
	 * 
	 * send command and get command return value
	 *
	 * @throws IOException
	 * @throws InterruptedException
	 */
	
	public void send() throws IOException, InterruptedException{
		//send command
		InputStream in = new PipedInputStream();
		this.channel.setInputStream(in);
		PipedOutputStream pout = new PipedOutputStream((PipedInputStream)in);
		pout.write(this.commandContent.getBytes());
		pout.flush();
		//get command's return value
		OutputStream out = new PipedOutputStream();
		this.channel.setOutputStream(out);
		PipedInputStream pin = new PipedInputStream((PipedOutputStream)out);
		long totaltime = this.timeout;
		ArrayList byteArrayList = new ArrayList();
		
		while(true){
			//if time is out, throws a timeout exception
			if(totaltime-500 < 0){
				System.out.println("read timeout");
				break;
			}
			//read return value every 0.5s
			Thread.sleep(500);
			totaltime = totaltime - 500;
			int byteLength = pin.available();
			//inputStream is blocked
			if(byteLength == 0){
				continue;
			}
			//there are bytes in inputStream
			else{
				byte[] temp = new byte[byteLength];
				int n = pin.read(temp);
				byteArrayList.add(temp.clone());
				//if there is no more byte in inputStream then finish reading.
				if(n==-1){
					break;
				}
				String value = Command.combine(byteArrayList).toString();
				System.out.println("value:" + value);
				//if return value is the correctReturnValue then finish reading.
				if(value == this.correctReturnValue){
					break;
				}
			}
		}
	}
	
	/**
	 * 
	 * combine byteArray in ArrayList
	 *
	 * @param byteArrayList
	 * @return
	 */
	private static byte[] combine(ArrayList byteArrayList){
		int size = 0;
		for(int i=0; i<byteArrayList.size(); i++){
			byte[] temp = (byte[])byteArrayList.get(i);
			size = size + temp.length;
		}
		
		byte[] array = new byte[size];
		int pos = 0;
		for(int i=0; i<byteArrayList.size(); i++){
			byte[] temp = (byte[])byteArrayList.get(i);
			System.arraycopy(temp,0,array,pos,temp.length);
			pos = pos + temp.length;
		}
		return array;
	}
	
	
}
