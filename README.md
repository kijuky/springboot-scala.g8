# SpringBoot-Scala3 g8 template

## Overall

- Java 11
- SpringBoot 3.0.4
  - PostgreSQL + Flyway
  - mustache
- Scala 3.2.2
  - scalatest
- Scala.js 1.13.0

```shell
sbt new kijuky/springboot-scala.g8 --branch scala3
```

## Test

```shell
sbt g8Test
```

## Restriction

- not enabled hot reload

## Reference

- https://github.com/vmunier/play-scalajs.g8
