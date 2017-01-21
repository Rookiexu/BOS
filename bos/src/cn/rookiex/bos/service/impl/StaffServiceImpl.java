package cn.rookiex.bos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.rookiex.bos.dao.IStaffDao;
import cn.rookiex.bos.domain.Staff;
import cn.rookiex.bos.service.IStaffService;
import cn.rookiex.bos.utils.PageBean;

@Service
@Transactional
public class StaffServiceImpl implements IStaffService {

	@Autowired 
	private IStaffDao staffDao;
	
	public void save(Staff staff){
		staffDao.save(staff);
		//应该用try catch，如果没有返回异常就是正常的
	}

	
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}


	public void deleteBatch(String ids) {
		
		String[] staffIds = ids.split(",");
		for (String id:staffIds) {
			staffDao.executeUpdate("staff.delete", id);
		}
	}


	public void update(Staff model) {
		
		staffDao.update(model);
	}


	public Staff findById(String id) {
		Staff staff = staffDao.findById(id);
		return staff;
	}

}
