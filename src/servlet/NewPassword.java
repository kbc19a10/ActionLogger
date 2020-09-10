package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.Action;
import model.User;

//新しいパスワードへ変更
@WebServlet("/NewPassword")
public class NewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewPassword() {
		super();
	}

	// HttpServletRequest、インスタンスから、リクエストの属性や中身を取得できる
	// HttpServletRespose、インスタンスに、レスポンスを書き込む
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	// HttpServletRequest、インスタンスから、リクエストの属性や中身を取得できる
	// HttpServletRespose、インスタンスに、レスポンスを書き込む
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String passwordHash = "";
		try {
			if (req.getParameter("password").equals(req.getParameter("passwordconfirm"))) {
				// パスワードのハッシュ化
				String rawPassword = req.getParameter("password");
				// ハッシュ計算
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				// ハッシュ計算を終了
				digest.reset();
				// 引数の文字列を日本語に
				digest.update(rawPassword.getBytes("utf8"));
				// ハッシュ化した文字列が含まれる
				passwordHash = String.format("%064x", new BigInteger(1, digest.digest()));

				User user = new User();

				user.setPwdHash(passwordHash);
				user.setUserId((String) session.getAttribute("userid"));

				UserDAO userDAO = new UserDAO();
				userDAO.chengePassword(user);

				resp.sendRedirect("/ActionLogger");
			} else {

			}

			// 例外処理
		} catch (NoSuchAlgorithmException e) {
			// printStackTraceは、スタックとレースを出力
			// 例外発生時は、例外が発生したメソッドと例外が発生するまでに経ってきたメソッドが分かる
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// printStackTraceは、スタックとレースを出力
			// 例外発生時は、例外が発生したメソッドと例外が発生するまでに経ってきたメソッドが分かる
			e.printStackTrace();
		}
	}

}
