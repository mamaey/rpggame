/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rpggame.net;


import java.io.OutputStream;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;


class StringOutputStream extends OutputStream {
	protected StringBuffer buf = new StringBuffer();
	public StringOutputStream() {}
        
        @Override
	public void close() {}
        @Override
	public void flush() {
		buf.delete(0, buf.length());
	}
        @Override
	public void write(byte[] b) {
		String str = new String(b);
		this.buf.append(str);
	}
        @Override
	public void write(byte[] b, int off, int len) {
		String str = new String(b, off, len);
		this.buf.append(str);
	}
        @Override
	public void write(int b) {
		String str = Integer.toString(b);
		this.buf.append(str);
	}
        @Override
	public String toString() {
		return buf.toString();
	}
}

public class Xml {
    public static <T> String getXML(T obj) throws JAXBException{
        StringOutputStream out = new StringOutputStream();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller m = context.createMarshaller();
        m.marshal(obj, out);
        return out.toString();
    }
    /**
     *
     * @param <T>
     * @param str
     * @return
     * @throws JAXBException
     */
    public static <T> T getObj(Class<T> clazz,String str) throws JAXBException{
        T t = null;
        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller m = context.createUnmarshaller();
        t = (T) m.unmarshal(new StreamSource( new StringReader(str)));
        return t;
    }
}
