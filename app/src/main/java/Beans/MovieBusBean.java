package Beans;

/**
 * Created by xsj on 2018/1/2.
 */

public class MovieBusBean {
    private String moviename;
    private String movieid;
    private String moviepic;

    public MovieBusBean(String moviename, String movieid, String moviepic) {
        this.moviename = moviename;
        this.movieid = movieid;
        this.moviepic = moviepic;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public String getMovieid() {
        return movieid;
    }

    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }

    public String getMoviepic() {
        return moviepic;
    }

    public void setMoviepic(String moviepic) {
        this.moviepic = moviepic;
    }


}
