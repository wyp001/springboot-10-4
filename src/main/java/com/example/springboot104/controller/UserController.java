package com.example.springboot104.controller;

import com.example.springboot104.pojo.User;
import com.example.springboot104.validator.UserValidator;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    /**
     * 调用控制器前先执行这个方法
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // 绑定验证器
        binder.setValidator(new UserValidator());
        // 定义日期参数格式，参数不再需注解@DateTimeFormat，boolean参数表示是否允许为空
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"), false));
    }

    /**
     *
     * @param user
     *            -- 用户对象用StringToUserConverter转换
     * @param Errors
     *            --验证器返回的错误
     * @param date
     *            -- 因为WebDataBinder已经绑定了格式，所以不再需要注解
     * @return 各类数据
     */

    @GetMapping("/validator")
    @ResponseBody
    public Map<String, Object> validator(@Valid User user, Errors Errors, Date date) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("date", date);
        // 判断是否存在错误
        if (Errors.hasErrors()) {
            // 获取全部错误
            List<ObjectError> oes = Errors.getAllErrors();
            for (ObjectError oe : oes) {
                // 判定是否字段错误
                if (oe instanceof FieldError) {
                    // 字段错误
                    FieldError fe = (FieldError) oe;
                    map.put(fe.getField(), fe.getDefaultMessage());
                } else {
                    // 对象错误
                    map.put(oe.getObjectName(), oe.getDefaultMessage());
                }
            }
        }
        return map;
    }



}
