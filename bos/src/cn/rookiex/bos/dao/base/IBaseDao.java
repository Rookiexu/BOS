package cn.rookiex.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import cn.rookiex.bos.domain.Region;
import cn.rookiex.bos.utils.PageBean;
/**
 * @author RookieXu
 * @功能		持久层通用方法
 * @日期和时间 2017年1月13日 下午7:50:15
 * @版本
 *
 */
public interface IBaseDao<T> {
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T findById(Serializable id);
	public List<T> findAll();
	public void saveOrUpdate(T region);
	//提供通用修改方法
	public void executeUpdate(String queryName,Object ...objects);
	
	public void pageQuery(PageBean pageBean);
	
}
