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
import model.NewGroup;
import model.User;

//DB上のactionテーブルに対応するDAO(検索、追加、更新、削除を担当するクラス)
//actionの検索、追加、更新、削除を担当
public class GroupDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public GroupDAO() {
		super();
	}

	// ユーザーを指定して、ユーザー情報を保存
	public boolean save(NewGroup newgroup, String userid) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			// 引数文[？]追加、左からgnameとなっている
			String sql = "INSERT INTO newgroup " + "(　gname,userid　)" + "VALUES (　?, ?　)";
			// SQLをDBに届けるPrepareStatementインスタンスを取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			// 名前
			pStmt.setString(1, newgroup.getGname());
			pStmt.setString(2, userid);

			// ユーザID
			// pStmt.setString(2, userid);

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
	public List<NewGroup> findAll(String userid) {
		List<NewGroup> groupList = new ArrayList<>();

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			// 引数文[？]追加、
			String sql = "SELECT * FROM newgroup" + " where userid = ?";
			// SQLをDBに届けるPrepareStatementインスタンスを取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userid);

			// 結果取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				NewGroup group = new NewGroup();
				// パスワード
				group.setGid("gid");
				group.setGname(rs.getString("gname"));
				groupList.add(group);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return groupList;
	}
}
