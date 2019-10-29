package de.hsh.dbs2.imdb.logic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.entities.Genre;
import de.hsh.dbs2.imdb.util.DBConnection;

public class GenreFactory {
	
	/** 
	 * 
	 * Diese Methode liefert eine Liste aller Genre zurueck
	 * 
	 * @return Lister aller Genre
	 * 
	 * */

	public static List<String> findAll() throws SQLException {
		
		List<String> genre = new ArrayList<>();
		String sql = "SELECT * FROM " +Genre.table;
		Connection con = DBConnection.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			genre.add(rs.getString(2));
		}
		return genre;
	
	}





}
