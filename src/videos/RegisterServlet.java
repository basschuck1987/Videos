package videos;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.User;
import videos.dao.UserDAO;

/**
 * Servlet implementation class RegisterServlet
 */
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usernameReg = request.getParameter("usernameReg");
		String passwordReg = request.getParameter("passwordReg");
		String passwordRep = request.getParameter("passwordRep");
		
		String message = "Uspesno ste se registrovali";
		try {
			if("".equals(usernameReg) || "".equals(passwordReg) || "".equals(passwordRep)) 
				throw new Exception("Niste popunili sva polja!");
				
			if(!passwordReg.equals(passwordRep)) 
				throw new Exception("Lozinke se ne poklapaju");
			
			User existingUser = UserDAO.get(usernameReg);
			if(existingUser != null)
				throw new Exception("Korisnik vec postoji");
				
			User newUser = new User();
		}catch (Exception ex){
			message = ex.getMessage();
			}
		
		
		//request.setAttribute("message", message);
		//request.getRequestDispatcher("Message.jsp").forward(request, response);
		Map<String, Object> data = new HashMap<>();
		data.put("message", message);
		
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonData = mapper.writeValueAsString(data);
		System.out.println(jsonData);
		response.setContentType("application/json");
		response.getWriter().write(jsonData);
		
		

	
	}
	

}
