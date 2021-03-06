package com.wyn.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.wyn.domain.Album;
import com.wyn.domain.Artist;

/**
 * Hard coded simulation of a Service + Data Access Layer.
 */
@Service
public class MusicService {

	private Map<String, Album> albums;
	private Map<String, Artist> artists;

	/**
	 * Constructor populates the 'database' (i.e. Maps) of Artists and Albums.
	 */
	public MusicService() {

		albums = new HashMap<String, Album>();
		artists = new HashMap<String, Artist>();
		
		Artist artist1 = new Artist("opeth", "Opeth");
		Artist artist2 = new Artist("cfrost", "Celtic Frost");
		artists.put(artist1.getArtistId(), artist1);
		artists.put(artist2.getArtistId(), artist2);
		
		Album album1 = new Album("1", "Heritage", artist1, 2);
		Album album2 = new Album("2", "Deliverance", artist1, 3);
		Album album3 = new Album("3", "Pale Communion", artist1, 0);
		Album album4 = new Album("3", "Monotheist", artist2, 1);
		albums.put(album1.getAlbumId(), album1);
		albums.put(album2.getAlbumId(), album2);
		albums.put(album3.getAlbumId(), album3);
		albums.put(album4.getAlbumId(), album4);
		
	}

	public Collection<Album> getAllAlbums() {
		return albums.values();
	}
	
	public Album getAlbum(final String id) {
		return albums.get(id);
	}

	public Artist getArtist(final String id) {
		return artists.get(id);
	}
}