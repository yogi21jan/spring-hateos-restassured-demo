package com.wyn.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wyn.domain.Artist;
import com.wyn.service.MusicService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/product")
@Api(value="Handles all request for Artist")
public class ArtistController {
	@Autowired
	private MusicService musicService;

	@GetMapping(value = "/yogi/artist/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "List albums for given {id} of Artist")
	public Resource<Artist> getArtist(@PathVariable(value = "id") String id) {
		Artist a = musicService.getArtist(id);
		Resource<Artist> resource = new Resource<Artist>(a);
		resource.add(linkTo(methodOn(ArtistController.class).getArtist(id)).withSelfRel());
		return resource;
	}
}