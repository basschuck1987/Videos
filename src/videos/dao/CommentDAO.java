package videos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.Comment;
import model.User;
import model.Video;



public class CommentDAO {
	
public static Comment getById(Integer id) {
		
		Comment comment = null;
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = "select * from comment where comment.id = likeDislike.id ";

			ps = conn.prepareStatement(query);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				int index = 1;
				Integer commentId = rs.getInt(index++);
				String content = rs.getString(index++);
				Date date = rs.getDate(index++);
				User owner = UserDAO.getById(rs.getInt(index++)) ;
				Video video = VideoDAO.getById(rs.getInt(index++));
				

			comment = new Comment(id, content, date, owner, video);
			}

		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			System.out.println("Greska u upitu");
			ex.printStackTrace();
		}

		finally {
			try {
				ps.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException ex1) {
				ex1.printStackTrace();
			}
		}
		return comment;
	}

}
