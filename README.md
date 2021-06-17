## admincontrol backend


<!-- vim-markdown-toc GFM -->

* [Start Backend](#start-backend)
* [APIs](#apis)
    * [会议](#会议)
        * [会议创建](#会议创建)
        * [会议详情](#会议详情)
        * [30天内的会议获取](#30天内的会议获取)
        * [某天的会议](#某天的会议)

<!-- vim-markdown-toc -->

### Start Backend

```bash
mvn spring-boot:run
```

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

