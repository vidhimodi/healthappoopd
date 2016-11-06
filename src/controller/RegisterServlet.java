package controller;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Admin;
import model.Moderator;
import model.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		HealthController hc = new HealthController();
		String username=request.getParameter("uname") ;
		String primary_email=request.getParameter("priemailid") ;
		String secondary_email=request.getParameter("secemailid") ;
		String password=request.getParameter("password") ;
		int utype;
		String ustr=session.getAttribute("temputype").toString();
		
		utype =Integer.parseInt(ustr);
		String firstname=request.getParameter("firstname") ;
		String lastname=request.getParameter("lastname") ;
		String streetname=request.getParameter("streetname") ;
		String streetnumber=request.getParameter("streetnumber") ;
		String majormunicipality=request.getParameter("mm") ;
		String governingdistrict=request.getParameter("gg") ;
		String postalarea=request.getParameter("postal") ;
		String aboutme=request.getParameter("aboutme") ;
		String url_com= request.getParameter("photourl");
		if(hc.exists_uname(username)){
			//RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?show=register&wrong=nuniuname");
			//rd.forward(request, response);
			response.sendRedirect("./index.jsp?show=register&wrong=nuniuname");
			return;
		}
		if(hc.exists_email(primary_email)){
			//RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?show=register&wrong=nuniproemail");
			//rd.forward(request, response);
			response.sendRedirect("./index.jsp?show=register&wrong=nunipriemail");
			return;
		}
		if(hc.exists_uname(secondary_email)){
			//response.sendRedirect("");
			//RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?show=register&wrong=nunisecemail");
			//rd.forward(request, response);
			response.sendRedirect("./index.jsp?show=register&wrong=nunisecemail");
			return;
		}
		String qual_com=null;
		String qual_sep[];
		String url[]=url_com.split(" ");
		String num;
		ArrayList<String> qualification;
		int status=1;
		if(utype==1){
			hc.register(new User(username, password, primary_email, secondary_email, firstname, lastname, aboutme, url, status, streetname, streetnumber, majormunicipality, governingdistrict, postalarea));
			response.sendRedirect("index.jsp?show=register&wrong=registered");
		}else if(utype==2){//admin
			num=request.getParameter("phone");
			hc.register(new Admin(username, password, primary_email, secondary_email, utype+2, firstname, lastname, aboutme, url, status, streetname, streetnumber, majormunicipality, governingdistrict, postalarea, num));
			response.sendRedirect("index.jsp?show=register&wrong=registered");
		}else if(utype==3){// mod
			num=request.getParameter("phone");
			qual_com = request.getParameter("qualification");
			qual_sep = qual_com.split(" ");
			qualification =new ArrayList<String>(Arrays.asList(qual_sep));
			hc.register(new Moderator(username, password, primary_email, secondary_email, utype+2, firstname, lastname, aboutme, url, status, streetname, streetnumber, majormunicipality, governingdistrict, postalarea, num, qualification));
			response.sendRedirect("index.jsp?show=register&wrong=registered");
		}else{
			response.sendRedirect("index.jsp?show=register&wrong=nutype");
//			RequestDispatcher rd = request.getRequestDispatcher("/healthappoopd/index.jsp?show=register&wrong=nutype");
//			rd.forward(request, response);
		}
	}
}
