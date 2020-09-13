package cn.tedu.db.sys.pojo;

import java.util.Date;

/**
 * 封装系统角色数据的实体类
 */
public class SysRoleDO extends BaseDO{
	
	private Long id;
	private String name; // 角色名称
	private String note; // 备注
	public SysRoleDO() {
		super();
	}
	public SysRoleDO(Date createdTime, Date modifiedTime, String createdUser, String modifiedUser) {
		super(createdTime, modifiedTime, createdUser, modifiedUser);
	}
	public SysRoleDO(Date createdTime, Date modifiedTime, String createdUser, String modifiedUser, Long id, String name,
			String note) {
		super(createdTime, modifiedTime, createdUser, modifiedUser);
		this.id = id;
		this.name = name;
		this.note = note;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
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
		SysRoleDO other = (SysRoleDO) obj;
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
		return true;
	}
	
	@Override
	public String toString() {
		return "SysRoleDO [id=" + id + ", name=" + name + ", note=" + note + ", toString()=" + super.toString() + "]";
	}
	
}
