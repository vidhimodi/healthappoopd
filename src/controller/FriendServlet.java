package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Member;

/**
 * Servlet implementation class FriendServlet
 */
@WebServlet("/FriendServlet")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HealthController hc  = (HealthController) session.getAttribute("hc");
		Member cur = (Member) session.getAttribute("user");
		String confusername = request.getParameter("confusername");
		String action = request.getParameter("action");
		switch(action){
		case "withdraw":
			hc.withdrawRequest(confusername, cur.getUsername());
			hc.updatefriend();
			hc.update(cur);
			session.setAttribute("user", cur);
			response.sendRedirect("welcome.jsp?show=showsentreq&success=withdraw");
			break;
		case "accept":
			hc.acceptRequest(confusername,cur.getUsername());
			hc.updatefriend();
			hc.update(cur);
			session.setAttribute("user", cur);
			response.sendRedirect("welcome.jsp?show=showpendreq&success=accept");
			break;
		case "reject":
			hc.rejectedRequest(confusername,cur.getUsername());
			hc.updatefriend();
			hc.update(cur);
			session.setAttribute("user", cur);
			response.sendRedirect("welcome.jsp?show=showpendreq&success=reject");
			break;
		case "unfriend":
			hc.unfriend(confusername, cur.getUsername());
			hc.updatefriend();
			hc.update(cur);
			session.setAttribute("user", cur);
			response.sendRedirect("welcome.jsp?show=friendlist&success=unfriend");
			break;
		case "viewprofile":
			response.sendRedirect("welcome.jsp?show=view&user="+confusername);
			break;
		case "viewhealth":
			response.sendRedirect("welcome.jsp?show=viewhealth&user="+confusername);
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
