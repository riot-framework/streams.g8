import riot.riotctl.sbt.RiotCtl._

name := "$name$"
scalaVersion := "2.12.8"
$if(generate_ensime_configuration_files.truthy)$
ensimeScalaVersion := "2.12.8"
$endif$

libraryDependencies ++= Seq(
  // RIoT minor releases are backwards-compatible:
  "org.riot-framework" % "riot-core" % "0.+",
  // Choose an SLF4J implementation, for example Logback:
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

lazy val root = (project in file("."))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
    // Skip javadoc for this project:
    publishArtifact in (Compile, packageDoc) := false,
  
    // Deployment Targets (hostname, username, password):
    riotTargets := Seq(
      riotTarget("raspberrypi", "pi", "raspberry")
      ),
      
    // Port to use for remote debugging:
    riotDbgPort := 8000,
      
    // Packages and features needed by your code:
    riotPrereqs := "oracle-java8-jdk wiringpi",
    riotRequiresI2C := false,
    riotRequiresSPI := false
  )
