lazy val root = (project in file("."))
  .enablePlugins(ScriptedPlugin)
  .settings(name := "springboot-scala.g8")

// add the below dependencies in the template build, so that Scala Steward can update versions in the giter8 template
lazy val dummy = (project in file("dummy"))
  .settings(
    libraryDependencies ++= Seq(
      "org.springframework.boot" % "spring-boot-starter-web" % "3.0.3",
      "org.springframework.boot" % "spring-boot-starter-data-jpa" % "3.0.3",
      "org.springframework.boot" % "spring-boot-starter-mustache" % "3.0.3",
      "org.flywaydb" % "flyway-core" % "9.15.0",
      "org.postgresql" % "postgresql" % "42.5.4",
      "javax.xml.bind" % "jaxb-api" % "2.3.1",
      "org.scalatest" % "scalatest_2.13" % "3.2.15",
      "org.scala-js" % "scalajs-dom_sjs1_2.13" % "2.4.0"
    ).map(_ % Runtime)
  )
