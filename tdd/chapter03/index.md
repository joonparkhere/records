---
title: "TDD: By Example 3부"
date: 2021-04-16
pin: false
tags:
- TDD
- Python
---

# TDD 3부: 테스트 주도 개발의 패턴



## 테스트 주도 개발 패턴

어떻게 테스트할 것인 가에 대해 얘기하기 전에 조금 더 본질적인 전략에 관한 질문에 답하고 진행하자.

- 테스트한다는 것은 무엇을 뜻하는가?
- 테스트를 언제 해야 하는가?
- 테스트할 로직을 어떻게 고를 것인가?
- 테스트할 데이터를 어떻게 고를 것인가?

### 테스트(명사)

작성한 소프트웨어를 어떻게 테스트할까? 자동화된 테스트를 만들어야 한다. 테스트는 '승인 또는 거부에 도달하는 과정'을 가리키는 명사의 뜻도 가지고 있다. 명사로서의 테스트 (자동으로 실행되는 과정) 가 동사로서의 테스트 (버튼을 몇 개 눌러보고 화면에 나오는 결과를 주시하는) 와 꽤나 다른 느낌을 준다.

프로그래머는 "내가 이걸 고치면서 뭔가 다른 부분을 망가트리지 않았을까?" 하는 일종의 스트레스를 받곤 한다. 테스트는 개발자를 위한 묘석으로, 두려움을 지루함으로 바꿔주는 효험이 있다. 테스트를 실행하면 즉시 리프레시가 되고 그러면 작업 중에 에러를 낼 일도 줄게 되며, 스트레스도 적어진다.

### 격리된 테스트

테스트를 실행하는 것이 서로 어떤 식으로 영향을 미쳐야 좋을까? 아무 영향이 없어야 한다. 테스트는 충분히 빨라서 본인이 직접, 자주 실행할 수 있게끔 만들어야  하며, 전체 애플리케이션을 대상으로 하는 것보다 좀 더 작은 스케일로 하는 편이 좋다. 즉, 문제가 하나면 테스트도 하나만 실패해야 하고, 둘이면 테스트도 두 개만 실패해야 한다.

격리된 테스트가 암묵적으로 내포하는 특징 중 하나는 테스트가 실행 순서에 독립적이게 된다는 점이다. 테스트의 일부만 실행하더라도, 선행 테스트가 실행되지 않아서 실패하지 않을까 걱정할 필요가 없어야 한다. 또 다른 내포점으로는, 주어진 문제를 작은 단위로 분리하기 위해 노력해서 각 테스트를 실행하기 위한 환경을 쉽고 빠르게 세팅할 수 있게 해야 한다는 것이다. 테스트를 격리하기 위한 작업은 결과적으로 시스템이 응집도는 높고 결합도는 낮은 객체의 모음으로 구성되도록 한다.

### 테스트 목록

뭘 테스트해야 할까? 시작하기 전에 작성해야 할 테스트 목록을 모두 적어두자. 스트레스를 줄이기 위한 접근법의 첫 단계는 발 디딜 곳이 확실해지기 전엔 결코 발을 떼어 전진하지 말자는 것이다. 우선 구현할 필요가 있는 모든 오퍼레이션의 사용 예들을 적는다. 그 다음, 이미 존재하지 않는 모든 오퍼레이션에 대해서는 해당 오퍼레이션의 NULL 버전 (아무 일도 하지 않는 버전) 을 리스트에 적는다. 마지막으로 깔끔한 코드를 얻기 위해 이번 작업을 끝내기 전에 반드시 해야할 리팩토링 목록을 적는다. 현재 작업 범위를 넘어서는 큰 리팩토링 거리를 발견한다면, '다음' 할일 목록으로 넘기자. 제대로 작동하지 않는 테스트를 하나라도 생각할 수 있다면, 그걸 제대로 되게 하는 것이 코드를 릴리즈하는 것보다 더 중요하다.

### 테스트 우선

테스트를 언제 작성하는 것이 좋을까? 테스트 대상이 되는 코드를 작성하기 직전에 작성하는 것이 좋다. 코드를 작성한 후에는 테스트를 만들지 않을 것이다. 테스트를 먼저 하면 스트레스가 줄어, 테스트를 더 많이 하게 된다. 테스트가 주는 즉각적인 이익들 (테스트가 프로그램 설계와 작업 범위 조절에 유용하다) 은 스트레스가 어느 정도 존재할 때도 테스트를 작성하는 것이 좋음을 알 수 있다.

### 단언 (Assert) 우선

테스트를 작성할 때 단언 (Assert) 은 언제쯤 쓸까? 단언을 제일 먼저 쓰고 시작하자. 단언을 먼저 작성하면 작업을 단순하게 만드는 강력한 효과를 볼 수 있다.

- 시스템을 개발할 때, 완료된 시스템이 어떨 거라고 알려주는 이야기부터 작성한다.
- 특정 기능을 개발할 때, 완료된 기능이 통과할 수 있는 테스트부터 작성한다.
- 테스트를 개발할 때, 완료된 테스트가 통과해야 할 단언부터 작성한다.

### 테스트 데이터

테스트할 때 어떤 데이터를 사용해야 할까? 테스트 작성에도 청중이 존재함을 명시하며, 테스트를 읽을 때 쉽고 따라가기 좋을 만한 데이터를 사용하자. 테스트 데이터에 대한 대안은 실제 세상에서 얻어진 실제 데이터를 사용하는 것이다. 실제 데이터는 다음과 같은 경우에 유용하다.

- 실제 실행을 통해 수집한 외부 이벤트의 결과를 이용하여 실시간 시스템을 테스트하고자 하는 경우
- 예전 시스템의 출력과 현재 시스템의 출력을 비교하고자 하는 경우 (병렬 테스팅).
- 시뮬레이션 시스템을 리팩토링한 후 기존과 정확히 동일한 결과가 나오는지 확인하고자 하는 경우. 특히 부동소수점 값의 정확성이 문제가 될 수 있다.

### 명백한 데이터

데이터의 의도를 어떻게 표현할까?  테스트 자체에 예상되는 값과 실제 값을 포함하고 이 둘 사이의 결과를 드러내기 위해 노력하자. 테스트를 작성할 때는 컴퓨터뿐 아니라 후에 코드를 읽을 다른 사람들도 생각해야 한다. 명백한 데이터가 주는 이점은 프로그래밍이 더 쉬워진다는 것이다. 단언 부분에 일단 수식을 써놓으면 다음으로 무엇을 해야 할지 쉽게 알게 된다.



## 빨간 막대 패턴

이 챕터에 해당하는 패턴들은 언제 어디에 작성할 것인지, 테스트 작성을 언제 멈출 지에 대한 것이다.

### 한 단계 테스트

할일 목록에서 다음 테스트를 고를 때 무엇을 기준으로 할까? 본인에게 새로운 무언가를 가르쳐 줄 수 있으며, 구현할 수 있다는 확신이 드는 테스트를 고르자. 각 테스트는 최종 목표로 한 단계 진전시켜 줄 수 있어야 한다. 상향식 개발 혹은 하향식 개발 접근 방식 모두는 사실 TDD의 프로세스를 그리 효과적으로 설명해주지 않는다. 이보다 '아는 것에서 모르는 것으로'의 방향이 더 유용하다. '성장'은 개발자가 어느 정도의 지식과 경험을 가지고 개발을 시작한다는 점, 개발하는 중에 새로운 것을 배우게 될 것임을 예상한다는 점 등을 암시한다.

### 시작 테스트

어떤 테스트부터 시작하는 게 좋을까? 오퍼레이션이 아무 일도 하지 않는 경우를 먼저 테스트하자. 한 단계 테스트는 시작 테스트에도 적용된다. 본인에게 뭔가를 가르쳐 줄 수 있으면서도 빠르게 구현할 수 있는 테스트를 선택하자. 만약 어떤 애플리케이션을 n번째 구현하고 있다면, 오퍼레이션을 한두 개 필요로 하는 테스트를 고르는 편이 좋을 것이다. 저자의 경우, 시작 테스트는 그 이후의 테스트에 비해 좀 더 높은 차원의 테스트로, 애플리케이션 테스트와 비슷하다.

### 설명 테스트

자동화된 테스트가 더 널리 쓰이게 하려면 어떻게 해야 할까? 테스트를 통해 설명을 요청하고 테스트를 통해 설명하자.

### 학습 테스트

외부에서 만든 소프트웨어에 대한 테스트를 작성해야 할 때도 있을까? 패키지의 새로운 기능을 처음으로 사용해보기 전에 작성할 수 있다. 어떤 라이브러리를 그냥 바로 사용하는 대신 API가 예상대로 실행된다는 것을 확인해 줄 만한 작은 테스트를 만들어 보는 것이다.

### 또 다른 테스트

어떻게 하면 주제에서 벗어나지 않고 기술적인 논의를 계속할 수 있을까? 주제와 무관한 아이디어가 떠오르면 이에 대한 테스트를 할일 목록에 적어놓고 다시 주제로 돌아오자. 때때로 프로그래밍은 뭔가 훌쩍 뛰어 넘는 기회에 의존한다. 하지만 대부분의 프로그래밍은 조금 더 일반 보행에 가깝다. 하루 온종일 비생산적인 날들을 보낸 경험으로부터, 가야 할 길을 놓치지 않는 것이 때로는 최선임을 배웠다. 새 아이디어가 떠오르면 존중하고 맞이하되 주의를 흩뜨리지 않게 리스트에 적어놓고는 하던 일로 다시 돌아간다.

### 회귀 테스트

시스템 장애가 보고될 때 무슨 일을 제일 먼저 할까? 그 장애로 인하여 실패하는 테스트, 그리고 통과할 경우에는 장애가 수정되었다고 볼 수 있는 테스트를 가장 간단하게 작성하자. 회귀 테스트를 작성할 때는 이 테스트를 작성해야 한다는 사실을 어떻게 하면 애초에 알 수 있었을지 항상 생각해보자.

### 다시 하기

길을 잃은 느낌이 들 땐, 코드를 다 지워버리고 처음부터 다시 해보자. 한 시간 전까지만 해도 잘 돌던 코드가 뒤죽박죽이 되고, 앞으로 구현해야 하는 테스트가 20개나 된, 꼬인 상태라면 잠시 멈추어 생각해보자. 때로는 처음부터 다시 하는 게 합리적이라는 결론이 날 수도 있다.



## 테스팅 패턴

### 자식 테스트

지나치게 큰 테스트 케이스를 어떻게 돌아가게 만들 수 있을까? 테스트 케이스의 깨지는 부분에 해당하는 작은 테스트 케이스를 작성하고 그 작은 테스트 케이스가 실행되도록 자식 테스트를 만들자. 그 후에 다시 원래의 큰 테스트 케이스를 추가하자. TDD의 리듬 (빨강 / 초록 / 리팩토링) 은 성공이 지속되는 데 너무나도 중요해서, 리듬이 깨질 것 같다면 재빨리 방향을 바꾸는 것도 방법이다. 거슬리는 테스트를 삭제하고 다시 시작해보고, "A, B, C 세 가지를 한 번에 동작하게 하려면 할 일이 과도하겠구나. A를 동작하게 만든 후에 B를, 그 다음에 C를 동작하게 만들면 전체가 동작할 수 있을 거 같아." 처럼 돌아보고, 스스로에게 적절한 방식을 선택하길 바란다.

### 모의 객체

비용이 많이 들거나 복잡한 리소스에 의존하는 객체를 테스트하려면 어떻게 해야 할까? 상수를 반환하게끔 만든 속임수 버전의 리소스를 만들면 된다. 데이터베이스의 커넥션이 필요한 테스트 코드 혹은 시큐리티 관련 중간 동작들을 테스트하는 코드 등의 경우에, 마치 실제 객체인 것처럼 행동하지만 실제로는 가짜로 존재하는 객체를 통해 작성될 수 있다.

성능과 견고함 이외에 모의 객체의 또 다른 가치는 가독성에 있다. 실 객체를 사용한다면, 엄청난 밑 작업 (코드) 가 필요했을 것이다. 모의 객체 덕분에 테스트 코드의 가독성은 한결 좋아진다.

### 셀프 션트 (Self Shunt)

한 객체가 다른 객체와 올바르게 대화하는지 테스트하려면 어떻게 할까? 테스트 대상이 되는 객체가 원래의 대화 상대가 아니라 테스트 케이스와 대화하도록 만들면 된다.

테스팅 사용자 인터페이스의 초록 막대를 동적으로 업데이트하고자 하는 상황을 가정해보자.

```python
class ResultListenerTest:
    def testNotification(self):
        result = TestResult()
        listener = ResultListener()
        result.addListener(listener)
        WasRun("testMethod").run(result)
        assert 1 == listener.count
```

```python
class ResultListener:
    def __init__(self):
        self.count = 0
        
    def startTest(self):
        self.count = self.count + 1
```

이렇게 이벤트 리스너를 위해 별도의 객체를 만들기 보다, 그냥 테스트 케이스 자체를 리스너로 쓰면 더 간단해진다. 즉, 테스트 케이스가 일종의 모의 객체 노릇을 하는 것이다.

```python
class ResultListenerTest:
    def testNotification(self):
        self.count = 0
        result = TestResult()
        result.addListener(self)
        Was("testMethod").run(result)
        assert 1 == self.count
        
    def startTest(self):
        self.count = self.count + 1
```

셀프 션트 패턴을 이용해 작성한 테스트는 그렇지 않은 테스트보다 읽기 더 수월하다. 통보 횟수가 0이었다가 1로 됐다. 이 순서를 테스트에서 바로 읽어낼 수 있기 때문이다. 해당 패턴은 테스트 케이스가 구현할 인터페이스를 얻기 위해 인터페이스 추출을 해야 한다. 인터페이스를 추출하는 것이 더 쉬운지, 존재하는 클래스를 블랙 박스로 테스트하는 것이 더 쉬운지는 상황에 따라 다를 것이다.

### 로그 문자열

메시지의 호출 순서가 올바른지 검사하려면 어떻게 해야 할까? 로그 문자열을 가지고 있다가 메시지가 호출될 때마다 그 문자열에 추가하도록 하면 된다. 이전 포스팅 (TDD 2부) 에서 쓴 xUnit 예제를 살펴보면 알 수 있다. 로그 문자열은 특히 옵저버를 구현하고, 이벤트 통보가 원하는 순서대로 발생하는지를 확인하고자 할 때 유용하다. 만약 어떤 이벤트 통보들이 일어나는지를 검사하기는 하지만 그 순서는 상관이 없을 경우엔 문자열 집합을 저장하고 있다가 assert 에서 집합 비교를 하면 된다.

### 크래시 테스트 더미

호출되지 않을 거 같은 에러 코드 (발생하기 힘든 에러 상황) 을 어떻게 테스트할 수 있을까? 실제 작업을 수행하는 대신 그냥 예외를 발생 시키기만 하는 특수한 객체를 만들어서 이를 호출하자. 테스트 되지 않은 코드는 작동하는 것이 아니다. 작동하길 원하는 부분에 대해서는 다양한 에러 상황에 대한 테스트 객체 (크래시 테스트 더미) 를 작성해보자. 객체 전체를 흉내낼 필요가 없다는 점을 제외하면 크래시 테스트 더미는 모의 객체와 유사하다.

### 깨진 테스트

혼자서 프로그래밍을 할 때 프로그래밍 세션을 어떤 상태로 끝마치는 게 좋을까? 마지막 테스트가 깨진 상태로 끝마치는 것이 좋다. 다음에 다시 글을 쓰기 위해 앉았을 때, 반 쪽 짜리 문장을 보면 전에 그 문장을 쓸 때 무슨 생각을 했는지 떠올리게 된다. 일단 생각의 실마리를 다시 떠올리고 나면 문장을 마무리하고 계속 진행할 수 있다. 즉, 나중에 다시 코딩하기 위해 돌아왔을 때, 어느 작업부터 시작할 것인지 명백히 알 수 있다. 전에 하고 있던 생각에 대한 명확하고 구체적인 책갈피를 가지게 되는 것이다. 깨진 테스트가 하나 있다고 해서 프로그램 완성도가 더 떨어지는 것은 아니며, 단지 프로그램의 상태를 드러나게 해줄 뿐이다.

### 깨끗한 체크인

그렇다면 팀 프로그래밍을 할 때 프로그래밍 세션을 어떤 상태로 끝마치는 게 좋을까? 이 경우에는 모든 테스트가 성공한 상태로 끝마치는 것이 좋다. 팀 프로젝트는 자신이 마지막으로 코딩한 다음부터 지금까지 무슨 일이 있었는지 세밀하게 알 수 없다. 안심이 되고 확신이 있는 상태에서 시작할 필요가 있다.



## 초록 막대 패턴

깨진 테스트가 있다면 재빨리 초록 막대로 갈아타기 위해 사용할 수 있는 몇 가지 패턴이 있다.

### 가짜로 구현하기

실패하는 테스트를 만든 후 첫 번째 구현은 우선 상수를 반환하게 하자. 일단 테스트가 통과하면 단계적으로 상수를 변수를 사용하는 수식으로 변형하는 방향으로 나아가자. 가짜로 구현하기에는 강력한 두 가지 효과가 있다.

- 심리학적

  초록 막대 상태에 있는 것은 빨간 막대 상태에 있는 것과 천지 차이다. 보다 확신을 갖고 해당 위치에서부터 리팩토링해 갈 수 있다.

- 범위 조절

  다음 테스트 케이스를 구현할 때, 이전 테스트의 작동이 보장된다는 것을 알기 때문에 다음 테스트 케이스 작성에 보다 집중할 수 있다.

### 삼각측량

추상화 과정을 테스트로 주도할 때 어떻게 최대한 보수적으로 할 수 있을까? 오로지 가능한 예 (구현체) 가 두 개 이상일 때에만 추상화를 진행하자.

### 명백한 구현

단순한 연산들은 그냥 바로 구현해 버리자. 가짜로 구현하기와 삼각측량은 눈곱만큼 작은 발걸음이다. 어떤 연산을 어떻게 구현해야 할지 확신이 든다면, 그렇게 하도록 하자. 다만 오로지 명백한 구현만 하려 한다면, 자신에게 완벽함을 요구하게 된다. 이는 망연자실케 할 수 있다. '제대로 동작하는'을 푸는 동시에 '깨끗한 코드'를 해결하려는 것은 한 번에 하기에는 너무 많은 일일 수 있다. 그렇게 되면 우선 '제대로 동작하는'으로 되돌아 가서 그걸 해결하고, 그 후에 '깨끗한 코드'를 느긋하게 해결하도록 하자.



## xUnit 패턴

다음은 xUnit 계열 테스팅 프레임워크를 위한 패턴이다.

### 단언 (Assertion)

테스트가 잘 작동하는지 검사하기 위해서는 `boolean` 수식을 작성해서 프로그램이 자동으로 판단을 수행하도록 해야 한다. 테스트를 완전히 자동화하려면 사람의 판단 및 관여를 배제해야 한다. 이를 위해서, 판단 결과는 `boolean` 값이어야 한다. 그리고 `boolean` 값은 다양한 형태의 `assert()` 메서드를 통해 검증된다.

### 픽스처 (Fixture)

여러 테스트에서 공통으로 사용하는 객체 (픽스처) 들을 생성할 때는 `setUp()` 메서드를 재정의하여 필요한 객체들을 초기화하도록 하자. 보통 개발자는 모델 코드에서 중복을 제거하려 한다. 테스트 코드에서도 물론 중복을 없애는 편이 좋다. 다만, 객체 세팅 코드들이 단언이 적힌 메서드에 포함되면 테스트 코드를 읽는 사람은 그냥 위에서 아래로 읽어내려 갈 수 있다는 장점이 있다. 하지만 `setUp()` 로 분리되면 테스트 코드를 읽기 전에 객체들이 어떻게 초기화되었는지를 기억해야 한다. 이 두 방법은 각 장단이 있기 때문에 직접 경험해보고 상황에 따라 선택해야 한다.

### 외부 픽스처

픽스처 중 외부 자원이 있을 경우 `tearDown()` 를 재정의하여 여기서 자원을 해제하면 된다. 각 테스트의 목적 중 하나는 테스트가 실행되기 전과 실행된 후의 외부 세계가 동일하게 유지되도록 만드는 것이다.

### 테스트 메서드

테스트 케이스 하나를 표현할 때는 ' test '로 시작하는 이름의 메서드로 나타내자. 메서드 이름의 나머지 부분은 나중에 아무것도 모르는 사람이 이 코드를 읽더라도 왜 이 테스트가 작성되었는지 알 수 있도록 단서를 주어야 한다.

### 예외 테스트

예외가 발생하는 것이 정상인 경우에 대한 테스트는 예상되는 예외는 잡아서 무시하고, 예상되지 않는 예외 혹은 예외가 발생하지 않은 경우에 한해서 테스트가 실패하게 만들자.


