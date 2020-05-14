package com.gingost.website.common;

import com.gingost.website.domain.TestUser;
import com.gingost.website.domain.WebUser;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {
	public static WebUser getLoginUser() {
		return (WebUser) SecurityUtils.getSubject().getPrincipal();
	}
}
