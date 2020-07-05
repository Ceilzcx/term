## 后台运行流程

> 登录采取JWT的技术,返回加密后的token
> 对不用用户获得的信息会使用@LoginToken注解的type进行拦截

### 隐患模块
+ 添加隐患
```text
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
            "id": null,
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

#### 获得隐患列表(分为安管负责人和安管员)
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
            "id": 1,
            "riskSource": "浙江大学城市学院",
            "status": "完成整控",
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

### 隐患整改验收模块