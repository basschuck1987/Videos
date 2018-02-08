package videos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Comment;
import model.LikeDislike;
import model.Video;

public class LikeDislikeDAO {


		public static List<LikeDislike> getByUser(Integer userId) {

			
			List<LikeDislike> likesdislikes = new ArrayList<LikeDislike>();
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement ps = null;
			ResultSet rs = null;
			try {

				String query = "select p.* from UserLikeDislike x left join likeDislike p on x.idLikeDislike = p.id where x.idUser = ?";

				ps = conn.prepareStatement(query);
				ps.setInt(1, userId);
				System.out.println(ps);

				rs = ps.executeQuery();

				while (rs.next()) {
					int index = 1;
					Integer id = rs.getInt(index++);
					boolean likeOrDislike = rs.getBoolean(index++);
					Date date = rs.getDate(index++);
					Video videoId = VideoDAO.getById(rs.getInt(index++));
					Comment commentId = CommentDAO.getById(rs.getInt(index++));
	
					
					LikeDislike ld = new LikeDislike(id, likeOrDislike, date, videoId, commentId);
					likesdislikes.add(ld);
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
			return likesdislikes;
		}
public static List<LikeDislike> getByVideo(Integer userId) {

			
			List<LikeDislike> likesdislikes = new ArrayList<LikeDislike>();
			Connection conn = ConnectionManager.getConnection();

			PreparedStatement ps = null;
			ResultSet rs = null;
			try {

				String query = "select * from likeDislike where video= ?";

				ps = conn.prepareStatement(query);
				ps.setInt(1, userId);
				System.out.println(ps);

				rs = ps.executeQuery();

				while (rs.next()) {
					int index = 1;
					Integer id = rs.getInt(index++);
					boolean likeOrDislike = rs.getBoolean(index++);
					Date date = rs.getDate(index++);
					Video videoId = VideoDAO.getById(rs.getInt(index++));
					Comment commentId = CommentDAO.getById(rs.getInt(index++));
	
					
					LikeDislike ld = new LikeDislike(id, likeOrDislike, date, videoId, commentId);
					likesdislikes.add(ld);
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
			return likesdislikes;
		}


		
		
		
	}

	

