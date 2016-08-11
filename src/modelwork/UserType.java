package modelwork;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the user_types database table.
 *
 */
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long uTypeId;

	private int uTypeCode;

	private String uTypeName;

	// bi-directional many-to-one association to User
	private List<User> users;

	public UserType() {
	}

	public UserType(Long uTypeId, int uTypeCode, String uTypeName) {
		this.uTypeId = uTypeId;
		this.uTypeCode = uTypeCode;
		this.uTypeName = uTypeName;
	}

	public Long getUTypeId() {
		return this.uTypeId;
	}

	public void setUTypeId(Long uTypeId) {
		this.uTypeId = uTypeId;
	}

	public int getUTypeCode() {
		return this.uTypeCode;
	}

	public void setUTypeCode(int uTypeCode) {
		this.uTypeCode = uTypeCode;
	}

	public String getUTypeName() {
		return this.uTypeName;
	}

	public void setUTypeName(String uTypeName) {
		this.uTypeName = uTypeName;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setUserTypeId(this.getUTypeId());

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setUserTypeId(null);

		return user;
	}

	@Override
	public String toString() {
		return "\nUserType [uTypeId=" + uTypeId + ", uTypeCode=" + uTypeCode
				+ ", uTypeName=" + uTypeName + "]";
	}

}