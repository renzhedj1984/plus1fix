package com.plus1fix.manage.models;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * FixCase Model
 * @author peter-zhang
 *
 */
@Table("plus_shop")
public class PlusOrder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2457154609375368155L;

	@Column
    @Id
    @Comment("id")
    @ColDefine(type = ColType.INT, width = 32)
    private long id;

    @Column
    @Comment("create_time")
    @ColDefine(type = ColType.INT,width=20)
    private long create_time = System.currentTimeMillis();
    
    @Column
    @Comment("status_flag:{1:inuse,-1:forbid,-2:del}")
    @ColDefine(type = ColType.INT,width=1)
    private int status_flag = 1;
}
