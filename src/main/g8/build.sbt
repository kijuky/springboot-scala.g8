ThisBuild / organization := "$organization$"
ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val springVersion = "2.5.0"

lazy val root = (project in file("."))
  .aggregate(server, client, shared.jvm, shared.js)

lazy val server = project
  .dependsOn(shared.jvm)
  .enablePlugins(JavaAppPackaging, SbtWeb)
  .settings(
    scalaJSProjects := Seq(client),
    Assets / WebKeys.packagePrefix := "public/",
    Assets / pipelineStages := Seq(scalaJSPipeline),
    Compile / compile := ((Compile / compile) dependsOn scalaJSPipeline).value,
    Compile / mainClass := Some("$package$.server.ServerApplication"),
    Runtime / managedClasspath += (Assets / packageBin).value,
    libraryDependencies ++= Seq(
      "junit" % "junit" % "4.13.2",
      "org.scalatest" %% "scalatest" % "3.2.9",
      "org.scalatestplus" %% "junit-4-13" % "3.2.9.0"
    ).map(_ % Test),
    libraryDependencies ++= Seq(
      "org.springframework.boot" % "spring-boot-starter-web" % springVersion,
      "org.springframework.boot" % "spring-boot-starter-data-jpa" % springVersion,
      "org.springframework.boot" % "spring-boot-starter-mustache" % springVersion,
      "org.flywaydb" % "flyway-core" % "7.9.1",
      "org.postgresql" % "postgresql" % "42.2.20",
      "org.slf4j" % "slf4j-simple" % "2.0.0-alpha1",
      "javax.xml.bind" % "jaxb-api" % "2.3.0"
    )
  )

lazy val client = project
  .dependsOn(shared.js)
  .enablePlugins(ScalaJSPlugin, ScalaJSWeb)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "1.1.0"
  )

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("shared"))
  .jsConfigure(_.enablePlugins(ScalaJSWeb))