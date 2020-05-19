package com.gingost.website.service.Impl;

import com.gingost.website.dao.UserDao;
import com.gingost.website.domain.WebUser;
import lombok.AllArgsConstructor;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShiroUserRealm extends AuthorizingRealm {

	private UserDao userDao;

	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		// TODO Auto-generated method stub
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		cMatcher.setHashAlgorithmName("MD5");
		cMatcher.setHashIterations(1);
		super.setCredentialsMatcher(cMatcher);
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		WebUser user = userDao.findUserByName(username);
		if (user == null) {
			throw new UnknownAccountException();
		}
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt().getBytes());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt,
				getName());
		return info;
	}
}
