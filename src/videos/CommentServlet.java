package videos;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
 * Servlet implementation class CommentServlet
 */
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
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
		User loggedInUser =(User) session.getAttribute("loggedInUser");
		String comment = request.getParameter("comment");
		String id = request.getParameter("id");
		Comment newComment = null;
		String message = "";
		String status = "";
		
		try {
			if(loggedInUser == null) {
				throw new Exception("Nemate pristup");
			}
			if("".equals(comment)) {
				throw new Exception("Komentar mora imati sadrzaj");
			}
			Video video = VideoDAO.getById(Integer.parseInt(id));
			if(video == null) {
				throw new Exception("Trazeni video ne postoji");
			}
			newComment = new Comment();
			newComment.setContent(comment);
			newComment.setOwner(loggedInUser);
			newComment.setVideo(video);
			newComment.setDate(new Date());
			CommentDAO.addComment(newComment);
			
			message = "Uspesno dodat komentar";
			status = "success";
		}
		catch(Exception e) {
			message = e.getMessage();
			status = "failure";
		}
		Map<String, Object> data = new HashMap<>();
		data.put("message", message);
		data.put("status", status);
		data.put("comment", newComment);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
	}
	

}
