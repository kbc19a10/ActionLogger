package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.Belongs;

public class BelongsDAO {

	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	// ユーザーを指定して、ユーザー情報を保存
	public boolean isgoup(Belongs belongs, String userid) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			// 引数文[？]追加、左からday, starttime, finishtime, place, reason, remarkとなっている
			String sql = "INSERT INTO belongs " + "( groupid ,userid )" + "VALUES (  ?, ? )";
			// SQLをDBに届けるPrepareStatementインスタンスを取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成

			// 備考
			pStmt.setString(1, belongs.getGroupid());
			// ユーザID
			pStmt.setString(2, userid);

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

}
