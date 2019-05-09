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
import java.util.ArrayList;

public class DOMExample {
    private Element student;
    private Document document;

//    public void addRecord(String studentsFirstName, String studentsSecondName, String studentsSurname,
//                          String mothersFirstName, String mothersSecondName, String mothersSurname,
//                          String fathersFirstName, String fathersSecondName, String fathersSurname,
//                          int fathersIncome, int mothersIncome, int brothers, int sisters){
//        try {
//            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//            document = documentBuilder.parse("resource/xml_file1.xml");
//
//            Node students = document.getFirstChild();
//
//            student = document.createElement("student");
//            students.appendChild(student);
//
//            Attr firstName = document.createAttribute("firstName");
//            firstName.setValue(studentsFirstName);
//            student.setAttributeNode(firstName);
//
//            Attr secondName = document.createAttribute("secondName");
//            secondName.setValue(studentsSecondName);
//            student.setAttributeNode(secondName);
//
//            Attr surname = document.createAttribute("surname");
//            surname.setValue(studentsSurname);
//            student.setAttributeNode(surname);
//
//            Attr studentSisters = document.createAttribute("sisters");
//            studentSisters.setValue(Integer.toString(sisters));
//            student.setAttributeNode(studentSisters);
//
//            Attr studentBrothers = document.createAttribute("brothers");
//            studentBrothers.setValue(Integer.toString(brothers));
//            student.setAttributeNode(studentBrothers);
//
//            createParent(fathersFirstName, fathersSecondName, fathersSurname, "father", fathersIncome);
//            createParent(mothersFirstName, mothersSecondName, mothersSurname, "mother", mothersIncome);
//
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource domSource = new DOMSource(document);
//            StreamResult streamResult = new StreamResult(new File("resource/xml_file1.xml"));
//            transformer.transform(domSource, streamResult);
//        }
//        catch (ParserConfigurationException pce)
//        {
//            System.out.println(pce.getLocalizedMessage());
//            pce.printStackTrace();
//        }
//        catch (IOException ioe)
//        {
//            System.out.println(ioe.getLocalizedMessage());
//            ioe.printStackTrace();
//        }
//        catch (TransformerException te)
//        {
//            System.out.println(te.getLocalizedMessage());
//            te.printStackTrace();
//        }
//        catch (SAXException sae)
//        {
//            System.out.println(sae.getLocalizedMessage());
//            sae.printStackTrace();
//        }
//    }

    private void createParent(String firstName, String secondName, String  surname, String type, int income, Document document) {
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
        parent.setAttributeNode(parentIncome);

        student.appendChild(parent);
    }

    public void createNewFile(File file, ArrayList<Student> studentArrayList) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element students = doc.createElement("students");
            doc.appendChild(students);

            for (Student studentFromArray : studentArrayList) {
                student = doc.createElement("student");
                students.appendChild(student);

                Attr firstName = doc.createAttribute("firstName");
                firstName.setValue(studentFromArray.getFirstName());
                student.setAttributeNode(firstName);

                Attr secondName = doc.createAttribute("secondName");
                secondName.setValue(studentFromArray.getSecondName());
                student.setAttributeNode(secondName);

                Attr surname = doc.createAttribute("surname");
                surname.setValue(studentFromArray.getSurname());
                student.setAttributeNode(surname);

                Attr studentSisters = doc.createAttribute("sisters");
                studentSisters.setValue(Integer.toString(studentFromArray.getSisters()));
                student.setAttributeNode(studentSisters);

                Attr studentBrothers = doc.createAttribute("brothers");
                studentBrothers.setValue(Integer.toString(studentFromArray.getBrothers()));
                student.setAttributeNode(studentBrothers);

                createParent(studentFromArray.getFather().getFirstName(), studentFromArray.getFather().getSecondName(),
                        studentFromArray.getFather().getSurname(), "father", studentFromArray.getFather().getIncome(), doc);
                createParent(studentFromArray.getMother().getFirstName(), studentFromArray.getMother().getSecondName(),
                        studentFromArray.getMother().getSurname(), "mother", studentFromArray.getMother().getIncome(), doc);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(domSource, streamResult);
        } catch (ParserConfigurationException pce)
        {
            System.out.println(pce.getLocalizedMessage());
            pce.printStackTrace();
        }
        catch (TransformerException te)
        {
            System.out.println(te.getLocalizedMessage());
            te.printStackTrace();
        }
    }

    private void writeStudent(Document doc, Student student) {

    }
}
