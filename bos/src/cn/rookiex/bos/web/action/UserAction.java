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

@Controller   //struts������Ĭ������Ϊ������������ĸСд�����Ҫ�Զ��塣@Controller("abc")
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
				//��½�ɹ�����user����session����ת����ҳ
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "home";
			} else {
				//��¼ʧ�ܣ���ת�ص�¼ҳ��
				this.addActionError("�˺Ż��������");//�˴���ϢӦ���ù��ʻ�
				return "login";
			}
		} else {
			//��֤��Ϣ����
			this.addActionError("��֤�����");
			return "login";
		}
	}
	
	public String logout(){
		//����session
		ServletActionContext.getRequest().getSession().invalidate();
		return "login";
	}
	
	public String editPassword() throws IOException {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String password = model.getPassword();//������
		password = MD5Utils.md5(password);
		String flag = "1";
		try{
			userService.editPassword(password,user.getId());
		}catch (Exception e) {
			//�޸�����ʧ��
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		return NONE;	
	}
}
