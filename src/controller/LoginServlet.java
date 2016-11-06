package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.Member;
import model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Backend for Login", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	HealthController hc = new HealthController();
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String uemail = null, upass = null;
		if (request.getParameter("login") != null
				|| request.getParameter("reactivate") != null) {
			uemail = (String) request.getParameter("emailid");
			upass = (String) request.getParameter("password");
		} else {
			return;
		}
		
		Member cur;
		if (request.getParameter("login") != null) {
			cur = hc.login(uemail, upass);
			int resultcode = hc.authact(uemail, upass);
			if(resultcode==0){
				//response.sendRedirect("index.jsp?show=login&wrong=deactive_login");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp?show=login&wrong=deactive_login");
				rd.forward(request, response);
			}
			if (resultcode != 0 && cur != null) {
				session.setAttribute("utype", cur.getUsertype());
				session.setAttribute("user", cur);
				session.setAttribute("hc", hc);
				/*if (cur instanceof User) {
					session.setAttribute("datum", hc.getDatumtable());
					session.setAttribute("friend", hc.getFriendtable());
				} else if (cur instanceof Admin) {
					session.setAttribute("datum", null);
					session.setAttribute("friend", null);
				} else {
					session.setAttribute("datum", null);
					session.setAttribute("friend", null);
				}*/
				cur.setStatus(1);
				if (cur instanceof User) {
					((User) cur).changeutype();
				}

				hc.loginattempt(cur.getUsername(), cur.getPassword(), "succes");
				response.sendRedirect("welcome.jsp");
			} else {

				hc.loginattempt(uemail, upass, "fail");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp?show=login&wrong=yes_login");
				rd.forward(request, response);				
			}
		}

		else if (request.getParameter("reactivate") != null) {
			int resultcode = hc.authact(uemail, upass);
			if (resultcode == 0) {
				cur = hc.login(uemail, upass);
				cur.setStatus(1);
				if(cur instanceof User){
					((User) cur).changeutype();
				}

				hc.update(cur);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp?show=login&wrong=deactive_react");
				rd.forward(request, response);	
			} 
			else if (resultcode == 1) {
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp?show=login&wrong=login_react");
				rd.forward(request, response);
				//out.println("<script>alert(\"Already Activated"
				//	+ "You can log in into the System\")</script>");
				/*cur = hc.login(uemail, upass);
				if (cur != null) {
					session.setAttribute("utype", cur.getUsertype());
					session.setAttribute("user", cur);
					session.setAttribute("hc", hc);*/
				/*if (cur instanceof User) {
						session.setAttribute("datum", hc.getDatumtable());
						session.setAttribute("friend", hc.getFriendtable());
					} else if (cur instanceof Admin) {
						session.setAttribute("datum", null);
						session.setAttribute("friend", null);
					} else {
						session.setAttribute("datum", null);
						session.setAttribute("friend", null);
					}*/
				//hc.loginattempt(cur.getUsername(), cur.getPassword(), "succes");
				//cur.setStatus(1);
				//if(cur instanceof User){
				//	((User) cur).changeutype();
				//}
				//response.sendRedirect("welcome.jsp");
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp?show=login&wrong=yes_react");
				rd.forward(request, response);	
				hc.loginattempt(uemail, upass, "fail");
			}
		}
	}
}
