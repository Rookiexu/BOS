package cn.rookiex.bos.service;

import cn.rookiex.bos.domain.Staff;
import cn.rookiex.bos.utils.PageBean;

public interface IStaffService {

	public void save(Staff staff);

	public void pageQuery(PageBean pageBean);

	public void deleteBatch(String ids);

	public void update(Staff staff);

	public Staff findById(String id);

}
