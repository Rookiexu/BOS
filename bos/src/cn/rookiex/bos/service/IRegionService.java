package cn.rookiex.bos.service;

import java.util.List;
import cn.rookiex.bos.domain.Region;
import cn.rookiex.bos.utils.PageBean;

public interface IRegionService {
	public void saveBatch(List<Region> list);
	
	public void pageQuery(PageBean pageBean);

	public List<Region> findAll();

	public List<Region> findByQ(String q);
}
