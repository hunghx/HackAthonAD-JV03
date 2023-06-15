package ra.controller;

import ra.model.Singer;
import ra.model.Song;
import ra.service.SearchService;

public class SearchController {
    SearchService service = new SearchService();

    public Song[] searchBySingerGenre(String keyWord, Song[] songList) {
        return service.searchBySingerGenre(keyWord, songList);
    }

    public Singer[] findSingerByNameOrGenre(String keyword, Singer[] singers) {
        return service.findSingerByNameOrGenre(keyword, singers);
    }

    public Song[] sortSongByName(Song[] song) {
        return service.sortSongByName(song);
    }

    public Song[] sortSongByDate(Song[] song){
        return service.sortSongByDate(song);
    }

}
