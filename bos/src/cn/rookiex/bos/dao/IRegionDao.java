package cn.rookiex.bos.dao;

import java.util.List;

import cn.rookiex.bos.dao.base.IBaseDao;
import cn.rookiex.bos.domain.Region;

public interface IRegionDao extends IBaseDao<Region>{
	public List<Region> findByQ(String q);

}
