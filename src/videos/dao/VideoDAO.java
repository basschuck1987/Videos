package videos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.LikeDislike;
import model.User;
import model.Video;
import model.Video.Visibility;

public class VideoDAO {
	
	public static Video getById(Integer id) {
		
		Video video = null;
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = "select * from video where video.id = likeDislike.video";

			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			System.out.println(ps);

			rs = ps.executeQuery();

			if (rs.next()) {
				int index = 1;
				Integer videoId = rs.getInt(index++);
				String url = rs.getString(index++);
				String thumbnail = rs.getString(index++);
				String description = rs.getString(index++);
				Visibility visibility = Visibility.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);
				Integer previews = rs.getInt(index++);
				Date date = rs.getDate(index++);
				User owner = UserDAO.getById(rs.getInt(index++)) ;

				
				video = new Video(videoId, url, thumbnail, description, visibility, blocked, previews, date, owner);
				
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
		return video;
	}

public static List<Video> getByType(Visibility visibilty) {
		
		Video video = null;
		Connection conn = ConnectionManager.getConnection();

		List<Video> videos = new ArrayList<Video>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = "select * from video where visibility = ?";
			

			ps = conn.prepareStatement(query);
			ps.setString(1, visibilty.toString());
			System.out.println(ps);

			rs = ps.executeQuery();

			while(rs.next()) {
				int index = 1;
				Integer id = rs.getInt(index++);
				String url = rs.getString(index++);
				String thumbnail = rs.getString(index++);
				String description = rs.getString(index++);
				Visibility visibility = Visibility.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);
				Integer previews = rs.getInt(index++);
				Date date = rs.getDate(index++);
				User owner = UserDAO.getById(rs.getInt(index++)) ;

				
				video = new Video(id, url, thumbnail, description, visibility, blocked, previews, date, owner);
				videos.add(video);
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
		return videos;
	}
	public static List<Video> getPrivateVideoUser(Integer id) {
	
	Video video = null;
	Connection conn = ConnectionManager.getConnection();

	List<Video> videos = new ArrayList<Video>();
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {

		String query = "select * from video where visibility = 'PRIVATE' and owner = ?;";
		

		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		System.out.println(ps);

		rs = ps.executeQuery();

		while(rs.next()) {
			int index = 1;
			Integer videoId = rs.getInt(index++);
			String url = rs.getString(index++);
			String thumbnail = rs.getString(index++);
			String description = rs.getString(index++);
			Visibility visibility = Visibility.valueOf(rs.getString(index++));
			boolean blocked = rs.getBoolean(index++);
			Integer previews = rs.getInt(index++);
			Date date = rs.getDate(index++);
			User owner = UserDAO.getById(rs.getInt(index++)) ;

			
			video = new Video(videoId, url, thumbnail, description, visibility, blocked, previews, date, owner);
			videos.add(video);
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
	return videos;
}

}
