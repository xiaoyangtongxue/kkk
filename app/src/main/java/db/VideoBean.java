package db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xsj on 2017/12/30.
 */

@Entity
public class VideoBean {
    @Id
    private Long id;

    private String name;
    private String movieid;
    private String moviepic;
    public String getMoviepic() {
        return this.moviepic;
    }
    public void setMoviepic(String moviepic) {
        this.moviepic = moviepic;
    }
    public String getMovieid() {
        return this.movieid;
    }
    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 1055405745)
    public VideoBean(Long id, String name, String movieid, String moviepic) {
        this.id = id;
        this.name = name;
        this.movieid = movieid;
        this.moviepic = moviepic;
    }
    @Generated(hash = 2024490299)
    public VideoBean() {
    }
}
