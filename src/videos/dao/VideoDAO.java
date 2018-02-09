package videos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.User;
import model.Video;
import model.User.Role;
import model.Video.Visibility;

public class VideoDAO {
	
	/*public static Video getById(Integer id) {
		
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
				String name = rs.getString(index++);

				
				video = new Video(videoId, name, url, thumbnail, description, visibility, blocked, previews, date, owner);
				
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
	}*/

	public static List<Video> getByTypeOrdered(Visibility visibility1, 
				Visibility visibility2,Visibility visibility3,
				String orderBy, String direction) {
			
			Video video = null;
			Connection conn = ConnectionManager.getConnection();
	
			List<Video> videos = new ArrayList<Video>();
			// (visibility, visibility2,......)
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String order = orderBy + " " + direction + ";";
				String query = "select * from video where visibility in (? , ? , ?) order by " + order;
				int index = 1;
				ps = conn.prepareStatement(query);
				if(visibility1 == null) {
					ps.setObject(index++,null);
				}else {
					ps.setString(index++, visibility1.toString());
				}
				if(visibility2 == null) {
					ps.setObject(index++,null);
				}else {
					ps.setString(index++, visibility2.toString());
				}
				if(visibility3 == null) {
					ps.setObject(index++,null);
				}else {
					ps.setString(index++, visibility3.toString());
				}
				System.out.println(ps);
	
				rs = ps.executeQuery();
	
				while(rs.next()) {
					index = 1;
					Integer id = rs.getInt(index++);
					String url = rs.getString(index++);
					String thumbnail = rs.getString(index++);
					String description = rs.getString(index++);
					Visibility visibility = Visibility.valueOf(rs.getString(index++));
					boolean blocked = rs.getBoolean(index++);
					Integer previews = rs.getInt(index++);
					Date date = rs.getDate(index++);
					User owner = UserDAO.getById(rs.getInt(index++)) ;
					String name = rs.getString(index++);
					boolean likeDislikeVisible = rs.getBoolean(index++);
	
					
					video = new Video(id,name, url, thumbnail, description, visibility, blocked, previews, date, owner, likeDislikeVisible);
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
public static List<Video> getPrivateVideoUser(Integer id, String orderBy, String direction) {
	
	Video video = null;
	Connection conn = ConnectionManager.getConnection();

	List<Video> videos = new ArrayList<Video>();
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String order = orderBy + " " + direction + ";";
		String query = "select * from video where visibility = 'PRIVATE' and owner = ? order by " + order;
		

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
			String name = rs.getString(index++);
			boolean likeDislikeVisible = rs.getBoolean(index++);
			
			video = new Video(videoId,name, url, thumbnail, description, visibility, blocked, previews, date, owner,likeDislikeVisible);
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

public static List<Video> getVideoByUser(Integer id, String orderBy, String direction) {
	
	Video video = null;
	Connection conn = ConnectionManager.getConnection();

	List<Video> videos = new ArrayList<Video>();
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String order = orderBy + " " + direction + ";";
		String query = "select * from video where owner = ? order by " + order;
		
		int index = 1;
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		System.out.println(ps);

		rs = ps.executeQuery();

		while(rs.next()) {
			index = 1;
			Integer videoId = rs.getInt(index++);
			String url = rs.getString(index++);
			String thumbnail = rs.getString(index++);
			String description = rs.getString(index++);
			Visibility visibility = Visibility.valueOf(rs.getString(index++));
			boolean blocked = rs.getBoolean(index++);
			Integer previews = rs.getInt(index++);
			Date date = rs.getDate(index++);
			User owner = UserDAO.getById(rs.getInt(index++)) ;
			String name = rs.getString(index++);
			boolean likeDislikeVisible = rs.getBoolean(index++);
			
			video = new Video(videoId,name, url, thumbnail, description, visibility, blocked, previews, date, owner,likeDislikeVisible);
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
	public static List<Video> getSearch(String searchParams) {
		
		Video video = null;
		Connection conn = ConnectionManager.getConnection();

		List<Video> videos = new ArrayList<Video>();
		// (visibility, visibility2,......)
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String parameters = "'%" + searchParams + "%'"  ;
			String query = "select * from video where description like " + parameters;
			int index = 1;
			ps = conn.prepareStatement(query);
			System.out.println(ps);

			rs = ps.executeQuery();

			while(rs.next()) {
				index = 1;
				Integer id = rs.getInt(index++);
				String url = rs.getString(index++);
				String thumbnail = rs.getString(index++);
				String description = rs.getString(index++);
				Visibility visibility = Visibility.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);
				Integer previews = rs.getInt(index++);
				Date date = rs.getDate(index++);
				User owner = UserDAO.getById(rs.getInt(index++)) ;
				String name = rs.getString(index++);
				boolean likeDislikeVisible = rs.getBoolean(index++);
				
				video = new Video(id,name, url, thumbnail, description, visibility, blocked, previews, date, owner, likeDislikeVisible);
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
/*public static List<Video> getByType() {
		
		Video video = null;
		Connection conn = ConnectionManager.getConnection();

		List<Video> videos = new ArrayList<Video>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = "select * from video where visibility = ?";
			

			ps = conn.prepareStatement(query);
			ps.setString(1, );
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
	}*/
	public static Video getById(Integer id) {

		Connection conn = ConnectionManager.getConnection();
		Video video = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = "select * from video where id = ?";

			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			System.out.println(ps);

			rs = ps.executeQuery();

			if (rs.next()) {
				int index = 1;
				Integer idVideo = rs.getInt(index++);
				String url = rs.getString(index++);
				String thumbnail = rs.getString(index++);
				String description = rs.getString(index++);
				Visibility visibility = Visibility.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);
				Integer previews = rs.getInt(index++);
				Date date = rs.getDate(index++);
				User owner = UserDAO.getById(rs.getInt(index++)) ;
				String name = rs.getString(index++);
				boolean likeDislikeVisible = rs.getBoolean(index++);
				
				return new Video(idVideo,name, url, thumbnail, description, visibility, blocked, previews, date, owner,likeDislikeVisible);
				
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
		return null;
	}
	/*public static List<Video> getComments(Integer id) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Video> comments = new ArrayList<Video>();
		try {

			String query = "select v.* from comment c left join video v on c.video = v.id where c.id = ?";

			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			System.out.println(ps);

			rs = ps.executeQuery();

			while (rs.next()) {
				int index = 1;
				Integer idVideo = rs.getInt(index++);
				String url = rs.getString(index++);
				String thumbnail = rs.getString(index++);
				String description = rs.getString(index++);
				Visibility visibility = Visibility.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);
				Integer previews = rs.getInt(index++);
				Date date = rs.getDate(index++);
				User owner = UserDAO.getById(rs.getInt(index++)) ;
				String name = rs.getString(index++);

				
				return new Video(idVideo,name, url, thumbnail, description, visibility, blocked, previews, date, owner);
				
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
		return null;
	}*/


}
