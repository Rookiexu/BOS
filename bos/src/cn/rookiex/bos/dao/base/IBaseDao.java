package cn.rookiex.bos.dao.base;

import java.io.Serializable;
import java.util.List;

import cn.rookiex.bos.domain.Region;
import cn.rookiex.bos.utils.PageBean;
/**
 * @author RookieXu
 * @����		�־ò�ͨ�÷���
 * @���ں�ʱ�� 2017��1��13�� ����7:50:15
 * @�汾
 *
 */
public interface IBaseDao<T> {
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T findById(Serializable id);
	public List<T> findAll();
	public void saveOrUpdate(T region);
	//�ṩͨ���޸ķ���
	public void executeUpdate(String queryName,Object ...objects);
	
	public void pageQuery(PageBean pageBean);
	
}
