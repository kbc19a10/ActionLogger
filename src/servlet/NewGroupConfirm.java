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

import dao.ActionDAO;
import dao.GroupDAO;
import model.Action;
import model.ErrorViewData;
import model.NewGroup;
import model.InputCheckException;
import model.User;
import model.ValidationKey;

@WebServlet("/NewGroupConfirm")
public class NewGroupConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewGroupConfirm() {
		super();
	}
	
	// doPost、登録フォームから送られたデータを処理
	// HttpServletRequest、インスタンスから、リクエストの属性や中身を取得できる
	// HttpServletRespose、インスタンスに、レスポンスを書き込む
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		// statusがconfirmedの場合
		// 本来は正当な登録確認であることをチェックするべきであるが、とりあえず省略
		if (req.getParameter("status").equals("confirmed")) {
			// セッションスコープに保存していた、DB登録前のユーザー情報を取得
			NewGroup newgroup = (NewGroup) session.getAttribute("groupToAdd");
			// セッションからユーザID取得
			String userid = (String) session.getAttribute("userid");
			// DB上のactionテーブルに対応するDAO(検索、追加、更新、削除を担当するクラス)
			GroupDAO groupDAO = new GroupDAO();
			// DBに保存
			groupDAO.save(newgroup, userid);
			// 主キーの重複で保存できなかった場合の処理を追加
		}
		// DBへの保存が成功したものとして、ログインページに遷移
		resp.sendRedirect("/ActionLogger/dashborard");
	}
}
