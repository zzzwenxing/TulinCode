package com.macro.mall.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * ,;,,;
 * ,;;'(    社
 * __      ,;;' ' \   会
 * /'  '\'~~'~' \ /'\.)  主
 * ,;(      )    /  |.     义
 * ,;' \    /-.,,(   ) \    码
 * ) /       ) / )|    农
 * ||        ||  \)
 * (_\       (_\
 *
 * @author ：杨过
 * @date ：Created in 2020/3/22
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
@Data
public class Register {

    @NotBlank(message = "电话号码不允许为空")
    @Length(max = 11,min = 11,message = "电话必须是11字符")
    @Pattern(regexp = "^1[3|4|5|8][0-9]\\d{8}$",message = "电话号码格式不正确")
    private String phone;

    @NotBlank(message = "动态校验码不允许为空")
    @Length(max = 6,min = 6,message = "校验码必须是6字符")
    private String otpCode;

    @NotBlank(message = "用户名不允许为空")
    @Length(max = 20,min = 4,message = "用户名长度必须在4-20字符之间")
    private String username;

    @NotBlank(message = "密码不允许为空")
    @Length(max = 20,min = 8,message = "密码长度必须在8-20字符之间")
    private String password;

}
