package videos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Comment;
import model.User;
import model.User.Role;
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
				

			comment = new Comment(commentId, content, date, owner, video);
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
public static List<Comment> getAll(Integer id, String orderBy, String direction) {
	
	Comment comment = null;
	Connection conn = ConnectionManager.getConnection();
	List<Comment> comments = new ArrayList<Comment>();
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String order = orderBy + " " + direction + ";";
		String query = "select * from comment where video = ? order by " + order;
		
		int index = 1;
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		System.out.println(ps);

		rs = ps.executeQuery();

		while (rs.next()) {
			index = 1;
			Integer commentId = rs.getInt(index++);
			String content = rs.getString(index++);
			Date date = rs.getDate(index++);
			User owner = UserDAO.getById(rs.getInt(index++)) ;
			Video video = VideoDAO.getById(rs.getInt(index++));
			
	
			comment = new Comment(commentId, content, date, owner, video);
			comments.add(comment);
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
	return comments;

}
public static List<Comment> getAll(Integer id) {
	
	Comment comment = null;
	Connection conn = ConnectionManager.getConnection();
	List<Comment> comments = new ArrayList<Comment>();
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		
		String query = "select * from comment where video = ? ;";
		
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		System.out.println(ps);

		rs = ps.executeQuery();

		while (rs.next()) {
			int index = 1;
			Integer commentId = rs.getInt(index++);
			String content = rs.getString(index++);
			Date date = rs.getDate(index++);
			User owner = UserDAO.getById(rs.getInt(index++)) ;
			Video video = VideoDAO.getById(rs.getInt(index++));
			

		comment = new Comment(commentId, content, date, owner, video);
		comments.add(comment);
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
	return comments;

}
public static boolean addComment(Comment comment) {

	Connection conn = ConnectionManager.getConnection();
	PreparedStatement ps = null;
	try {

		String query = "insert into comment (content, date, owner, video) values (?, ?, ?, ?);";
		int index = 1;
		ps = conn.prepareStatement(query);
		
		ps.setString(index++, comment.getContent());
		ps.setDate(index++, new java.sql.Date(comment.getDate().getTime()));
		ps.setInt(index++, comment.getOwner().getId());
		ps.setObject(index++, comment.getVideo().getId());
			
		return ps.executeUpdate() == 1;

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
	}
	return false;
}

}
