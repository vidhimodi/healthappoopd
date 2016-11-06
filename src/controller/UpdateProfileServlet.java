package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
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
		HttpSession session =request.getSession();
		HealthController hc = new HealthController();
		Member cur = (Member)session.getAttribute("user");
		cur.setAboutme(request.getParameter("aboutme"));
		cur.setFirstname(request.getParameter("firstname"));
		cur.setGoverningdistrict(request.getParameter("gg"));
		cur.setMajormunicipality(request.getParameter("mm"));
		cur.setLastname(request.getParameter("lastname"));
		cur.setPassword(request.getParameter("password"), cur.getPassword());
		cur.setStreetname(request.getParameter("streetname") );
		cur.setStreetnumber(request.getParameter("streetnumber"));
		cur.setPostalarea(request.getParameter("postal"));
		int utype=cur.getUsertype();
		String url_com= request.getParameter("photourl");
		String qual_com=null;
		String qual_sep[];
		String url[]=url_com.split(" ");
		cur.setUrl(url);
		String num;
		ArrayList<String> qualification;
		int status=1;
		if(utype<4){
			System.out.println("in ifelse before update"+cur.getFirstname());
			hc.update(cur);
			session.setAttribute("user", cur);
			//hc.register(new User(username, password, primary_email, secondary_email, firstname, lastname, aboutme, url, status, streetname, streetnumber, majormunicipality, governingdistrict, postalarea));
			response.sendRedirect("welcome.jsp?show=update&success=updated");
		}else if(utype==4){//admin
			((Admin)cur).setEm_num(request.getParameter("phone"));
			hc.update(cur);
			session.setAttribute("user", cur);
			//hc.register(new Admin(username, password, primary_email, secondary_email, utype+2, firstname, lastname, aboutme, url, status, streetname, streetnumber, majormunicipality, governingdistrict, postalarea, num));
			response.sendRedirect("welcome.jsp?show=update&success=updated");
		}else if(utype==5){// mod
			((Moderator)cur).setEm_number(request.getParameter("phone"));
			qual_com = request.getParameter("qualification");
			qual_sep = qual_com.split(" ");
			qualification =new ArrayList<String>(Arrays.asList(qual_sep));
			((Moderator)cur).setQualification(qualification);
			hc.update(cur);
			session.setAttribute("user", cur);
			//hc.register(new Moderator(username, password, primary_email, secondary_email, utype+2, firstname, lastname, aboutme, url, status, streetname, streetnumber, majormunicipality, governingdistrict, postalarea, num, qualification));
			response.sendRedirect("welcome.jsp?show=update&success=updated");
		}else{
			response.sendRedirect("welcome.jsp?show=update&success=nutype");
		}
	}
}
