package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {
    static String fileName=System.getProperty("user.dir")+"/src/main/java/resources/config.properties";
    public static String readConfigProperty(String value) throws IOException
    {
        Properties prop=new  Properties();
        FileInputStream input = new FileInputStream(fileName);
        prop.load(input);
        return prop.getProperty(value);
    }
}
