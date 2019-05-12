package entity;

import java.util.Map;

public class ActionBean {

	private String actionName, actionClass, actionMethod;
	private Map<String, ResultBean> resultMap;

	@Override
	public String toString() {
		return "ActionBean [actionName=" + actionName + ", actionClass=" + actionClass + ", actionMethod="
				+ actionMethod + ", resultMap=" + resultMap + "]";
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod;
	}

	public Map<String, ResultBean> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, ResultBean> resultMap) {
		this.resultMap = resultMap;
	}
}
