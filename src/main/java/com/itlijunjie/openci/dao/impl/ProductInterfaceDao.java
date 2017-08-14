package com.itlijunjie.openci.dao.impl;

import com.itlijunjie.openci.dao.IProductInterfaceDao;
import com.itlijunjie.openci.vo.ProductInterface;
import org.springframework.stereotype.Repository;

@Repository("productInterfaceDao")
public class ProductInterfaceDao extends PageBaseDAO implements IProductInterfaceDao {
    @Override
    public ProductInterface loadProductInterface(String productName,
                                                 String parameter) {
        ProductInterface p = (ProductInterface) this.getSession().createQuery("from ProductInterface where productname = ? and ifparameter = ?").setParameter(0, productName).setParameter(1, parameter).uniqueResult();
        return p;
    }
}