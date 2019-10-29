package de.hsh.dbs2.imdb.logic;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.entities.Movie;
import de.hsh.dbs2.imdb.util.DBConnection;


public class MovieFactory {

	public static Movie findByID(int movieID) throws SQLException {

		//SQL-Statment
		String sql = "SELECT * FROM " + Movie.table + "WHERE " + Movie.col_movieID + " = ?";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setInt(1, movieID);

		//Select
		ResultSet rs = stmt.executeQuery();
		Movie movie = null;

		if(rs.next()) {
			System.out.println("Der Film mit der ID "+ movieID+ "existiert nicht!");
		} else {
			movie = new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4).charAt(0));
		}
		rs.close();
		stmt.close();
		DBConnection.getConnection().close();

		return movie;
	}

	public static List<Movie> findByTitle(String title) throws SQLException {
		//SQL-Statement

		String sql = "SELECT * FROM " + Movie.table + "WHERE UPPER(" + Movie.col_title + ") LIKE UPPER('%" + title+ "%')";
		Statement stmt = DBConnection.getConnection().createStatement();

		//Select 
		ResultSet rs = stmt.executeQuery(sql);
		List<Movie> movies = new ArrayList<Movie>();
		while (rs.next()) {
			movies.add(new Movie(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4).charAt(0)));
		}
		rs.close();
		stmt.close();
		DBConnection.getConnection().close();
		
		return movies;
	}
	
	
	
}
