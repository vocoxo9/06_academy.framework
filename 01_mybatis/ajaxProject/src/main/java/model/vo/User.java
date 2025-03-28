package model.vo;

public class User {
	private int userNo;
	private String userName;
	private String userId;
	private String address;
	
	public User() {
		super();
	}

	public User(int userNo, String userName, String userId, String address) {
		super();
		this.userNo = userNo;
		this.userName = userName;
		this.userId = userId;
		this.address = address;
	}

	public int getUserNo() {
		return userNo;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserId() {
		return userId;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userName=" + userName + ", userId=" + userId + ", address=" + address
				+ "]";
	}
	
	
}
