import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SAXExample {
    private List<Student> students = new ArrayList<>();
    private Student student;

    public List getStudents(File file) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(file, handler);

        return students;
    }

    private class XMLHandler extends DefaultHandler {

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (student == null) {
                if (qName.equals("student")) {
                    String firstName = attributes.getValue("firstName");
                    String secondName = attributes.getValue("secondName");
                    String surname = attributes.getValue("surname");
                    int sisters =  Integer.parseInt(attributes.getValue("sisters"));
                    int brothers = Integer.parseInt(attributes.getValue("brothers"));
                    student = new Student(firstName, secondName, surname, sisters, brothers);
                }
            }
            if (qName.equals("parent")) {
                String firstName = attributes.getValue("firstName");
                String secondName = attributes.getValue("secondName");
                String surname = attributes.getValue("surname");
                int income = Integer.parseInt(attributes.getValue("income"));
                if (attributes.getValue("type").equals("mother")) {
                    Parent mother = new Parent(income, firstName, secondName, surname);
                    student.setMother(mother);
                    students.add(student);
                    student = null;
                }
                if (attributes.getValue("type").equals("father")) {
                    Parent father = new Parent(income, firstName, secondName, surname);
                    student.setFather(father);
                }
            }

        }
    }
}

