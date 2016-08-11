package modelwork;

import java.io.Serializable;

/**
 * The persistent class for the users database table.
 *
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long userId;
	// bi-directional many-to-one association to UserType
	private Long userTypeId;
	private String userLogin;
	private String userPass;

	public User() {
	}

	public User(Long userId, Long userTypeId, String userLogin, String userPass) {
		this.userId = userId;
		this.userTypeId = userTypeId;
		this.userLogin = userLogin;
		this.userPass = userPass;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserLogin() {
		return this.userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public Long getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	@Override
	public String toString() {
		return "\nUser [userId=" + userId + ", userType=" + userTypeId
				+ ", userLogin=" + userLogin + ", userPass=" + userPass + "]";
	}

}