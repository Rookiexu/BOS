package cn.rookiex.bos.web.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.rookiex.bos.dao.Impl.StaffDaoImpl;
import cn.rookiex.bos.domain.Staff;
import cn.rookiex.bos.service.IStaffService;
import cn.rookiex.bos.utils.PageBean;
import cn.rookiex.bos.web.action.base.BaseAction;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	

	@Autowired
	private IStaffService staffService;
	
	private int page;
	private int rows;
	private String ids;
	
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public String add() {
		staffService.save(model);
		return "list";
	}
	
	public String edit() {
		//显查询数据库中原始数据
		Staff staff = staffService.findById(model.getId());
		
		//再按照页面提交的参数进行覆盖
		staff.setName(model.getName());
		staff.setTelephone(model.getTelephone());
		staff.setStation(model.getStation());
		staff.setHaspda(model.getHaspda());
		staff.setStandard(model.getStandard());
		
		staffService.update(staff);
		return "list";
	}
	public String pageQuery() throws IOException{
		staffService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[]{"currentPage","detachedCriteria","pageSize","subareas"});
		return NONE;
	}
	
	public String deleteBatch() {
		
		staffService.deleteBatch(ids);		
		return "list";
	}
	

}
