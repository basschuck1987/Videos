package videos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Comment;
import model.User;
import model.Video;
import videos.dao.CommentDAO;
import videos.dao.VideoDAO;

/**
 * Servlet implementation class VideoServlet
 */
public class VideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VideoServlet() {
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
		Video video = null;
		List<Comment> comments = new ArrayList<>();
		String message = "";
		String status = "";
		
		try {
			video = VideoDAO.getById(Integer.parseInt(id));
			/*if(video != null) {
				if(loggedInUser != null && (loggedInUser.getRole() == User.Role.ADMIN &&(loggedInUser.getRole() == User.Role.USER && loggedInUser.getId() == video.getOwner().getId() || video.getVisibility() == Video.Visibility.PUBLIC || video.getVisibility() == Video.Visibility.UNLISTED ))) {
					if (orderBy == null || direction == null){
							comments.addAll(CommentDAO.getAll(Integer.parseInt(id), defaultOrderBy, defaultDirection));
						}else {
							comments.addAll(CommentDAO.getAll(Integer.parseInt(id), orderBy, direction));
						}
					}else{
						throw new Exception ("Nemate pristup zeljenoj stranici.");
					}
			}else {
				throw new Exception("Trazeni video ne postoji");
			}*/
			
			if(video != null) {
				if(loggedInUser != null && loggedInUser.getRole() == User.Role.ADMIN){
					if (orderBy == null || direction == null){
							comments.addAll(CommentDAO.getAll(Integer.parseInt(id), defaultOrderBy, defaultDirection));
						}else {
							comments.addAll(CommentDAO.getAll(Integer.parseInt(id), orderBy, direction));
						}
					}else
						 if(loggedInUser != null && loggedInUser.getRole() == User.Role.USER && loggedInUser.getId() == video.getOwner().getId() || video.getVisibility() == Video.Visibility.PUBLIC || video.getVisibility() == Video.Visibility.UNLISTED ) {
							if (orderBy == null || direction == null){
								comments.addAll(CommentDAO.getAll(Integer.parseInt(id), defaultOrderBy, defaultDirection));
							}else {
								comments.addAll(CommentDAO.getAll(Integer.parseInt(id), orderBy, direction));
							}
						
						}else if (loggedInUser ==null && video.getVisibility() == Video.Visibility.PUBLIC || video.getVisibility() == Video.Visibility.UNLISTED) {
								if (orderBy == null || direction == null){
									comments.addAll(CommentDAO.getAll(Integer.parseInt(id), defaultOrderBy, defaultDirection));
								}else {
									comments.addAll(CommentDAO.getAll(Integer.parseInt(id), orderBy, direction));
								}
							}else {
								throw new Exception("Nemate pristup zeljenoj stranici");
							}
						}else {
							throw new Exception("Trazeni video ne postoji");
						}
			
			
/*			
			if(video != null) {
				if (loggedInUser != null) {
					if(loggedInUser.getRole() == User.Role.ADMIN) {
						if(orderBy != null && direction != null ) {
							comments.addAll(CommentDAO.getAll(Integer.parseInt(id), orderBy, direction));
						}else {
							comments.addAll(CommentDAO.getAll(Integer.parseInt(id), defaultOrderBy, defaultDirection));
						}
					}else if(loggedInUser.getRole() == User.Role.USER || loggedInUser == video.getOwner()) {
						if(orderBy != null && direction != null ) {
							comments.addAll(CommentDAO.getAll(Integer.parseInt(id), orderBy, direction));
						}else {
							comments.addAll(CommentDAO.getAll(Integer.parseInt(id), defaultOrderBy, defaultDirection));
						}
					}
					
				}
				
				
				else {
					throw new Exception("Nemate pristup zeljenoj stranici");
				}
				
				
				
			} else {
				throw new Exception("Trazeni video ne postoji");
			}*/
		/*	if(video != null) {
				if(loggedInUser == null ||(video.getVisibility() == Video.Visibility.PUBLIC && video.getVisibility() == Video.Visibility.UNLISTED)) {
					if(orderBy != null && direction != null ) {
						comments.addAll(CommentDAO.getAll(Integer.parseInt(id), orderBy, direction));
					}else {
						comments.addAll(CommentDAO.getAll(Integer.parseInt(id), defaultOrderBy, defaultDirection));
					}
					
				}else if(loggedInUser != null && loggedInUser.getRole() == User.Role.ADMIN){
					if(orderBy != null && direction != null ) {
						comments.addAll(CommentDAO.getAll(Integer.parseInt(id), orderBy, direction));
					}else {
						comments.addAll(CommentDAO.getAll(Integer.parseInt(id), defaultOrderBy, defaultDirection));
					}
				}else if((loggedInUser != null && loggedInUser.getRole() == User.Role.USER) ||((video.getVisibility() == Video.Visibility.PUBLIC && video.getVisibility() == Video.Visibility.UNLISTED) && loggedInUser.getId() == video.getOwner().getId() )) {
					if(orderBy != null && direction != null ) {
						comments.addAll(CommentDAO.getAll(Integer.parseInt(id), orderBy, direction));
					}else {
						comments.addAll(CommentDAO.getAll(Integer.parseInt(id), defaultOrderBy, defaultDirection));
					}
				}else {
					throw new Exception("Nemate pristup zeljenoj stranici");
				}
			}else {
				throw new Exception("Trazeni video ne postoji");
			}*/
			
	
	
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
		data.put("video", video);
		data.put("comments", comments);
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
		String tittle = request.getParameter("tittleInput");
		String url = request.getParameter("urlInput");
		String descriptionVideo = request.getParameter("descriptionVideoInput");
		String select2 = request.getParameter("select2");
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		Video newVideo = null;
		String message = "";
		String status = "";
		
		try {
			switch(action) {
			case "add" :
				if(loggedInUser == null) {
					throw new Exception("Nemate pristup zeljenoj funkciji");
				}else {
					if("".equals(tittle) || "".equals(url)) {
						throw new Exception("Niste popunili sva polja");
					}
					Video existingVideo = VideoDAO.getByUrl(url);
					if(existingVideo != null) {
						throw new Exception("Video vec postoji");
					}
					newVideo = new Video();
					newVideo.setUrl(url);
					newVideo.setName(tittle);
					newVideo.setDescription(descriptionVideo);
					newVideo.setOwner(loggedInUser);
					if(select2.equals("PUBLIC")) {
						newVideo.setVisibility(Video.Visibility.PUBLIC);
						
					}else if(select2.equals("UNLISTED")) {
						newVideo.setVisibility(Video.Visibility.UNLISTED);
					}else if(select2.equals("PRIVATE")){
						newVideo.setVisibility(Video.Visibility.PRIVATE);
					}
					newVideo.setDate(new Date());
					
					VideoDAO.createVideo(newVideo);
					newVideo = VideoDAO.getByUrl(url);
					
				}
				
				message = "Uspesno ste dodali video.";
				status = "success";
				break;
				
			case "update":
				Video video = VideoDAO.getById(Integer.parseInt(id));
				if(video == null) {
					throw new Exception("Video ne postoji");
				}
				if(loggedInUser == null) {
					throw new Exception("Nemate pristup zeljenoj funkciji");
				}else {
					video.setName(tittle);
					video.setUrl(url);
					video.setDescription(descriptionVideo);
					if(select2.equals("PUBLIC")) {
						video.setVisibility(Video.Visibility.PUBLIC);
						
					}else if(select2.equals("UNLISTED")) {
						video.setVisibility(Video.Visibility.UNLISTED);
					}else if(select2.equals("PRIVATE")){
						video.setVisibility(Video.Visibility.PRIVATE);
					}
				}
				if(!VideoDAO.updateVideo(video)) {
					throw new Exception("Promene nisu izvrsene");
				}
			
			status = "success";
			message = "Uspesno obradjen zahtev";
			break;
			
			case "delete" :
				if(!VideoDAO.deleteVideo(Integer.parseInt(id))) {
					throw new Exception("Brisanje nije moguce");
				}
				status = "success";
				message = "Uspesno obradjen zahtev";
				
				break;
			}
		}
		catch(Exception e){
			message = e.getMessage();
			status = "failure";
		}
		Map<String, Object> data = new HashMap<>();
		data.put("message", message);
		data.put("status", status);
		data.put("video", newVideo);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}

}
