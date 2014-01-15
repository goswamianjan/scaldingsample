scalding
========

scalding FAQ:

1) How do I generate the jar?

mvn package

2) How do you run it in cluster?

hadoop jar scaldingsample-0.0.1-SNAPSHOT.jar com.twitter.scalding.Tool com.mllab.scalding.WordCountJob  --hdfs --input /local/anjan/inFile  --output /local/anjan/outFile


3) How do I run locally?

java -cp target/scaldingsample-0.0.1-SNAPSHOT.jar com.twitter.scalding.Tool com.mllab.scalding.WordCountJob --local --input input.txt --output output.txt -Xmx1024mjava
