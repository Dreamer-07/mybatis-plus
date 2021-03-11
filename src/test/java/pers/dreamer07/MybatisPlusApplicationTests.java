package pers.dreamer07;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.dreamer07.mapper.UserMapper;
import pers.dreamer07.pojo.User;

import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    // 注入 Mapper 组件
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        // 通过 Mapper 组件查询数据, 需要传入一个 queryWrapper(条件封装器)，不需要时传入 null 即可
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("巴御前");
        user.setAge(16);
        user.setEmail("918756117@qq.com");
        int result = userMapper.insert(user);
        System.out.println("返回的结果：" + result);
        System.out.println(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1369927440509964292L);
        user.setName("巴御前");
        user.setEmail("111222@qq.com");
        int result = userMapper.updateById(user); // 根据 ID 修改对应对象在数据库中的记录
        System.out.println("影响的行数：" + result);
    }

    // 测试使用成功国的乐观锁
    @Test
    public void testOptimisticLockerInner01(){
        //1. 查询对应的数据
        User user = userMapper.selectById(1L);
        //2. 修改部分属性
        user.setName("巴御前");
        user.setAge(16);
        //3. 修改数据库中的数据
        userMapper.updateById(user);
    }


}
