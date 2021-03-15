package pers.dreamer07;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.mysql.cj.jdbc.Driver;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @program: mybatis-plus
 * @description: 代码自动生成器
 * @author: EMTKnight
 * @create: 2021-03-14
 **/
@SpringBootTest
public class CodeGenerator {

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
        gc.setFileOverride(false); // 设置不覆盖已有文件
        gc.setServiceName("%sService"); // 去除 Service 接口开头的 I
        gc.setIdType(IdType.AUTO); // 设置主键策略
        ag.setGlobalConfig(gc);

        // 3 设置数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUsername("root");
        dsc.setPassword("Dreamer07");
        dsc.setUrl("jdbc:p6spy:mysql://localhost:3306/mybatis_plus?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC");
        dsc.setDriverName("com.p6spy.engine.spy.P6SpyDriver");
        ag.setDataSource(dsc);


        // 4. 策略配置
        StrategyConfig sc = new StrategyConfig();
        // 下划线转驼峰命名
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        // setInclude() 设置要为哪些数据表生成实体
        sc.setInclude("user");
        // setEntityLombokModel() 设置是否使用 Lombok 注解
        sc.setEntityLombokModel(true);
        // setControllerMappingHyphenStyle() 开启 Controller 请求映射的下划线命名
        sc.setControllerMappingHyphenStyle(true);
        // setEntityTableFieldAnnotationEnable() 生成字段注解
        sc.setEntityTableFieldAnnotationEnable(true);
        // setVersionFieldName() 设置乐观锁字段名
        sc.setVersionFieldName("version");
        // setLogicDeleteFieldName() 设置逻辑删除字段名
        sc.setLogicDeleteFieldName("deleted");
        // setTableFillList() 设置填充字段集合
        sc.setTableFillList(Arrays.asList(
                new TableFill("create_time", FieldFill.INSERT),
                new TableFill("update_time", FieldFill.INSERT_UPDATE)
        ));
        ag.setStrategy(sc);

        // 5. 配置包信息
        PackageConfig pc = new PackageConfig();
        // 设置父包
        pc.setParent("pers.dreamer07");
        // 设置模块名
        pc.setModuleName("cg");
        pc.setEntity("pojo");
        ag.setPackageInfo(pc);


        // 5. 执行代码自动生成器
        ag.execute();
    }


}
