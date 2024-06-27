package bg.softuni.MVCDemo.utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.FileReader;
import java.io.IOException;

public class XmlParser {
    public static  <T> T fromXmlFile(String filePath, Class<T> tClass) {
        try (FileReader fileReader = new FileReader(filePath)) {
            JAXBContext context = JAXBContext.newInstance(tClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(fileReader);
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
