package cn.tedu.db.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.apache.shiro.mgt.SecurityManager;

import cn.tedu.db.shiro.realm.ShiroUserRealm;

@Configuration
public class ShiroConfig {
	
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 过滤器配置 : LinkedHashMap可以保证数据按照添加的顺序存放
        Map<String, String> filterChainDefinitionMap = 
        					new LinkedHashMap<String, String>();
        //配置退出过滤器,退出逻辑Shiro已经实现
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/bower_components/**", "anon");
        filterChainDefinitionMap.put("/build/**", "anon");
        filterChainDefinitionMap.put("/dist/**", "anon");
        filterChainDefinitionMap.put("/plugins/**", "anon");
        // 放行登录请求
        filterChainDefinitionMap.put("/user/login", "anon");
        // 除了上面的资源，其他资源都需要进行认证，这个要放在所有放行资源之后
        filterChainDefinitionMap.put("/**", "authc");
        // 设置登录页面的路径，需要认证时，自动跳转该url
        shiroFilterFactoryBean.setLoginUrl("/login_page");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    // 将自己的验证方式加入容器
    @Bean
    public Realm myShiroRealm() {
        ShiroUserRealm myShiroRealm=new ShiroUserRealm();
        return myShiroRealm;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    @Bean(name="lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        // 对Shiro的Bean的生命周期进行管理
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

}
