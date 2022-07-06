package com.prasad.spotify.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prasad.spotify.model.*;
import com.prasad.spotify.service.SongService;
import com.prasad.spotify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

@RestController
public class SongController
{

    @Autowired
    SongService songService;

    @Autowired
    UserService userService;

    private String getLoggedUser()
    {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/test")
    public ResponseEntity<String> testBackend()
    {
        return ResponseEntity.ok("{\"message\":\"Springbooot called succcessfully...!\"}");
    }


    @PostMapping("/addsong")
    public ResponseEntity<?> addSong(@RequestParam("thumbnail") MultipartFile thumbnail, @RequestParam("music") MultipartFile music, @RequestParam("songdata") String songdata) throws JsonProcessingException {

        String thumbnailPath=saveFile(thumbnail,"thumbnail");
        String musicPath=saveFile(music,"music");
        SongBody songBody=new ObjectMapper().readValue(songdata,SongBody.class);

         return this.songService.addSong(new Song(songBody.getName(),songBody.getArtistId(),thumbnailPath,musicPath));

    }




    @PostMapping("/addartist")
    public ResponseEntity<?> addSong(@RequestBody Artist artist)
    {
        return this.songService.addArtist(artist);
    }

    @PostMapping("/rate")
    public ResponseEntity<?> rate(@RequestBody Rating rating)
    {
        rating.setUser_id(this.userService.getByUsername(getLoggedUser()).getId());
        return this.songService.rate(rating);
    }

    @GetMapping("/allsongs")
    public ResponseEntity<List<Object>> allSong(@RequestParam(value = "search",required = false) String search)
    {
        return this.songService.allSong(search);
    }





    @GetMapping("/allartists")
    public ResponseEntity<List<Object>> allArtist()
    {
        return this.songService.allArtist();
    }

    @GetMapping("/songs/{artist}")
    public ResponseEntity<List<Object>> getArtistSongs(@PathVariable int artist)
    {
        return this.songService.getArtistSongs(artist);
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<List<Object>> getArtist(@PathVariable int id)
    {
        return this.songService.getArtist(id);
    }

    @GetMapping("/songDetails/{id}")
    public ResponseEntity<SongResponse> songDetails(@PathVariable int id)
    {
        return this.songService.songDetails(id,this.getLoggedUser());
    }

    private String saveFile(MultipartFile file,String folder)
    {
        String  target="C:\\Users\\ACER\\Desktop\\DeltaX\\Spotify Angular Frontend\\Spotify\\src\\assets";
        String path="\\"+folder+"\\"+System.currentTimeMillis()+ file.getOriginalFilename();
        String storedPath=target+path;

           if(file.isEmpty())
            {
                throw new RuntimeException("File is Empty");
            }

            try
            {
                byte[] bytes=file.getBytes();
                Files.write(Path.of(storedPath),bytes);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            return path;
    }
}
