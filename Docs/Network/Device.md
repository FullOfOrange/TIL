### 스위치

라우터랑은 다르게 하드웨어의 스위치 회로로 이것을 컨트롤 하는 것. VLAN 같은 것으로 스위치를 여러개 쓰는 효과를 낼 수도 있으며 L2, L3, L4, L7 등 종류가 여러가지인데 각각 어떤 레이어 레벨에서 스위칭을 해주는가에 따라 달라진다.

### 라우터

WAN과 LAN의 경계라고도 말함.

Router는 MAC 기반이 아니라 IP 기반으로 길을 찾는 놈이며, 자신의 내부 메모리에 Routing Table이 있어서 이것을 통해 라우팅을 한다.

**interface**

라우터에는 각각의 인터페이스가 존재함 interface e0/1 이런식으로 접근함.

**static route**

라우팅 테이블에 자동으로 정보가 추가되는 것도 있지만, 내부망 같은 경우에는 이를 수동으로 추가하는 경우도 있음.

```sh
ip route <Destination IP> <Subnet-mask> <interface>
```

위와 같은 경우는 자신과 연결된 장비를 말하는 부분임.

```
ip route <my IP> <Subnet-mask> <destination IP>
```

위와 같은 경우는 나한테 들어온 아이피 대역이 어디로 향해야 하는지를 알려주는 부분임. (다른 장비의 아이피를 넣으면 됨)

**dynamic route**

라우팅 테이블을 직접 설정하는 것이 아니라, 다른 라우터들의 정보를 스캔 한뒤에, 그것을 또 다른 장비에게 내 정보를 알려주는 방식. 직접 경로를 정하는 것이 아니라 주변 라우터들을 스캔해서 그것들을 가지고 있는 것. 정보가 바뀌는 것은 잘 모르겠다.