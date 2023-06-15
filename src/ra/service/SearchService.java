package ra.service;

import ra.model.Singer;
import ra.model.Song;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class SearchService {
    public Song[] searchBySingerGenre(String keyWord, Song[] songList) {
        Song[] result = new Song[100];
        int i = 0;
        for (Song song : songList) {
            if (song != null && (song.getSinger().getGenre().toLowerCase().contains(keyWord.toLowerCase()) || song.getSinger().getSingerName().toLowerCase().contains(keyWord.toLowerCase()))) {
                result[i] = song;
                i++;
            }
        }
        return result;
    }

    public Singer[] findSingerByNameOrGenre(String keyword, Singer[] singers) {
        Singer[] result = new Singer[100];
        int i = 0;
        for (Singer s : singers) {
            if (s != null && (s.getGenre().toLowerCase().contains(keyword) || s.getSingerName().toLowerCase().contains(keyword.toLowerCase()))) {
                result[i++] = s;
            }
        }
        return result;
    }

    public Song[] sortSongByDate(Song[] song){
        Comparator<Song> dateComparator = Comparator.comparing(Song::getCreatedDate);  // tạp biến compasreto cho để sắp xếp theo date
        Song[] newSong = convertSongNotNullElement(song);  // convert sang mảng không còn phần tử null để sắp xếp bằng hàm có sẵn
        Arrays.sort(newSong, dateComparator); // thực hiện sắp xếp
        return newSong;  // trả về
    }

/** hàm convert mảng để lấy phần tử không null */
    private Song[] convertSongNotNullElement(Song[] song){
        /** đếm số bài hát không null */
        int count = 0;
        for (int s = 0; s < song.length; s++) {
            if (song[s] != null) {
                count++;
            }
        }
        /** tạo mảng mới lưu bài hát không null */
        Song newSong[] = new Song[count];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (song[i] != null) {
                newSong[index] = song[i];
                index++;
            }
        }
        return newSong;
    }

    public Song[] sortSongByName(Song[] song) {
        Song[] newSong = convertSongNotNullElement(song); // lấy mảng không null
        /** sắp xếp bằng hàm có sẵn */
        Arrays.sort(newSong);
        return newSong;
    }

}
