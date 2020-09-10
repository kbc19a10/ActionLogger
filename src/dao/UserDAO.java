package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

//DB上のuserテーブルに対応するDAO(検索、追加、更新、削除を担当するクラス)
//userの検索、追加、更新、削除を担当
public class UserDAO {
	// データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/h2db/actionlogger";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	// 新規登録
	// ユーザーIDを指定して、ユーザー情報を取得
	// ユーザーIDが存在しない場合はnullを返す
	public User get(String userId) {
		User user = null;
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// SELECT文の準備
			String sql = "SELECT * FROM user WHERE userid = ?";
			// SQLをDBに届けるPreparedStatementインスタンスを取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, userId);
			// SELECTを実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();
			// SELECT文の結果をuserに格納
			// UserDAOのデータベースから取り出し、AssUser.javaで受け取り、addUserConfirm.jspのformで表示
			while (rs.next()) {
				user = new User();
				// ユーザID
				user.setUserId(rs.getString("userid"));
				// パスワード
				user.setPwdHash(rs.getString("pwdhash"));
				// 名前
				user.setName(rs.getString("name"));
				// 住所
				user.setAddress(rs.getString("address"));
				// 電話番号
				user.setTel(rs.getString("tel"));
				// メールアドレス
				user.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}

	// 活動記録登録
	// ユーザーを指定して、ユーザー情報を保存
	// 戻り値:true 成功 , false 失敗
	public boolean save(User user) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			// 引数文[？]追加、左からuserid, pwdhash, name, address, tel, emailとなっている
			String sql = "INSERT INTO user " + "( userid, pwdhash, name, address, tel, email ) "
					+ "VALUES ( ?, ?, ?, ?, ?, ? )";
			// SQLをDBに届けるPrepareStatementインスタンスを取得
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

	// パスワード変更
	public boolean chengePassword(User user) {
		// データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			// INSERT文の準備(idは自動連番なので指定しなくてよい）
			// 引数文[？]追加、userid,pwdHashの追加
			String sql = "update user set password = ?" + "where userid = ?";
			// SQLをDBに届けるPrepareStatementインスタンスを取得
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// INSERT文中の「?」に使用する値を設定しSQLを完成
			pStmt.setString(1, user.getUserId());
			pStmt.setString(2, user.getPwdHash());
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
