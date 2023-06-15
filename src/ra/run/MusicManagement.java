package ra.run;

import ra.config.Config;
import ra.config.InputMethods;
import ra.controller.SearchController;
import ra.controller.SingerController;
import ra.controller.SongController;
import ra.model.Singer;
import ra.model.Song;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class MusicManagement {
    private final static SingerController singerController = new SingerController();
    private final static SongController songController = new SongController();

    private final static SearchController searchController = new SearchController();

    public static void main(String[] args) {
        singerController.save(new Singer(1,"phạm tấn Bình", 20, "viet nam", false, "nhạc đỏ"));
        singerController.save(new Singer(2,"Hùng",19,"VN",true,"Nhạc vàng"));

        songController.save(new Song("S001","Đi học","code",singerController.findById(1),"HH",new Date(),true));
        songController.save(new Song("S002","Đi chơi","hết tiền",singerController.findById(2),"HH",new Date(),true));
        songController.save(new Song("S003","Đi nhậu","say", singerController.findById(2),"HH",new Date(),true));
//        Config.scanner().nextInt()
//        Singer[] sings = {new Singer(1,"Hùng",19,"VN",true,"Nhạc vàng"),
//                new Singer(2,"Nam",19,"VN",true,"Nhạc Xanh")};
//        Song[] songs = {new Song("S001","Đi học","sss",null,"HH",new Date(),true),};
//        Song newSong  = new Song();
//        newSong.inputData(songs,sings);

        // xay menu
        while (true) {
            System.out.println("******************MUSIC-MANAGEMENT******************\n" +
                    "1. Quản lý ca sĩ [20 điểm]\n" +
                    "2. Quản lý bài hát [20 điểm]\n" +
                    "3. Tìm kiếm bài hát [25 điểm]\n" +
                    "4. Thoát [5 điểm]");
            System.out.println("Nhập vào  lựa chọn của bạn");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    //menu quản lí ca sĩ
                    menuSingerManager();
                    break;
                case 2:
                    //menu quản lí bài hát
                    menuSongManager();
                    break;
                case 3:
                    // menu tìm kiếm
                    menuSearchManager();
                    break;
                case 4:
                    // thoát
                    System.exit(0);
                    break;
                default:
                    System.err.println("Phải nhâp số từ 1 đến 4");
            }
        }

    }

    // menu quản lí ca sĩ
    public static void menuSingerManager() {
        byte choice = 0;
        while (choice != 5) {
            System.out.println("**********************SINGER-MANAGEMENT*************************\n" +
                    "1.Nhập vào số lượng ca sĩ cần thêm và nhập thông tin cần thêm mới (có validate dữ\n" +
                    "liệu nhập vào) [10 điểm]\n" +
                    "2.Hiển thị danh sách tất cả ca sĩ đã lưu trữ [5 điểm]\n" +
                    "3.Thay đổi thông tin ca sĩ theo mã id [10 điểm]\n" +
                    "4.Xóa ca sĩ theo mã id (kiểm tra xem nếu ca sĩ có bài hát thì không xóa được)[5 điểm]\n" +
                    "5.Thoát\n");
            System.out.println("Nhập vào  lựa chọn của bạn");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    // thêm mới n ca sĩ
                    createNewSinger();
                    break;
                case 2:
                    // hiển thị
                    showListSinger();
                    break;
                case 3:
                    // thay đổi thông tin theo mã ca sĩ
                    editSingerInfo();
                    break;
                case 4:
                    // xóa
                   deleteSinger();
                    break;
                case 5:
                    // quay lại
                    break;
                default:
                    System.err.println("Phải nhâp số từ 1 đến 4");
                    break;
            }
        }

    }

    // thêm mới ca sĩ
    public static void createNewSinger() {
        // nhập vào số lượng ca sĩ cần thêm mới
        int size = singerController.getSize();
        // lấy ra số lượng tối đâ có thể thêm mới
        int maxInput = singerController.getAll().length - size;
        System.out.println("Nhập vào số lượng cần thêm mới");
        int n;
        while (true) {
            n = InputMethods.getPositiveInteger();
            if (n > maxInput) {
                System.err.println("Số lượng phần tử quá lớn vui lòng nhập lại");
            } else {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho ca sĩ thứ " + (i + 1));
            Singer newSinger = new Singer();
            // id tự tăng
            int newId = singerController.getNewId();
            newSinger.setSingerId(newId);
            System.out.println("New ID : " + newId);
            // nhập nhưngx thông tin còn lại
            newSinger.inputData();
            // tiến hành them mới vào mảng
            singerController.save(newSinger);
        }
    }

    // hiển thị danh sách ca sĩ
    public static void showListSinger() {
        if (singerController.getSize() == 0) {
            System.err.println("Không có ca sĩ nào ");
            return;
        }

        for (Singer singer : singerController.getAll()
        ) {
            if (singer != null) {
                singer.displayData();
            }
        }
    }

    // up date thông tin
    public static void editSingerInfo() {
        System.out.println("Nhập vào id cần sửa");
        int idEdit = InputMethods.getInteger();
        Singer editSinger = singerController.findById(idEdit);
        if(editSinger ==null){
            System.err.println("Không tìm thấy ca sĩ ");
            return;
        }
        // cho phép sửa
        editSinger.inputData();
        singerController.save(editSinger);
    }
    public static void deleteSinger(){
        System.out.println("nhập vào id cần xóa ");
        int idDel = InputMethods.getInteger();
        singerController.deleteSinger(idDel);
    }


    public static void menuSongManager() {
        byte choice = 0;
        while (choice != 5) {
            System.out.println("**********************SONG-MANAGEMENT*************************\n" +
                    "1.Nhập vào số lượng bài hát cần thêm và nhập thông tin cần thêm mới (có validate dữ\n" +
                    "liệu nhập vào) [10 điểm]\n" +
                    "2.Hiển thị danh sách tất cả bài hát đã lưu trữ [5 điểm]\n" +
                    "3.Thay đổi thông tin bài hát theo mã id [10 điểm]\n" +
                    "4.Xóa bài hát theo mã id [5 điểm]\n" +
                    "5.Thoát");
            System.out.println("Nhập vào  lựa chọn của bạn");
            choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    // thêm mới n ca sĩ
                    createNewSong();
                    break;
                case 2:
                    // hiển thị
                    showListSong();
                    break;
                case 3:
                    // thay đổi thông tin theo mã ca sĩ
                    editSongInfo();
                    break;
                case 4:
                    // xóa
                    deleteSong();
                    break;
                case 5:
                    // quay lại
                    break;
                default:
                    System.err.println("Phải nhâp số từ 1 đến 4");
                    break;
            }
        }

    }

    // thêm mới bài hát
    public static void createNewSong() {
        // nhập vào số lượng cbài hát cần thêm mới
        // lưu ý , phải có ca sĩ rồi ms thêm được bài hát
        if (singerController.getSize() == 0) {
            System.err.println("Không có ca sĩ nào , vui lóng quay ại thêm ca sĩ trước");
            return;
        }
        int size = songController.getSize();
        // lấy ra số lượng tối đâ có thể thêm mới
        int maxInput = songController.getAll().length - size;
        System.out.println("Nhập vào số lượng cần thêm mới");
        int n;
        while (true) {
            n = InputMethods.getPositiveInteger();
            if (n > maxInput) {
                System.err.println("Số lượng phần tử quá lớn vui lòng nhập lại");
            } else {
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin cho bai hat thứ " + (i + 1));
            Song newSong = new Song();
            // nhập tat thông tin còn lại
            newSong.inputData(songController.getAll(), singerController.getAll());
            // tiến hành them mới vào mảng
            songController.save(newSong);
        }
    }

    // hiển thị danh sách ca sĩ
    public static void showListSong() {
        if (songController.getSize() == 0) {
            System.err.println("Không có bai hat nào ");
            return;
        }

        for (Song song : songController.getAll()
        ) {
            if (song != null) {
                song.displayData();
            }
        }
    }

    // up date thông tin
    public static void editSongInfo() {
        System.out.println("Nhập vào id cần sửa");
        String idEdit = InputMethods.getString();
        Song editSong = songController.findById(idEdit);
        if(editSong ==null){
            System.err.println("Không tìm thấy ca sĩ ");
            return;
        }

        // cho phép sửa
        editSong.inputDataNotId( singerController.getAll());
        songController.save(editSong);
    }
    public static void deleteSong(){
        System.out.println("nhập vào id cần xóa ");
        String idDel = InputMethods.getString();
        songController.deleteSong(idDel);
    }

   static private String getKeyword(){
        System.out.println("nhập keyword:");
        return InputMethods.getString();
    }

    public static void menuSearchManager() {
        int choice;
        while (true){
            System.out.println("1.Tìm kiếm bài hát theo tên ca sĩ hoặc thể loại . [5 điểm]\n" +
                    "2.Tìm kiếm ca sĩ theo tên hoặc thể loại [5 điểm]\n" +
                    "3.Hiển thị danh sách bài hát theo thứ tự tên tăng dần [5 điểm]\n" +
                    "4.Hiển thị 10 bài hát được đăng mới nhất [10 điểm]\n" +
                    "5.Thoát");
            choice = InputMethods.getInteger();
            switch (choice){
                case 1:
                    searchSongBySingerNameOrGenre();
                    break;
                case 2:
                    getSingerByNameOrGenre();
                    break;
                case 3:
                    sortSongByName();
                    break;
                case 4: sortSongByDate();
                    break;
                case 5:
                   return;
            }
        }
    }

    private static void sortSongByName(){
        Song[] song = songController.getAll(); // lấy danh sách tất cả bài hát
        Song[] newSong = searchController.sortSongByName(song);
        /** hiển thị */
        for (Song s: newSong) {
            if (song != null){
                s.displayData();
            }
        }
    }

    public static void sortSongByDate(){
        Song[] song = songController.getAll(); // lấy danh sách tất cả bài hát

        Song[] newSong = searchController.sortSongByDate(song);
        for (Song s: newSong
             ) {
            s.displayData();
        }
    }


        private static void getSingerByNameOrGenre() {
        String keyword = getKeyword();
        Singer[] singers = singerController.getAll();
        Singer[] result = searchController.findSingerByNameOrGenre(keyword, singers);
        for (Singer s: result) {
            if (s != null){
                s.displayData();
            }
        }
    }

    private static void searchSongBySingerNameOrGenre() {
        Song[] songs = songController.getAll();
        String keyword = getKeyword();
        Song[] result = searchController.searchBySingerGenre(keyword, songs);
        System.out.println("kết quả tìm kiếm: ");
        for (Song song: result) {
            if (song != null){
                song.displayData();
            }
        }
    }

}
