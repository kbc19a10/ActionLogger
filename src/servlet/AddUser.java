package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ErrorViewData;
import model.InputCheckException;
import model.User;
import model.ValidationKey;

//static import
import static model.InputChecker.checkLongInput;
import static model.InputChecker.checkPhoneNumber;
import static model.InputChecker.checkMailAddress;

//ユーザー追加機能
//GETでアクセスされた場合　登録フォームを表示
//POSTでアクセスされた場合　登録フォームから送られたデータを処理
//登録フォームから送られたデータは、DB保存候補としてsession変数に保存
@WebServlet("/adduser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddUser() {
		super();
	}

	// doGet、登録フォームを表示
	// throws(複数のメソッドを使用した処理を書く際、例外処理を各メソッドの中ではなく呼び出し元でまとめて行いたい場合)
	// req = request
	// HttpServletRequest、インスタンスから、リクエストの属性や中身を取得できる
	// HttpServletRespose、インスタンスに、レスポンスを書き込む
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 正当なフォームから送られたデータであることを確認するためのキーの生成
		ValidationKey validationKey = new ValidationKey();
		try {
			// 乱数を生成
			Random random = new Random();
			// valueOfは、int型に限らず様々なデータ型をString型に変換する際に利用
			String randomStr = String.valueOf(random.nextLong());
			// MessageDigestのインスタンスを生成
			// MD5、任意の長さの原文をもとに128ビットの値を生成するハッシュ関数の一つ
			MessageDigest validation = MessageDigest.getInstance("MD5");
			validation.reset();
			// 引数の文字列をutg8にエンコード（データをほかの形式に変換）する
			validation.update(randomStr.getBytes("utf8"));
			// ハッシュ化
			String vkey = String.format("%032x", new BigInteger(1, validation.digest()));
			validationKey.setValue(vkey);
			// 例外処理
		} catch (NoSuchAlgorithmException e) {
			// スタックトレースを出力
			// 例外発生時は、例外が発生したメソッドと例外が発生するまでに経ってきたメソッドが分かる
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// スタックトレースを出力
			// 例外発生時は、例外が発生したメソッドと例外が発生するまでに経ってきたメソッドが分かる
			e.printStackTrace();
		}

		// フォーム確認キーをセッションスコープに設定
		HttpSession session = req.getSession();
		session.setAttribute("validationKey", validationKey);

		// RequestDispatcherは、画面の遷移先を定義するオブジェクト
		// addUserForm.jspへ
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addUserForm.jsp");
		dispatcher.forward(req, resp);
	}

	// doPost、登録フォームから送られたデータを処理
	// HttpServletRequest、インスタンスから、リクエストの属性や中身を取得できる
	// HttpServletRespose、インスタンスに、レスポンスを書き込む
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		// フォームから送られた確認キーが保存したものと一致するか確認
		ValidationKey validationKey = (ValidationKey) session.getAttribute("validationKey");
		if (!req.getParameter("vKey").equals(validationKey.getValue())) {
			// 一致しなかったので、セッションスコープに保存したキーを破棄し、エラーページに
			session.removeAttribute("validationKey");
			// 表示データを用意する
			ErrorViewData errorData = new ErrorViewData("問題が発生しました。", "トップに戻る", "/ActionLogger/");
			req.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
			dispatcher.forward(req, resp);
			return;
		}

		User user = new User();
		try {
			// getParameter（クライアント側のformから送られてきたデータを取得し、値はすべてStringとなるメソッド）
			// UserDAOのデータベースから取り出し、AddUser.javaで受け取り、addUserConfirm.jspのformで表示
			// ユーザID
			user.setUserId(checkLongInput(req.getParameter("userid")));
			// 名前
			user.setName(checkLongInput(req.getParameter("name")));
			// 住所
			user.setAddress(checkLongInput(req.getParameter("address")));
			// 電話
			user.setTel(checkPhoneNumber(req.getParameter("tel")));
			// メールアドレス
			user.setEmail(checkMailAddress(req.getParameter("email")));

			// パスワードのハッシュ化
			String rawPassword = req.getParameter("password");
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(rawPassword.getBytes("utf8"));
			String passwordHash = String.format("%064x", new BigInteger(1, digest.digest()));

			// ハッシュ値をオブジェクトに設定
			user.setPwdHash(passwordHash);

			// userオブジェクトをセッションスコープに一旦保存（DBに入れるのはConfirmの後）
			session.setAttribute("userToAdd", user);

			// 確認画面にリダイレクト
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addUserConfirm.jsp");
			dispatcher.forward(req, resp);

			// 例外処理
		} catch (NoSuchAlgorithmException e) {
			// スタックトレースを出力
			// 例外発生時は、例外が発生したメソッドと例外が発生するまでに経ってきたメソッドが分かる
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// スタックトレースを出力
			// 例外発生時は、例外が発生したメソッドと例外が発生するまでに経ってきたメソッドが分かる
			e.printStackTrace();
		} catch (InputCheckException e1) {
			// 表示データを用意する
			// 入力画面に戻る
			ErrorViewData errorData = new ErrorViewData("フォームに入力された内容に問題がありました。", "入力画面に戻る", "/ActionLogger/adduser");
			req.setAttribute("errorData", errorData);
			// エラー表示にフォワード
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/errorView.jsp");
			dispatcher.forward(req, resp);
		}
	}

}
