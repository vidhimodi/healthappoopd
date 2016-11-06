<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="model.* , java.sql.* , java.util.*"
	import="controller.*"%>
	
<%
	HealthController hc = (HealthController) session.getAttribute("hc");
	Member cur = (Member) session.getAttribute("user");
	String forumstr = request.getParameter("forumid");
	session.setAttribute("forumid", forumstr);
	
	int forumid = 0;
	Forum f = new Forum();
	ArrayList<Post> postarr = new ArrayList<>();
	ArrayList<ArrayList<Comment>> allcom = new ArrayList<ArrayList<Comment>>();
	ArrayList<Double> avgstar = new ArrayList<>();

	ForumController forumcontroller = hc.getForumcontroller();

	if (forumstr != null && !forumstr.equalsIgnoreCase("null")) {

		ArrayList<Comment> comarr;// = new ArrayList<Comment>();
		ArrayList<Rating> ratearr;
		Double avgst = 0.0;
		Double totalstar = 0.0;

		forumid = Integer.parseInt(forumstr);
		f = forumcontroller.getForum(forumid);

		if (f != null) {
			//str.append(f.toString()+"\n");
			postarr = forumcontroller.getpost(f.getForumID());
			avgst = 0.0;
			for (Post p : postarr) {
				//str.append("\n");
				//str.append(p.toString());
				ratearr = forumcontroller.getrate(p.getUsername(),
						p.getTimeCreated());
				for (Rating r : ratearr) {
					totalstar = totalstar + r.getStars();
				}
				if (ratearr.size() > 0) {
					avgst = totalstar / ratearr.size();
				}
				avgstar.add(avgst);
				//str.append("avg star ratin is "+avgstar+"\n");
				avgst = 0.0;
				totalstar = 0.0;
				comarr = forumcontroller.getcomment(p.getUsername(),
						p.getTimeCreated());
				allcom.add(comarr);
			}
		}
	}
%>

<div class="col-xs-12 col-sm-9" data-spy="scroll"
	data-target="#sidebar-nav">
	<div class="row">
		<div class="col-sm-9">
			<div class="panel panel-default">
				<div class=" panel-heading ">
					<%
						out.println("<h4><a href=\"#\" class=\"list-group\" style=\"color:inherit;\">"
								+ "<strong>Post for Forum With Topic: "
								+ f.getTopic()
								+ "</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>"
								+ "Created By: "
								+ f.getCreatedByModerator_Username()
								+ "</strong></a></h4>");
					%>
				</div>
				<div class="panel-body">
					<div class="list-group">
						<%
							Post p = null;
							for (int i = 0; i < postarr.size(); i++) {
								p = postarr.get(i);
								out.println("<a href=\"welcome.jsp?show=viewforum&forumid="+f.getForumID()+"&postno="+i+"\" class=\"list-group-item \">"
										+ "Username: " + p.getUsername() + "&nbsp;&nbsp;"
										+ "Text: " + p.getTextEntry() + "&nbsp;&nbsp;"
										+ "Photo: " + p.getPhotoLocation() + "&nbsp;&nbsp;"
										+ "video: " + p.getVideoLocation() + "&nbsp;&nbsp;"
										+ "Link: " + p.getLinkLocation() + "&nbsp;&nbsp;"
										+ "Avg.Star: "+avgstar.get(i)+"</a>");
							}
						%>
						<form class="form" action="PostCommentRateServlet" method="post">
						<h4>Write a new Post</h4>
							<div class="input-group text-center">
								<input type="text" name="text"
									class="form-control input-lg"
									title="Enter your Post."
									placeholder="Enter your Post">
								<input type="text" name="link"
									class="form-control input-lg"
									title="Enter optional Link."
									placeholder="Enter optional Link"> 
								<input type="text" name="photo"
									class="form-control input-lg"
									title="Enter optional PhotoLink"
									placeholder="Enter optional PhotoLink"> 
								<input type="text" name="video"
									class="form-control input-lg"
									title="Enter optional VideoLink"
									placeholder="Enter optional VideoLink">  
								<input type="submit"
									name="submit" class="btn btn-lg btn-primary" type="button"
									value="Post It In The Forum">
							</div>
						</form>
					</div>
				</div>
				<!--/panel-body-->
			</div>
		</div>
		<%
			String postnostr = request.getParameter("postno"); 
			int post_no = 0;
			String poststr = null;
			ArrayList<Comment> comm =new ArrayList<>();
			if(postnostr!=null&&!postnostr.equalsIgnoreCase("null")){
				if(allcom.size()>post_no){
					post_no = Integer.parseInt(postnostr);
					comm = allcom.get(post_no);
					Post aa = postarr.get(post_no);
					poststr = aa.getTextEntry();
					session.setAttribute("post", aa);
					session.setAttribute("postno", post_no);
				}
				else{
					session.setAttribute("post", null);
					session.setAttribute("postno", 0);
				}
			}
		%>
		<div class="col-sm-9">
			<div class="panel panel-default">
				<div class="panel-heading">
					<a href="#" class="pull-right">View all</a>
					<h4>Comments For post : <%=poststr %></h4>
				</div>
				<div class="panel-body">
					<div class="list-group">
						<%
							//for(String uname :userfrilist){//<a class="btn btn-success center-block" href="#">Success</a> <button class="btn btn-primary">Download</button>
							//out.println("<a href=\"#\" class=\"list-group-item\">"+uname+"<input type='submit' value='Unfriend' onclick =\"window.location.href='FriendServlet?confusername="+uname+"&action=unfriend'\"  style='float:right; margin-left:5px'/>&nbsp;&nbsp;&nbsp;<input type='submit' value='View Health' onclick =\"window.location.href='FriendServlet?confusername="+uname+"&action=viewhealth'\"  style='float:right; margin-left:5px'/><input type='submit' value='View Profile' onclick =\"window.location.href='FriendServlet?confusername="+uname+"&action=viewprofile'\"  style='float:right; margin-left:5px'/></a>");
							//} 
							//for(Forum f :forum){
							//out.println("<a href=\"welcome.jsp?show=viewforum&forumid="+f.getForumID()+"\" class=\"list-group-item\">"+"Topic: "+f.getTopic()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+"Created By: "+f.getCreatedByModerator_Username()+"</a>");
							//}
							for(Comment c :comm){
								out.println("<a href=\"#\" class=\"list-group-item \">"
										+ "Commenter Username: " + c.getCommenter_Username() + "&nbsp;&nbsp;"
										+ "Text: " + c.getCommentText() + "&nbsp;&nbsp;"
										+ "Photo: " + c.getPhotoLocation() + "&nbsp;&nbsp;"
										+ "video: " + c.getVideoLocation() + "&nbsp;&nbsp;"
										+ "Link: " + c.getLinkLocation() + "&nbsp;&nbsp;"+"</a>");
							}
						%>
						<form class="form" action="PostCommentRateServlet" method="get">
						<h4>Write a new Comment</h4>
							<div class="input-group text-center">
								<input type="text" name="ctext"
									class="form-control input-lg"
									title="Enter your Comment"
									placeholder="Enter your Comment">
								<input type="text" name="clink"
									class="form-control input-lg"
									title="Enter optional Link."
									placeholder="Enter optional Link"> 
								<input type="text" name="cphoto"
									class="form-control input-lg"
									title="Enter optional PhotoLink"
									placeholder="Enter optional PhotoLink"> 
								<input type="text" name="cvideo"
									class="form-control input-lg"
									title="Enter optional VideoLink"
									placeholder="Enter optional VideoLink">
								<input type="number" name="crate"
									class="form-control input-lg"
									title="Enter optional Rating For The Post"
									placeholder="Enter optional Rating For The Post(1 to 5)">  
								<input type="submit"
									name="submit" class="btn btn-lg btn-primary" type="button"
									value="Comment It In The Post">
							</div>
						</form>
					</div>
				</div>
				<!--/panel-body-->
			</div>
		</div>
	</div>
</div>