package com.itlijunjie.openci.util;

import com.itlijunjie.openci.util.enums.EnumInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.*;

public class EnumHelper {

    private static final Logger logger = LoggerFactory.getLogger("EnumHelper");

    /**
     * 枚举类对应的包路径
     */
    private final static String PACKAGE_NAME = "com.itlijunjie.openci.util.enums.";
    /**
     * 枚举接口类全路径
     */
    private final static String ENUM_MESSAGE_PATH = PACKAGE_NAME + "EnumInterface";

    /**
     * 所有枚举对象的 map
     */
    private static final Map<String, List<EnumInterface>> ENUM_MAP = new HashMap<>();


    /**静态初始化块*/
    static {

    }

    private static synchronized void initNewEnum(String className) {
        try {
            String fullClassName = PACKAGE_NAME + className;
            Class<?> cls;
            cls = Class.forName(fullClassName);
            Class<?>[] iter = cls.getInterfaces();
            boolean flag = false;

            for (Class cz : iter) {
                if (cz.getName().equals(ENUM_MESSAGE_PATH)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                Method method = cls.getMethod("values");
                EnumInterface inter[] = (EnumInterface[]) method.invoke(null, null);
                List<EnumInterface> list = new ArrayList<>();
                for (EnumInterface enumInterface : inter) {
                    list.add(enumInterface);
                }
                ENUM_MAP.put(cls.getSimpleName(), list);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * 获取value返回枚举对象的所有
     */
    public static List<EnumInterface> getEnumValues(String className) {
        if (!ENUM_MAP.containsKey(className)) {
            initNewEnum(className);
        }
        return ENUM_MAP.containsKey(className) ? ENUM_MAP.get(className) : new ArrayList<>();

    }


    public static List<EnumInterface> getEnumValues(Class clazz){
        String className = clazz.getSimpleName();
        return getEnumValues(className);
    }

    /**
     * 获取value返回枚举对象的所有
     */
    public static String getEnumJson(String className) {
        List<EnumInterface> enumInterfaces = getEnumValues(className);
        return getEnumJson(enumInterfaces);
    }


    public static String getEnumJson(EnumSet enumSet){
        final List<EnumInterface> enumInterfaces = getEnumInterfaces(enumSet);
        return getEnumJson(enumInterfaces);
    }

    public static String getEnumJson(List<EnumInterface> enumInterfaces){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (EnumInterface ei : enumInterfaces) {
            sb.append(String.format("{'text':'%s', 'value':%s},", ei.getDescription(), ei.getValue()));
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    public static List<EnumInterface> getEnumInterfaces(EnumSet enumSet){

        List<EnumInterface> enumInterfaces = new ArrayList<>();
        for (Object enumInterface : enumSet) {
            final EnumInterface enumInterface1 = (EnumInterface) enumInterface;
            enumInterfaces.add(enumInterface1);
        }

        return enumInterfaces;
    }

    /**
     * 获取value返回枚举对象的所有
     */
    public static <F> String getEnumJson(final Class<F> clazz) {
        String className = clazz.getSimpleName();
        return getEnumJson(className);
    }

    /**
     * 枚举转换为valuetext
     */
    public static List<ValueTextDto> getValueText(String className) {
        List<ValueTextDto> valueTextDtos =  new ArrayList<>();
        List<EnumInterface> enumInterfaces = getEnumValues(className);
        for (EnumInterface ei : enumInterfaces) {
            valueTextDtos.add(new ValueTextDto(ei.getValue(), ei.getDescription()));
        }

        return valueTextDtos;
    }
}