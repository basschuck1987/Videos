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
import videos.dao.VideoDAO;

/**
 * Servlet implementation class VideosServlet
 */
public class VideosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideosServlet() {
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
		String defaultOrderBy = "id";
		String defaultDirection = "DESC";
		String parameters = request.getParameter("searchParams");
		System.out.println(parameters);
		
		List<Video> lista = new ArrayList<Video>();
		String message = "";
		String status = "";
		try {
			if(orderBy != null && direction != null) {
				if(loggedInUser == null) {
					lista.addAll(VideoDAO.getByTypeOrdered(Visibility.PUBLIC, null, null,orderBy, direction));
				}else {
					if(loggedInUser.getRole() == User.Role.ADMIN) {
						lista.addAll(VideoDAO.getByTypeOrdered(Visibility.PUBLIC, Visibility.PRIVATE,Visibility.UNLISTED, orderBy, direction));
					}else {
						lista.addAll(VideoDAO.getByTypeOrdered(Visibility.PUBLIC, Visibility.UNLISTED,null, orderBy, direction));
						lista.addAll(VideoDAO.getPrivateVideoUser(loggedInUser.getId()));
					}
				}
			} else {
				if(loggedInUser == null) {
					lista.addAll(VideoDAO.getByTypeOrdered(Visibility.PUBLIC, null, null,defaultOrderBy, defaultDirection));
				}else {
					if(loggedInUser.getRole() == User.Role.ADMIN) {
						lista.addAll(VideoDAO.getByTypeOrdered(Visibility.PUBLIC, Visibility.PRIVATE,Visibility.UNLISTED, defaultOrderBy, defaultDirection));
					}else {
						lista.addAll(VideoDAO.getByTypeOrdered(Visibility.PUBLIC, Visibility.UNLISTED,null, defaultOrderBy, defaultDirection));
						lista.addAll(VideoDAO.getPrivateVideoUser(loggedInUser.getId()));
					}
				}
			}	
			status = "success";
			message = "Uspesno obradjen zahtev";

		} catch(Exception e){
			status = "failure";
			message = e.getMessage();
		}
		
		Map<String, Object> data = new HashMap<>();
		data.put("message", message);
		data.put("status",status);
		data.put("videos", lista);
		
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
