package cn.tedu.db.sys.pojo;

import java.util.Date;

/**
 * 菜单数据表对应的实体类
 */
public class SysMenuDO extends BaseDO {
	
	private Integer id;
	private String name; // 资源名称
	private String url; // 资源URL
	private Integer type; // 类型     1：菜单   2：按钮
	private Integer sort; // 排序
	private String note; // 备注
	private Integer parentId; // 父菜单ID，一级菜单为0
	private String permission; //授权(如：user:create)
	private String parentName;// 父菜单名称
	
	public SysMenuDO() {
		super();
	}

	public SysMenuDO(Date createdTime, Date modifiedTime, String createdUser, String modifiedUser, Integer id,
			String name, String url, Integer type, Integer sort, String note, Integer parentId, String permission,
			String parentName) {
		super(createdTime, modifiedTime, createdUser, modifiedUser);
		this.id = id;
		this.name = name;
		this.url = url;
		this.type = type;
		this.sort = sort;
		this.note = note;
		this.parentId = parentId;
		this.permission = permission;
		this.parentName = parentName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ((parentName == null) ? 0 : parentName.hashCode());
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysMenuDO other = (SysMenuDO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (parentName == null) {
			if (other.parentName != null)
				return false;
		} else if (!parentName.equals(other.parentName))
			return false;
		if (permission == null) {
			if (other.permission != null)
				return false;
		} else if (!permission.equals(other.permission))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SysMenuDO [id=" + id + ", name=" + name + ", url=" + url + ", type=" + type + ", sort=" + sort
				+ ", note=" + note + ", parentId=" + parentId + ", permission=" + permission + ", parentName="
				+ parentName + "]";
	}
	
}
