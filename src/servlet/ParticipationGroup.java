package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BelongsDAO;
import model.Belongs;

@WebServlet("/ParticipationGroup")
public class ParticipationGroup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ParticipationGroup() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		try {
			if (req.getParameter("groupid").equals(req.getParameter("groupid"))) {

				Belongs belongs = new Belongs();
				belongs.setGroupid(req.getParameter("groupid"));
				String userid = (String) session.getAttribute("userid");

				BelongsDAO belongsDAO = new BelongsDAO();
				belongsDAO.isgoup(belongs, userid);

				resp.sendRedirect("/ActionLogger");

			} else {
				// idが合わなかったら同じページに戻す
				RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/participetegroup.jsp");
				dispatcher.forward(req, resp);
			}

//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
