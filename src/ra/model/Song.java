package ra.model;

import ra.config.InputMethods;

import java.util.Date;

public class Song implements Comparable<Song> {
    private String songId;
    private  String songName;
    private  String descriptions;
    private Singer singer;
    private  String songWriter;
    private Date createdDate = new Date();

    private boolean songStatus =true;

    public Song() {
    }

    public Song(String songId, String songName, String descriptions, Singer singer, String songWriter, Date createdDate, boolean songStatus) {
        this.songId = songId;
        this.songName = songName;
        this.descriptions = descriptions;
        this.singer = singer;
        this.songWriter = songWriter;
        this.createdDate = createdDate;
        this.songStatus = songStatus;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getSongWriter() {
        return songWriter;
    }

    public void setSongWriter(String songWriter) {
        this.songWriter = songWriter;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isSongStatus() {
        return songStatus;
    }

    public void setSongStatus(boolean songStatus) {
        this.songStatus = songStatus;
    }
    public void inputData(Song[] songs, Singer[] singers){
        System.out.println("Nhập vào Id : (bắt đầu chữ S và có 4 kí tự)");
        // kiểm tra trùng
        while (true){
            String songId = InputMethods.getSongId();
            boolean flag =true;
            for (Song song: songs) {
                if(song!=null&&song.getSongId().equals(songId)){
                    System.err.println("Id đã tồn tại vui lòng thử Id khác");
                    flag= false;
                    break;
                }
            }
            if (flag){
                this.songId = songId;
                break;
            }
        }
        System.out.println("nhập tên bài hát");
        this.songName = InputMethods.getString();
        System.out.println("Nhập mô tả");
        this.descriptions = InputMethods.getInput();
        // chọn ca sĩ từ danh sách;
        // hiển thị danh sách ca sĩ
        for (Singer singer: singers) {
            if(singer!=null){
                System.out.println("----------------------------------------------------------------");
                System.out.println("ID : "+singer.getSingerId() + " | Name : "+ singer.getSingerName());
            }
        }
        // cho phép người dùng lựa chọn theo id
        System.out.println("Nhập vào id cua ca sĩ");
        while (true){
            int idSinger = InputMethods.getInteger();
            boolean flag =true;
            for (Singer sing: singers) {
                if(sing!=null&&sing.getSingerId()==idSinger){
                    flag= false;
                    this.singer= sing;
                    break;
                }
            }
            // kiểm tra cờ
            if(flag){
                System.err.println("id không tồn tại, vui lòng nhập lại");
            }else {
                break;
            }
        }
        System.out.println("Nhập nhạc sĩ");
        this.songWriter= InputMethods.getString();

    }
    public void inputDataNotId( Singer[] singers){
        System.out.println("nhập tên bài hát");
        this.songName = InputMethods.getString();
        System.out.println("Nhập mô tả");
        this.descriptions = InputMethods.getInput();
        // chọn ca sĩ từ danh sách;
        // hiển thị danh sách ca sĩ
        for (Singer singer: singers) {
            if(singer!=null){
                System.out.println("----------------------------------------------------------------");
                System.out.println("ID : "+singer.getSingerId() + " | Name : "+ singer.getSingerName());
            }
        }
        // cho phép người dùng lựa chọn theo id
        System.out.println("Nhập vào id cua ca sĩ");
        while (true){
            int idSinger = InputMethods.getInteger();
            boolean flag =true;
            for (Singer sing: singers) {
                if(sing!=null&&sing.getSingerId()==idSinger){
                    flag= false;
                    this.singer= sing;
                    break;
                }
            }
            // kiểm tra cờ
            if(flag){
                System.err.println("id không tồn tại, vui lòng nhập lại");
            }else {
                break;
            }
        }
        System.out.println("Nhập nhạc sĩ");
        this.songWriter= InputMethods.getString();
    }
    public void displayData(){
        System.out.println("---------------------------------------");
        System.out.println("ID: "+ songId + " | Name : "+songName);
        System.out.println("SingerName: "+singer.getSingerName() + " |Descriptions: "+ descriptions);
        System.out.println("SongWriter "+ songWriter + " | Status : "+(songStatus?"Hoạt động":"Không họat động"));
        System.out.println("CreatedDate : " + createdDate);
    }


    @Override
    public int compareTo(Song other) {
        if (this.songName == null && other.songName == null) {
            return 0;
        } else if (this.songName == null) {
            return -1;
        } else if (other == null || other.songName == null) {
            return 1;
        } else {
            return this.songName.compareTo(other.songName);
        }
    }

}
