package de.hsh.dbs2.imdb.entities;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.hsh.dbs2.imdb.util.DBConnection;


/** Entitätsklasse MovieCharacter mit allen Attributen und 
 * passenden set- und get-Methoden
 */

public class MovieCharacter {

	public static final String seq_movCharID = "MovCharID";
	public static final String table = "MovieCharacter";
	public static final String col_char = "Character";
	public static final String col_alias = "Alias";
	public static final String col_movCharID = "MovCharID";
	public static final String col_pos = "Position";
	public static final String col_movID = "hasCharacter";
	public static final String col_persID = "plays";

	private int movCharID;
	private String character;
	private String alias;
	private int position; 
	private int personID; // plays -> PersonID(Person)
	private int movieID; //hasCharacter -> MovieID(Movie)


	public int getMovCharID() {
		return movCharID;
	}
	public void setMovCharID(int movCharID) {
		this.movCharID = movCharID;
	}
	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}

	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	
	public MovieCharacter(int movCharID, String character, String alias, int position, int movieID, int personID) {
		this.setCharacter(character);
		this.setAlias(alias);
		this.setPosition(position);
		this.setMovieID(movieID);
		this.setPersonID(personID);
		this.setMovCharID(movCharID);		
	}
	
	public MovieCharacter() {
	}


	/** 
	 * Methode zum einfügen 
	 * 
	 * */

	public void insert() throws SQLException{

		//SQL-Statement:
		String sql = "INSERT INTO " + table + "(" +col_movCharID + "," +col_char+","+col_pos+","+col_movID+","+
		col_persID+","+col_alias+") VALUES (" + seq_movCharID + ".nextval, ?, ?, ?, ?,?)";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);

		stmt.setString(1, this.getCharacter());
		stmt.setInt(2, this.getPosition());
		stmt.setInt(3, this.getMovieID());
		System.out.println(this.getMovieID());
		stmt.setInt(4, this.getPersonID());
		System.out.println(this.getPersonID());
		stmt.setString(5, this.getAlias());

		int rowsInserted = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsInserted+ "Zeilen hinzugefügt");
		stmt.close();

	}



	/** 
	 * akutalisiert einen Film Charakter
	 * 
	 * */
	public void update() throws SQLException {
		//SQL-Statment
		String sql = " UPDATE " +table+ " SET " + col_char + " = ?, " + col_pos + " = ?, " 
				+ col_movID + " = ?, " + col_persID + "= ?, " + col_alias + " = ? WHERE "
				+ col_movCharID + " = ?";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setString(1, this.getCharacter());
		stmt.setInt(2, this.getPosition());
		stmt.setInt(3, this.getMovieID());
		stmt.setInt(4, this.getPersonID());
		stmt.setString(5, this.getAlias());
		stmt.setInt(5, this.getMovCharID());

		//Update
		int rowsUpdated = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsUpdated+ "Zeilen hinzugefügt");
		stmt.close();

	}


	/** 
	 * Methode zum löschen 
	 * 
	 * */

	public void delete() throws SQLException{
		//SQL Statement
		
		String sql = "DELETE FROM " + table+ " WHERE " + col_movCharID + " = ? ";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setInt(1, this.getMovCharID());

		
		//Delete
		int rowsDeleted = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsDeleted+ "Zeilen gelöscht");
		stmt.close();
	}

}
