package com.wyn.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wyn.domain.Album;
import com.wyn.service.MusicService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/product")
@Api(value="Handles all request for Albums")
public class AlbumController {
	
	@Autowired
	private MusicService musicService;
	
	@GetMapping(value = "/yogi/albums", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Lists all Albums")
	public Collection<Resource<Album>> getAllAlbums() {
		
		Collection<Album> albums = musicService.getAllAlbums();
		List<Resource<Album>> resources = new ArrayList<Resource<Album>>();
		for (Album album : albums) {
			resources.add(getAlbumResource(album));
		}
		return resources;
		
	}

	@GetMapping(value = "/yogi/album/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "List albums for given {id} of album")
	public Resource<Album> getAlbum(@PathVariable(value = "id") String id) {

		Album album = musicService.getAlbum(id);
		return getAlbumResource(album);

	}

	private Resource<Album> getAlbumResource(Album album) {

		Resource<Album> resource = new Resource<Album>(album);

		// Link to Album
		resource.add(linkTo(methodOn(AlbumController.class).getAlbum(album.getAlbumId())).withSelfRel());
		
		// Link to Artist
		resource.add(linkTo(methodOn(ArtistController.class).getArtist(album.getArtist().getArtistId())).withRel("artist"));
		// Option to purchase Album
		if (album.getStockLevel() > 0) {
			resource.add(linkTo(methodOn(AlbumController.class).purchaseAlbum(album.getAlbumId())).withRel("album.purchase"));
		}

		return resource;

	}

	@GetMapping(value = "/yogi/album/purchase/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Purchase given {id} of album")
	public Resource<Album> purchaseAlbum(@PathVariable(value = "id") String id) {
		
		Album a = musicService.getAlbum(id);
		a.setStockLevel(a.getStockLevel() - 1);
		Resource<Album> resource = new Resource<Album>(a);
		resource.add(linkTo(methodOn(AlbumController.class).getAlbum(id)).withSelfRel());
		return resource;
		
	}
}
