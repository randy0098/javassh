package command;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import com.jcraft.jsch.Channel;


public class CommandManager {
	
	public static String sendCommand(Channel channel,Command command) throws IOException {
		InputStream in = new PipedInputStream();
		channel.setInputStream(in);
		PipedOutputStream pout = new PipedOutputStream((PipedInputStream)in);
		pout.write(command.getCommandContent().getBytes());
		pout.flush();
		return null;
	}
	
	public String getCommandReturnValue(Channel channel) throws IOException{
		OutputStream out = new PipedOutputStream();
		channel.setOutputStream(out);
		PipedInputStream pin = new PipedInputStream((PipedOutputStream)out);
		return null;
	}
	
}
