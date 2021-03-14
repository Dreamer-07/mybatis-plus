package pers.dreamer07;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

/**
 * @program: mybatis-plus
 * @description: 代码自动生成器
 * @author: EMTKnight
 * @create: 2021-03-14
 **/
@SpringBootTest
public class CodeGenerator {

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {
        // 1 创建代码自动生成器对象
        AutoGenerator ag = new AutoGenerator();

        // 2 创建全局配置对象
        GlobalConfig gc = new GlobalConfig();
        // 2.1 设置代码生成位置
        String projectPath = System.getProperty("user.dir"); // 获取当前项目的路径位置
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("Dreamer07"); // 设置作者
        gc.setOpen(false); // 不打开 Win 资源管理器
        ag.setGlobalConfig(gc);

        // 3 设置数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        ag.setDataSource(dsc);
    }

}
