package cn.rookiex.bos.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.rookiex.bos.dao.IUserDao;
import cn.rookiex.bos.dao.base.impl.BaseDaoImpl;
import cn.rookiex.bos.domain.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {


	public User findByUsernameAndPassword(String username, String password) {
		String hql = "FROM User u WHERE u.username = ? AND u.password = ?";
		List<User> list = this.getHibernateTemplate().find(hql,username,password);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
