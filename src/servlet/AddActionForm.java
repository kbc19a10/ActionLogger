package servlet;

import static model.InputChecker.checkLongInput;
import static model.InputChecker.checkMailAddress;
import static model.InputChecker.checkPhoneNumber;

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

import model.Action;
import model.ErrorViewData;
import model.InputCheckException;
import model.User;
import model.ValidationKey;

@WebServlet("/AddActionForm")
public class AddActionForm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddActionForm() {
		super();
	}

	// doGet、登録フォームを表示
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

		Action action = new Action();
		try {
			// getParameter（クライアント側のformから送られてきたデータを取得し、値はすべてStringとなるメソッド）
			// ActionDAOのデータベースから取り出し、AddActionForm.javaで受け取り、addactionConfirm.jspのformで表示

			// 日付
			action.setDay(req.getParameter("day"));
			// 開始時間
			action.setStarttime(req.getParameter("starttime"));
			// 終了時間
			action.setFinishtime(req.getParameter("finishtime"));
			// 場所
			action.setPlace(req.getParameter("place"));
			// 理由
			action.setReason(req.getParameter("reason"));
			// 備考
			action.setRemark(req.getParameter("remark"));

			// actionオブジェクトをセッションスコープに一旦保存（DBに入れるのはConfirmの後）
			session.setAttribute("actionToAdd", action);

			// 確認画面にリダイレクト(jsp)
			// addActionConfirmへ
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/addActionConfirm.jsp");
			dispatcher.forward(req, resp);

			// 例外処理
		} catch (UnsupportedEncodingException e) {
			// スタックトレースを出力
			// 例外発生時は、例外が発生したメソッドと例外が発生するまでに経ってきたメソッドが分かる
			e.printStackTrace();
		}
	}
}
