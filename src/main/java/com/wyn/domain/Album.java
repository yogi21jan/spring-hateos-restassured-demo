package com.wyn.domain;

/**
 * Album (available to purchase - hence 'stockLevel' attribute).
 *
 */
public class Album {
	
	private final String albumId;
	private final String title;
	private final Artist artist;
	private int stockLevel;

	public Album(final String id, final String title, final Artist artist, int stockLevel) {
		this.albumId = id;
		this.title = title;
		this.artist = artist;
		this.stockLevel = stockLevel;
	}

	public String getAlbumId() {
		return albumId;
	}

	public String getTitle() {
		return title;
	}

	public Artist getArtist() {
		return artist;
	}

	public int getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(int stockLevel) {
		this.stockLevel = stockLevel;
	}
}