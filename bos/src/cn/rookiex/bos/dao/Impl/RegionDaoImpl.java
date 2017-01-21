package cn.rookiex.bos.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.rookiex.bos.dao.IRegionDao;
import cn.rookiex.bos.dao.base.impl.BaseDaoImpl;
import cn.rookiex.bos.domain.Region;

@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao{

	public List<Region> findByQ(String q) {
		String hql = "FROM Region WHERE province LIKE ? OR city LIKE ? OR district LIKE ?";
		return this.getHibernateTemplate().find(hql, "%"+q+"%","%"+q+"%","%"+q+"%");
	}

}
