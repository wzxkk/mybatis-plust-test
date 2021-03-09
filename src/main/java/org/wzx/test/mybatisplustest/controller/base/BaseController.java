package org.wzx.test.mybatisplustest.controller.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.wzx.test.mybatisplustest.entity.base.CommonEntity;
import org.wzx.test.mybatisplustest.entity.base.Result;
import org.wzx.test.mybatisplustest.service.BaseService;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseController<S extends BaseService<T>, T extends Serializable> {
    @Autowired
    protected S s;

    @PostMapping
    public Result insert(T obj) {
        Result result = new Result();
        if (s.checkRepeat(obj)) {
            boolean ret = s.save(obj);
            if (ret) {
                result.setMessage("添加成功");
                result.setCode(0);
            } else {
                result.setMessage("添加失败");
                result.setCode(-1);
            }
        } else {
            result.setMessage("业务内容有重复，系统不允许添加！");
            result.setCode(-1);
        }
        return result;
    }

    @DeleteMapping
    public Result delete(String uuid) {
        Result result = new Result();
        if (s.checkDelete(uuid)) {
            boolean ret = s.removeById(uuid);
            if (ret) {
                result.setMessage("删除成功");
                result.setCode(0);
            } else {
                result.setMessage("删除失败");
                result.setCode(-1);
            }
        } else {
            result.setMessage("系统不允许删除！");
            result.setCode(-1);
        }
        return result;
    }

    @PutMapping
    public Result update(T obj) {
        Result result = new Result();
        if (s.checkRepeat(obj)) {
            boolean ret = s.updateById(obj);
            if (ret) {
                result.setMessage("修改成功");
                result.setCode(0);
            } else {
                result.setMessage("修改失败");
                result.setCode(-1);
            }
        } else {
            result.setMessage("业务内容有重复，系统不允许更新！");
            result.setCode(-1);
        }
        return result;
    }

    @GetMapping
    public Result selectAll() {
        return new Result(s.list(), "", 0);
    }

    @GetMapping("/{id}")
    public Result selectById(@PathVariable("id") String uuid) {
        Result result = new Result();
        T ret = s.getById(uuid);
        if (ret != null) {
            result.setData(ret);
            result.setMessage("获取成功");
            result.setCode(0);
        } else {
            result.setMessage("获取失败");
            result.setCode(-1);
        }
        return result;
    }

    @GetMapping("/{page}/{size}")
    public Result selectByPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        return new Result(s.page(new Page<T>(page, size)), "", 0);
    }

    @GetMapping("/{page}/{size}/{order}")
    public Result selectByPageOrder(@PathVariable("order") String order, @PathVariable("page") int page, @PathVariable("size") int size) {
        List list = new ArrayList<>();
        for (String item : order.split("\\,")) {
            String fieldName = parseColumnName(getEntityClass(), item.substring(2));
            //小写正序，大写反序
            list.add("D_".equals(item.substring(0, 2)) ? new OrderItem().setColumn(fieldName).setAsc(false) : new OrderItem().setColumn(fieldName));
        }
        return new Result(s.page(new Page<T>(page, size).addOrder(list)), "", 0);
    }

    @GetMapping("/{page}/{size}/{order}/{search}")
    public Result selectByPageOrder(@PathVariable("search") String search, @PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("order") String order) {
        List list = new ArrayList<>();
        for (String item : order.split("\\,")) {
            String fieldName = parseColumnName(getEntityClass(), item.substring(2));
            //小写正序，大写反序
            list.add("D_".equals(item.substring(0, 2)) ? new OrderItem().setColumn(fieldName).setAsc(false) : new OrderItem().setColumn(fieldName));
        }
        QueryWrapper wrapper = new QueryWrapper<T>();
        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
        try {
            Map<String, Object> searchMap = jacksonJsonParser.parseMap(search);
            for (String col : searchMap.keySet()) {
                String fieldName = parseColumnName(getEntityClass(), col);
                wrapper.like(fieldName, searchMap.get(col));
            }
        } catch (Exception e) {
            System.err.println("搜索内容有误：" + search);
            wrapper = null;
        }
        return new Result(s.page(new Page<T>(page, size).addOrder(list), wrapper), "", 0);
    }

    //检查重名
    @GetMapping("/checkRename/{name}")
    public Result checkRename(@PathVariable("name") String name) {
        QueryWrapper wrapper = new QueryWrapper<T>();
        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();
        try {
            Map<String, Object> searchMap = jacksonJsonParser.parseMap(name);
            for (String col : searchMap.keySet()) {
                String fieldName = parseColumnName(getEntityClass(), col);
                wrapper.eq(fieldName, searchMap.get(col));
            }
        } catch (Exception e) {
            wrapper = null;
        }
        return new Result(s.count(wrapper) <= 0, "返回true表示可以命名，false表示已有此名称", 0);
    }

    protected String parseColumnName(Class<?> clazz, String columnName) {
        Field field = ReflectionUtils.findField(clazz, columnName);
        if (field == null) {
            return "";
        }
        TableField annotation = field.getAnnotation(TableField.class);
        if (annotation == null || "".equals(annotation.value())) {
            //驼峰转下划线处理
            return camelToUnderline(columnName);
        } else {
            return annotation.value();
        }
    }

    protected String camelToUnderline(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append('_');
                c = Character.toLowerCase(c);
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private final Class<T> getEntityClass() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        for (Type typeArgument : type.getActualTypeArguments()) {
            if (ClassUtils.isAssignable(CommonEntity.class, (Class<?>) typeArgument)) {
                return (Class<T>) typeArgument;
            }
        }
        throw new RuntimeException("无法获取到实体对象的Class，请检查泛型声明");
    }
}
