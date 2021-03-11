package pers.dreamer07.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @program: mybatis-plus
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-11
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /*
    * ASSIGN_ID：指定生成主键策略时'全局唯一ID'
    * AUTO: 自增 ID
    * INPUT：手动输入 ID
    * */
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String email;

    /* @TableField(fill..) fill 属性设置该对象顺序的填充策略
    *   INSERT：创建时填充
    *   INSERT_UPDATE：创建 & 修改时填充
    * */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @Version
    private Integer version; // 乐观锁
}
