package rs.node.oc.data;

import rs.node.oc.model.Combo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

public class Snimac {
	
	public Snimac() {
	}
	
	public void saveOos(String fileName, Object o){
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(o);
			oos.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Object readOos(String fileName){
		try {
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis =  new BufferedInputStream(fis);
			ObjectInputStream ois =  new ObjectInputStream(bis);
			Object o = ois.readObject();
			ois.close();
			return o;
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	
	public void doXe(String fileName, Object o){
		try {
			XMLEncoder xe;
			xe = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
			xe = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
			xe.writeObject(o);
			xe.close();
			
			
			// FileOutputStream fos = new FileOutputStream(fileName);
			// BufferedOutputStream bos = new BufferedOutputStream(fos);
			// ObjectOutputStream oos = new ObjectOutputStream(bos);
			// xe = new XMLEncoder(oos);
			// xe.writeObject(o);
			// xe.close();
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Object getXE(String fileName){
		try {
			XMLDecoder xd = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
			Object result = xd.readObject();
			xd.close();
			return result;
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
}
