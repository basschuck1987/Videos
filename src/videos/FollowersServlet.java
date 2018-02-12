package videos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import videos.dao.UserDAO;

/**
 * Servlet implementation class FollowersServlet
 */
public class FollowersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		String id = request.getParameter("idUser");
		String action = request.getParameter("action");
		User user = null;
		
		String status = "";
		String message = "";
		
		try {
			switch(action) {
			case "add" :
				
				user = UserDAO.getById(Integer.parseInt(id));
				if(user == null) {
					throw new Exception("Nepostojeci korisnik.");
				}
				if(loggedInUser == null) {
					throw new Exception("Nemate pristup zeljenoj funkciji.");
				}else {
					for (User follower : user.getFollowers()) {
						if(loggedInUser.getId() == follower.getId()) {
							throw new Exception("Nije dozvoljeno");	
						}else {
							UserDAO.addFollower(Integer.parseInt(id), loggedInUser.getId());
						}
					}
				}
				
				
				status = "success";
				message = "Uspesno obradjen zahtev";
				break;
				
			case "delete" :
				
				
				if(!UserDAO.deleteFollower(Integer.parseInt(id),loggedInUser.getId())) {
					throw new Exception("Nije dozvoljeno");
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
