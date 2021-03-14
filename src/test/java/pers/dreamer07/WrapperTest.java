package pers.dreamer07;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.dreamer07.mapper.UserMapper;
import pers.dreamer07.pojo.User;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Wapper 条件构造器测试
 * @program: mybatis-plus
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-14
 **/
@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试 Is Not Null & gt(大于)
     */
    @Test
    public void test01(){
        // 1. 创建查询条件构造器实例： QueryWrapper<T> T 为查询的结果对应的实体类
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 2. 通过链式调用添加查询条件
        wrapper
            .isNotNull("name")
            .isNotNull("email")
            .gt("age", 12);
        // 3. 通过 Mapper 查询时穿入 Wrapper
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    /**
     * 测试 eq(等于)
     */
    @Test
    public void test02(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "阿巴巴巴");
        // selectOne(): 只查询一条记录，如果有多条记录就会报错
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    /**
     * 测试 between(区间查询)
     */
    @Test
    public void test03(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age", 20, 30);
        Integer count = userMapper.selectCount(wrapper);
        System.out.println("用户个数：" + count);
    }

    /**
     * 测试 Like 模糊查询
     */
    @Test
    public void test04(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        /*
        * like() 、notLike -> %m%
        * leftLike() -> %m
        * rightLike() -> m%
        * */
        wrapper
            .notLike("name", "阿")
            .likeRight("email", "test");
        // userMapper.selectMaps() 将查询的结果封装为一个 Map，该 Map 的 key 为对应的字段，value 为对应的值
        List<Map<String, Object>> mapList = userMapper.selectMaps(wrapper);
        mapList.forEach((stringObjectMap) -> {
            for (String key : stringObjectMap.keySet()) {
                System.out.println(key + ":" + stringObjectMap.get(key));
            }
        });
    }

    /**
     * 测试 IN 子查询
     */
    @Test
    public void test05(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 也可以使用普通的 in(R column, Collection<?> value) 方法传入一个数据集合
        wrapper.inSql("id", "SELECT id FROM user WHERE id > 3");
        userMapper.selectObjs(wrapper).forEach(System.out::println);
    }

    /**
     * 测试 Order 排序查询
     */
    @Test
    public void test06(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        userMapper.selectList(wrapper).forEach(System.out::println);
    }
}
