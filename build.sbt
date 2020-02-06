name := "ProdutorPlacas"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.3.0"

libraryDependencies += "org.apache.flink" %% "flink-streaming-java" % "1.9.1" % "provided"

libraryDependencies += "org.apache.flink" %% "flink-connector-kafka" % "1.9.1"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.3" % Runtime

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.2" % Test

libraryDependencies += "org.apache.flink" %% "flink-streaming-scala" % "1.9.1"

libraryDependencies += "org.apache.flink" %% "flink-scala" % "1.9.1"

libraryDependencies += "org.apache.flink" % "flink-core" % "1.9.1"

libraryDependencies += "org.apache.flink" %% "flink-runtime-web" % "1.9.1" % Test

libraryDependencies += "org.apache.flink" %% "flink-clients" % "1.9.1"

libraryDependencies += "org.apache.flink" %% "flink-cep" % "1.9.1"