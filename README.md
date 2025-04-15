# Introduction.

[Nettee](https://github.com/nettee-space) 스터디 활동의 일환으로 설명을 담당하여 생성한 레포지터리입니다.

### Purpose

라이브러리, 코어모듈 등의 개발 시 로깅 함수 선택을 콜러 모듈에서 결정할 수 있도록 합니다.

<details open>
<summary><b>속성을 통한 제어 시 속성 예시</b></summary>

```yaml
nettee.cors:
  log-emission-level:
    endpoints: info # 이처럼 로그 함수의 emission 레벨을 제공합니다.
  endpoints:
    - path: "/a"
      allowed: ...
      exposed.headers: "*"
    - path: "/b"
      allowed: ...
      exposed.headers: "*"
```

</details>

# Features

로깅 레벨을 선택하여 사용할 수 있는 로깅 객체를 제공합니다.

```java
// cached thread-safely
var logger = AdaptiveLogger.getLogger(CurrentClassName.class)
                .with(LogLevel.DEBUG);

logger.log("이 로그를 DEBUG 레벨로 남깁니다.");
logger.log("클래스 이름: {}", CurrentClassName.class.getName());
```

```java
void foo() {
    // not cached
    var logger = AdaptiveLogger.getLoggerNonCached(CurrentClassName.class)
            .with(LogLevel.DEBUG);

    logger.log("이 로거는 캐싱 되지 않습니다.");
    logger.log("예를 들어 지역변수에서만 잠시 사용할 때 이처럼 생성할 수 있습니다.");
}
```
