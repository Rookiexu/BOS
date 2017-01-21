package cn.rookiex.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.rookiex.bos.dao.ISubareaDao;
import cn.rookiex.bos.domain.Subarea;
import cn.rookiex.bos.service.ISubareaService;
import cn.rookiex.bos.utils.PageBean;

@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	@Autowired
	private ISubareaDao subareDao;
	
	public void save(Subarea model) {
		subareDao.save(model);
	}

	public void pageQuery(PageBean pageBean) {
		
	}

	public List<Subarea> findAll() {
		
		return null;
	}

}
