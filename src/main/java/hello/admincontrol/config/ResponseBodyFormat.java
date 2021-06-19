package hello.admincontrol.config;

import com.fasterxml.jackson.databind.JsonNode;


public class ResponseBodyFormat {
    private JsonNode data;
    public JsonNode getData() {
        return this.data;
    }
    public void setData(JsonNode data) {
        this.data = data;
    }

    private String msg;
    public String getMsg() {
        return this.msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int code;
    public int getCode() {
        return this.code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public ResponseBodyFormat(int httpstatus, String message, JsonNode json) {
        this.code = 0;
        if(httpstatus >= 400) {
            this.code = httpstatus;
        }
        this.msg = message;
        this.data = json;
    }
}

