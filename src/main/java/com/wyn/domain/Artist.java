package com.wyn.domain;

/**
 * Music Artist/Group.
 *
 */
public class Artist{
	
	private final String artistId;
	private final String name;

	public Artist(final String artistId, final String name) {
		this.artistId = artistId;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getArtistId() {
		return artistId;
	}
	

}
