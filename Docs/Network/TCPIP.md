## TCP/IP

https://d2.naver.com/helloworld/47667 를 정리한 내용임.

데이터의 순차성을 보장하며, 데이터의 유실이 되지 않도록 신뢰성을 보장하며, 이왕이면 빠르게 데이터를 보내기 위한 수단.

### 성질 

1. **Connection oriented**

   연결지향이다. 이는 먼저 서로의 상태를 확인하고, 연결을 맺은 뒤에 데이터를 주고 받는 방식이다.
   여기서 'TCP 연결 식별자'는 두 엔드포인트의 주소를 합친 것으로, <로컬 IP 주소, 로컬 포트번호, 리모트 IP 주소, 리모트 포트번호> 형태이다.

2. **Bidirection byte stream**

   양방향 통신이며 바이트 스트림을 사용한다.

3. **In-order delivery**

   송신자가 보낸 순서대로 수신자가 데이터를 받고, 이를 위해 데이터 순서를 표시하는데 32-bit 정수형을 사용한다.

4. **Flow control**

   송신자는 수신자가 받을 수 있는 만큼만 데이터를 전송한다. 수신자가 자신의 받을 수 있는 바이트 수(가능한 버퍼의 크기)를 송신자에게 전달하고, 송신자는 수신자에게 받은 정보만큼 전달해준다.

5. **Congestion control**

   네트워크 정체를 방지하기 위해 receive window와 별도로 congestion window를 사용하는데 이는 네트워크에 유입되는 데이터양을 제한하기 위해서이다. Receive window와 마찬가지로 congestion window가 허용하는 바이트 수만큼 데이터를 전송하며 여기에는 TCP Vegas, Westwood, BIC, CUBIC 등 다양한 알고리즘이 있다. Flow control과 달리 송신자가 단독으로 구현한다.

6. **Reliability through ACK**

   데이터를 송신하고 ACK를 보낸다. 만약 수신자가 특정 데이터의 ACK를 보내지 않았다면, 송신자는 그 부분의 데이터를 다시 보냄

### Window

기본적인 TCP에는 Window size가 존재하며, 자신이 데이터를 처리할 수 있는 공간을 말한다.

- TCP Receive Window (RWIN) : 수신측이 Ack 없이 한번에 data를 받을수 있는 크기. (가용 가능한 Buffer Size)

- TCP Congestion Window (CWND) : 송신측이 Ack 없이 한번에 data를 보내는 크기. (Congestion Control을 위하여 계속 변동)

### 데이터 전송

소켓은 POSIX 계열 운영체제에서는 파일로 관리된다. 이 파일을 레이어단계에서 검사한 뒤에, 소켓 구조체를 사용해서 소켓 함수를 호출한다.

![networkstack1](https://d2.naver.com/content/images/2015/06/helloworld-47667-1.png)

네트워크 스택에는 위와 같이 여러 스택이 있는데 크게 User / Kernel / Device 스택이 있다. 여기서 NIC 는 네트워크 인터페이스 카드이다. 패킷을 물리적 부분에서 송수신 하는 친구이다.

\* checksum은 중복검사로, 비트 오류 검사같은 행위를 하는것임. (무결성 보호)
:10010000214601360121470136007EFE09D2190140 여기서 마지막 40 이 특정 데이터들의 연산을 통해 얻을 수 있는 값인데, 이 값을 통과하는 행위임.

먼저 App 영역에서 시스템 콜을 호출한다. 그러면 바로 커널부로 넘어가서 남은 부분이 진행된다.

커널부로 넘어간 파일부는 어떤 데이터를 보낼지 검사를 한 뒤, 소켓함수를 호출한다.

소켓은 송신, 수신 총 두개의 버퍼를 가지곡 있다. write 를 호출하면, 유저의 데이터가 커널부로 복사되고, send socket buffer 뒷부분에 데이터가 축가된다. 버퍼에 들어간 순서대로 보내기 위해서이다. 이미 붙어있는 회색 상자에 데이터를 넣어놓은 다음, TCP 를 호출한다.

소켓과 연결된 TCP Control Block 이 있다. 이 TCB에는 TCP 연결 처리에 필요한 connection state(LISTEN, ESTABLISHED, TIME_WAIT 등), receive window, congestion window, sequence 번호, 재전송 타이머 등의 데이터가 있다.

TCP 상태가 데이터 전송을 허용하면 새로운 TCP segment = 패킷 을 생성한다. Flow control 같은 이유로 데이터 전송이 불가능하면 시스템 콜은 이곳에서 중지되고 다시 App으로 제어권이 넘어간다.

TCP segment 에는 TCP 헤더와 Payload가 담겨있고, payload에는 아직 ACK 받지 않은 send socket buffer의 데이터가 들어있다.

TCP checksum을 계산한다. 여기서 pseudo 헤더 정보를 포함시킨다. (IP주소, segment 길이, 프로토콜 번호) 여기서 TCP 상태에 따라 패킷을 전송할 수 있다.

TCP segment 가 다 생성되었다면, IP 레이어로 내려간다. TCP segment에 IP 헤더를 추가하고, IP routing을 통해 목적지 주소를 가기 위한 다음 장비의 IP 주소를 찾아낸다. (이 결과물로 next hop IP와 해당 IP로 패킷을 전송할 때 사용하는 인터페이스를 알게됨)

헤더 checksum을 계산하여 붙인다음에, Ethernet 레이어로 데이터를 내린다.

Ethernet 에서는 Address Resolution Protocol 을 사용해서 next hop IP의 MAC 주소를 찾는다. 그리고 Ethernet 헤더를 패킷에 추가한 뒤 IP routing을 통해 알게 된 NIC를 호출한다.

NIC는 전송 요청을 받으면 메인 메모리의 패킷을 자신의 메모리로 복사한 후에 네트워크 선으로 전송한다.

### 데이터 수신

![networkstack2](https://d2.naver.com/content/images/2015/06/helloworld-47667-2.png)

먼저 NIC가 받은 패킷을 자신의 메모리에 기록 후에 호스트로 넘겨준다. 이 버퍼는 드라이버가 미리 패킷 수신용으로 할당해놓은 메모리인데, 미리 할당해놓은 버퍼가 없으면 패킷을 버린다.

드라이버는 패킷을 운영체제가 이해할 수 있도록 운영체재의 패킷 구조체에 맞게 포장한다. 포장 한 이후에 상위 레이어로 보냄.

Ethernet 레이어에서도 패킷을 검사하고, (de-multiplex) 상위 프로토콜을 찾는다. 이때 헤더의 ethertype를 이용한다고 한다. IPv4 ethertype은 0x0800이다. (아마도 여기 레이어에서 다음 레이어를 어디로 태울지를 검사하는 것 같다.) 다 검사되면 Ethernet 헤더를 제거하고 IP 레이어로 패킷을 전달한다.

IP 레이어에서도 IP 헤더 checksum 을 통해 패킷을 검사한다. 여기서 논리적인 IP 라우팅을 통해서 패킷을 로컬 장비가 처리해야하는지, 다른 장비로 보내야하는지 본다. 로컬 장비가 처리해야 한다면, IP 헤더의 proto 값을 보고 상위 프로토콜을 찾는다. TCP proto 값은 6이다. 헤더를 떼어내고 TCP 단으로 올려보낸다

TCP 레이어에서도 checksum을 확인하고 패킷 검사를 한다. 그리고 패킷의 소켓인 TCP control block을 찾는다. 이때 패킷의 <소스 IP, 소스 port, 타깃 IP, 타깃 port>를 식별자로 사용. 데이터를 받았다면 receive socket buffer 에 추가한다.

### 스택 내부 제어 흐름(control flow)

내부의 네트워크 흐름은 다음과 같다. event driven 방식이며 별도의 스레드가 없이 Interrupt에 의해 이뤄지는 행위들이다. 이부분 부터는 너무 어렵다;

![networkstack3](https://d2.naver.com/content/images/2015/06/helloworld-47667-3.png)
