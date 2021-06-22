package hello.admincontrol.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

/**
 * 一些通用的对象操作方法
 */
public class ObjUtil {

    private static Optional<Field> getClassDeclaredFieldRecur(Class<?> cls, final String fieldName) //{
    {
        while(cls != null) {
            try {
                Field f = cls.getDeclaredField(fieldName);
                return Optional.of(f);
            } catch (NoSuchFieldException ex) {
                cls = cls.getSuperclass();
            }
        }

        return Optional.empty();
    } //}

    /**
     * 对象浅复制, 即将一个对象的字段赋值给另一个对象,
     * 被赋值的字段要和源对象中字段的类型完全相同.
     * 该方法主要用于数据传输对象(DTO)与JPA实例对象之间的转换.
     *
     * @param target     目标对象
     * @param source     源对象
     * @param ignores    忽略的字段名
     * @param ignoreNull 忽略源对象中为空的字段
     * @return           被赋值的字段名
     */
    public static Collection<String> assignFields(final Object target, final Object source, final Set<String> ignores, final boolean ignoreNull) //{
    {
        Collection<String> ans = new ArrayList<>();
        Set<String> fields = new TreeSet<>();

        Class<?> C = target.getClass();
        while(C != null) {
            for(Field field: C.getDeclaredFields()) {
                final String fieldName = field.getName();
                if(ignores.contains(fieldName)) continue;
                fields.add(fieldName);
            }

            C = C.getSuperclass();
        }

        for(final String fieldName: fields) {
            try {
                Field targetField = getClassDeclaredFieldRecur(target.getClass(), fieldName).get();
                Field sourceField = getClassDeclaredFieldRecur(source.getClass(), fieldName).get();

                if(targetField.getType() != sourceField.getType()) {
                    continue;
                }
                targetField.setAccessible(true);
                sourceField.setAccessible(true);

                Object v = sourceField.get(source);
                if(v != null || !ignoreNull) {
                    targetField.set(target, v);
                    ans.add(fieldName);
                }
            } catch (NoSuchElementException e) {
                continue;
            } catch (IllegalAccessException e) {
                continue;
            }
        }

        return ans;
    } //}

    /**
     * 和上面的方法相同, 只是少了两个参数
     * @see assignFields
     */
    public static Collection<String> assignFields(final Object target, final Object source) //{
    {
        return assignFields(target, source, new HashSet<>(), true);
    } //}

}

