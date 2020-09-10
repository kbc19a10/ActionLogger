package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/logincheck")
public class LoginCheck extends HttpServlet {

	// Getメソッドでこのページが呼ばれることはない。不正処理の疑いもあるが、とりあえずログインフォームにリダイレクト
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// LoginView.jspへ
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/LoginView.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	// HttpServletRequest、インスタンスから、リクエストの属性や中身を取得できる
	// HttpServletRespose、インスタンスに、レスポンスを書き込む
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 自動生成されたメソッド・スタブ
		// 日本語が含まれるパラメータを処理
		req.setCharacterEncoding("UTF-8");
		String passwordHash = "";
		try {
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

		// DBからユーザーＩＤを取得
		UserDAO userDAO = new UserDAO();
		User user = userDAO.get(req.getParameter("userid"));

		// DB(userID)からの取得が成功 AND パスワードハッシュが合致
		if (user != null && user.getPwdHash().equals(passwordHash)) {
			HttpSession session = req.getSession();
			//getSessionから取り出す
			//ID
			session.setAttribute("userid", user.getUserId());
			//名前
			session.setAttribute("name", user.getName());
			//住所
			session.setAttribute("address",user.getAddress());
			//電話番号
			session.setAttribute("tel", user.getTel());
			//メールアドレス
			session.setAttribute("email",user.getEmail());
			// setAttributeの説明
			// リクエストに新しい属性名と属性値をセットすることが出来る
			// session.setAttribute(“属性名(String型)”, “属性値（Object型）”);
			session.setAttribute("userid", user.getUserId());
			// sendRedirectの説明
			// 何かの処理をしてエラーだった場合にはエラーページへ飛ばす、
			// データベースの処理だけをするサーブレットを呼び出した後に処理が終わったらサーブレットでは何も出力を行わずに特定のページへ飛ばす
			// 別のサーバにあるURLへ飛ばす（ActionLoggerへ）
			resp.sendRedirect("/ActionLogger/");

		} else {
			// TODO ログインエラーにリダイレクト
			// エラー画面がまだないのでログイン画面に戻す
			resp.sendRedirect("/ActionLogger/login");
		}
	}
}
