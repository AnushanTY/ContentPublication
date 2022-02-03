package com.zerobeta.contentpublication.domain;

public class Response {

    private ResponseEnum status;
    private Object object;

    public Response(ResponseEnum status, Object object) {
        this.status = status;
        this.object = object;
    }

    public static Response success(Object data){
        return new Response(ResponseEnum.OK, data);

    }

    public static Response failure(Object data){
        return new Response(ResponseEnum.FAILURE, data);

    }

    public ResponseEnum getStatus() {
        return status;
    }

    public void setStatus(ResponseEnum status) {
        this.status = status;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
