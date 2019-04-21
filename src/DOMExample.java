import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DOMExample {
    private Element student;
    private Document document;

    public void addRecord(String studentsFirstName, String studentsSecondName, String studentsSurname,
                          String mothersFirstName, String mothersSecondName, String mothersSurname,
                          String fathersFirstName, String fathersSecondName, String fathersSurname,
                          int fathersIncome, int mothersIncome, int brothers, int sisters){
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse("resource/xml_file1.xml");

            Node students = document.getFirstChild();

            student = document.createElement("student");
            students.appendChild(student);

            Attr firstName = document.createAttribute("firstName");
            firstName.setValue(studentsFirstName);
            student.setAttributeNode(firstName);

            Attr secondName = document.createAttribute("secondName");
            secondName.setValue(studentsSecondName);
            student.setAttributeNode(secondName);

            Attr surname = document.createAttribute("surname");
            surname.setValue(studentsSurname);
            student.setAttributeNode(surname);

            Attr studentSisters = document.createAttribute("sisters");
            studentSisters.setValue(Integer.toString(sisters));
            student.setAttributeNode(studentSisters);

            Attr studentBrothers = document.createAttribute("brothers");
            studentBrothers.setValue(Integer.toString(brothers));
            student.setAttributeNode(studentBrothers);

            createParent(mothersFirstName, mothersSecondName, mothersSurname, "mother", mothersIncome);
            createParent(fathersFirstName, fathersSecondName, fathersSurname, "father", fathersIncome);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("resource/xml_file1.xml"));
            transformer.transform(domSource, streamResult);
        }
        catch (ParserConfigurationException pce)
        {
            System.out.println(pce.getLocalizedMessage());
            pce.printStackTrace();
        }
        catch (IOException ioe)
        {
            System.out.println(ioe.getLocalizedMessage());
            ioe.printStackTrace();
        }
        catch (TransformerException te)
        {
            System.out.println(te.getLocalizedMessage());
            te.printStackTrace();
        }
        catch (SAXException sae)
        {
            System.out.println(sae.getLocalizedMessage());
            sae.printStackTrace();
        }
    }

    public void createParent(String firstName, String secondName, String  surname, String type, int income) {
        Element parent = document.createElement("parent");

        Attr parentFirstName = document.createAttribute("firstName");
        Attr parentSecondName = document.createAttribute("secondName");
        Attr parentSurname = document.createAttribute("surname");
        Attr parentType = document.createAttribute("type");
        Attr parentIncome = document.createAttribute("income");

        parentFirstName.setValue(firstName);
        parentSecondName.setValue(secondName);
        parentSurname.setValue(surname);
        parentType.setValue(type);
        parentIncome.setValue(Integer.toString(income));

        parent.setAttributeNode(parentFirstName);
        parent.setAttributeNode(parentSecondName);
        parent.setAttributeNode(parentSurname);
        parent.setAttributeNode(parentType);

        student.appendChild(parent);
    }
}
