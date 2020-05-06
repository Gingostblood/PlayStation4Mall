package com.gingost.website.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sManager=new DefaultWebSessionManager();
		sManager.setGlobalSessionTimeout(60*60*1000*3);
		return sManager;
	}
	@Bean
	public SecurityManager securityManager(Realm realm,SessionManager sessionManager) {
		DefaultWebSecurityManager sManager=new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		sManager.setSessionManager(sessionManager);
		return sManager;
	}
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean sfBean=new ShiroFilterFactoryBean();
		sfBean.setSecurityManager(securityManager);
		sfBean.setLoginUrl("/signin");
		Map<String, String>map=new LinkedHashMap<>();
		//放行静态资源
		map.put("/css/**", "anon");
		map.put("/js/**", "anon");
		map.put("/img/**", "anon");
		map.put("/user/saveUser","anon");
		//放行ajax登录
		map.put("/user/findOneUser", "anon");
		//放行ajax校验
		map.put("/user/isExit", "anon");
		//放行ajax注册
		map.put("/user/addOneUser", "anon");
		//放行注册页面
		map.put("/signup", "anon");
		//放行忘记密码
		map.put("/forget", "anon");
		//退出登录
		map.put("/logout", "logout");
		 map.put("/**","user");
		 sfBean.setFilterChainDefinitionMap(map);
		 return sfBean;
	}
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
}
