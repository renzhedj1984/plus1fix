package com.plus1fix.manage.models;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import cn.wizzer.common.base.Model;

/**
 * News Model
 * 
 * @author peter-zhang
 *
 */
@Table("plus_news")
public class PlusNews extends Model implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7184155878263245001L;

	@Column
	@Id
	@Comment("id")
	@ColDefine(type = ColType.INT, width = 32)
	private long id;

	@Column
	@Comment("big_img_path")
	@ColDefine(type = ColType.VARCHAR, width = 50)
	private String bigImgPath;

	@Column
	@Comment("small_img_path")
	@ColDefine(type = ColType.VARCHAR, width = 50)
	private String smallImgPath;

	@Column
	@Comment("title")
	@ColDefine(type = ColType.VARCHAR, width = 50)
	private String title;

	@Column
	@Comment("short_desc")
	@ColDefine(type = ColType.VARCHAR, width = 200)
	private String shortDesc;

	@Column
	@Comment("content")
	@ColDefine(type = ColType.TEXT)
	private String content;

	@Column
	@Comment("author")
	@ColDefine(type = ColType.VARCHAR, width = 50)
	private String author;

	@Column
	@Comment("is_recommend:{1.recommend,0.unrecommend}")
    @ColDefine(type = ColType.BOOLEAN)
	private boolean isRecommend;

	@Column
	@Comment("is_hot:{1.hot,0.unhot}")
	@ColDefine(type = ColType.BOOLEAN)
	private boolean isHot;
	
	@Column
    @Comment("is_show:{1.show,0.unshow}")
	@ColDefine(type = ColType.BOOLEAN)
	private boolean isShow;

	@Column
	@Comment("show_index")
	@ColDefine(type = ColType.INT)
	private int showIndex;

	@Column
	@Comment("vist")
	@ColDefine(type = ColType.INT)
	private int vist;
	
	@Column
	@Comment("publish_time")
	@ColDefine(customType = "INT(20)")
	private long publishTime = System.currentTimeMillis();

	@Column
	@Comment("create_time")
	@ColDefine(customType = "INT(20)")
	private long createTime = System.currentTimeMillis();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isHot() {
		return isHot;
	}

	public void setHot(boolean isHot) {
		this.isHot = isHot;
	}

	public int getShowIndex() {
		return showIndex;
	}

	public void setShowIndex(int showIndex) {
		this.showIndex = showIndex;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getBigImgPath() {
		return bigImgPath;
	}

	public void setBigImgPath(String bigImgPath) {
		this.bigImgPath = bigImgPath;
	}

	public String getSmallImgPath() {
		return smallImgPath;
	}

	public void setSmallImgPath(String smallImgPath) {
		this.smallImgPath = smallImgPath;
	}

	public int getVist() {
		return vist;
	}

	public void setVist(int vist) {
		this.vist = vist;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public boolean isRecommend() {
		return isRecommend;
	}

	public void setRecommend(boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
}
