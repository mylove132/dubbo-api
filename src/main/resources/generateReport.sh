md5=$1
CMDRunnerPath=/Users/liuzhanhui/Documents/jmeter/apache-jmeter-3.1/lib/ext/CMDRunner.jar

java -jar $CMDRunnerPath  --tool Reporter --generate-png /Users/liuzhanhui/Documents/jmeter/project/img/${md5}_rtot.png --input-jtl  /Users/liuzhanhui/Documents/jmeter/project/jtl/${md5}.jtl  --plugin-type ResponseTimesOverTime
java -jar $CMDRunnerPath  --tool Reporter --generate-png /Users/liuzhanhui/Documents/jmeter/project/img/${md5}_tps.png --input-jtl  /Users/liuzhanhui/Documents/jmeter/project/jtl/${md5}.jtl  --plugin-type TransactionsPerSecond
java -jar $CMDRunnerPath  --tool Reporter --generate-csv /Users/liuzhanhui/Documents/jmeter/project/csv/${md5}.csv --input-jtl  /Users/liuzhanhui/Documents/jmeter/project/jtl/${md5}.jtl  --plugin-type AggregateReport
