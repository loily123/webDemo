package filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class CharacterEncodingFilter implements Filter {
	private String encoding;
	private Logger log = Logger.getLogger(CharacterEncodingFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		encoding = filterConfig.getServletContext().getInitParameter("encoding");
		log.info("字符集为" + encoding);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		log.info("开始设置字符集");
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpServletResponse response2 = (HttpServletResponse) response;
		String method = request2.getMethod();
		log.info("提交方式" + method);
		if ("post".equalsIgnoreCase(method)) {
			request2.setCharacterEncoding(encoding);
		} else if ("get".equalsIgnoreCase(method)) {
			request2 = new ResetRequest(request2);
		}
		chain.doFilter(request2, response2);
		log.info("结束设置字符集");
	}

	private class ResetRequest extends HttpServletRequestWrapper {
		HttpServletRequest request;

		public ResetRequest(HttpServletRequest request) {
			super(request);
			// TODO Auto-generated constructor stub
			this.request = request;
		}

		public String getParameter(String name) {
			String value = request.getParameter(name);
			if (value == null) {
				value = "";
			} else {
				try {
					value = new String(value.getBytes("iso-8859-1"), encoding);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					value = "";
					e.printStackTrace();
				}
			}
			return value;
		}

		public String[] getParameterValues(String name) {
			String[] values = request.getParameterValues(name);
			if (values == null || values.length == 0) {
				return values;
			}
			for (int i = 0; i < values.length; i++) {
				String value;
				try {
					value = new String(values[i].getBytes("iso-8859-1"), encoding);
					values[i] = value;
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					values[i] = "";
					e.printStackTrace();
				}
			}
			return values;
		}
	}
}