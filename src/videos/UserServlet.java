package videos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import model.Video;
import model.Video.Visibility;
import videos.dao.CommentDAO;
import videos.dao.UserDAO;
import videos.dao.VideoDAO;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		String id = request.getParameter("id");
		String orderBy = request.getParameter("orderBy");
		String direction = request.getParameter("direction");
		String defaultOrderBy = "id";
		String defaultDirection = "DESC";
		System.out.println(id);
		User user = null;
		List<Video> videos = new ArrayList<Video>();
		List<User> followers = new ArrayList<User>();
		String message = "";
		String status = "";
		try { 
			user = UserDAO.getById(Integer.parseInt(id));
			if(user != null) {
				if(loggedInUser != null && loggedInUser.getRole() == User.Role.ADMIN){
					if (orderBy == null && direction == null){
						videos.addAll(VideoDAO.getVideoByUser(Integer.parseInt(id),Visibility.PUBLIC, Visibility.PRIVATE, Visibility.UNLISTED, defaultOrderBy, defaultDirection));
						followers.addAll(UserDAO.getFollowing(Integer.parseInt(id)));
					}else {
							videos.addAll(VideoDAO.getVideoByUser(Integer.parseInt(id),Visibility.PUBLIC, Visibility.PRIVATE, Visibility.UNLISTED, orderBy, direction));
							followers.addAll(UserDAO.getFollowing(Integer.parseInt(id)));
						}
				}else 
					if(loggedInUser != null && loggedInUser.getRole() == User.Role.USER) {
						if (loggedInUser.getId() != user.getId()){
							if (orderBy == null && direction == null){
								videos.addAll(VideoDAO.getVideoByUser(Integer.parseInt(id),Visibility.PUBLIC, null, Visibility.UNLISTED, defaultOrderBy, defaultDirection));
								followers.addAll(UserDAO.getFollowing(Integer.parseInt(id)));
							}else {
								videos.addAll(VideoDAO.getPrivateVideoUser(Integer.parseInt(id), orderBy, direction));
								followers.addAll(UserDAO.getFollowing(Integer.parseInt(id)));
							}
						}else {
							if (orderBy == null && direction == null){
								videos.addAll(VideoDAO.getVideoByUser(Integer.parseInt(id),Visibility.PUBLIC, Visibility.PRIVATE, Visibility.UNLISTED, defaultOrderBy, defaultDirection));
								followers.addAll(UserDAO.getFollowing(Integer.parseInt(id)));
							}else {
								videos.addAll(VideoDAO.getVideoByUser(Integer.parseInt(id),Visibility.PUBLIC, Visibility.PRIVATE, Visibility.UNLISTED, defaultOrderBy, defaultDirection));
								followers.addAll(UserDAO.getFollowing(Integer.parseInt(id)));
							}
						}
					}else if(loggedInUser == null){
						if (orderBy == null && direction == null){
							videos.addAll(VideoDAO.getVideoByUser(Integer.parseInt(id),Visibility.PUBLIC, null, Visibility.UNLISTED, defaultOrderBy, defaultDirection));
							followers.addAll(UserDAO.getFollowing(Integer.parseInt(id)));
						}else {
							videos.addAll(VideoDAO.getVideoByUser(Integer.parseInt(id),Visibility.PUBLIC, null, Visibility.UNLISTED, defaultOrderBy, defaultDirection));
							followers.addAll(UserDAO.getFollowing(Integer.parseInt(id)));
						}
					}
				
				else {
					throw new Exception("Nemate pristup zeljenoj stranici.");
				}
			}else{
				throw new Exception("Nepostojeci korisnik.");
			}
			
			status = "success";
			message = "Uspesno obradjen zahtev";
		}
		catch (Exception e){
			status = "failure";
			message = e.getMessage();
		}
		Map<String, Object> data = new HashMap<>();
		data.put("message", message);
		data.put("status",status);
		data.put("user", user);
		data.put("videos", videos);
		data.put("followers", followers);
		data.put("loggedInUser", loggedInUser);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		String inputUsr = request.getParameter("inputUsr");
		String inputSur = request.getParameter("inputSur");
		String inputPs = request.getParameter("inputPs");
		String inputDesc = request.getParameter("inputDesc");
		String role = request.getParameter("sel1");
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		String idRemove = request.getParameter("idRemove");
		String blocked = request.getParameter("blocked");
		String message = "";
		String status = "";
		
		try {
			switch(action) {
			case "update" :
				User user = UserDAO.getById(Integer.parseInt(id));
				if(user == null) {
					throw new Exception("Nepostojeci korisnik.");
				}
				if(loggedInUser == null) {
					throw new Exception("Nemate pristup zeljenoj funkciji.");
				}else {
					if(loggedInUser.getRole() == User.Role.ADMIN) {
						user.setUsername(inputUsr);
						user.setSurname(inputSur);
						user.setPassword(inputPs);
						user.setDescription(inputDesc);
						if(blocked.equals("true")) {
							user.setBlocked(true);
						}else {
							user.setBlocked(false);
						}
						if(role.equals("Admin")) {
							user.setRole(User.Role.ADMIN);
						}else if(role.equals("User")) {
							user.setRole(User.Role.USER);
						}
					}else {
						user.setUsername(inputUsr);
						user.setSurname(inputSur);
						user.setPassword(inputPs);
						user.setDescription(inputDesc);
					}
					if(!UserDAO.update(user)) {
						throw new Exception("Promene nisu izvrsene");
					}
				}
				
				
				status = "success";
				message = "Uspesno obradjen zahtev";
				break;
				
			case "delete" :
				
				if(!UserDAO.delete(Integer.parseInt(idRemove))) {
					throw new Exception("Brisanje nije moguce");
				}
				status = "success";
				message = "Uspesno obradjen zahtev";
				
				break;
			}
			
		}
		catch (Exception e){
			status = "failure";
			message = e.getMessage();
			
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("message", message);
		data.put("status",status);
		
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

}
