name := "$name$"
scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.riot-framework" % "riot-core" % "0.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)

lazy val root = (project in file("."))
  .enablePlugins(JavaServerAppPackaging)
  .settings(
  
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
