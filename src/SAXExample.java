import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SAXExample {
    private static ArrayList<Student> students = new ArrayList<>();
    private static Student student;

    public ArrayList getStudents() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File("resource/xml_file1.xml"), handler);

//        for (Student student : students)
//            System.out.println(String.format("Имя студента: %s,  его мамочки: %s,  его папочки: %s, кол-во сестер: %d", student.getName(), student.getMother().getName(), student.getFather().getName(), student.getSisters()));

        return students;
    }

    private static class XMLHandler extends DefaultHandler {


        @Override
        public void startDocument() throws SAXException {
            // Тут будет логика реакции на начало документа
        }

        @Override
        public void endDocument() throws SAXException {
            // Тут будет логика реакции на конец документа
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            // Тут будет логика реакции на начало элемента
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

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            // Тут будет логика реакции на конец элемента
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            // Тут будет логика реакции на текст между элементами
        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
            // Тут будет логика реакции на пустое пространство внутри элементов (пробелы, переносы строчек и так далее).
        }
    }
}

