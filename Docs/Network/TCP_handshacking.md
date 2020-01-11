## TCP handshaking

세션의 시작과 종료시 연결 지향을 유지하기 위해 각각의 송수신자가 현재 alive 상태를 확인하기 위함임.

### 3way-handshaking

세션을 시작하기 위해서 각자의 상태 확인을 하는 단계임

<img src="https://camo.githubusercontent.com/774e08740deffe1491e90625623a8a2e209f3d5a/68747470733a2f2f6d656469612e6765656b73666f726765656b732e6f72672f77702d636f6e74656e742f75706c6f6164732f5443502d636f6e6e656374696f6e2d312e706e67" alt="img" style="zoom:50%;" />

1. 클라가 서버에게 SYN 패킷을 보냄 sequence : x
2. 서버는 SYN과 ACK 를 보냄 sequence : x, ACK x+1
3. 다시 클라는 ACK를 보낸다 ACK: y+1

여기서 SYN 은 시퀸스를 패킷에 담아서 보내며 ACK를 할 때에는 1을 더해서 보내준다.

그리고 만약 ACK가 TIMEOUT 시간동안 안온다면 SYN이 안갔다고 판단하고, 다시 보낸다.

위와 같이 패킷이 중간에 유실되거나 하면 항상 다시 보내서 시퀸스 넘버를 확인하는 작업 반복

### 4way-handshaking

세션을 종료하기 위해서 각자 끝낸다는 신호를 보내는 것

<img src="https://camo.githubusercontent.com/521202d394e7ca2eeac35b6351105d42026a6b81/68747470733a2f2f6d656469612e6765656b73666f726765656b732e6f72672f77702d636f6e74656e742f75706c6f6164732f434e2e706e67" alt="img" style="zoom:50%;" />

1. 클라가 서버한테 FIN을 보낸다.
2. 서버는 FIN을 확인했다는 ACK를 보내며 모든 데이터를 보내기 위해 TIMEOUT 상태가 된다.
3. 데이터를 보냈다면 마지막에 FIN을 보낸다.
4. 클라는 FIN을 받으면 ACK를 서버에게 보내고, TIMEOUT시까지 기다린다.
   - 서버는 여기에서 ACK를 받으면 연결을 끊는다.
