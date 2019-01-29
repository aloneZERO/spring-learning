package spittr.test;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @author justZero
 * @since 2019/1/29
 */
public class OtherTest {

    /**
     * 区分好三种 SQL 日期类型，
     * 避免出现丢失时分秒等日期精度的问题。
     */
    @Test
    public void testTime() {
        // java.sql.Date
        // 只有日期
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);

        // java.sql.Time
        // 只有时间
        Time time = new Time(System.currentTimeMillis());
        System.out.println(time);

        // java.sql.Timestamp
        // 既有日期，又有时间
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);

        // java.util.Date
        // 是以上三种类型的父类
        java.util.Date jdate = new java.util.Date();
        System.out.println(jdate);
    }

    /**
     * Timestamp 的表示范围
     * 1970-01-01 00:00:00 ~ 2038-01-09 03:14:07
     * 在数据库中超出范围会变成：0000-00-00 00:00:00
     *
     * 如果要实现大范围的时间存储，可以使用 Datetime
     * 表示范围：1000-01-01 00:00:00 ~ 9999-12-31 23:59:59
     *
     * 更详细的区别对比：
     * https://blog.csdn.net/wangjun5159/article/details/48010563
     */
    @Test
    public void testTimestamp() {
        Timestamp timestamp = Timestamp.valueOf("1969-12-30 00:00:00");
        System.out.println(timestamp);
    }

    @Test
    public void testUrl() throws UnsupportedEncodingException {
        String url = URLEncoder.encode("中文链接", "utf-8");
        System.out.println(url);
    }
}
