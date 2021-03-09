package org.wzx.test.mybatisplustest;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.setting.dialect.Props;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import javax.tools.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;

/**
 * @author: 鱼头
 * @description: 代码自动生成器
 * @time: 2020/5/20 9:26
 */
public class CodeGenerator {
    public static void main(String[] args) throws Exception {
        //填写要生成的表名
        String[] table = null;
        table = new String[]{"user_key"};
        table = new String[]{"trade_type", "bourse_info", "currency_info", "entrust_type", "bourse_trade"};
//        table = new String[]{"entrust_type"};

        //创建生成器及获取项目基础信息
        AutoGenerator autoGenerator = new AutoGenerator();
        Class cl = null;
        String srcPath = "/src/main/java/";
        String packagaName = ClassUtil.getPackage(CodeGenerator.class);
        String projectPath = System.getProperty("user.dir");
        String packagePath = ClassUtil.getPackagePath(CodeGenerator.class);
        String classFilePath = projectPath + srcPath + packagePath + "/";
        String templatePath = projectPath + "/src/main/resources/templates/";

        //生成基础文件
        String[] filePaths = new String[]{
                "config/MyConfig.java",
                "entity/base/CommonEntity.java",
                "entity/base/Result.java",
                "service/BaseService.java",
                "controller/base/BaseController.java"
        };
        for (int i = 0; i < filePaths.length; i++) {
            File javaFile = new File(classFilePath + filePaths[i]);
            if (!javaFile.exists()) {
                javaFile.getParentFile().mkdirs();
                File file = new File(templatePath + javaFile.getName());
                if (file.exists()) {
                    new FileWriter(javaFile).write(new FileReader(file).readString().replace("xxxxx", packagaName));
                    if (file.getName().equals("CommonEntity.java")) {
                        cl = createClass(new FileReader(javaFile).readString());
                    }
                } else {
                    throw new RuntimeException(javaFile.getName() + "基础文件不存在！");
                }
            }
        }

        // mysql数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        Props props = new Props("db.properties");
        dataSourceConfig.setUrl("jdbc:mysql://" + props.getStr("dbIp") + ":" + props.getStr("dbPort") + "/" + props.getStr("dbName") + "?characterEncoding=utf-8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        // postgre数据源配置
//        dsc.setUrl("jdbc:postgresql://"+props.getStr("dbIp")+":"+ props.getStr("dbPort")+"/"+props.getStr("dbName")+"?stringtype=unspecified");
//        dsc.setDriverName("org.postgresql.Driver");
        dataSourceConfig.setUsername(props.getStr("dbUser"));
        dataSourceConfig.setPassword(props.getStr("dbPwd"));
        autoGenerator.setDataSource(dataSourceConfig);

        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(projectPath + srcPath);//生成文件的输出目录
        globalConfig.setAuthor("鱼头");
        globalConfig.setOpen(false);//是否打开输出目录
        globalConfig.setBaseResultMap(true);
        globalConfig.setSwagger2(true);//使用swagger注解
        globalConfig.setFileOverride(true); //是否覆盖原来的文件（慎用）
        autoGenerator.setGlobalConfig(globalConfig);

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        String packageName = ClassUtil.getPackage(CodeGenerator.class);
        packageConfig.setParent(packageName.substring(0, packageName.lastIndexOf(".")));
        packageConfig.setModuleName(packageName.substring(packageName.lastIndexOf(".") + 1));
        //一旦设置，之前默认的controller就没有了，其它设置也是一样
//        packageConfig.setController("controller.common");
        //指定文件的生成路径,路径一旦配置了一个就要全部配置
//        Map<String, String> pathInfo = new HashMap<>();
        //主包路径
//        String path=projectPath + "/" + moduleName + "/src/main/java/org/wzx/dc/" + moduleName ;
//        pathInfo.put(ConstVal.ENTITY_PATH, path+ "/entity");
//        pathInfo.put(ConstVal.MAPPER_PATH, path+ "/mapper");
//        pathInfo.put(ConstVal.SERVICE_PATH, path+  "/service");
//        pathInfo.put(ConstVal.SERVICE_IMPL_PATH, path+ "/service/impl");
//        pathInfo.put(ConstVal.CONTROLLER_PATH, path+ "/controller");
//        pathInfo.put(ConstVal.XML_PATH, projectPath + "/" + moduleName + "/src/main/resources/mapper");//xml文件路径
//        packageConfig.setPathInfo(pathInfo);
        autoGenerator.setPackageInfo(packageConfig);


        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //对表字段名进行对应注解
        strategyConfig.setEntityTableFieldAnnotationEnable(true);
        strategyConfig.setEntityLombokModel(true);
        //实体父类，cl为null说明已经是第二次生成了
        if (cl == null) {
            cl = Class.forName(packagaName + ".entity.base.CommonEntity");
        }
        strategyConfig.setSuperEntityClass(cl);
        //父类的字段名，一旦设置后，子类将不再出现
        strategyConfig.setSuperEntityColumns("uuid", "create_time", "update_time", "status");
        //只对哪些表生成代码
        if (table == null || table.length == 0) {
            return;
        }
        strategyConfig.setInclude(table);
        //以下是设计什么字段在什么时候才填充，要创建一个类实现
//        List<TableFill> tableFills = new ArrayList<>();
//        TableFill createdTime = new TableFill("createTime", FieldFill.INSERT);
//        TableFill updateTime = new TableFill("updateTime", FieldFill.INSERT_UPDATE);
//        tableFills.add(createdTime);
//        tableFills.add(updateTime);
//        strategyConfig.setTableFillList(tableFills);
        autoGenerator.setStrategy(strategyConfig);

        // 自定义变量，模版里就能引用
//        InjectionConfig injectionConfig = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("baseControllerImport" , packageConfig.getParent() + ".controller.base.BaseController" );
//                map.put("time" , DateUtil.format(new Date(),"HH:mm"));
//                this.setMap(map);
//            }
//        };
//        autoGenerator.setCfg(injectionConfig);

        // 配置各个模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/entity.java.vm");
        templateConfig.setMapper("templates/mapper.java.vm").setXml(null);
        templateConfig.setService("templates/service.java.vm");
        templateConfig.setServiceImpl("templates/serviceImpl.java.vm");
        templateConfig.setController("templates/controller.java.vm");
        autoGenerator.setTemplate(templateConfig);

        //执行生成器
        autoGenerator.execute();
    }

    /**
     * @param source
     * @return
     * @throws Exception
     * @方法说明:动态生成java类,不生成中间文件
     * @创建时间:2019年9月19日 下午4:45:48
     * @创建者:韦忠幸
     * @修改时间:
     * @修改人员:
     * @修改说明:
     */
    public static Class<?> createClass(String source) throws Exception {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(null, null, null);
        ClassJavaFileManager classJavaFileManager = new ClassJavaFileManager(standardFileManager);
        String className = null;
        String[] cs = source.split("\\s+");
        String packagePath = "";
        for (int i = 0; i < cs.length - 1; i++) {
            if (cs[i].equals("package")) {
                packagePath = cs[i + 1].replace(";", ".");
            }
            if (cs[i].equals("class")) {
                className = cs[i + 1];
                if (className.contains("{")) {
                    className = className.substring(0, className.indexOf("{"));
                }
                break;
            }
        }
        StringObject stringObject = new StringObject(new URI(className + ".java"), JavaFileObject.Kind.SOURCE, source);
        JavaCompiler.CompilationTask task = compiler.getTask(null, classJavaFileManager, null, null, null,
                Arrays.asList(stringObject));
        if (task.call()) {
            ClassJavaFileObject javaFileObject = classJavaFileManager.getClassJavaFileObject();
            ClassLoader classLoader = new MyClassLoader(javaFileObject);
            return classLoader.loadClass(packagePath + className);
        }
        return null;
    }

    /**
     * 自定义fileManager
     */
    public static class ClassJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {
        private ClassJavaFileObject classJavaFileObject;

        public ClassJavaFileManager(JavaFileManager fileManager) {
            super(fileManager);
        }

        public ClassJavaFileObject getClassJavaFileObject() {
            return classJavaFileObject;
        }

        /**
         * 这个方法一定要自定义
         */
        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind,
                                                   FileObject sibling) throws IOException {
            return (classJavaFileObject = new ClassJavaFileObject(className, kind));
        }
    }

    /**
     * 存储源文件
     */
    private static class StringObject extends SimpleJavaFileObject {
        private String content;

        public StringObject(URI uri, Kind kind, String content) {
            super(uri, kind);
            this.content = content;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return this.content;
        }
    }

    /**
     * class文件（不需要存到文件中）
     */
    private static class ClassJavaFileObject extends SimpleJavaFileObject {
        ByteArrayOutputStream outputStream;

        public ClassJavaFileObject(String className, Kind kind) {
            super(URI.create(className + kind.extension), kind);
            this.outputStream = new ByteArrayOutputStream();
        } // 这个也要实现

        @Override
        public OutputStream openOutputStream() throws IOException {
            return this.outputStream;
        }

        public byte[] getBytes() {
            return this.outputStream.toByteArray();
        }
    }

    /**
     * 自定义classloader
     */
    private static class MyClassLoader extends ClassLoader {
        private ClassJavaFileObject stringObject;

        public MyClassLoader(ClassJavaFileObject stringObject) {
            this.stringObject = stringObject;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] bytes = this.stringObject.getBytes();
            return defineClass(name, bytes, 0, bytes.length);
        }
    }
}