package com.itlijunjie.openci.services;

import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.ProductInterface;

public interface IProductInterfaceService {

    ProductInterface add(ProductInterface productInterface);

    void update(ProductInterface productInterface);

    void delete(int ifId);

    ProductInterface load(int ifId);

    PageInfo pageList(String hql, int pageNo, int pageCount);

    ProductInterface getProductInterface(String prodactName, String parameter);
}
