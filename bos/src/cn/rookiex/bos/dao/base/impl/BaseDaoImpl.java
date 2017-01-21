package cn.rookiex.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import cn.rookiex.bos.dao.base.IBaseDao;
import cn.rookiex.bos.utils.PageBean;


public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>{

	// ʵ������
	private Class<T> entityClass;
	
	// ʹ��ע�ⷽʽ��������ע��

	
	// @Qualifier(value="abc")
	//@Autowired
	@Resource
	public void setMySessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 *   �ڹ��췽���ж�̬��ò�����ʵ������
	 */
	public BaseDaoImpl() {
		// ��ø��ࣨBaseDaoImpl<T>������
		ParameterizedType genericSuperclass = (ParameterizedType) this
				.getClass().getGenericSuperclass();
		// ��ø����ϵķ�������
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		entityClass = (Class<T>) actualTypeArguments[0];
	}
	
	
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);	
		//û�з���ֵ�����û���쳣���Ǳ���ɹ�
	}
	
	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);			
	}

	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	public void update(T entity) {
		this.getHibernateTemplate().update(entity);		
	}

	public T findById(Serializable id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	public List<T> findAll() {
		String hql = "FROM  " + entityClass.getSimpleName();
		return this.getHibernateTemplate().find(hql);
	}

	public void executeUpdate(String queryName, Object... objects) {
		Session session = this.getSession();

		Query query = session.getNamedQuery(queryName);

		int i = 0;
		for (Object arg : objects) {
			query.setParameter(i++, arg);
		}
		query.executeUpdate();
	}

	
	public void pageQuery(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();
		int pageSize = pageBean.getPageSize();
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		//��������----select count(*) from bc_staff
		//�ı�Hibernate��ܷ�����sql��ʽ
		detachedCriteria.setProjection(Projections.rowCount());//select count(*) from bc_staff
		List<Long> list = this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long total = list.get(0);
		pageBean.setTotal(total.intValue());//������������
		detachedCriteria.setProjection(null);//�޸�sql����ʽΪselect * from ....
		//���ñ�����ӳ���ϵ
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		//��ǰҳչʾ�����ݼ���
		int firstResult = (currentPage - 1) * pageSize;
		int maxResults = pageSize;
		List rows = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
		pageBean.setRows(rows);
		
	}

}
