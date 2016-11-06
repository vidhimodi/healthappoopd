package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class PostCommentRateServlet
 */
@WebServlet("/PostCommentRateServlet")
public class PostCommentRateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostCommentRateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //comment
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HealthController hc  = (HealthController) session.getAttribute("hc");
		Member cur = (Member) session.getAttribute("user");
		Post p = (Post)session.getAttribute("post");
		int postno = (int) session.getAttribute("postno");
		if(p!=null && postno!=-1){
			String text  =request.getParameter("ctext");
			String photolocation  =request.getParameter("cphoto");
			String linklocation  =request.getParameter("clink");
			String videolocation  =request.getParameter("cvideo");
			String ratestr = request.getParameter("crate");
			if(ratestr!=null&&!ratestr.equalsIgnoreCase("")&&!ratestr.equalsIgnoreCase("null")){
				int star = Integer.parseInt(ratestr);
				if(star>0&&star<6){
					if(hc.addrate(cur,p.getUsername(),p.getTimeCreated(),star)){
						System.out.println("Success");
						if(cur instanceof User){
							((User)cur).setKarma(((User)cur).getKarma()+1);
						}
						hc.update(cur);
					}else{
						System.out.println("u entered wrong input");
					}
				}else{
					System.out.println("u entered wrong input");
				}
			}
			
			if(hc.addcom(cur,p.getUsername(),p.getTimeCreated(),text,photolocation,linklocation,videolocation)){
				System.out.println("Success");
				if(cur instanceof User){
					((User)cur).setKarma(((User)cur).getKarma()+1);
				}
				hc.update(cur);
			}else{
				System.out.println("u entered wrong input");
			}
			response.sendRedirect("welcome.jsp?show=viewforum&forumid="+p.getForumID()+"&postno="+postno);
		}
		else{
			response.sendRedirect("welcome.jsp?show=viewforum&forumid=1"+p.getForumID()+"&postno="+postno);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HealthController hc  = (HealthController) session.getAttribute("hc");
		Member cur = (Member) session.getAttribute("user");
		int forumid = Integer.parseInt((String) session.getAttribute("forumid"));
		int postno = (int) session.getAttribute("postno");
		String text  =request.getParameter("text");
		String photolocation  =request.getParameter("photo");
		String linklocation  =request.getParameter("link");
		String videolocation  =request.getParameter("video");
		if(hc.addpost(cur,forumid,text,photolocation,linklocation,videolocation)){
			if(cur instanceof User){
				((User)cur).setKarma(((User)cur).getKarma()+1);
			}
			hc.update(cur);
			response.sendRedirect("welcome.jsp?show=viewforum&forumid="+forumid+"&postno="+postno);
		}else{
			response.sendRedirect("welcome.jsp?show=viewforum&forumid=1"+forumid+"&postno="+postno);
		}
		
	}

}
