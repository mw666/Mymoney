package com.tuofu.lenovo.myone;

/**
 * Created by lenovo on 2017/5/9.
 */

public class model {
    int pic;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    int type;

    public model(int pic, int type, String context) {
        this.pic = pic;
        this.type = type;
        this.context = context;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    String context;
    
}
