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


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // Создание фабрики и образца парсера
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File("resource/xml_file1.xml"), handler);

        for (Student student : students)
            System.out.println(String.format("Имя студента: %s,  его мамочки: %s,  его папочки: %s, кол-во сестер: %d", student.getName(), student.getMother().getName(), student.getFather().getName(), student.getSisters()));
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
                    String firstname = attributes.getValue("firstname");
                    String secondname = attributes.getValue("secondname");
                    String surname = attributes.getValue("surname");
                    int sisters =  Integer.parseInt(attributes.getValue("sisters"));
                    int brothers = Integer.parseInt(attributes.getValue("brothers"));
                    FullName name = new FullName(surname, firstname, secondname);
                    student = new Student(name, sisters, brothers);
                }
            }
            if (qName.equals("mother")) {
                String firstname = attributes.getValue("firstname");
                String secondname = attributes.getValue("secondname");
                String surname = attributes.getValue("surname");
                int income = Integer.parseInt(attributes.getValue("income"));
                FullName name = new FullName(surname, firstname, secondname);
                Parent mother = new Parent(income, name);
                student.setMother(mother);
            }
            if (qName.equals("father")) {
                String firstname = attributes.getValue("firstname");
                String secondname = attributes.getValue("secondname");
                String surname = attributes.getValue("surname");
                int income = Integer.parseInt(attributes.getValue("income"));
                FullName name = new FullName(surname, firstname, secondname);
                Parent father = new Parent(income, name);
                student.setFather(father);
                students.add(student);
                student = null;
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

