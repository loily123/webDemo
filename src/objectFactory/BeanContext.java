package objectFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class BeanContext {
	static Logger log = Logger.getLogger(BeanContext.class);
	static Map<String, Object> map = new HashMap<String, Object>();
	static {
		parserXML();
	}

	@SuppressWarnings("unchecked")
	static void parserXML() {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(BeanContext.class.getClassLoader().getResource("beans.xml"));
			List<Element> list = document.getRootElement().elements("bean");
			for (int i = 0; i < list.size(); i++) {
				Element bean = list.get(i);
				String key = bean.attributeValue("id");
				log.info("key:" + key);
				String classPath = bean.attributeValue("class");
				log.info("classPath:" + classPath);
				Object obj = Class.forName(classPath).newInstance();
				map.put(key, obj);
				setField(bean, obj);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	static void setField(Element bean, Object obj) {
		// TODO Auto-generated method stub
		List<Element> prop = bean.elements("property");
		for (int i = 0; i < prop.size(); i++) {
			Element field = prop.get(i);
			Object fObj = map.get(field.attributeValue("ref"));
			String str = field.attributeValue("name");
			str = str.substring(0, 1).toUpperCase() + str.substring(1);
			str = "set" + str;
			try {
				Method method = obj.getClass().getDeclaredMethod(str, fObj.getClass().getInterfaces());
				method.invoke(obj, fObj);
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static Object getBeans(String key) {
		return map.get(key);
	}
}
