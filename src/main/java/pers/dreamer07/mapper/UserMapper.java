package pers.dreamer07.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pers.dreamer07.pojo.User;

/**
 * @program: mybatis-plus
 * @description: 实现 BaseMapper<T> 的子接口，用来定义对应泛型 T 的 DAO 操作
 *  基本的 CRUD 操作会由 BaseMapper 接口来自动帮助我们实现
 * @author: EMTKnight
 * @create: 2021-03-11
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
}
