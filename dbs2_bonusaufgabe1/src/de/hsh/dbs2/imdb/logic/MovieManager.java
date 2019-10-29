package de.hsh.dbs2.imdb.logic;

import java.util.ArrayList;
import java.util.List;

import de.hsh.dbs2.imdb.entities.Movie;
import de.hsh.dbs2.imdb.logic.dto.MovieDTO;
import de.hsh.dbs2.imdb.util.DBConnection;

public class MovieManager {

	/**
	 * Ermittelt alle Filme, deren Filmtitel den Suchstring enthaelt.
	 * Wenn der String leer ist, sollen alle Filme zurueckgegeben werden.
	 * Der Suchstring soll ohne Ruecksicht auf Gross/Kleinschreibung verarbeitet werden.
	 * @param search Suchstring. 
	 * @return Liste aller passenden Filme als MovieDTO
	 * @throws Exception
	 */
	public List<MovieDTO> getMovieList(String search) throws Exception {
		List<Movie> movies = MovieFactory.findByTitle(search);
		List<MovieDTO> moviesDTO = new ArrayList<>();
		for (Movie m : movies) {
			moviesDTO.add(m.createDTO());
		}
		return moviesDTO;
	}

	/**
	 * Speichert die uebergebene Version des Films neu in der Datenbank oder aktualisiert den
	 * existierenden Film.
	 * Dazu werden die Daten des Films selbst (Titel, Jahr, Typ) beruecksichtigt,
	 * aber auch alle Genres, die dem Film zugeordnet sind und die Liste der Charaktere
	 * auf den neuen Stand gebracht.
	 * @param movie Film-Objekt mit Genres und Charakteren.
	 * @throws Exception
	 */
	public void insertUpdateMovie(MovieDTO movieDTO) throws Exception {
		// TODO
	}
	
	
	
	

	/**
	 * Loescht einen Film aus der Datenbank. Es werden auch alle abhaengigen Objekte geloescht,
	 * d.h. alle Charaktere und alle Genre-Zuordnungen.
	 * @param movieID
	 * @throws Exception
	 */
	public void deleteMovie(long movieID) throws Exception {
		Movie m = new Movie();
		m.setMovieID((int) movieID);
		m.delete();
		DBConnection.getConnection().commit();
	}
	
	/** 
	 * Methode liest einen Film mit allen dazugehörigen Information aus der DB 
	 * und gibt diese als MovieDTO zurück
	 * */

	public MovieDTO getMovie(long movieID) throws Exception {
		return MovieFactory.findByID((int) movieID).createDTO();
	}

}
