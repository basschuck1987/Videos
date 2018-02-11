package videos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.User;
import model.User.Role;

public class UserDAO {

	public static User getByUserName(String username) {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = "select * from user where username = ?";

			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			System.out.println(ps);

			rs = ps.executeQuery();

			if (rs.next()) {
				int index = 1;
				Integer id = rs.getInt(index++);
				username = rs.getString(index++);
				String password = rs.getString(index++);
				String name = rs.getString(index++);
				String surname = rs.getString(index++);
				String email = rs.getString(index++);
				String description = rs.getString(index++);
				Date date = rs.getDate(index++);
				Role role = Role.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);

				return new User(id, username, password, name, surname, email, description, date, role, blocked);
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
	
	
	public static User getById(Integer id) {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = "select * from user where id = ?";

			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			System.out.println(ps);

			rs = ps.executeQuery();

			if (rs.next()) {
				int index = 1;
				Integer userId = rs.getInt(index++);
				String username = rs.getString(index++);
				String password = rs.getString(index++);
				String name = rs.getString(index++);
				String surname = rs.getString(index++);
				String email = rs.getString(index++);
				String description = rs.getString(index++);
				Date date = rs.getDate(index++);
				Role role = Role.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);

				return new User(userId, username, password, name, surname, email, description, date, role, blocked);
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

	public static List<User> getAll() {
		
		User user = null;
		Connection conn = ConnectionManager.getConnection();
		List<User> users = new ArrayList<User>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			String query = "select * from user";

			ps = ps = conn.prepareStatement(query);
			System.out.println(ps);

			rs = ps.executeQuery();

			while (rs.next()) {
				int index = 1;
				Integer userId = rs.getInt(index++);
				String username = rs.getString(index++);
				String password = rs.getString(index++);
				String name = rs.getString(index++);
				String surname = rs.getString(index++);
				String email = rs.getString(index++);
				String description = rs.getString(index++);
				Date date = rs.getDate(index++);
				Role role = Role.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);

				user = new User(userId, username, password, name, surname, email, description, date, role, blocked);
				users.add(user);
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
		return users;
	}
	public static List<User> getFollowers(Integer id) {

		Connection conn = ConnectionManager.getConnection();

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> followers = new ArrayList<User>();
		try {

			String query = "select u.* from followers f left join user u on f.idFollower = u.id where f.idUser = ?;";

			ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();

			while(rs.next()) {
				int index = 1;
				Integer userId = rs.getInt(index++);
				String username = rs.getString(index++);
				String password = rs.getString(index++);
				String name = rs.getString(index++);
				String surname = rs.getString(index++);
				String email = rs.getString(index++);
				String description = rs.getString(index++);
				Date date = rs.getDate(index++);
				Role role = Role.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);

				User follower = new User(userId, username, password, name, surname, email, description, date, role,
						blocked);
				followers.add(follower);

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
		return followers;
	}

	public static List<User> getOrderBy(String orderBy, String direction) {
		
		User user = null;
		Connection conn = ConnectionManager.getConnection();

		List<User> users = new ArrayList<User>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String order = orderBy + " " + direction + ";";
			String query = "select * from user order by " + order;
			int index = 1;
			ps = conn.prepareStatement(query);
			System.out.println(ps);

			rs = ps.executeQuery();

			while (rs.next()) {
				index = 1;
				Integer userId = rs.getInt(index++);
				String username = rs.getString(index++);
				String password = rs.getString(index++);
				String name = rs.getString(index++);
				String surname = rs.getString(index++);
				String email = rs.getString(index++);
				String description = rs.getString(index++);
				Date date = rs.getDate(index++);
				Role role = Role.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);

				user = new User(userId, username, password, name, surname, email, description, date, role, blocked);
				users.add(user);
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
		return users;
	}
	
	public static boolean update(User user) {
		
		Connection conn = ConnectionManager.getConnection();

		PreparedStatement ps = null;
		try {
			String query = "update user set name = ?, surname = ?, password = ?, description = ?, role = ? where id=?; ";
			
			ps = conn.prepareStatement(query);
			int index = 1;
			ps.setString(index++, user.getName());
			ps.setString(index++, user.getSurname());
			ps.setString(index++, user.getPassword());
			ps.setString(index++, user.getDescription());
			ps.setString(index++, user.getRole().toString());
			ps.setInt(index++, user.getId());
			System.out.println(ps);

			return ps.executeUpdate() == 1;
			
		} catch (SQLException ex) {
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
	
	
	
	
	/*
	 * public static List<User> getAll(){ return new ArrayList<>(); } public static
	 * List<User> getAll(String username, Role role) { return new ArrayList<>(); }
	 * 
	 * public static boolean add(User user) { Connection conn =
	 * ConnectionManager.getConnection();
	 * 
	 * PreparedStatement ps = null; try { String query =
	 * "Insert into user (username,password,name,surname,email,desription,Date,role,blocked) values (?, ?,null,null,null,null,null,null,null,null)"
	 * ;
	 * 
	 * ps = conn.prepareStatement(query); int index =1; ps.setString(index++,
	 * user.getUsername()); ps.setString(index++, user.getPassword());
	 * ps.setString(index++, user.getName()); ps.setString(index++,
	 * user.getSurname()); ps.setString(index++, user.getEmail());
	 * ps.setString(index++, user.getDescription()); ps.setString(index++,
	 * user.getDate().toString()); ps.setString(index++, user.getRole().toString());
	 * 
	 * return ps.executeUpdate() == 1; } catch (Exception ex){
	 * System.out.println("greska u upitu"); ex.printStackTrace(); } finally { try
	 * {ps.close();} catch (SQLException ex1) {ex1.printStackTrace();}
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * return false; }
	 */

}

