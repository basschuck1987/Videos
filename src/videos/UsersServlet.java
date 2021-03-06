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
import model.Video.Visibility;
import videos.dao.UserDAO;
import videos.dao.VideoDAO;

/**
 * Servlet implementation class UsersServlet
 */
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
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
		String orderBy = request.getParameter("orderBy");
		String direction = request.getParameter("direction");
		System.out.println(orderBy +""+ direction);
		String defaultOrderBy = "id";
		String defaultDirection = "DESC";
		
		List<User> lista = new ArrayList<User>();
		String message = "";
		String status = "";
		try { 
			if(loggedInUser != null && loggedInUser.getRole() == User.Role.ADMIN) {
				if(orderBy != null && direction != null) {
					lista.addAll(UserDAO.getOrderBy(orderBy, direction));
				}else {
				
				lista.addAll(UserDAO.getOrderBy(defaultOrderBy, defaultDirection));
			}
			}else {
				throw new Exception ("Nemate pristup zeljenoj stranici.");
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
		data.put("users", lista);
		
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
		doGet(request, response);
	}
}
