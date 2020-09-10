package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginPage extends HttpServlet {

	@Override
	// HttpServletRequest、インスタンスから、リクエストの属性や中身を取得できる
	// HttpServletRespose、インスタンスに、レスポンスを書き込む
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// loginView.jspへ
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/loginView.jsp");
		// "forward"メソッドの引数も、呼び出し元のサーブレットの"doGet"メソッドや"doPost"メソッドが呼び出された時に引数に指定されている値をそのまま渡す
		dispatcher.forward(req, resp);
	}

	@Override
	// HttpServletRequest、インスタンスから、リクエストの属性や中身を取得できる
	// HttpServletRespose、インスタンスに、レスポンスを書き込む
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 日本語が含まれるパラメータを処理
		req.setCharacterEncoding("UTF-8");

	}

}
