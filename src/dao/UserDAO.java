package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

//DB上のuserテーブルに対応するDAO(検索、追加、更新、削除を担当するクラス)
public class UserDAO {//userの検索、追加、更新、削除を担当
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	// ユーザーIDを指定して、ユーザー情報を取得
	// ユーザーIDが存在しない場合はnullを返す
	public User get(String userId) {
		User user = null;

		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// SELECT文の準備
			String sql = "SELECT * FROM user WHERE userid = ?";
			//SQLをDBに届けるPreparedStatementインスタンスを取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);

			// SELECTを実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();

			// SELECT文の結果をuserに格納
			while (rs.next()) {
				user = new User();
				user.setUserId(rs.getString("userid"));//ユーザID
				user.setPwdHash(rs.getString("pwdhash"));//パスワード
				user.setName(rs.getString("name"));//名前
				user.setAddress(rs.getString("address"));//住所
				user.setTel(rs.getString("tel"));//電話番号
				user.setEmail(rs.getString("email"));//メールアドレス
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	// ユーザーを指定して、ユーザー情報を保存
	// 戻り値:true 成功 , false 失敗
	public boolean save(User user) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			String sql = "INSERT INTO user " + "( userid, pwdhash, name, address, tel, email ) "
					+ "VALUES ( ?, ?, ?, ?, ?, ? )";
			//SQLをDBに届けるPrepareStatementインスタンスを取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getPwdHash());
			pStmt.setString(3, user.getName());
			pStmt.setString(4, user.getAddress());
			pStmt.setString(5, user.getTel());
			pStmt.setString(6, user.getEmail());

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
