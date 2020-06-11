# CloudStack API monitoring

A Java application to monitor all the API's happening in the CloudStack platform\
and to export the stats for external system

## Getting Started

Steps mentioned below will tell you how to get started withe the project.

### Prerequisites

What things you need to install the software and how to install them

```
Mandatory

Java 8+
Apache Maven
Any IDE to import the project
Mysql


Optional

Docker for running containers
Prometheus and grafana to receive stats and draw graphs
Kafka cluster to receive cloudstack events
```

### Installing

A step by step series of instructions that tell you how to start the project

Download the project

```
git clone <repo url>
```

Import the project into any IDE as a Maven project.

To talk to CloudStack, you need the URL endpoint, ApiKey and SecretKey.
You need to enter these values in "application.properties" file.

It can connect to multiple CLoudStack endpoints simultaneously.
If you have 5 data centers with CloudStack installed
in different regions then you need to enter the above
mentioned three parameters corresponding to the location
name.

For ex: If you have CloudStack instance running in USA,
EUROPE and ASIA location, below is the configuration
you need to enter


```
cloudstack.private.platforms=asia,europe
cloudstack.public.platforms=america

usa.url=<https://...>
usa.apiKey=
usa.secretKey=

europe.url=
europe.apiKey=
europe.secretKey=

asia.url=
asia.apiKey=
asia.secretKey=
```


Once these changes are done, you are all good to go.
Only steps left is to build the packages and start the project

Build the project from the top directory using the command

```bash
mvn package -DskipTests
```

This will generate the jar file in the target directory
Now run the project using

```bash
java -jar target/cloudstack-stats.jar
```

This app stores all the logs in /var/log/cloudstack_stats.log

## Getting stats
Login to mysql database to display the tables and to get all stats
```
mysql cloudstack_stats -u <username> -p
```

## Exporting data to prometheus and Grafana

1. Create a prometheus docker container using
```
docker run -d --name=prometheus -p 9090:9090 -v ./prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml
```

This will run prometheus on port 9090

2. Now start the grafana container
```
docker run -d --network=host --name=grafana -p 3000:3000 grafana/grafana
```

3. Login to grafana and add both mysql and prometheus datasources

4. Now load the dashboard present in `config/grafana/dashboards/`
```
Stats.json
Active Customers.json
API Count.json
```
