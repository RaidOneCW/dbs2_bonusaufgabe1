package de.hsh.dbs2.imdb.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import de.hsh.dbs2.imdb.logic.dto.MovieDTO;
import de.hsh.dbs2.imdb.util.DBConnection;


/** Entitätsklasse Movie mit allen Attributen und 
 * passenden set- und get-Methoden
 */

public class Movie {

	public static final String seq_movieID = "MovieID";
	public static final String table = "Movie";
	public static final String col_type = "Type";
	public static final String col_title = "Title";
	public static final String col_movieID = "MovieID";
	public static final String col_year = "Year";

	private int movieID;
	private String title;
	private int year;
	private char type;
	
	private List<Genre> genre;
	private List<MovieCharacter> character;



	public Movie(int movieID, String title, int year, char type) {
		this.setTitle(title);
		this.setType(type);
		this.setYear(year);
		this.setMovieID(movieID);
	}

	public Movie() {
	}

	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	public List<Genre> getGenre() {
		return genre;
	}
	public void setGenre(List<Genre> genre) {
		this.genre = genre;
	}
	public List<MovieCharacter> getCharacter() {
		return character;
	}

	public void setCharacter(List<MovieCharacter> character) {
		this.character = character;
	}

	/** 
	 * Methode zum einfuegen 
	 * @throws SQLException
	 */

	public void insert() throws SQLException {

		//SQL-Statement:
		System.out.println(DBConnection.getConnection().toString());
		String sql = "INSERT INTO " + table + "("+ col_movieID+ ","+col_title+","+col_year+","+col_type+")"
				+ "VALUES ("+seq_movieID+".nextval, ?, ?, ?)";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		System.out.println(DBConnection.getConnection().toString());
		stmt.setString(1, this.getTitle());
		stmt.setInt(2, this.getYear());
		stmt.setString(3, String.valueOf(this.getType()));

		int rowsInserted = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsInserted+ "Zeilen hinzugefügt");

		//ID:

		sql = "SELECT "+seq_movieID+".currval FROM DUAL";
		stmt = DBConnection.getConnection().prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())this.setMovieID(rs.getInt(1));
		rs.close();
		stmt.close();
	}

	/** 
	 * Methode zum aktualisieren
	 * @throws SQLException 
	 * */

	public void update() throws SQLException {
		//SQL-Statment

		String sql = " UPDATE " +table+ " SET " + col_type + " = ?, " + col_year + " = ?, "
				+ col_title + " = ? WHERE " + col_movieID + " = ?";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setString(1, String.valueOf(this.getType()));
		stmt.setInt(2, this.getType());
		stmt.setString(3, this.getTitle());
		stmt.setInt(4, this.getMovieID());

		//Update
		int rowsUpdated = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsUpdated+ "Zeilen hinzugefügt");
		stmt.close();

	}
	
	/** 
	 * Methode zum löschen
	 * */

	public void delete() throws SQLException{
		//SQL Statement

		String sql = "DELETE FROM " + table+ " WHERE " + col_movieID + " = ?";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setInt(1, this.getMovieID());

		//Delete
		int rowsDeleted = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsDeleted+ "Zeilen gelöscht");
		stmt.close();
	}
	
	/**
	 * Diese Methode erstellt aus aktuellen Objekten eine DTO
	 * 
	 * @return DTO
	 * 
	 * */
	
	public MovieDTO createDTO() {
		MovieDTO dto = new MovieDTO();
		dto.setId((long) this.getMovieID());
		dto.setTitle(this.getTitle());
		dto.setType(Character.toString(this.getType()));
		dto.setYear(this.getYear());
		for(Genre g : this.genre) {
			dto.addGenre(g.getGenre());
		}
		for(MovieCharacter c : character) {
			//dto.addCharacter(c.createDTO());
		}
		return dto;
	}

}
