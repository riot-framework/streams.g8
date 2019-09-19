addSbtPlugin("org.riot-framework" % "sbt-riotctl" % "0.3")
$if(generate_eclipse_project_files.truthy)$
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "5.2.4")
$endif$
$if(generate_ensime_configuration_files.truthy)$
addSbtPlugin("org.ensime" % "sbt-ensime" % "2.5.1")
$endif$