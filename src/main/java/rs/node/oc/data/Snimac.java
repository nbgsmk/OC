package rs.node.oc.data;

import rs.node.oc.model.Combo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Snimac {
	
	private final String subfolder = "userdata";
	
	public Snimac() {
		try {
			Files.createDirectories(Paths.get(subfolder));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private String getAbsFileName(String fn){
		return subfolder + "/" + fn;
	}
	public void writeXml(String fileName, Object o){
		if (! fileName.endsWith(".xml")) {
			fileName += ".xml";
		}
		try {
			XMLEncoder xe;
			xe = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(getAbsFileName(fileName))));
			xe.writeObject(o);
			xe.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Object readXml(String fileName){
		try {
			XMLDecoder xd = new XMLDecoder(new BufferedInputStream(new FileInputStream(getAbsFileName(fileName))));
			Object result = xd.readObject();
			xd.close();
			return result;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void writeObj(String fileName, Object o){
		try {
			FileOutputStream fos = new FileOutputStream(getAbsFileName(fileName));
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(o);
			oos.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Object readObj(String fileName){
		try {
			FileInputStream fis = new FileInputStream(getAbsFileName(fileName));
			BufferedInputStream bis =  new BufferedInputStream(fis);
			ObjectInputStream ois =  new ObjectInputStream(bis);
			Object o = ois.readObject();
			ois.close();
			return o;
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
