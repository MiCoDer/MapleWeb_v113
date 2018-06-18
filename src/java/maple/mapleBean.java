package maple;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "mapleBean")
@SessionScoped
public class mapleBean implements Serializable {

    private String id;
    private String name;
    private int level;
    private int fame;
    private int meso;

    public ArrayList<mapleBean> getCHRList() {
        System.out.println("呼叫getCHRList()");
        return DBOperation.getCHRList();
    }

    public void reset() {
        this.id = null;
        this.name = null;
        this.level = 0;
        this.fame = 0;
        this.meso = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFame() {
        return fame;
    }

    public void setFame(int fame) {
        this.fame = fame;
    }

    public int getMeso() {
        return meso;
    }

    public void setMeso(int meso) {
        this.meso = meso;
    }

    //先做測試
    public static void main(String[] args) {
        mapleBean b = new mapleBean();
        for (mapleBean o : b.getCHRList()) {
            System.out.println(o.getId());
        }

    }
}
