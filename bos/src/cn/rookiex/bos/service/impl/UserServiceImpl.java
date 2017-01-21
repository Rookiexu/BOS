package cn.rookiex.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.rookiex.bos.dao.IUserDao;
import cn.rookiex.bos.domain.User;
import cn.rookiex.bos.service.IUserService;
import cn.rookiex.bos.utils.MD5Utils;

@Service
@Transactional

/*

此处有问题、明天解决

*/
public class UserServiceImpl implements IUserService{

	@Autowired
	private  IUserDao userDao;
	
	public User login(User user) {
		String username = user.getUsername();
		String password = user.getPassword();
		password = MD5Utils.md5(password);
		//username = MD5Utils.md5(username);
		user = userDao.findByUsernameAndPassword(username,password);
		return user;
	}

	public void editPassword(String password, String id) {
		userDao.executeUpdate("editPassword", password,id);
	}

}
