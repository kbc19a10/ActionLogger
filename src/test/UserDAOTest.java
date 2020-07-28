package test;

import dao.UserDAO;
import model.User;

public class UserDAOTest {
	public static void saveUser() {
		UserDAO userDAO = new UserDAO();
		User user = new User();
		user.setUserId("test");
		user.setPwdHash("test123");
		user.setName("user01");
		user.setAddress("愛媛県松山市正円寺二丁目1-23");
		user.setTel("080-2313-3456");
		user.setEmail("user01@icloud.com");

		if (userDAO.save(user)) {
			System.out.println("success");
		} else {
			System.out.println("failed");
		}
	}

	public static void getUser() {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.get("yuyu");
	}

	public static void main(String[] args) {
		saveUser();
		getUser();

	}

}
