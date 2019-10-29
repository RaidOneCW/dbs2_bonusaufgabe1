package de.hsh.dbs2.imdb.entities;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import de.hsh.dbs2.imdb.util.DBConnection;

import java.sql.ResultSet;

/** Entitätsklasse Person mit allen Attributen und 
 * passenden set- und get-Methoden
 */

public class Person {

	public static final String seq_personID = "PersonID";
	public static final String table = "Person";
	public static final String col_name = "Name";
	public static final String col_sex = "Sex";
	public static final String col_personID = "PersonID";

	private int personID;
	private String name;
	private char sex;

	public Person(String name, char sex, int personID) {
		this.setName(name);
		this.setSex(sex);
		this.setPersonID(personID);
	}

	public Person() {
	}

	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}


	/** 
	 * Methode zum Hinzufügen von Datensätzen
	 * 
	 * */


	public void insert() throws SQLException{

		//SQL-Statement:
		String sql = "INSERT INTO Person (" + col_personID + "," +col_name + "," + col_sex + ") VALUES(" +seq_personID+ ".nextval, ?, ?)";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);

		stmt.setString(1, this.getName());
		stmt.setString(2, String.valueOf(this.getSex()));

		int rowsInserted = stmt.executeUpdate();
		stmt.close();
		if(rowsInserted < 1 ) 
			System.out.println("Es wurde kein Datensatz hinzugefügt!");
		else 
			System.out.println("Es wurden "+rowsInserted+ "Zeilen hinzugefügt");


		//ID:

		sql = "SELECT " + seq_personID + ".currval FROM DUAL";
		stmt = DBConnection.getConnection().prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())this.setPersonID(rs.getInt(1));
		rs.close();
		stmt.close();
	}

	/** 
	 * Methode zum aktualisieren der Daten
	 * 
	 * */

	public void update() throws SQLException {
		//SQL-Statment

		String sql = " UPDATE " +table+ " SET " + col_name + " = ?, " + col_sex + " = ? WHERE " + col_personID + " = ?";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setString(1, this.getName());
		stmt.setString(2, String.valueOf(this.getSex()));

		int rowsUpdated = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsUpdated+ "Zeilen verändert");
		stmt.close();

	}

	/** 
	 * Methode zum löschen 
	 * 
	 * */

	public void delete() throws SQLException{
		//SQL Statement

		String sql = "DELETE FROM " + table+ " WHERE " + col_personID + " = ?";
		PreparedStatement stmt = DBConnection.getConnection().prepareStatement(sql);
		stmt.setInt(1, this.getPersonID());

		//Delete
		int rowsDeleted = stmt.executeUpdate();
		System.out.println("Es wurden "+rowsDeleted+ "Zeilen gelöscht");
		stmt.close();
	}


	/**
	 * Diese Methode gibt den Wert des Person Objekts als String zurück.
	 *
	 * @return String Wert
	 */
	@Override
	public String toString() {
		return this.getName();
	}

}