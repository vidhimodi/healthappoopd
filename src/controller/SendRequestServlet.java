package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Member;

/**
 * Servlet implementation class SendRequestServlet
 */
@WebServlet("/SendRequestServlet")
public class SendRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session =request.getSession();
		boolean resultcode;
		HealthController hc = (HealthController)session.getAttribute("hc");
	    Member cur = (Member)session.getAttribute("user");
	    String username=cur.getUsername();
	    String friend_uname=request.getParameter("friend_uname");
	    if(friend_uname!=null){
	    	resultcode = hc.sentRequest(friend_uname, username);
	    	hc.updatefriend();
	    	if(resultcode){
	    		response.sendRedirect("welcome.jsp?show=sentreq"
	    				+ "&success=true_fsent");
	    		//RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp?show=sentreq&success=true_fsent");
				//rd.forward(request, response);
	    	}else{
	    		response.sendRedirect("welcome.jsp?show=sentreq"
	    				+ "&success=false_fsent");
	    		//RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp?show=sentreq&success=false_fsent");
				//rd.forward(request, response);
	    	}
	    }
	}

}
