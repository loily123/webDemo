package dispatcher;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import entity.ActionBean;
import entity.ResultBean;
import objectFactory.BeanContext;
import util.ActionUtil;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(DispatcherServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatcherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = request.getRequestURI();
		log.info("请求地址：" + path);
		path = path.substring(request.getContextPath().length());
		log.info("截取地址：" + path);
		ActionBean actionBean = ActionUtil.getAction(path);
		log.info("actionBean为：" + actionBean);
		if (actionBean == null) {
			log.info("根据请求地址拿不到action");
			return;
		}
		String key = actionBean.getActionClass().substring(15, 16).toLowerCase()
				+ actionBean.getActionClass().substring(16);
		log.debug("key为：" + key);
		Object object = BeanContext.getBeans(key);
		Class<? extends Object> objectClass = object.getClass();
		try {
			Method method = objectClass.getDeclaredMethod(actionBean.getActionMethod(), HttpServletRequest.class,
					HttpServletResponse.class);
			String result = (String) method.invoke(object, request, response);
			Map<String, ResultBean> resultMap = actionBean.getResultMap();
			if (resultMap == null || resultMap.get(result) == null) {
				return;
			}
			ResultBean resultBean = resultMap.get(result);
			if ("forward".equals(resultBean.getResultType())) {
				request.getRequestDispatcher(resultBean.getPagePath()).forward(request, response);
			} else if ("redirect".equals(resultBean.getResultType())) {
				response.sendRedirect(resultBean.getPagePath());
			} else {
				log.debug("没有匹配的跳转方式" + resultBean);
			}
		} catch (NoSuchMethodException | SecurityException e) {
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
