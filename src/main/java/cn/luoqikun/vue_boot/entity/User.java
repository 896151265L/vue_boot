package cn.luoqikun.vue_boot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)  //链式编程支持
public class User extends BaseEntity {

    private String userName; //用户名
    private String account;  //账号
    private String passWord; //密码
    private String idCardNo; //身份证号码
    private String sex;      //性别
    private String mobile;   //联系方式

}
