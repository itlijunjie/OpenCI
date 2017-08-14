package com.itlijunjie.openci.dao;

import com.itlijunjie.openci.vo.ProductInterface;

public interface IProductInterfaceDao extends IPageBaseDAO {
	ProductInterface loadProductInterface(String productName , String parameter);
}