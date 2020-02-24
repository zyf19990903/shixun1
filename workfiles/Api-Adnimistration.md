#1.Administration
## 0.1 图片上传

URL: /image/upload  
Method：POST  
Request Content-Type: multipart/formdata  
RequestParam: image  

ResponseBody:  
```
http://xxx.com/xxx1.jpg

```

Request Field  

| 字段     |     类型 |   描述   | 
| :--------------: | :--------:| :------: |
| image   | String   | 上传文件key    |

Response Field  

| 字段     |     类型 |   描述   | 
| :--------------: | :--------:| :------: |
|    | String   | 上传图片后Url    |


## 1.1 管理员登陆
URL: /administrator/login?jusername={username}&password={password}  
Method：GET  

ResponseBody:  
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

```

Request Field  

| 字段     |     类型 |   描述   | 
| :--------------: | :--------:| :------: |
| username   | String   | 用户名    |
| password   | String   | 密码    |

Response Field  

| 字段     |     类型 |   描述   | 
| :--------------: | :--------:| :------: |
|    | String   | jwt token    |


## 1.2 管理员获取Profile
URL: /administrator/getProfile  
Method：GET  

RequestHeader:  
jcartToken: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

ResponseBody:  
```json
{
    "administratorId": 1234,
    "username": "user001",
    "realName": "cjf",
    "email": "cjf@qq.com",
    "avatarUrl": "xxx1.jpg",
    "status": 1,
    "createTimestamp": 1582514828383
}
```

Request Header  

| 字段     |     类型 |   描述   | 
| :--------------: | :--------:| :------: |
| jcartToken   | String   | jwt token    |


Response Field  

| 字段     |     类型 |   描述   | 
| :--------------: | :--------:| :------: |
|  administratorId  | Integer   | 管理员Id    |
|  username | String   | 用户名    |
|  realName | String   | 姓名    |
|  email  | String   | 邮箱    |
|  avatarUrl  | String   | 头像    |
|  status | Byte   | 状态    |
|  createTimestamp | Long   | 创建时间    |

## 1.3 管理员更新Profile
URL: /administrator/updateProfile  
Method：POST  

RequestHeader:  
jcartToken: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

RequestBody:  
```json
{
    "password": "123456",
    "realName": "cjf",
    "email": "cjf@qq.com",
    "avatarUrl": "xxx1.jpg",
    "status": 1
}
```

Request Header  

| 字段     |     类型 |   描述   | 
| :--------------: | :--------:| :------: |
| jcartToken   | String   | jwt token    |

Request Field  

| 字段     |     类型 |   描述   | 
| :--------------: | :--------:| :------: |
| password   | String   | 密码    |
| realName   | String   | 姓名    |
| email   | String   | 邮箱    |
| avatarUrl   | String   | 头像    |
| status   | Byte   | 状态    |

## 1.4 管理员获取密码重置码

## 1.5 管理员重置密码
URL: /administrator/resetpassword  
Method：POST

RequestBody:  
```json
{
    "email": "123456@qq.com",
}
```

Request Header  

|字段|类型|描述|
|---|---|---|
| email   | String   | 用户邮箱    |



ResponseBody:  
```
true
```

Response Field  

|字段|类型|描述|
|---|---|---|
|  email_verified  | boolean   |邮箱验证，默认为false   |

## 1.6 管理员搜索列表
URL: /administrator /list?pageNum={pageNum}&pageSize={pageSize}  
Method：get  

RequestBody:  
```
{
	"pageNum":0,
	"pageSize":3,
	"list":[]
}

```

Request Field  

|字段|类型|描述|
|---|---|---|
| pageNum   | Integer   | 当前页   |
| pageSize   | Integer   | 每页显示条数   |

ResponseBody:  
```
{
	"pageNum":0,
	"pageSize":3,
	"list":[
		{
			"id":1,
			"username":"张三",
			"status":"enabled",
			"dateAdd":"2020.01.02",
		},
		{
			"id":2,
			"username":"张三2",
			"status":"enabled",
			"dateAdd":"2020.01.02",
		},
		{
			"id":3,
			"username":"张三3",
			"status":"enabled",
			"dateAdd":"2020.01.02",
		}
	]
}

```

Response Field  

|字段|类型|描述|
|---|---|---|
| id   | Integer   | id    |
| username   | String   | 姓名    |
| status   | Integer   | 状态    |
| dateAdd   | date   | 创建时间    |



## 1.7 管理员回显

URL: /administrator /show?administratorid={administratorid}
Method：get  

RequestBody:  
```
468515

```

Request Field  

|字段|类型|描述|
|---|---|---|
| administratorid   | Integer   | id   |


ResponseBody:  
```

		{
			"id":1,
			"username":"张三",
			"realname":"李四",
			"email":"2222@qq.com",
			"password":"95965"
		}
```

Response Field  

|字段|类型|描述|
|---|---|---|
| id   | Integer   | id    |
| username   | String   | 姓名    |
| realname   | String   | 真实姓名    |
| email   | String   | 邮箱    |
| password   | String   | 密码    |
## 1.8 管理员创建
URL: /administrator /create
Method：POST  

RequestBody:  
```
{
			"username":"张三",
			"realname":"李四",
			"email":"2222@qq.com",
			"password":"95965"
}

```

Request Field  

|字段|类型|描述|
|---|---|---|
| username   | String   | 姓名    |
| realname   | String   | 真实姓名    |
| email   | String   | 邮箱    |
| password   | String   | 密码    |

ResponseBody:  
```
5666
```

Response Field  

|字段|类型|描述|
|---|---|---|
| administratorid   | Integer   | id   |

## 1.9 管理员更新
URL: /administrator /update
Method：POST  

RequestBody:  
```
{
			"username":"张三",
			"realname":"李四",
			"email":"2222@qq.com",
			"password":"95965"
}

```

Request Field  

|字段|类型|描述|
|---|---|---|
| username   | String   | 姓名    |
| realname   | String   | 真实姓名    |
| email   | String   | 邮箱    |
| password   | String   | 密码    |
