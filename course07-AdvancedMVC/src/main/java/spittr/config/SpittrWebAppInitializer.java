package spittr.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;

/**
 * 配置 DispatcherServlet
 * 这种配置方法仅能部署到支持Servlet3.0的容器（Tomcat 7+）
 *
 * @author justZero
 * @since 2019/1/1
 */
public class SpittrWebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * @return 带有@Configuration的类，用来配置
     *         ContextLoadListener创建的应用上下文中的bean
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ RootConfig.class };
    }

    /**
     * @return 带有@Configuration的类，用来定义
     *         DispatcherServlet应用上下文中的bean
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * 添加过滤器
     */
    @Override
    protected Filter[] getServletFilters() {
        // 配置字符过滤器：解决中文乱码问题
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] { characterEncodingFilter };
    }

//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        registration.setMultipartConfig(
//                new MultipartConfigElement(
//                        "c:/users/Administrator/Desktop/Recent/upload", // 上传文件临时目录（绝对路径）
//                        2097152, // 限制文件大小不超过2MB
//                        4194304, // 整个请求不超过4MB
//                        0 // 可理解为最大内存大小（单位：字节）；超过该数值，上传文件会写入到临时目录
//                )
//        );
//    }
}
