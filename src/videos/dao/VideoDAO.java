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
					String name = rs.getString(index++);
					String url = rs.getString(index++);
					String thumbnail = rs.getString(index++);
					String description = rs.getString(index++);
					Visibility visibility = Visibility.valueOf(rs.getString(index++));
					boolean blocked = rs.getBoolean(index++);
					Integer previews = rs.getInt(index++);
					Date date = rs.getDate(index++);
					User owner = UserDAO.getById(rs.getInt(index++)) ;
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
			String name = rs.getString(index++);
			String url = rs.getString(index++);
			String thumbnail = rs.getString(index++);
			String description = rs.getString(index++);
			Visibility visibility = Visibility.valueOf(rs.getString(index++));
			boolean blocked = rs.getBoolean(index++);
			Integer previews = rs.getInt(index++);
			Date date = rs.getDate(index++);
			User owner = UserDAO.getById(rs.getInt(index++)) ;
			
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

public static List<Video> getVideoByUser(Integer id, Visibility visibility1, 
										Visibility visibility2,Visibility visibility3,
										String orderBy, String direction) {
	
	Video video = null;
	Connection conn = ConnectionManager.getConnection();

	List<Video> videos = new ArrayList<Video>();
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	try {
		String order = orderBy + " " + direction + ";";
		String query = "select * from video where visibility in (?,?,?) and owner = ? order by " + order;
		
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
		ps.setInt(index++, id);
		System.out.println(ps);

		rs = ps.executeQuery();

		while(rs.next()) {
			index = 1;
			Integer videoId = rs.getInt(index++);
			String name = rs.getString(index++);
			String url = rs.getString(index++);
			String thumbnail = rs.getString(index++);
			String description = rs.getString(index++);
			Visibility visibility = Visibility.valueOf(rs.getString(index++));
			boolean blocked = rs.getBoolean(index++);
			Integer previews = rs.getInt(index++);
			Date date = rs.getDate(index++);
			User owner = UserDAO.getById(rs.getInt(index++)) ;
			
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
				String name = rs.getString(index++);
				String url = rs.getString(index++);
				String thumbnail = rs.getString(index++);
				String description = rs.getString(index++);
				Visibility visibility = Visibility.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);
				Integer previews = rs.getInt(index++);
				Date date = rs.getDate(index++);
				User owner = UserDAO.getById(rs.getInt(index++)) ;
				
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
				String name = rs.getString(index++);
				String url = rs.getString(index++);
				String thumbnail = rs.getString(index++);
				String description = rs.getString(index++);
				Visibility visibility = Visibility.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);
				Integer previews = rs.getInt(index++);
				Date date = rs.getDate(index++);
				User owner = UserDAO.getById(rs.getInt(index++)) ;
				
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
	
	public static Video getByUrl(String url) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = "select * from video where url = ?";

			ps = conn.prepareStatement(query);
			ps.setString(1, url);
			System.out.println(ps);

			rs = ps.executeQuery();

			if (rs.next()) {
				int index = 1;
				Integer id = rs.getInt(index++);
				String name = rs.getString(index++);
				String urlVideo = rs.getString(index++);
				String thumbnail = rs.getString(index++);
				String description = rs.getString(index++);
				Visibility visibility = Visibility.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);
				Integer previews = rs.getInt(index++);
				Date date = rs.getDate(index++);
				User owner = UserDAO.getById(rs.getInt(index++)) ;
				
				boolean likeDislikeVisible = rs.getBoolean(index++);
				
				return new Video(id,name, urlVideo, thumbnail, description, visibility, blocked, previews, date, owner,likeDislikeVisible);
				
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
	public static boolean createVideo(Video video) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		try {

			String query = "insert into video (url, thumbnail, description, visibility, blocked, previews, date, owner, name, likeDislikeVisible) values(?,?,?,?,?,?,?,?,?,?);";
			int index = 1;
			ps = conn.prepareStatement(query);
			
			ps.setString(index++, video.getUrl());
			ps.setString(index++, video.getThumbnail());
			ps.setString(index++, video.getDescription());
			ps.setString(index++, video.getVisibility().toString());
			ps.setBoolean(index++, video.isBlocked());
			ps.setInt(index++, video.getPreviews());
			ps.setDate(index++, new java.sql.Date(video.getDate().getTime()));
			ps.setInt(index++, video.getOwner().getId());
			ps.setString(index++, video.getName());
			ps.setBoolean(index++, video.isLikeDislikeVisible());
			System.out.println(ps);	
				
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
	public static boolean updateVideo(Video video) {

		Connection conn = ConnectionManager.getConnection();
		PreparedStatement ps = null;
		try {

			String query = "update video set url=?, thumbnail=?, description=?, visibility=?, blocked=?, previews=?, date=?, owner=?, name=?, likeDislikeVisible=? where id=?;";
			
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setString(index++, video.getUrl());
			ps.setString(index++, video.getThumbnail());
			ps.setString(index++, video.getDescription());
			ps.setString(index++, video.getVisibility().toString());
			ps.setBoolean(index++, video.isBlocked());
			ps.setInt(index++, video.getPreviews());
			ps.setDate(index++, new java.sql.Date(video.getDate().getTime()));
			ps.setInt(index++, video.getOwner().getId());
			ps.setString(index++, video.getName());
			ps.setBoolean(index++, video.isLikeDislikeVisible());
			ps.setObject(index++, video.getId());
			System.out.println(ps);	
				
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
	
public static boolean deleteVideo(Integer id) {
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement ps = null;
		try {
			String query = "delete from video where id = ?; ";
			
			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			System.out.println(ps);
			
			boolean retVal = ps.executeUpdate() == 1;

			return retVal;
			
		} catch (SQLException ex) {
			System.out.println("Greska u upitu");
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
