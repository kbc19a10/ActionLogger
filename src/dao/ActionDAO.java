package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.User;

//DB上のactionテーブルに対応するDAO(検索、追加、更新、削除を担当するクラス)
//actionの検索、追加、更新、削除を担当
public class ActionDAO extends HttpServlet {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public ActionDAO() {
		super();
	}

	// ユーザーを指定して、ユーザー情報を保存
	public boolean save(Action action, String userid) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			// 引数文[？]追加、左からday, starttime, finishtime, place, reason, remarkとなっている
			String sql = "INSERT INTO action " + "( day, starttime, finishtime, place, reason, remark, userid )"
					+ "VALUES (  ?, ?, ?, ?, ?, ?, ? )";
			// SQLをDBに届けるPrepareStatementインスタンスを取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			// 日付
			pStmt.setString(1, action.getDay());
			// 開始時間
			pStmt.setString(2, action.getStarttime());
			// 終了時間
			pStmt.setString(3, action.getFinishtime());
			// 場所
			pStmt.setString(4, action.getPlace());
			// 理由
			pStmt.setString(5, action.getReason());
			// 備考
			pStmt.setString(6, action.getRemark());
			// ユーザID
			pStmt.setString(7, userid);

			// INSERT文を実行
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// actionのlist
	public List<Action> findAll(String userid) {
		List<Action> actionList = new ArrayList<>();

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			// 引数文[？]追加、
			String sql = "SELECT * FROM ACTION" + " where userid = ?" + "order by day desc";
			// SQLをDBに届けるPrepareStatementインスタンスを取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userid);

			// 結果取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Action action = new Action();
				// ユーザID
				action.setDay(rs.getString("day"));
				// パスワード
				action.setStarttime(rs.getString("starttime"));
				// 名前
				action.setFinishtime(rs.getString("finishtime"));
				// 住所
				action.setPlace(rs.getString("place"));
				// 電話番号
				action.setReason(rs.getString("reason"));
				// メールアドレス
				action.setRemark(rs.getString("remark"));
				actionList.add(action);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return actionList;
	}

}
