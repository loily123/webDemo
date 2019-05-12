package util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import entity.ActionBean;
import entity.ResultBean;
import objectFactory.BeanContext;

public class ActionUtil {
	private static Logger log = Logger.getLogger(ActionUtil.class);
	private static Map<String, ActionBean> actionMap = new HashMap<String, ActionBean>();
	static {
		SAXReader reader = new SAXReader();
		Document document;
		log.info("��ʼ����xml");
		try {
			document = reader.read(BeanContext.class.getClassLoader().getResource("action.xml"));
			List<Element> actionList = document.getRootElement().elements("action");
			for (Element action : actionList) {
				ActionBean actionBean = new ActionBean();
				actionBean.setActionName(action.attributeValue("name"));
				actionBean.setActionClass(action.attributeValue("class"));
				actionBean.setActionMethod(action.attributeValue("method"));
				List<Element> resultList = action.elements("result");
				if (resultList != null || resultList.size() > 0) {
					Map<String, ResultBean> resultMap = new HashMap<String, ResultBean>();
					for (Element result : actionList) {
						ResultBean resultBean = new ResultBean();
						resultBean.setResultName(result.attributeValue("name"));
						resultBean.setResultType(result.attributeValue("type"));
						resultBean.setPagePath(result.getTextTrim());
						resultMap.put(resultBean.getResultName(), resultBean);
					}
					actionBean.setResultMap(resultMap);
				}
				actionMap.put(actionBean.getActionName(), actionBean);
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ActionBean getAction(String key) {
		return actionMap.get(key);
	}
}
