ThisBuild / organization := "$organization$"
ThisBuild / scalaVersion := "3.2.2"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val root = (project in file(".") withId "$name$")
  .aggregate(server, client, shared.jvm, shared.js)
  .settings(Compile / bgRun := (server / Compile / bgRun).evaluated)

lazy val server = project
  .dependsOn(shared.jvm)
  .enablePlugins(JavaAppPackaging, SbtWeb)
  .settings(
    scalaJSProjects := Seq(client),
    Assets / WebKeys.packagePrefix := "public/",
    Assets / pipelineStages := Seq(scalaJSPipeline),
    Compile / compile := ((Compile / compile) dependsOn scalaJSPipeline).value,
    Compile / mainClass := Some("$package$.server.ServerApplication"),
    Compile / run / fork := true,
    Runtime / managedClasspath += (Assets / packageBin).value,
    libraryDependencies ++= Seq(
      "org.springframework.boot" % "spring-boot-starter-web" % "3.0.3",
      "org.springframework.boot" % "spring-boot-starter-data-jpa" % "3.0.3",
      "org.springframework.boot" % "spring-boot-starter-mustache" % "3.0.3",
      "org.flywaydb" % "flyway-core" % "9.15.0",
      "org.postgresql" % "postgresql" % "42.5.4",
      "javax.xml.bind" % "jaxb-api" % "2.3.1"
    ),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.15" % Test
    )
  )

lazy val client = project
  .dependsOn(shared.js)
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies += "org.scala-js" % "scalajs-dom_sjs1_2.13" % "2.4.0"
  )

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("shared"))
  .jsConfigure(_.enablePlugins(ScalaJSWeb))
