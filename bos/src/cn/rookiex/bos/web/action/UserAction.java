package cn.rookiex.bos.web.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import cn.rookiex.bos.domain.User;
import cn.rookiex.bos.service.IUserService;
import cn.rookiex.bos.utils.MD5Utils;
import cn.rookiex.bos.web.action.base.BaseAction;

@Controller   //struts配置中默认名字为简单类名，首字母小写，如果要自定义。@Controller("abc")
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	
	@Resource
	private IUserService userService;
	
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public String login() {		

		String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		
		//-------------chichu yongdeshi key zhaochuyunaying
		
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(key)){
			
			User user = userService.login(model);
			if(user != null) {
				//登陆成功，将user放入session，跳转到主页
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "home";
			} else {
				//登录失败，跳转回登录页面
				this.addActionError("账号或密码错误");//此处信息应该用国际化
				return "login";
			}
		} else {
			//验证信息出错
			this.addActionError("验证码错误");
			return "login";
		}
	}
	
	public String logout(){
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
	public String editPassword() throws IOException {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String password = model.getPassword();//新密码
		password = MD5Utils.md5(password);
		String flag = "1";
		try{
			userService.editPassword(password,user.getId());
		}catch (Exception e) {
			//修改密码失败
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;	
	}
}
