# single-mall 在Windows环境下的部署

## 开发环境搭建

简易环境搭建流程：

- 安装IDEA并导入项目源码；
- 安装MySql，创建一个`micromall`数据库，并导入`/document/sql/micromall.sql`文件；
- 安装Redis、Elasticsearch、MongoDB、RabbitMQ等环境。

## 项目部署

> `single-mall`项目启动有先后顺序，大家要按照以下顺序启动。

### 启动后台管理服务`mall-admin`

- 直接运行com.macro.mall.MallAdminApplication的main函数即可；
- 访问接口文档：http://localhost:8081/swagger-ui.html

- 登录接口地址：http://localhost:8081/admin/login
- 访问登录接口获取到token后放入认证的头信息即可正常访问其他需要登录的接口：

### 启动前台服务`mall-portal`

- 直接运行com.macro.mall.portal.MallPortalApplication的main函数即可；
- 访问接口文档：http://localhost:8888/swagger-ui.html

- 登录接口地址：http://localhost:8888/sso/login
- 调用需要登录的接口方式同`mall-admin`。

### 启动搜索服务`mall-search`

- 直接运行com.macro.mall.search.MallSearchApplication的main函数即可；
- 访问接口文档：http://localhost:8085/swagger-ui.html