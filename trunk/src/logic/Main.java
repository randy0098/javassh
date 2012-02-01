/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package logic;

import hp.fcs.webservicesfw.FWConfig;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import xml.XMLManager;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import command.Command;
import command.CommandManager;
import command.SessionManager;

public class Main
{

	/**
	 * Description goes here.
	 *
	 * @param args
	 * @throws JAXBException 
	 * @throws JSchException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws JAXBException, JSchException, IOException, InterruptedException
	{
//		FWConfig fWConfig = (FWConfig)XMLManager.getRootNodeObject("hp.fcs.webservicesfw", "request\fh.20111101.55555.1111.22222.req.xml");
		Session session = SessionManager.getSession("16.173.244.21", "admin", "passw0rd123");
		session.connect(3000);
		Channel channel = session.openChannel("shell");
//		channel.connect();
//		ExpertCommand expertCommand = new ExpertCommand("passw0rd123");
//		CommandHandler.excuteCommand(channel, expertCommand);
		
		Command command = new Command();
		command.setCommandContent("expert\r");
		command.setSuccessMsg("password:");
		command.setChannel(channel);
		command.send();
		System.out.println(command.isSuccess());
//		System.out.println("returnValue:" + command.getActualReturnValue());
		System.out.println("-------------------------------------------------");
		Command command2 = new Command();
		command2.setCommandContent("passw0rd123\r");
		command.setSuccessMsg("CMALabClient1]#");
		command2.setChannel(channel);
		command2.send();
		System.out.println(command.isSuccess());
		session.disconnect();
	}

}
