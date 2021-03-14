package pers.dreamer07;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.dreamer07.mapper.UserMapper;
import pers.dreamer07.pojo.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    // 注入 Mapper 组件
    @Autowired
    private UserMapper userMapper;

    @Test
    public void     test01(){
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

    // 测试使用成功的乐观锁
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

    // 测试使用失败的乐观锁
    @Test
    public void testOptimisticLockerInner02(){
        // 1. (线程1)先查询一个用户
        User user = userMapper.selectById(2);
        // 2. (线程1)修改部分属性
        user.setName("A");

        // 3. (线程2) 再获取一次相同数据
        User user2 = userMapper.selectById(2);
        user2.setName("B");
        // 4. (线程2)修改数据库
        userMapper.updateById(user2);

        // 5. (线程1)再修改第一个线程的数据
        userMapper.updateById(user);
    }

    /**
     * 测试根据多个 id 查询多个用户
     */
    @Test
    public void selectByIds(){
        // 使用 selectBatchIds() 方法，该方法接受对应的主键(id)集合
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        users.forEach(System.out::println);
    }

    /**
     * 条件查询方式一：使用 map 封装对应的条件
     */
    @Test
    public void selectByCondition(){
        // 1. 定义存储条件的 Map，并添加对应的条件
        HashMap<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("name", "巴御前");
        conditionMap.put("age", 28);

        // 2. 通过 selectByMap 查询结果 - 会自动帮我们动态拼接 sql
        List<User> users = userMapper.selectByMap(conditionMap);
        users.forEach(System.out::println);
    }

    /**
     * 测试分页查询
     */
    @Test
    public void testSelectByPage(){
        // 1. 创建一个 Page(分页对象): 第一个参数时当前页数，第二个参数为当前页大小
        Page<User> userPage = new Page<>(0, 3);
        // 2. 通过 selectPage 方法查询
        userMapper.selectPage(userPage, null);
        // 3. 查看分页数据
        userPage.getRecords().forEach(System.out::println);
    }

    /**
     * 测试基本删除
     */
    @Test
    public void testDelete(){
        // 1. 根据 ID 删除
        userMapper.deleteById(1369927440509964294L);
//        // 2. 批量删除
//        userMapper.deleteBatchIds(Arrays.asList(1, 2, 4));
//        // 3. 条件删除
//        HashMap<String, Object> conditionMap = new HashMap<>();
//        conditionMap.put("name", "a");
//        userMapper.deleteByMap(conditionMap);
    }

}
