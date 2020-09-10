package model;

import java.io.Serializable;

public class User implements Serializable {
	//ユーザーID
	private String userId;
	//パスワードハッシュ
	private String pwdHash;
	//名前
	private String name;
	//住所
	private String address;
	//電話番号
	private String tel;
	//メールアドレス
	private String email;

	public User() {
		super();
	}

	// setter and getter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPwdHash() {
		return pwdHash;
	}

	public void setPwdHash(String pwdHash) {
		this.pwdHash = pwdHash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
