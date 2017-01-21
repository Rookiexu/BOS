package cn.rookiex.bos.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import cn.rookiex.bos.domain.User;

public class BOSLoginInterceptor extends MethodFilterInterceptor{

	//À¹½Ø·½·¨
	protected String doIntercept(ActionInvocation invocation) throws Exception {

		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if(user == null) 
			return "login";
		return invocation.invoke();
	}

}
