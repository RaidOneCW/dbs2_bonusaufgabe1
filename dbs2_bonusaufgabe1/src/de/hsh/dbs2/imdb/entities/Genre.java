package de.hsh.dbs2.imdb.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hsh.dbs2.imdb.util.DBConnection;


/** Entitätsklasse Genre mit allen Attributen und passenden
 * set- und get-Methoden
 */

public class Genre {

	public static final String seq_genreID = "GenreID";
	public static final String table = "Genre";
	public static final String col_genre = "Genre";
	public static final String col_genreID = "GenreID";

	private int genreID;
	private String genre;

	public Genre(int genreID, String genre) {
		this.setGenreID(genreID);
		this.setGenre(genre);
	}

	public Genre() {
	}


	public int getGenreID() {
		return genreID;
	}
	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}

	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * Diese Methode fuegt das aktuelle Genre-Objekt in SQL(Tablle Movie) ein.
	 */
	public void insert() throws SQLException {
		//SQL-Statement

		String sql = "INSERT INTO " + table +" VALUES (" + seq_genreID +".nextval, ?)";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setString(1, this.getGenre());

		int roswInserted = stmt.executeUpdate();
		System.out.println("Es wurden " +roswInserted+ "Zeilen hinzugefügt");

		sql = "SELECT "+seq_genreID+".currval FROM DUAL";
		stmt = DBConnection.getConnection().prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())this.setGenreID(rs.getInt(1));
		rs.close();
		stmt.close();
	}

	/**
	 * Diese Methode aktualisiert das aktuelle Genre-Objekt in SQL.
	 */
	public void update() throws SQLException {
		//SQL-Statment

		String sql = " UPDATE " +table+ " SET " + col_genre + " = ?"
				+ "WHERE " + col_genreID + " = ?";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setString(1, this.getGenre());
		stmt.setInt(2, this.getGenreID());


		//Update
		int rowsUpdated = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsUpdated+ "Zeilen hinzugefügt");
		stmt.close();

	}
	/**
	 * Diese Methode loescht das aktuelle Genre-Objekt und seine Referenzen in
	 * SQL.
	 */
	public void delete() throws SQLException {
		//SQL Statement

		String sql = "DELETE FROM " + table+ " WHERE " + col_genreID + " = ?";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setInt(1, this.getGenreID());

		int rowsDeleted = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsDeleted+ "Zeilen gelöscht");
		stmt.close();
	}
	
}