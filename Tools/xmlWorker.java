package Tools;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import com.google.gson.JsonArray;

public class xmlWorker {

	private List<Element> msgIn1;
	private List<Element> msgOut1;
	private List<Element> msgIn2;
	private List<Element> msgOut2;

	private List<Element> getMsgIn1() {
		return msgIn1;
	}

	private void setMsgIn1(List<Element> msgIn1) {
		this.msgIn1 = msgIn1;
	}

	private List<Element> getMsgOut1() {
		return msgOut1;
	}

	private void setMsgOut1(List<Element> msgOut1) {
		this.msgOut1 = msgOut1;
	}

	private List<Element> getMsgIn2() {
		return msgIn2;
	}

	private void setMsgIn2(List<Element> msgIn2) {
		this.msgIn2 = msgIn2;
	}

	private List<Element> getMsgOut2() {
		return msgOut2;
	}

	private void setMsgOut2(List<Element> msgOut2) {
		this.msgOut2 = msgOut2;
	}

	public void setMessages(String[] xmlStringMessages) throws JDOMException, IOException {
		int i = 0;
		for (String stringXmlMessage : xmlStringMessages) {
			Document document = stringXmlToDocument(stringXmlMessage);
			Element rootElement = getRootElement(document);
			List<Element> elementsList = getChildrens(rootElement);

			switch (i) {
			case 0:
				setMsgIn1(elementsList);
				System.out.println("Se guardo el MsgIn1");
				i++;
				break;
			case 1:
				setMsgOut1(elementsList);
				System.out.println("Se guardo el MsgOut1");
				i++;
				break;
			case 2:
				setMsgIn2(elementsList);
				System.out.println("Se guardo el MsgIn2");
				i++;
				break;
			case 3:
				setMsgOut2(elementsList);
				System.out.println("Se guardo el MsgOut2");
				i++;
				break;
			default:
				System.out.println("Caso default del Switch.");
			}

		}
	}

	public void main(String xmlString) throws JDOMException, IOException {
		if (xmlString.contains("isomsg")) {
			Document document = stringXmlToDocument(xmlString);
			Element rootElement = getRootElement(document);
			List<Element> elementsList = getChildrens(rootElement);
			recorrerLista(elementsList);
		}
	}

	private void recorrerLista(List<Element> elementsList) {
		for (Element field : elementsList) {
			if (field.getAttributeValue("value") == null) {
				String isoMsgValue = field.getAttributeValue("id");

				List<Element> field2 = field.getChildren("field");
				for (Element field2Element : field2) {
					String field2ID = isoMsgValue + "." + field2Element.getAttributeValue("id");
					System.out.println(
							"Field id: " + field2ID + " Value: \"" + field2Element.getAttributeValue("value") + "\"");
				}
			} else {
				System.out.println("Field id: " + field.getAttributeValue("id") + " Value: \""
						+ field.getAttributeValue("value") + "\"");
			}
		}
	}

	public String[][] getResponse(String mti, JsonArray validationFields) {
		String[][] fields = new String[validationFields.size()][2];
		String[] expectedFields = new String[validationFields.size()];
		List<Element> msg = null;

		switch (mti) {
		case "msgIn1":
			System.out.println("Se obtiene el " + mti);
			msg = getMsgIn1();
			break;
		case "msgOut1":
			System.out.println("Se obtiene el " + mti);
			msg = getMsgOut1();
			break;
		case "msgIn2":
			System.out.println("Se obtiene el " + mti);
			msg = getMsgIn2();
			break;
		case "msgOut2":
			System.out.println("Se obtiene el " + mti);
			msg = getMsgOut2();
			break;
		default:
			System.out.println("No existe mensaje con mti: " + mti);
		}

		for (int i = 0; i < validationFields.size(); i++) {
			expectedFields[i] = validationFields.get(i).getAsJsonArray().get(0).getAsString();
			System.out.println("Expected Fields: " + expectedFields[i]);
		}

		fields = getFields(msg, expectedFields);
		return fields;
	}

	private String[][] getFields(List<Element> msg, String[] expectedFields) {
		String[][] receiveFields = new String[expectedFields.length][2];
		String id = "";
		String value = "";

		for (int i = 0; i < receiveFields.length; i++) {

			for (Element field : msg) {

				if (field.getAttributeValue("value") == null) {
					String isoMsgValue = field.getAttributeValue("id");

					List<Element> field2 = field.getChildren("field");

					for (Element field2Element : field2) {
						String field2ID = isoMsgValue + "." + field2Element.getAttributeValue("id");
						System.out.println("Field id: " + field2ID + " Value: \""
								+ field2Element.getAttributeValue("value") + "\"");
						if (expectedFields[i].equals(field2ID)) {
							id = field2ID;
							value = field2Element.getAttributeValue("value");
							break;
						}
					}
				} else {
					System.out.println("Field id: " + field.getAttributeValue("id") + " Value: \""
							+ field.getAttributeValue("value") + "\"");
					id = field.getAttributeValue("id");
					value = field.getAttributeValue("value");
				}

				if (expectedFields[i].equals(id)) {
					receiveFields[i][0] = id;
					receiveFields[i][1] = value;
					break;
				}
				
				
			}
			
			
		}

		return receiveFields;
	}

	public String[] multipleStringXmlSplitter(String multipleStringXml) throws JDOMException, IOException {
		String[] mensajes = null;
		System.out.println("multiple String Xml Splitter");
		multipleStringXml = multipleStringXml.trim();
		mensajes = multipleStringXml.split("-separator-");
		return mensajes;
	}

	public Document stringXmlToDocument(String xmlString) throws JDOMException, IOException {
		System.out.println("Transformar xml String a Document.");
		SAXBuilder builder = new SAXBuilder();
		return builder.build(new StringReader(xmlString));
	}

	public Element getRootElement(Document xmlDocument) {
		System.out.println("getting root element of XML DOCUMENT");
		return xmlDocument.getRootElement();
	}

	public List<Element> getChildrens(Element rootElement) {
		Element isoMsg = rootElement.getChild("isomsg");
		System.out.println(isoMsg);
		System.out.println("getting childrens elements of rootElement: <field/>'s");
		return isoMsg.getChildren();
	}

	public void messagesPrint() {
		System.out.println("getMsgIn1: \n" + getMsgIn1());
		System.out.println("getMsgOut1: \n" + getMsgOut1());
		System.out.println("getMsgIn2: \n" + getMsgIn2());
		System.out.println("getMsgOut2: \n" + getMsgOut2());
	}
}
