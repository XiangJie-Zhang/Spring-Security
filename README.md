# Spring-Security
本文是spring security与mybatis的整合，实现基于数据库的登录验证，使用postgres数据库，并取消了它自带的session验证而是用jwt(json web token)的token来验证，并且实现了 RBAC（Role-Based Access Control，基于角色的访问控制），希望对你有所帮助！
# 项目介绍
[项目介绍](https://awslzhang.top/2020/01/04/Spring-Security%E5%85%A5%E9%97%A8%EF%BC%9A%E5%9F%BA%E4%BA%8E%E6%95%B0%E6%8D%AE%E5%BA%93%E9%AA%8C%E8%AF%81/#more)
# 如何使用
项目使用postgres数据库，修改配置文件中数据库名和密码
使用数据库mydb,需要手动创建，数据库的表和数据在项目启动时自动插入
然后运行就可以了
界面很简陋：
1. user:只有user用户才能访问
2. admin:只有admin用户才能访问
3. adminOrUser：两种角色都可以
4. public：不登陆就可以访问

默认数据库中的角色`zxj`，具有`ROLE_SUPER`、`ROLE_ADMIN`权限

# 更新
**2020/1/11**
添加csrf防护
[介绍使用](https://awslzhang.top/2020/01/10/Spring-Security%E5%BC%80%E5%90%AF%E9%98%B2%E6%8A%A4csrf%E6%94%BB%E5%87%BB/#more)

