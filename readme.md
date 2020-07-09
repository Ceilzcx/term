## 后台运行流程

> 登录采取JWT的技术,返回加密后的token
> 对不用用户获得的信息会使用@LoginToken注解的type进行拦截

### 隐患模块
+ 添加隐患
```text
POST
http://localhost:8088/term/danger
{
  "type": "烟花爆竹",
  "riskSource": "浙江大学城市学院",
  "level": "较大",
  "description": "11111",
  "measure": "无",
  "timeLimit": 10,
  "uid": 1,
  "position": ["杭州市", "宁波市"]
}
```
结果(时间前端是否有问题?)
```text
{
    "status": 200,
    "msg": "success",
    "data": {
        "dangerEntity": {
            "id": 1,
            "type": 2,
            "riskSource": "浙江大学城市学院",
            "level": 2,
            "description": "11111",
            "measure": "无",
            "timeLimit": 10,
            "uid": 1,
            "createDate": "2020-07-05T11:19:48.868",
            "pid1": 5,
            "pid2": 6,
            "pid3": null,
            "dangerStatus": 0
        },
        "photoEntityList": [
            {
                "id": 5,
                "url": null,
                "createDate": "2020-07-05T11:19:48.831",
                "uid": 1,
                "position": "杭州市"
            },
            {
                "id": 6,
                "url": null,
                "createDate": "2020-07-05T11:19:48.861",
                "uid": 1,
                "position": "宁波市"
            }
        ]
    }
}
```
+ 添加图片(通过返回的图片id添加图片内容)
图片通过url获得,从本地文件映射
```text
http://localhost:8088/term/photo/upload/{id}
```

#### 获得隐患列表(分为安管负责人和安管员)
> 安管负责人和安管员只获取状态为创建的隐患列表
+ 排查用户
```text
http://localhost:8088/term/dangers/{uid}
结果:
{
    "status": 200,
    "msg": "success",
    "data": [
        {
            "id": 1,
            "riskSource": "浙江大学城市学院",
            "status": "完成整控",
            "type": "机械安全",
            "timeLevel": 1
        },
        {
            "id": 2,
            "riskSource": "浙江大学城市学院",
            "status": "创建",
            "type": "烟花爆竹",
            "timeLevel": 2
        }
    ]
}
```
+ 安管负责人
```text
http://localhost:8088/term/dangers/keynote
结果:
{
    "status": 200,
    "msg": "success",
    "data": [
        {
            "id": 15,
            "riskSource": "asd",
            "status": "创建",
            "type": "机械安全",
            "timeLevel": 1
        },
        {
            "id": 16,
            "riskSource": "asd",
            "status": "创建",
            "type": "机械安全",
            "timeLevel": 1
        }
    ]
}
```
+ 安管员
```text
http://localhost:8088/term/dangers/others
结果:
{
    "status": 200,
    "msg": "success",
    "data": [
        {
            "id": 2,
            "riskSource": "浙江大学城市学院",
            "status": "创建",
            "type": "烟花爆竹",
            "timeLevel": 2
        }
    ]
}
```

### 隐患整改模块
+ 添加整改信息(type查看enums的DangerStatus)
```text
POST
http://localhost:8088/term/rectification
{
  "status": "完成整控",
  "measure": "111",
  "uid": "1",
  "did": "1",
  "position": ["杭州市", "宁波市"]
}
结果:
{
    "status": 200,
    "msg": "success",
    "data": {
        "entity": {
            "id": 11,
            "status": "完成整控",
            "measure": "111",
            "document": null,
            "uid": 1,
            "createDate": "2020-07-09T14:58:58.489",
            "pid1": 48,
            "pid2": 49,
            "pid3": null,
            "did": 1
        },
        "photoEntityList": [
            {
                "id": 48,
                "url": null,
                "createDate": null,
                "uid": 1,
                "position": "杭州市"
            },
            {
                "id": 49,
                "url": null,
                "createDate": null,
                "uid": 1,
                "position": "宁波市"
            }
        ]
    }
}
```
+ 主键获得整改信息
```text
http://localhost:8088/term/rectification/1
```
+ 通过隐患id获得整改信息
```text
http://localhost:8088/term/rectification/did/1
```
+ 通过隐患id上传文档信息(二进制,与添加分开)
```text
http://localhost:8088/term/rectification/document/{id}
```

### 隐患整改验收模块
```text
POST
http://localhost:8088/term/acceptance
{
  "acceptOption": "hahaha",
  "uid": "1",
  "acceptStatus": "合格",
  "rid": "1"
}
结果:
{
    "status": 200,
    "msg": "success",
    "data": {
        "id": 5,
        "acceptOption": "hahaha",
        "uid": 1,
        "createDate": "2020-07-09T15:03:11.713",
        "acceptStatus": "合格",
        "rid": 1
    }
}
```
+ 通过主键id获得信息
```text
http://localhost:8088/term/acceptance/1
```
+ 通过整改id获得信息(外键唯一)
```text
http://localhost:8088/term/acceptance/rid/1
```

### 企业模块
+ 查看企业信息
```text
http://localhost:8088/term/enterprise/1
```

### 用户模块
> JWT生成token返回, 通过拦截器对不同类型的用户进行拦截
+ 登录(android保存token)
```text
POST
http://localhost:8088/term/user/login
{
  "username": "admin",
  "password": "123456"
}
结果:
{
    "status": 200,
    "msg": "success",
    "data": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxIn0.qfd0G-elhE1aGr15LrnYlIZ_3UToaOM5HeMcXrmDGBM"
}
```
+ getInfo(**头部添加token**)
```text
http://localhost:8088/term/user
{
    "status": 200,
    "msg": "success",
    "data": {
        "id": 1,
        "username": "admin",
        "nickname": "admin",
        "password": "123456",
        "type": "企业安全管理员",
        "eid": 1
    }
}
```
