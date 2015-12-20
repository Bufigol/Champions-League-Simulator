package util;

import model.Team;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Data_managment implements INT_Data_Managment {
	private Path log;
	private ArrayList<Team> team_list;
	private static final Charset CHAR_CODING = Charset.forName("UTF-8");

	// Constructores
	public Data_managment() {
		this.team_list = new ArrayList<Team>();
	}

	@Override
	public void log_journal(String input_line) {
		OpenOption[] options = new OpenOption[] { APPEND, WRITE };
		try (BufferedWriter writer = Files.newBufferedWriter(this.log, this.CHAR_CODING, options)) {
			writer.newLine();
			writer.write(input_line, 0, input_line.length());
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}
	}

	@Override
	public void write_teams(String route) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, "Team", null);
			document.setXmlVersion("1.0"); // asignamos la version de nuestro
											// XML

			for (Team equipo : team_list) {
				Element root = document.createElement("team");
				document.getDocumentElement().appendChild(root);
				create_element("key", Integer.toString(equipo.getKey()), root, document);
				create_element("Team_Name", equipo.getNombre_equipo(), root, document);
				create_element("Code", equipo.getCodigo_equipo(), root, document);
				create_element("Country", equipo.getPais_Origen(), root, document);
			}
			Source source = new DOMSource(document);
			Result result = new StreamResult(new java.io.File("ruta"));
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source, result);
			Result console = new StreamResult(System.out);
			transformer.transform(source, console);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void create_element(String string_input, String value, Element root, Document document) {
		Element elem = document.createElement(string_input);
		Text text = document.createTextNode(value);
		root.appendChild(elem);
		elem.appendChild(text);
	}

	@Override
	public void read_teams(String route) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File(route));
			document.getDocumentElement().normalize();
			NodeList teams = document.getElementsByTagName("team");
			for (int i = 0; i < teams.getLength(); i++) {
				Node team_node = teams.item(i); // obtener un nodo
				if (team_node.getNodeType() == Node.ELEMENT_NODE) {
					Element elemento = (Element) team_node; // tipo de nodo
					this.team_list.add(new Team(Integer.parseInt(getNodo("key", elemento)),
							getNodo("Team_Name", elemento), getNodo("Code", elemento), getNodo("Country", elemento)));
				}
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String getNodo(String string, Element elemento) {
		// TODO Auto-generated method stub
		return elemento.getElementsByTagName(string).item(0).getTextContent();
	}

}
