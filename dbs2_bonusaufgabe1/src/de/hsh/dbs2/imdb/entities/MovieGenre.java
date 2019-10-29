package de.hsh.dbs2.imdb.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.hsh.dbs2.imdb.util.DBConnection;

/** Entitätsklasse MovieGenre mit allen Attributen und 
 * passenden set- und get-Methoden
 */

public class MovieGenre {

	public static final String table = "hasGenre";
	public static final String col_genreID = "GenreID";
	public static final String col_movieID = "MovieID";

	private int genreID;
	private int movieID;

	public MovieGenre(int genreID, int movieID) {
		this.setGenreID(genreID);
		this.setMovieID(movieID);
	}

	public MovieGenre() {
	}


	public int getGenreID() {
		return genreID;
	}
	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	/** 
	 * Methode zum einfügen 
	 * */

	public void insert() throws SQLException {

		//SQL-Statement:
		String sql = "INSERT INTO " + table + " VALUES(?, ?)";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);

		stmt.setInt(1, this.getGenreID());
		stmt.setInt(2, this.getMovieID());

		int rowsInserted = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsInserted+ "Zeilen hinzugefügt");
		stmt.close();

	}

	public void update() throws SQLException {
		//SQL-Statment
		String sql = " UPDATE " +table+ " SET " + col_movieID + " = ?, " + col_genreID + " = ?, "
				+ " = ? WHERE " + col_movieID + " = ? AND " + col_genreID + " = ?";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setInt(1, this.getMovieID());
		stmt.setInt(2, this.getGenreID());
		stmt.setInt(3, this.getMovieID());
		stmt.setInt(4, this.getGenreID());

		//Update
		int rowsUpdated = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsUpdated+ "Zeilen hinzugefügt");
		stmt.close();

	}


	public void delete() throws SQLException{
		//SQL Statement
		
		String sql = "DELETE FROM " + table+ " WHERE " + col_movieID + " = ? AND " + col_genreID + " = ?";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setInt(1, this.getMovieID());
		stmt.setInt(2, this.getGenreID());
		
		//Delete
		int rowsDeleted = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsDeleted+ "Zeilen gelöscht");
		stmt.close();
	}


}
