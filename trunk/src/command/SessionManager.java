package command;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SessionManager
{
	public static Session getSession(String ipAddress,String username,String password) throws JSchException{
		JSch jsch = new JSch();
		Session session = jsch.getSession(username, ipAddress, 22);
		session.setPassword(password);
		session.setConfig("StrictHostKeyChecking", "no");
		return session;
	}

}
