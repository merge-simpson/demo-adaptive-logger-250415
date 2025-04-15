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