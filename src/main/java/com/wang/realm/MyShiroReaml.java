package com.wang.realm;


import com.wang.entity.Permission;
import com.wang.entity.User;
import com.wang.service.PermissionService;
import com.wang.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;


import java.util.List;



public class MyShiroReaml extends AuthorizingRealm {
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {

        /**
         *
         * 流程
         * 1.根据用户user->2.获取角色id->3.根据角色id获取权限permission
         */
        //方法一：获得user对象
        String username= (String) pc.getPrimaryPrincipal();
        User user = userService.findUserByName(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取permission
        if(user != null) {
            List<Permission> permissions = permissionService.getPermissionListByUser(user);

            for (Permission p :permissions){
                info.addStringPermission(p.getPermission());
            }

           return info;//反回验证权限信息

        }

        return null;
    }

    // 认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //验证账号密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findUserByName(token.getUsername());
        if(user==null){
            throw new UnknownAccountException();
        }
        if(Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }


        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());//这里的参数要给个唯一的;

        //最后的比对需要交给安全管理器
        //三个参数进行初步的简单认证信息对象的包装
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                credentialsSalt,
                getName()  //realm name
        );

        return authenticationInfo;
    }

    private UserService userService;
    private PermissionService permissionService;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    public PermissionService getPermissionService() {
        return permissionService;
    }

    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

}
