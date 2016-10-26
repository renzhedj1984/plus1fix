package com.plus1fix.manage.services;

import org.nutz.dao.Dao;
import org.nutz.ioc.loader.annotation.IocBean;

import cn.wizzer.common.base.Service;

import com.plus1fix.manage.models.PlusShop;

/**
 * 
 * @author peter-zhang
 *
 */
@IocBean(args = {"refer:dao"})
public class PlusShopService extends Service<PlusShop> {
    public PlusShopService(Dao dao) {
        super(dao);
    }
}
