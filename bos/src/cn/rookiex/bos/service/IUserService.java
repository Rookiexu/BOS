package cn.rookiex.bos.service;

import cn.rookiex.bos.domain.User;

public interface IUserService {
	public User login(User model);

	public void editPassword(String password, String id);
}
