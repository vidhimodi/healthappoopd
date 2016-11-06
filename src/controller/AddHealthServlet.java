package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Member;
import model.User;

/**
 * Servlet implementation class AddHealthServlet
 */
@WebServlet("/AddHealthServlet")
public class AddHealthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddHealthServlet() {
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
		int choice=0;
		int data1=0,data2=0,data3=0;
		String d1 = request.getParameter("1");
		String d2 = request.getParameter("2");
		String d3 = request.getParameter("3");
		if (d1!=null&&!d1.equalsIgnoreCase("")&&!d1.equalsIgnoreCase("null")) {
			choice =1;
			data1 = Integer.parseInt(request.getParameter("1"));
			if(valid(data1, choice)){
				hc.addDatum(cur.getUsername(), choice, Integer.toString(data1));
				if(cur instanceof User){
					((User)cur).setKarma(((User)cur).getKarma()+1);
				}
			}
			hc.updateDatum();
			hc.update(cur);
		}
		if (d2!=null&&!d2.equalsIgnoreCase("")&&!d2.equalsIgnoreCase("null")) {
			choice =2;
			data2 = Integer.parseInt(request.getParameter("2"));
			if(valid(data2, choice)){
				hc.addDatum(cur.getUsername(), choice, Integer.toString(data2));
				if(cur instanceof User){
					((User)cur).setKarma(((User)cur).getKarma()+1);
				}
			}
			hc.updateDatum();
			hc.update(cur);
		}
		if (d3!=null&&!d3.equalsIgnoreCase("")&&!d3.equalsIgnoreCase("null")) {
			choice =3;
			data3 = Integer.parseInt(request.getParameter("3"));
			if(valid(data3, choice)){
				hc.addDatum(cur.getUsername(), choice, Integer.toString(data3));
				if(cur instanceof User){
					((User)cur).setKarma(((User)cur).getKarma()+1);
				}
				hc.updateDatum();
				hc.update(cur);
			}
		}
		hc.updateDatum();
		hc.update(cur);
		response.sendRedirect("welcome.jsp?show=addhealth");
		
	}

	public Boolean valid(int d,int ch){
		if(ch==1){
			if(d>0&&d<10){
				return true;
			}else{
				System.out.println("wrong data");
				return false;
			}
		}else if(ch==2){
			if(d>10&&d<2500){
				return true;
			}else{
				System.out.println("wrong data");
				return false;
			}
		}else if(ch==3){
			if(d>30&&d<150){
				return true;
			}else{
				System.out.println("wrong data");
				return false;
			}
		}else{
			System.out.println("wrong choice");
			return false;
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
