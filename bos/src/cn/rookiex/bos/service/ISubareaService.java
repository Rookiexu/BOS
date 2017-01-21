package cn.rookiex.bos.service;

import java.util.List;

import cn.rookiex.bos.domain.Subarea;
import cn.rookiex.bos.utils.PageBean;

public interface ISubareaService {

	public void save(Subarea model);

	public void pageQuery(PageBean pageBean);

	public List<Subarea> findAll();

}
