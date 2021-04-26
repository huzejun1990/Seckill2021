package com.dream.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: huzejun
 * @Date: 2021/4/4 16:45
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    /**
     * 所有属性都应该在数据库中
     */
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String password;

    //以下是数据库字段
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
