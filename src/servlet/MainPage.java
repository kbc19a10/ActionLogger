package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ActionDAO;
import dao.BelongsDAO;
import dao.GroupDAO;
import model.Action;
import model.Belongs;
import model.NewGroup;

@WebServlet("/")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainPage() {
	}

	// HttpServletRequest、インスタンスから、リクエストの属性や中身を取得できる
	// HttpServletRespose、インスタンスに、レスポンスを書き込む
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// HttpSessionインタフェースのオブジェクトを取得
		HttpSession session = request.getSession();
		// useridデータをsessionスコープで保存
		String userid = (String) session.getAttribute("userid");
		String groupid = request.getParameter("groupid");
		String view = (String) request.getAttribute("view");
		String search = (String) request.getAttribute("search");

		ActionDAO actionDAO = new ActionDAO();
		List<Action> actionname = new ArrayList<>();
		actionname = actionDAO.findAll(userid);
		session.setAttribute("actionname", actionname);

		GroupDAO groupDAO = new GroupDAO();
		List<NewGroup> gname = new ArrayList<>();
		gname = groupDAO.findAll(userid);
		session.setAttribute("gname", gname);

		BelongsDAO belong = new BelongsDAO();
		List<Action> memberList = new ArrayList<>();
		memberList = actionDAO.memberAll(groupid);
		request.setAttribute("groupid", groupid);
		session.setAttribute("memberList", memberList);
		
		if (view != null && view.equals("groupMember")) {
			List<Action> membersearch = new ArrayList<>();
			if (search != null) {
				String day = (String) request.getParameter("day");
				memberList = ActionDAO.search(day, groupid);
			}
			session.setAttribute("memberList", memberList);
		}

		if (userid == null) {
			// MainViewを表示
			// メインページへ
			response.sendRedirect("/ActionLogger/login");

		} else {
			// MainViewを表示
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mainView.jsp");
			dispatcher.forward(request, response);
		}
	}

	// HttpServletRequest、インスタンスから、リクエストの属性や中身を取得できる
	// HttpServletRespose、インスタンスに、レスポンスを書き込む
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(基本的にサーバーにあるデータベースなどの情報を読むだけで、情報の変更を行わない処理)
		doGet(request, response);
	}

}
