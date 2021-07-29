package com.example.moviecharactersapi.seeders;

import com.example.moviecharactersapi.models.Franchise;
import com.example.moviecharactersapi.models.Movie;
import com.example.moviecharactersapi.models.Character;
import com.example.moviecharactersapi.repositories.CharacterRepository;
import com.example.moviecharactersapi.repositories.FranchiseRepository;
import com.example.moviecharactersapi.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Seeder component that adds data to PostgreSQL if it is empty. If you want an empty one change the boolean attribute
 * emptyDatabase to true.
 */
@Component
public class DatabaseSeeder {

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private FranchiseRepository franchiseRepository;

    private boolean emptyDatabase = false;

    @EventListener
    public void seedDatabase(ContextRefreshedEvent event){


        if(franchiseRepository.findAll().isEmpty() && !emptyDatabase){
            seedFranchise();
        }
        if(movieRepository.findAll().isEmpty() && !emptyDatabase){
            seedMovies();
        }
        if(characterRepository.findAll().isEmpty() && !emptyDatabase){
            seedCharacters();
        }

    }

    private void seedFranchise() {
        Franchise newFranchiseOne = new Franchise();
        newFranchiseOne.setName("Lord of the Rings");
        newFranchiseOne.setDescription("The Lord of the Rings is the saga of a group of sometimes reluctant heroes who set forth to save their world from consummate evil. Its many worlds and creatures were drawn from Tolkien's extensive knowledge of philology and folklore.");
        franchiseRepository.save(newFranchiseOne);

        Franchise newFranchiseTwo = new Franchise();
        newFranchiseTwo.setName("Star Wars");
        newFranchiseTwo.setDescription("Star Wars is an American epic space opera multimedia franchise created by George Lucas, which began with the eponymous 1977 film and quickly became a worldwide pop-culture phenomenon.");
        franchiseRepository.save(newFranchiseTwo);

        Franchise newFranchiseThree = new Franchise();
        newFranchiseThree.setName("Marvel Cinematic Universe");
        newFranchiseThree.setDescription("Marvel Studios is known for the production of the Marvel Cinematic Universe films, based on characters that appear in Marvel Comics publications.");
        franchiseRepository.save(newFranchiseThree);
    }

    private void seedMovies() {
        Movie newMovieOne = new Movie();
        newMovieOne.setTitle("The Fellowship od the Ring");
        newMovieOne.setReleaseYear("2001");
        newMovieOne.setDirector("Peter Jackson");
        newMovieOne.setGenre("Fantasy");
        newMovieOne.setFranchise(franchiseRepository.getById(1));
        newMovieOne.setPictureURL("https://www.imdb.com/title/tt0120737/mediaviewer/rm3592958976/?ref_=ext_shr_lnk");
        newMovieOne.setTrailerURL("https://www.imdb.com/video/vi2073101337?playlistId=tt0120737");
        movieRepository.save(newMovieOne);

        Movie newMovieTwo = new Movie();
        newMovieTwo.setTitle("The Two Towers");
        newMovieTwo.setReleaseYear("2002");
        newMovieTwo.setDirector("Peter Jackson");
        newMovieTwo.setGenre("Fantasy");
        newMovieTwo.setFranchise(franchiseRepository.getById(1));
        newMovieTwo.setPictureURL("https://www.imdb.com/title/tt0167261/mediaviewer/rm306845440/?ref_=ext_shr_lnk");
        newMovieTwo.setTrailerURL("https://www.imdb.com/video/vi2073101337?playlistId=tt0120737");
        movieRepository.save(newMovieTwo);

        Movie newMovieThree = new Movie();
        newMovieThree.setTitle("The Force Awakens");
        newMovieThree.setReleaseYear("2015");
        newMovieThree.setDirector("J.J. Abrams");
        newMovieThree.setGenre("Fantasy");
        newMovieThree.setFranchise(franchiseRepository.getById(2));
        newMovieThree.setPictureURL("https://www.imdb.com/title/tt2488496/mediaviewer/rm527556608/?ref_=ext_shr_lnk");
        newMovieThree.setTrailerURL("https://www.imdb.com/video/vi2762323481?playlistId=tt2488496");
        movieRepository.save(newMovieThree);

        Movie newMovieFour = new Movie();
        newMovieFour.setTitle("Avengers: Endgame");
        newMovieFour.setReleaseYear("2019");
        newMovieFour.setDirector("Anthony Russo");
        newMovieFour.setGenre("Fantasy");
        newMovieFour.setFranchise(franchiseRepository.getById(3));
        newMovieFour.setPictureURL("https://www.imdb.com/title/tt4154796/mediaviewer/rm2775147008/?ref_=ext_shr_lnk");
        newMovieFour.setTrailerURL("https://www.imdb.com/video/vi2163260441?playlistId=tt4154796");
        movieRepository.save(newMovieFour);
    }

    private void seedCharacters() {
        Character newCharacterOne = new Character();
        newCharacterOne.setFullName("Elijah Wood");
        newCharacterOne.setAlias("Frodo");
        newCharacterOne.setGender("Male");
        newCharacterOne.setPictureURL("https://www.imdb.com/name/nm0000704/mediaviewer/rm2331620608/?ref_=ext_shr_lnk");
        ArrayList<Movie> list = new ArrayList<Movie>();
        list.add(movieRepository.getById(1));
        list.add(movieRepository.getById(2));
        newCharacterOne.setMovies(list);
        characterRepository.save(newCharacterOne);

        Character newCharacterTwo = new Character();
        newCharacterTwo.setFullName("Sean Bean");
        newCharacterTwo.setAlias("Boromir");
        newCharacterTwo.setGender("Male");
        newCharacterTwo.setPictureURL("https://www.imdb.com/name/nm0000293/mediaviewer/rm1477227264/?ref_=ext_shr_lnk");
        list.clear();
        list.add(movieRepository.getById(1));
        list.add(movieRepository.getById(2));
        newCharacterTwo.setMovies(list);
        characterRepository.save(newCharacterTwo);

        Character newCharacterThree = new Character();
        newCharacterThree.setFullName("Harrison Ford");
        newCharacterThree.setAlias("Han Solo");
        newCharacterThree.setGender("Male");
        newCharacterThree.setPictureURL("https://www.imdb.com/name/nm0000148/mediaviewer/rm2178324224/?ref_=ext_shr_lnk");
        list.clear();
        list.add(movieRepository.getById(3));
        newCharacterThree.setMovies(list);
        characterRepository.save(newCharacterThree);

        Character newCharacterFour = new Character();
        newCharacterFour.setFullName("Mark Hamill");
        newCharacterFour.setAlias("Luke Skywalker");
        newCharacterFour.setGender("Male");
        newCharacterFour.setPictureURL("https://www.imdb.com/name/nm0000434/mediaviewer/rm1572940288/?ref_=ext_shr_lnk");
        list.clear();
        list.add(movieRepository.getById(3));
        newCharacterFour.setMovies(list);
        characterRepository.save(newCharacterFour);

        Character newCharacterFive = new Character();
        newCharacterFive.setFullName("Robert Downey Jr.");
        newCharacterFive.setAlias("Tony Stark");
        newCharacterFive.setGender("Male");
        newCharacterFive.setPictureURL("https://www.imdb.com/name/nm0000375/mediaviewer/rm421447168/?ref_=ext_shr_lnk");
        list.clear();
        list.add(movieRepository.getById(4));
        newCharacterFive.setMovies(list);
        characterRepository.save(newCharacterFive);

        Character newCharacterSix = new Character();
        newCharacterSix.setFullName("Scarlett Johansson");
        newCharacterSix.setAlias("Natasha Romanoff");
        newCharacterSix.setGender("Female");
        newCharacterSix.setPictureURL("https://www.imdb.com/name/nm0424060/mediaviewer/rm1916122112/?ref_=ext_shr_lnk");
        list.clear();
        list.add(movieRepository.getById(4));
        newCharacterSix.setMovies(list);
        characterRepository.save(newCharacterSix);
    }
}
