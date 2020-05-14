package com.gingost.website.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author:lezzy
 * @Date:2020/4/6 15:21
 */
@Data
@NoArgsConstructor
public class ResponseEntity {
    public Integer code = 0;
    public String msg;
    private Object data;

    public ResponseEntity(String msg) {
        this.msg = msg;
    }

    public ResponseEntity(Object data) {
        this.data = data;
    }

    public ResponseEntity(Object data, String msg) {
        this.data = data;
        this.msg = msg;
    }

    public ResponseEntity(Exception e) {
        this.code = 1;
        this.msg = e.getMessage();
    }
}
