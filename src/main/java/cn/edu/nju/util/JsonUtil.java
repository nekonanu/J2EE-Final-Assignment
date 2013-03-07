package cn.edu.nju.util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: nekosama
 * Date: 13-3-7
 * Time: 下午7:30
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtil {
    private static ObjectMapper objectMapper=new ObjectMapper();

    public static Object[] readJsonToArray(String jsonStr,java.lang.Class aClass){
        Object[] objects=null;
        try {
            objects= (Object[]) objectMapper.readValue(jsonStr,aClass);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return objects;
    }
}
