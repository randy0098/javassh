/*
 * Copyright Notice ====================================================
 * This file contains proprietary information of SNS.
 * Copying or reproduction without prior written approval is prohibited.
 * Copyright (c) 2010   All rights reserved. ======================
 */

package xml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XMLManager
{
	public static Object getRootNodeObject(String packageName,String xmlFilePath) throws JAXBException, FileNotFoundException{
        JAXBContext jc = JAXBContext.newInstance(packageName);
        //create an Unmarshaller
        Unmarshaller u = jc.createUnmarshaller();
        //get xml root object
        Object rootNodeObject = (Object) u.unmarshal(new FileInputStream(xmlFilePath)); 
		return rootNodeObject;
	}
}
