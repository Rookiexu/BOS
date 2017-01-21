package cn.rookiex.bos.dao;

import cn.rookiex.bos.dao.base.IBaseDao;
import cn.rookiex.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {

	public User findByUsernameAndPassword(String username, String password);

}
