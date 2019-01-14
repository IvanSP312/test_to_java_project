package ru.convertXmlToJson;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.TestLibs;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.Scanner;

public class ConvetXmlToJson {
    public static int PRETTY_PRINT_INDENT_FACTOR = 2;
    private static final org.apache.log4j.Logger log = Logger.getLogger(ConvetXmlToJson.class);

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        if (args.length>0) {

            try {
                String filename = args[0];
                FileReader reader = new FileReader(filename);
                Scanner scan = new Scanner(reader);
                int c;
                String xml = "";
                while (scan.hasNextLine()) {
                    xml += scan.nextLine();
                }


                JSONObject xmlJSONObj = XML.toJSONObject(xml);
                String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);

                System.out.println(jsonPrettyPrintString);

            } catch (JSONException e) {
                System.out.println(e.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            log.info("Имя файла не задано! Взято значение по умолчанию.");
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "\n" +
                    "<testPlans>\n" +
                    "\n" +
                    "<testPlan path=\"C:\\JMX\\acknowledgeReceipt_1.jmx\" timeToWait=\"10\">\n" +
                    "\n" +
                    "<outLog>C:\\JMX\\acknowledgeReceipt_1.log</outLog>\n" +
                    "\n" +
                    "</testPlan>\n" +
                    "\n" +
                    "<testPlan path=\"C:\\JMX\\acknowledgeReceipt_10.jmx\">\n" +
                    "\n" +
                    "<outLog>C:\\JMX\\acknowledgeReceipt_10.log</outLog>\n" +
                    "\n" +
                    "</testPlan>\n" +
                    "\n" +
                    "<testPlan path=\"C:\\JMX\\acknowledgeReceipt_20.jmx\"/>\n" +
                    "\n" +
                    "</testPlans>\n";
            log.info(format(xml));

            JSONObject xmlJSONObj = XML.toJSONObject(xml);
            String jsonPrettyPrintString = xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
            log.info("Итоговое значение:\n" + jsonPrettyPrintString);
        }
    }




    public static String format(String xml) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        Document doc = fromXML(xml);
        return toXML(doc);
    }

    public static Document fromXML(String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
    }

    public static String toXML(Document document) throws TransformerException, IOException {

        try {
            OutputFormat format = new OutputFormat(document);
            format.setLineWidth(65);
            format.setIndenting(true);
            format.setIndent(2);
            Writer out = new StringWriter();
            XMLSerializer serializer = new XMLSerializer(out, format);
            serializer.serialize(document);

            return out.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
