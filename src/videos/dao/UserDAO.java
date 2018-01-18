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
	
	public static User get(String username) {
		
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			 
			String query = "select password,name,surname,email,description,date,role,blocked from user where username = ?";
			
			ps = conn.prepareStatement(query);
			ps.setString(1, username);
			System.out.println(ps);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				int index = 1;
				String password = rs.getString(index++);
				String name = rs.getString(index++);
				String surname = rs.getString(index++);
				String email = rs.getString(index++);
				String description = rs.getString(index++);
				Date date = rs.getDate(index++);
				Role role = Role.valueOf(rs.getString(index++));
				boolean blocked = rs.getBoolean(index++);
				
				return new User(username,password,name,surname,email,description,date,role,blocked,null,null,null);
				
				
			}
			
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			System.out.println("Greska u upitu");
			ex.printStackTrace();
		} 
		
		finally {
			try {ps.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			try {rs.close();} catch (SQLException ex1) {ex1.printStackTrace();}
		}
		return null;
	}
	
	public static List<User> getAll(){
		return new ArrayList<>();
	}
	public static List<User> getAll(String username, Role role) {
		return new ArrayList<>();
	}
	
	public static boolean add(User user) {
		Connection conn = ConnectionManager.getConnection();
		
		PreparedStatement ps = null;
		try {
			String query = "Insert into user (username,password,name,surname,email,desription,Date,role,blocked) values (?, ?,null,null,null,null,null,null,null,null)";
		
			ps = conn.prepareStatement(query);
			int index =1;
			ps.setString(index++, user.getUsername());
			ps.setString(index++, user.getPassword());
			ps.setString(index++, user.getName());
			ps.setString(index++, user.getSurname());
			ps.setString(index++, user.getEmail());
			ps.setString(index++, user.getDescription());
			ps.setString(index++, user.getDate().toString());
			ps.setString(index++, user.getRole().toString());
			
			return ps.executeUpdate() == 1;
		} catch (Exception ex){
			System.out.println("greska u upitu");
			ex.printStackTrace();
		}
		finally {
			try {ps.close();} catch (SQLException ex1) {ex1.printStackTrace();}
			
			
			
		
		}
		
		
	 return false;	
	} 
	
}
