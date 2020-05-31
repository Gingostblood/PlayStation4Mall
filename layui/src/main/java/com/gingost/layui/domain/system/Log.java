package com.gingost.layui.domain.system;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Lezzy
 * @description 日志实体
 * @date 2020/4/27 22:17
 */
@Table(name = "sys_log")
@Entity
@Data
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 描述
     */
    private String description;

    /**
     * 方法名
     */
    private String method;

    /**
     * 参数
     */
    @Column(columnDefinition = "text")
    private String params;

    @Column
    private String source;

    /**
     * 日志类型
     */
    @Column(name = "log_type")
    private String logType;

    /**
     * 请求ip
     */
    @Column(name = "request_ip")
    private String requestIp;
    /**
     * 请求耗时
     */
    private Long time;

    /**
     * 异常详细
     */
    @Column(name = "exception_detail", columnDefinition = "text")
    private byte[] exceptionDetail;

    /**
     * 创建日期
     */
    @CreationTimestamp
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createTime;

    public Log(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }
}
