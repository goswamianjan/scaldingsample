import AssemblyKeys._ // put this at the top of the file

organization := "com.mllab.scalding"

name := "test"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

resolvers += "conjars.org" at "http://conjars.org/repo"

resolvers += "clojars.org" at "http://clojars.org/repo"

resolvers += "maven.twttr.com" at "http://maven.twttr.com/"

libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.7"

libraryDependencies += "cascading" % "cascading-core" % "2.5.1"

libraryDependencies += "cascading" % "cascading-local" % "2.5.1"

libraryDependencies += "cascading" % "cascading-hadoop" % "2.5.1"

libraryDependencies += "com.twitter" %% "scalding-core" % "0.16.0-RC4"

libraryDependencies += "com.twitter" %% "scalding-commons" % "0.16.0-RC4"

libraryDependencies += "org.apache.hadoop" % "hadoop-core" % "1.2.1"

libraryDependencies += "joda-time" % "joda-time" % "2.3"

libraryDependencies += "org.joda" % "joda-convert" % "1.5"

libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.10.0"

libraryDependencies += "org.apache.lucene" % "lucene-analyzers" % "3.6.2"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.6.6"

libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.6.6"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "3.0.0-M15" % "test"

// Automatic formating 
scalariformSettings

// Attach sources of all dependencies
EclipseKeys.withSource := true

testOptions in Test += Tests.Argument("-oF")

// Assembly for the cluster
assemblySettings  

// do not test in assembly 
test in assembly := {}

// We resolve conflicts in this JAR hell.
excludedJars in assembly <<= (fullClasspath in assembly) map { cp =>
  val excludes = Set(
    "jsp-api-2.1-6.1.14.jar",
    "jsp-2.1-6.1.14.jar",
    "jasper-compiler-5.5.12.jar",
    "stax-api-1.0.1.jar",
    "asm-3.1.jar",
    "minlog-1.2.jar", // Otherwise causes conflicts with Kyro (which bundles it)
    "janino-2.6.1.jar", // Janino includes a broken signature, and is not needed anyway
    "commons-beanutils-core-1.8.0.jar", // Clash with each other and with commons-collections
    "commons-beanutils-1.7.0.jar"
  ) 
  cp filter { jar => excludes(jar.data.getName) }
}

mergeStrategy in assembly <<= (mergeStrategy in assembly) {
  (old) => {
    case "project.clj" => MergeStrategy.discard // Leiningen build files
    case x => old(x)
  }
}
