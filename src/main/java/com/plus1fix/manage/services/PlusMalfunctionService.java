package com.plus1fix.manage.services;

import org.nutz.aop.interceptor.ioc.TransAop;
import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.Sqls;
import org.nutz.ioc.aop.Aop;
import org.nutz.ioc.loader.annotation.IocBean;

import cn.wizzer.common.base.Service;

import com.plus1fix.manage.models.PlusMalfunction;

/**
 * @author peter-zhang
 */
@IocBean(args = {"refer:dao"})
public class PlusMalfunctionService extends Service<PlusMalfunction> {
    public PlusMalfunctionService(Dao dao) {
        super(dao);
    }

    /**
     * 新增单位
     *
     * @param malfunction
     * @param pid
     */
    @Aop(TransAop.READ_COMMITTED)
    public void save(PlusMalfunction malfunction, long pid) {
        String path = "";
        if (pid!=0) {
        	PlusMalfunction parent = this.fetch(pid);
            path = parent.getPath();
        }
        malfunction.setPath(getSubPath("plus_malfunction", "path", path));
        malfunction.setParentId(pid);
        dao().insert(malfunction);
        if (pid!=0) {
            this.update(Chain.make("hasChildren", true), Cnd.where("id", "=", pid));
        }
    }

    /**
     * 级联删除单位
     *
     * @param malfunction
     */
    @Aop(TransAop.READ_COMMITTED)
    public void deleteAndChild(PlusMalfunction malfunction) {
        dao().execute(Sqls.create("delete from plus_malfunction where path like @path").setParam("path", malfunction.getPath() + "%"));
        dao().execute(Sqls.create("delete from sys_user_unit where unitId=@id or unitId in(SELECT id FROM plus_malfunction WHERE path like @path)").setParam("id", malfunction.getId()).setParam("path", malfunction.getPath() + "%"));
        dao().execute(Sqls.create("delete from sys_role where unitid=@id or unitid in(SELECT id FROM plus_malfunction WHERE path like @path)").setParam("id", malfunction.getId()).setParam("path", malfunction.getPath() + "%"));
        if (malfunction.getParentId()!=0) {
            int count = count(Cnd.where("parentId", "=", malfunction.getParentId()));
            if (count < 1) {
                dao().execute(Sqls.create("update plus_malfunction set hasChildren=0 where id=@pid").setParam("pid", malfunction.getParentId()));
            }
        }
    }
}
