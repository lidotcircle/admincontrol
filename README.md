## admincontrol backend


<!-- vim-markdown-toc GFM -->

* [Start Backend](#start-backend)
    * [环境变量配置](#环境变量配置)
    * [启动](#启动)
* [DTO类](#dto类)
    * [DTO类的命名](#dto类的命名)
* [异常处理](#异常处理)
* [APIs](#apis)
    * [会议](#会议)
        * [会议创建](#会议创建)
        * [会议详情](#会议详情)
        * [30天内的会议获取](#30天内的会议获取)
        * [某天的会议](#某天的会议)

<!-- vim-markdown-toc -->


### Start Backend

#### 环境变量配置

```bash
PORT=       # 端口
DB_URL=     # 数据库链接, 如`jdbc:mysql://localhost:3306/admincontrol`
DB_USER=    # 数据库用户
DB_PASS=    # 数据库密码
REDIS_HOST= # Redis 地址, 如`localhost`
REDIS_PORT= # Redis 端口
REDIS_PASS= # Redis 密码

JWT_SECRET= # JWT 密文字段
```

#### 启动

```bash
mvn spring-boot:run
```


### DTO类

DTO类用于进程间进行数据对象的交换, 出现在`controller`和`service`层中.
DTO类的定义在[service/dto](./src/main/java/hello/admincontrol/service/dto)目录下.
DTO类通过注解对客户端的数据进行约束.

#### DTO类的命名

DTO类可以按照下面进行分类:

* 请求
  * Put
  * Post
* 返回(Response)

所以规定DTO类的名称由3部分组成, 即`XXX (Put|Post|Response) DTO`.
其中`XXX`表示DTO的业务名称可以由一个或者多个单词组成,
第二部分为DTO的类别, 最后的`DTO`用于表示该类是一个DTO类.



### 异常处理

项目中抛出的异常都要继承[BaseException](./src/main/java/hello/admincontrol/exception/BaseException.java).
在[ResponseFormatterFilter](./src/main/java/hello/admincontrol/config/ResponseFormatterFilter.java)中会将类型为`BaseException`
的异常转化为 HTTP 返回体(Resposne Body).

`BaseException`中有`code`和`reason`两个字段, 分别为 HTTP 返回体中的`code`和`msg`字段.

### APIs

#### 会议

##### 会议创建

Path: `/apis/meeting`  
Method: `POST`  
请求参数:
```typescript
interface Request {
    date: string; // yyyy-MM-dd HH:mm:ss
    title: string;
    mark?: int;
    content?: string;
    startTime: string; // yyyy-MM-dd HH:mm:ss
    endTime: string;   // yyyy-MM-dd HH:mm:ss
    location: string;
    attachments?: Attachment[];
    sponsor: string;
    schedules: Schedule[];
}
```

##### 会议详情

Path: `/apis/meeting`  
Method: `GET`  
前置条件: `username = meeting.sponsor OR username in meeting.users`  
请求参数:
```typescript
interface Request {
    methodId: long;
}
```
返回参数:
```typescript
interface Response {
    date: string; // yyyy-MM-dd HH:mm:ss
    title: string;
    mark?: int;
    content?: string;
    startTime: string; // yyyy-MM-dd HH:mm:ss
    endTime: string;   // yyyy-MM-dd HH:mm:ss
    location: string;
    attachments?: Attachment[];
    sponsor: string;
    schedules: Schedule[];
    id: long;
    status: int;
    users: User[];
    comments: Comment[];
}
```

##### 30天内的会议获取

Path: `/apis/meeting/latest-thirty-days`  
Method: `GET`  
返回值:
```typescript
interface MeetingBrief {
    date: string;
    title: string;
    mark: int;
}
type Response = MeetingBrief[];
```

##### 某天的会议

Path: `/apis/meeting/day-list`  
Method: `GET`  
请求参数:
```typescript
interface Request {
    date: string; // yyyy-MM-dd
}
```
返回值:
```typescript
interface MeetingMedium {
    date: string; // yyyy-MM-dd HH:mm:ss
    title: string;
    mark: int;
    id: long;
    status: int;
    content: string;
    startTime: string; // yyyy-MM-dd HH:mm:ss
    endTime: string; // yyyy-MM-dd HH:mm:ss
    location: string;
}
type Response = MeetingMedium[];
```

