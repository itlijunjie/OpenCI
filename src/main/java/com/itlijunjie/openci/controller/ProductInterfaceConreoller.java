package com.itlijunjie.openci.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itlijunjie.openci.services.IProductInterfaceService;
import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.ProductInterface;
import com.itlijunjie.openci.vo.exception.ProductInterfaceException;

@Controller
@RequestMapping("/productif")
public class ProductInterfaceConreoller {
	
	private PageInfo pageInfo = null;
	
	private IProductInterfaceService productInterfaceService;

	public IProductInterfaceService getProductInterfaceService() {
		return productInterfaceService;
	}
	
	@Resource
	public void setProductInterfaceService(
			IProductInterfaceService productInterfaceService) {
		this.productInterfaceService = productInterfaceService;
	}
	
	@RequestMapping(value="/productifs",method=RequestMethod.GET)
	public String list(HttpServletRequest request,Model model) {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		pageInfo = productInterfaceService.pageList(null,pageNo,20);
		model.addAttribute("pis", pageInfo);
		return "productif/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute(new ProductInterface());
		return "productif/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid ProductInterface p,BindingResult result) {
		if (result.hasErrors()) {
			return "productif/add";
		}
		productInterfaceService.add(p);
		return "redirect:/productif/productifs?pageNo="+pageInfo.getCurPage();
	}
	
	@RequestMapping(value="{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable int id){
		productInterfaceService.delete(id);
		return "redirect:/productif/productifs?pageNo="+pageInfo.getCurPage();
	}
	
	@RequestMapping(value="{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		model.addAttribute(productInterfaceService.load(id));
		return "productif/update";
	}
	
	@RequestMapping(value="{id}/update",method=RequestMethod.POST)
	public String update(@PathVariable int id, @Valid ProductInterface productInterface ,BindingResult result) {
		productInterface.setIfid(id);
		productInterfaceService.update(productInterface);
		return "redirect:/productif/productifs?pageNo="+pageInfo.getCurPage();
	}
	
	@RequestMapping(value="{id}")
	public String show(@PathVariable int id , Model model) {
		model.addAttribute(productInterfaceService.load(id));
		return "productif/show";
	}
	
	@ExceptionHandler(ProductInterfaceException.class)
	public String handlerException(Exception ex,HttpServletRequest req) {
		req.setAttribute("ex", ex);
		return "inc/error";
	}
	
}