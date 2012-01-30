/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package logic;

import hp.fcs.webservicesfw.FWConfig;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Main
{

	/**
	 * Description goes here.
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
        try {
            JAXBContext jc = JAXBContext.newInstance( "hp.fcs.webservicesfw" );
            //create an Unmarshaller
            Unmarshaller u = jc.createUnmarshaller();
            //get xml root object
            FWConfig fwConfig = (FWConfig) u.unmarshal( new FileInputStream( "fh.20111101.55555.1111.22222.req.xml" )); 
            
        } catch( JAXBException je ) {
            je.printStackTrace();
        } catch( IOException ioe ) {
            ioe.printStackTrace();
        }
	}

}
