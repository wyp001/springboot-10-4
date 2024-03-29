package com.example.springboot104.pojo;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
public class ValidatorPojo {

    //非空判断
    @NotNull(message = "id不能为空")
    private int id;

    @Future(message = "需要一个将来的日期")//只能是将来的日期
    //@past  //只能是过去的日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;

    @NotNull // 不能为空
    @DecimalMin(value = "0.1") // 最小值0.1元
    @DecimalMax(value = "10000.00") // 最大值10000元
    private Double doubleValue = null;

    @Min(value = 1, message = "最小值为1") // 最小值为1
    @Max(value = 88, message = "最大值为88") // 最大值88
    @NotNull // 不能为空
    private Integer integer;

    @Range(min = 1, max = 888, message = "范围为1至888") // 限定范围
    private Long range;

    // 邮箱验证
    @Email(message = "邮箱格式错误")
    private String email;

    @Size(min = 20, max = 30, message = "字符串长度要求20到30之间。")
    private String size;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
