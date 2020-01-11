# HTTP

http는 html과 xml 형식의 문서 리소스를 받아올 수 있게 해주는 프로토콜임.

### HTTP 흐름

1. TCP 세션을 시작한다. 일반적으로 모든 통신방법으로 HTTP 가 움직일 수 있지만 TCP가 권장된다.

2. HTTP/2 는 캡슐화 되어서 읽지는 못하지만 1.1 은 가능하다

   ```
   GET / HTTP/1.1
   Host: developer.mozilla.org
   Accept-Language: fr
   ```

3. 서버에서 전송된 응답은 다음과 같다

   ```
   HTTP/1.1 200 OK
   Date: Sat, 09 Oct 2010 14:28:02 GMT
   Server: Apache
   Last-Modified: Tue, 01 Dec 2009 20:18:22 GMT
   ETag: "51142bc1-7449-479b075b2891b"
   Accept-Ranges: bytes
   Content-Length: 29769
   Content-Type: text/html
   
   <!DOCTYPE html... (here comes the 29769 bytes of the requested web page)
   ```

4. 연결을 닫거나 다른 요청에 다시 쓴다.

### HTTP 메시지

#### 요청

![A basic HTTP request](https://mdn.mozillademos.org/files/13687/HTTP_Request.png)

위와 같이 생겨먹었다. HTTP 메소드, 리소스 URL (http://do.ma.in:80 이후의 모든 것), HTTP 버전이 명시되어있다. header에는 추가적인 정보를 보내는 것들이 있고, 마지막 body에는 정보가 들어가있다.

#### 응답

![img](https://mdn.mozillademos.org/files/13691/HTTP_Response.png)

먼저 프로토콜을 보낸 다음에, Status code를 보낸다.

위와 같은 요청/응답 문서를 만들어서 보내줄 수 만 있다면, (socket 같은 경우는 net모듈이 알아서 해준다는 가정 하에) 

### HTTP 헤더

헤더는 통신에 필요한 부가적인 요소를 담는 옵션이라고 생각해도 된다.

- General header: 요청과 응답 모두에 적용되지만 바디에서 최종적으로 전송되는 데이터와는 관련이 없는 헤더.
- Request header: 페치될 리소스나 클라이언트 자체에 대한 자세한 정보를 포함하는 헤더.
- Response header: 위치 또는 서버 자체에 대한 정보(이름, 버전 등)와 같이 응답에 대한 부가적인 정보를 갖는 헤더.
- Entity header: 컨텐츠 길이나 MIME 타입과 같이 엔티티 바디에 대한 자세한 정보를 포함하는 헤더.

위와 같은 그룹으로 나뉠 수 있다. 대체로 헤더는 응답 코드와 함께 쓰이면서 관련 기능을 어디로 보낼지에 대한 정보를 담아놓는다. 예를 들어 301 인가 redirect 를 넣어놓으면 location을 넣어놓는 등의 행위이다.

내가 많이 봤던 것을 써놔봐야겠다

#### 연결 관리

[`Connection`](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Connection)

현재 트랜잭션이 끝난후에 네트워크 연결을 열린 상태로 둘지 여부를 제어합니다.

[`Keep-Alive`](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Keep-Alive)

지속적인 연결이 열린 상태로 유지할 기간을 제어합니다.

### CORS

cross origin resource sharing 이라는 말인데, 현재 접속되어있는 클라이언트 서버와는 다른곳에 요청을 보낼 때 권한을 부여하는 것이다.

원래 cross-origin http request는 가능했지만,  script 의 경우에는 이것이 불가능했었다. 그치만 이런 이야기가 많이 나오면서 부터 CORS가 나왔다.

예를 들어 a.com이라는 곳에서 클라이언트를 받아왔는데, 이곳에서 b.com 에 요청을 보내면 cross-origin http 요청을 제한한다.

좀 더 정확하게는.. 아래의 그림을 보면서 설명하면 좋을 것 같다. 

**Simple Request**

![img](https://mdn.mozillademos.org/files/14293/simple_req.png)

위의 경우에는 Simple request에 해당하는 부분이다. b.com에서 보낸 두에, Access-control-allow-origin 에서는 모든 부분에 대해 허용을 하기에 각각 한번씩 왔다 갔다 한다. 아래의 3가지 조건을 모두 만족하면 Simple Request이다.

- GET, HEAD, POST 중의 한 가지 방식을 사용해야 한다.
- POST 방식일 경우 Content-type이 아래 셋 중의 하나여야 한다.
  - application/x-www-form-urlencoded
  - multipart/form-data
  - text/plain
- 커스텀 헤더를 전송하지 말아야 한다.

위와 같은 조건에 맞지 않고 어긋날경우, 

**Preflight Request**

![img](https://mdn.mozillademos.org/files/16753/preflight_correct.png)

살짝 복잡한 형태를 띄면서 두번 요청을 한다. 최초에 prefiight 라고 예비 요청을 먼저 보내고 본 요청을 하는데, 이걸 프로그래머가 하는건 아니고 서버단에서 알아서 해준다.

OPTIONS 라는 메소드로 보내는데, 이건 목표 리소스와 통신 옵션을 설명하기 위해서 만들어진 헤더이다. 나머지는 위와 같이 진행됨.

**Request with Credential**

HTTP Cookie와 HTTP Authentication 정보를 인식할 수 있게 해주는 요청이며 요청 시 **`xhr.withCredentials = true`**를 지정해서 Credential 요청을 보낼 수 있고, 서버는 Response Header에 반드시 **`Access-Control-Allow-Credentials: true`**를 포함해야 하고, **Access-Control-Allow-Origin`** 헤더의 값에는 **`\*`**가 오면 안되고 `http://foo.origin`과 같은 구체적인 도메인이 와야 한다.

위와 같은 형태들로 통신이 되며, Credential을 포함시킬지에 대한 여부를 넣어주면 된다. 쿠키는 그냥 클라이언트 도메인에 들어가는 것 같다.

**Request without Credential**

기본적으로 no credential 이므로 상관 없다.

#### CORS 관련 HTTP Response Headers

서버에서 CORS 요청을 처리할 때 지정하는 헤더

##### Access-Control-Allow-Origin

Access-Control-Allow-Origin 헤더의 값으로 지정된 도메인으로부터의 요청만 서버의 리소스에 접근할 수 있게 한다.

```
Response HeaderAccess-Control-Allow-Origin: <origin> | *
```

``에는 요청 도메인의 URI를 지정한다.
모든 도메인으로부터의 서버 리소스 접근을 허용하려면 `*`를 지정한다. Request with Credential의 경우에는 `*`를 사용할 수 없다.

### Access-Control-Expose-Headers

기본적으로 브라우저에게 노출이 되지 않지만, 브라우저 측에서 접근할 수 있게 허용해주는 헤더를 지정한다.

기본적으로 브라우저에게 노출이 되는 HTTP Response Header는 아래의 6가지 밖에 없다.

- Cache-Control
- Content-Language
- Content-Type
- Expires
- Last-Modified
- Pragma

다음과 같이 `Access-Control-Expose-Headers`를 Response Header에 지정하여 회신하면 브라우저 측에서 커스텀 헤더를 포함하여, 기본적으로는 접근할 수 없었던 Content-Length 헤더 정보도 알 수 있게 된다.

```
Response HeaderAccess-Control-Expose-Headers: Content-Length, X-My-Custom-Header, X-Another-Custom-Header
```



### Access-Control-Max-Age

Preflight Request의 결과가 캐쉬에 얼마나 오래동안 남아있는지를 나타낸다.

```
Response HeaderAccess-Control-Max-Age: <delta-seconds>
```

### Access-Control-Allow-Credentials

Request with Credential 방식이 사용될 수 있는지를 지정한다.

```
Response HeaderAccess-Control-Allow-Credentials: true | false
```

예비 요청에 대한 응답에 `Access-Control-Allow-Credentials: false`를 포함하면, 본 요청은 Request with Credential을 보낼 수 없다.

Simple Request에 `withCredentials = true`가 지정되어 있는데, Response Header에 `Access-Control-Allow-Credentials: true`가 명시되어 있지 않다면, 그 Response는 브라우저에 의해 무시된다.



### Access-Control-Allow-Methods

예비 요청에 대한 Response Header에 사용되며, 서버의 리소스에 접근할 수 있는 HTTP Method 방식을 지정한다.

```
Response HeaderAccess-Control-Allow-Methods: <method>[, <method>]*
```

### Access-Control-Allow-Headers

예비 요청에 대한 Response Header에 사용되며, 본 요청에서 사용할 수 있는 HTTP Header를 지정한다.

```
Response HeaderAccess-Control-Allow-Headers: <field-name>[, <field-name>]*
```

# CORS 관련 HTTP Request Headers

클라이언트가 서버에 CORS 요청을 보낼 때 사용하는 헤더로, 브라우저가 자동으로 지정하며, XMLHttpRequest를 사용하는 프로그래머가 직접 지정해 줄 필요 없다.

### Origin

Cross-site 요청을 날리는 요청 도메인 URI을 나타내며, access control이 적용되는 모든 요청에 `Origin` 헤더는 반드시 포함된다.

```
Request HeaderOrigin: <origin>
```

``은 서버 이름(포트 포함)만 포함되며 경로 정보는 포함되지 않는다.

``은 공백일 수도 있는데, 소스가 data URL일 경우에 유용하다.

### Access-Control-Request-Method

예비 요청을 보낼 때 포함되어, 본 요청에서 어떤 HTTP Method를 사용할 지 서버에게 알려준다.

```
Request HeaderAccess-Control-Request-Method: <method>
```

### Access-Control-Request-Headers

예비 요청을 보낼 때 포함되어, 본 요청에서 어떤 HTTP Header를 사용할 지 서버에게 알려준다.

```
Request HeaderAccess-Control-Request-Headers: <field-name>[, <field-name>]*
```