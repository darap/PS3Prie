package pkgEmpty;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import exceptions.BookException;
import pkgLibrary.Catalog;

public class TestBook {
	
	@Test (expected=BookException.class)
	public void TestAddBook1() throws BookException {
		Catalog cat= ReadXMLFile();
		cat.AddBook("bk101");
	}
	
	@Test
	public void TestAddBook2() throws BookException{
		Catalog cat= ReadXMLFile();
		int size= cat.getBooks().size();
		cat.AddBook("bk002");
		assertTrue(cat.getBooks().size()== size+1);
	}
	
	
	private static void WriteXMLFile(Catalog cat) {
		try {

			String basePath = new File("").getAbsolutePath();
			basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
			File file = new File(basePath);

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(cat, file);
			jaxbMarshaller.marshal(cat, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "\\src\\main\\resources\\XMLFiles\\Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;

	}
}

