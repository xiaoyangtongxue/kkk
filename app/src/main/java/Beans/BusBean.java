package Beans;

/**
 * Created by xsj on 2017/12/15.
 */

public class BusBean {

    private String id;
    private Object o;

    public BusBean(String id, Object o) {
        this.id = id;
        this.o = o;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }
}
