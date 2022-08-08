package fun.sssdnsy.melon.util;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @desc
 * @class CodeGenerator
 * @since 2022-08-08
 */
public class CodeGenerator {

    public static void main(String[] args) {
        new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/test", "root", "123456")
                .build();

        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/test", "root", "imi123")
                .globalConfig(builder -> {
                    builder.author("") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E:\\source\\melon\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("fun.sssdnsy.melon") // 设置父包名
                            .entity("dao.entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("dao.mapper")
                            .xml("mapper")
                            .controller("controller")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "E:\\source\\melon\\src\\main\\resources\\")); // 设置mapperXml生成路径
                }).templateConfig(build->{
                     build.entity("/templates/entity.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                            .controller("/templates/controller.java")
                            .build();
                })
                .strategyConfig((scanner, builder) -> {


                    builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                            .addTablePrefix("t_", "c_")
                            .entityBuilder().enableLombok().addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("update_time", FieldFill.INSERT)
                            ).

                            entityBuilder()
                            .disableSerialVersionUID()
                            .enableChainModel()
                            .enableLombok()
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()
                            .enableActiveRecord()
                            .versionColumnName("version")
                            .versionPropertyName("version")
                            .logicDeleteColumnName("deleted")
                            .logicDeletePropertyName("deleteFlag")
                            .naming(NamingStrategy.no_change)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
                            .addIgnoreColumns("age")
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                            .idType(IdType.ASSIGN_ID)
                            .formatFileName("%sEntity")

                            .controllerBuilder()
                            .enableHyphenStyle()
                            .enableRestStyle()
                            .formatFileName("%sController")
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sImpl")

                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .enableMapperAnnotation()
                            .enableBaseResultMap()
                            .enableBaseColumnList()
                            .formatMapperFileName("%sDao")
                            .build();

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
