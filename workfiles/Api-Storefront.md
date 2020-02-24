#1Customer
## 1.1 客户注册

URL: /customer/register  
Method：POST  

RequestBody:  
```json
{
    "username": "user01",
    "realName": "张三",
    "mobile": "13234567890",
    "email": "aa@qq.com",
    "password": "123456",
    "newsSubscribed": false
}
```

ResponseBody:  
```
123456
```

Request Field  

|字段|类型|描述|
|---|---|---|
| username   | String   | 用户名    |
| realName   | String   | 真实姓名    |
| mobile   | String   | 手机    |
| email   | String   | 邮箱    |
| password   | String   | 密码    |
| newsSubscribed   | Boolean   | 订阅新闻    |

Response Field  
|字段|类型|描述|
|---|---|---|
|    | Integer   | 客户Id    |

## 1.2 客户登陆

URL: /customer/login?username={username}&encryptedpassword={encryptedpassword}  
Method：GET  

ResponseBody:  
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

Request Field  

|字段|类型|描述|
|---|---|---|
| username   | String   | 用户名    |
| encryptedpassword   | String   | 密码    |

Response Field  

|字段|类型|描述|
|---|---|---|
|    | String   | jwt token    |

## 1.3 客户修改密码

URL: /customer/changepassword  
Method：POST  

RequestHeader:  
jwtToken: xxx

RequestBody:  
```json
{
    "originPwd": "123456",
    "newPwd": "abcdef"
}
```

Request Header  

|字段|类型|描述|
|---|---|---|
| jwtToken   | String   | 登陆后jwtToken    |

Request Field  

|字段|类型|描述|
|---|---|---|
| originPwd   | String   | 用户名    |
| newPwd   | String   | 真实姓名    |


##1.4客户重置密码（忘记密码）

URL: /customer/resetpassword  
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

##1.5客户查看个人信息
URL: /customer/getprofile 
Method：GET

RequestHeader:  
jcartToken: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

Request Field  

|字段|类型|描述|
|---|---|---|
| jcartToken   | String   | jwt token    |


ResponseBody:  
```
json{
	"username":"张三",
	"realname":"李四",
	"email":"5616@qq.com",
	"mobile":"54656"
}
```

Response Field  

|字段|类型|描述|
|---|---|---|
|  username  | String   |用户名|
|  realname  | String   |真实姓名|
|  email  | String   |邮箱|
|  mobile  | String   |手机号|


##1.6客户修改个人信息
URL: /customer/updateprofile 
Method：GET

RequestHeader:  
jcartToken: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c

RequestBody:  
```
json{
	"username":"张三",
	"realname":"李四",
	"email":"5616@qq.com",
	"mobile":"54656"
}
```

Request Header  

|字段|类型|描述|
|---|---|---|
| jcartToken   | String   | jwt token    |


Request Field  

|字段|类型|描述|
|---|---|---|
|  username  | String   |用户名|
|  realname  | String   |真实姓名|
|  email  | String   |邮箱|
|  mobile  | String   |手机号|

##1.7用户获取地址列表
URL: /address/list?customerid={customerid} 
Method：GET

RequestBody:  
```
46856
```
Request Field  
|字段|类型|描述|
|---|---|---|
|  customerid  | Integer   |用户id|

ResponseBody:  
```
json{
	[
		{
			"receivername":"ChloeSun",
			"content":"上海市奉贤区南桥镇江南新村"
		},
		{
			"receivername":"DavidChen",
			"content":"上海市奉贤区海光路海滨新村"
		}
	]
}
```

Response Field  

|字段|类型|描述|
|---|---|---|
|  receivername  | String   |收货人|
|  content  | String   |地址内容|


##1.8用户添加地址
URL: /address/add?customerid={customerid} 
Method：POST


RequestBody:
```
{
	"customerid":46546,
	 "address":{
		"receivername":"张三",
		"content":"山东省青岛市"
		"city":"青岛市"
		"postcode":"45669",
		"country":"bhnj",
		"state":"山东省青岛市",
	 }
}
```
Request Field  

|字段|类型|描述|
|---|---|---|
|  customerid  | Integer   |用户id|
|  receivername  | String   |收货人|
|  content  | String   |地址内容|
|  city  | String   |城市|
|  postcode  | String   |邮政编码|
|  country  | String   |村镇|
|  state  | String   |区域|

ResponseBody:  
```
123456
```
Response Field  

|字段|类型|描述|
|---|---|---|
|  addressID  | Integer   |地址ID|


##1.9用户修改地址
URL: /address/update?addressid={addressid} 
Method：POST


RequestBody:
```
{
	 	"addressid":59665
		"receivername":"张三",
		"content":"山东省青岛市"
		"city":"青岛市"
		"postcode":"45669",
		"country":"bhnj",
		"state":"山东省青岛市",
}
```
Request Field  

|字段|类型|描述|
|---|---|---|
|  addressid  | Integer   |地址ID|
|  receivername  | String   |收货人|
|  content  | String   |地址内容|
|  city  | String   |城市|
|  postcode  | String   |邮政编码|
|  country  | String   |村镇|
|  state  | String   |区域|

#2.image图片上传
##2.1图片上传

URL: /image/upload  
Method：POST  
Request Content-Type: multipart/formdata  
RequestParam: image  

ResponseBody:  
```
http://xxx.com/xxx1.jpg

```

Request Field  

|字段|类型|描述|
|---|---|---|
| image   | String   | 上传文件key    |

Response Field  

|字段|类型|描述|
|---|---|---|
|    | String   | 上传图片后Url    |


#3.Product
##3.1商品列表
URL: /product /list?pageNum={pageNum}&pageSize={pageSize}  
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
			"productid":1,
			"mainpicurl":"http://123.png",
			"name":"牙膏",
			"abstract":"清洁美白",
			"price":25,
			"discount":0.5,
			"quantity":5
		},
		{
			"productid":2,
			"mainpicurl":"http://456.png",
			"name":"牙膏",
			"abstract":"清洁美白",
			"price":25,
			"discount":0.5,
			"quantity":5
		},
		{
			"productid":3,
			"mainpicurl":"http://789.png",
			"name":"牙膏",
			"abstract":"清洁美白",
			"price":25,
			"discount":0.5,
			"quantity":5
		}
	]
}

```

Response Field  

|字段|类型|描述|
|---|---|---|
| productid   | Integer   | 商品id    |
| mainpicurl   | String   | 图片    |
| name   | String   | 商品名称    |
| abstract   | String   | 描述    |
| price   | double   | 价格    |
| discount   | double   | 打折    |
| quantity   | Integer   | 库存    |

##3.2商品预览
URL: /product /show?productid={productid} 
Method：get  

RequestBody:  
```
45646516

```

Request Field  

|字段|类型|描述|
|---|---|---|
| productid   | Integer   | 商品id   |


ResponseBody:  
```
		{
			"mainpicurl":"http://123.png",
			"name":"牙膏",
			"abstract":"清洁美白",
			"price":25,
			"discount":0.5,
			"quantity":5
		}

```

Response Field  

|字段|类型|描述|
|---|---|---|
| mainpicurl   | String   | 图片    |
| name   | String   | 商品名称    |
| abstract   | String   | 描述    |
| price   | double   | 价格    |
| discount   | double   | 打折    |
| quantity   | Integer   | 库存    |

