package model;

import java.util.regex.*;

public class InputChecker {
	//throws(複数のメソッドを使用した処理を書く際、例外処理を各メソッドの中ではなく呼び出し元でまとめて行いたい場合)
	public static String checkLongInput(String data) throws InputCheckException {
		// 256文字以下であればOK
		if (data.length() <= 256) {
			return data;
		}
		// ダメなので、例外をスロー
		throw new InputCheckException();
	}
	//throws(複数のメソッドを使用した処理を書く際、例外処理を各メソッドの中ではなく呼び出し元でまとめて行いたい場合)
	public static String checkPhoneNumber(String phone) throws InputCheckException {
		// 電話番号の正規表現(regularExpression)複雑なパターン文字列の値が正しいかどうかや、文字列の置換などによく使われる
		String regularExpression = "^[0-9]{2,4}-[0-9]{2,4}-[0-9]{3,4}$";
		Pattern pattern = Pattern.compile(regularExpression);
		Matcher matcher = pattern.matcher(phone);
		// パターンがマッチしたら引数をそのまま返す。
		if (matcher.find()) {
			return phone;
		}
		// マッチしなかったので、例外をスロー
		throw new InputCheckException();
	}

	public static String checkMailAddress(String address) throws InputCheckException {
		// メールアドレスの正規表現
		//メールを正しく表現するために使用
		String regularExpression = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
		Pattern pattern = Pattern.compile(regularExpression);
		Matcher matcher = pattern.matcher(address);
		// パターンがマッチしたら引数をそのまま返す。
		if (matcher.find()) {
			return address;
		}
		// マッチしなかったので、例外をスロー
		throw new InputCheckException();
	}
}
