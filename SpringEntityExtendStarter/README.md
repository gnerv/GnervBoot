### GnervBoot
#### spring-entity-extend-starter

---
实体动态属性扩展插件
可以在不修改sql原始返回对象的基础上 动态添加扩展属性

此功能用于 集成第三方jar提供的controller service mapper方法时 无需修改源码或者重写接口方法
直接将指定属性注入自己的扩展对象

---

依赖| 版本
---|---
spring-context|5.2.2.RELEASE
spring-boot-configuration-processor|2.2.2.RELEASE
mybatis|2.1.1
lombok|1.18.10

在UserDTO中添加一个扩展属性 extend ， 类型为 Map<String, Object>用于注入扩展对象

默认扩展属性为 extend  可在配置 application.yml 配置中自定义

```$xslt
gnerv.boot.extend.name = extend
```

```
public class UserDTO extends BaseDTO {

    private String ukId;
    private String account;
    private String username;
    private Integer status;
    private Integer sort;

    private String orgId;
    private List<String> roleIds;

    private Map<String, Object> extend;
}
```

为UserDTO对象扩展一个OrgDTO 在扩展类上添加 EntityExtend 注解
（可为同一个对象扩展多个扩展类 或者就将同一个扩展类扩展给多个对象）
``` 
@EntityExtend(
        masterEntity = {UserDTO.class, UserInfoDTO.class},
        selectMethod = "com.gnerv.boot.platform.mapper.OrgMapper.seletcOrgByUserId",
        column = "userId=uk_id",
        resultType = OrgDTO.class,
        mapKey = "org"
)
public class OrgDTO extends BaseDTO {

    private String ukId;
    private String parentId;
    private String code;
    private String name;
    private Integer status;
    private Integer sort;

}

@EntityExtend(
        masterEntity = {UserDTO.class},
        selectMethod = "com.gnerv.boot.platform.mapper.RoleMapper.selectRoleByUserId",
        column = "userId=uk_id",
        resultType = List.class,
        mapKey = "roleIds"
)
public class RoleDTO extends BaseDTO {

    private String ukId;
    private String code;
    private String name;
    private Integer status;
    private Integer sort;

    private List<String> menuIds;
}
```

在查询UserDTO对象时 会自动注入OrgDTO 和 RoleDTO对象




