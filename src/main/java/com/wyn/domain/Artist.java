package com.wyn.domain;

import java.io.Serializable;

/**
 * Music Artist/Group.
 *
 */
public class Artist implements Serializable{
	
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
