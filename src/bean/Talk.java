package bean;

import java.util.Date;
import java.util.Map;

public class Talk {
    String id ;
    String time ;
    String content;
    String did ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Talk(Map<String, Object> map) {
        this.time = (String) map.get("time");
        this.content = (String) map.get("content");
        this.id = String.valueOf(map.get("id")) ;
    }
    public Talk(String content,String did) {
        this.content = content;
        this.did = did;
    }
}
