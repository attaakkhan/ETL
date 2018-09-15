# ETL--Extract, Tranform, and Load


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You need Ubuntu or any unix based  machine to run this project


### Setting up the enviroment for ETL
#### 1. Install and configure Kafka servers on Debian/Ubuntu

##### Step 1- Install Java 
Update your system to the latest available version in the repository
```
$  sudo apt-get update
```
Apache Kafka requires java runtime environment, install it using apt-get
```
$ sudo apt-get install default-jre
```

##### Step 2- Install Zookeeper
Kafka servers depend on zookeeper to coordinate, and we will use Apache zookeeper, which is an open source service used to manage information among distributed servers. Install zookeeper using apt-get
```
$  sudo apt-get install zookeeperd 
```
After installing, it will run automatically listening on port 2181, to test it run:
```
$  telnet localhost 2181
```
At the prompt type 'ruok' and if it returns 'imok' then everything is working well.

Optionally, to check the zookeeper status use the following command
```
$ sudo systemctl status zookeeper
```
if zookeeper is not running, use the following command to activate the zookeeper
```
$ sudo systemctl enable zookeeper
```

##### Step 2- Download and install Apache kafka
Navigate to ~/Downloads/ directory to download Kafka binaries
```
$ cd ~/Downloads 
```
Download Kafka 2.0 using wget
```
$  wget http://www-us.apache.org/dist/kafka/2.0.0/kafka_2.11-2.0.0.tgz 
```
Now create a directory Kafka in the /opt directory
```
$  sudo mkdir /opt/Kafka
```
Extract Kafka binaries to /opt/kafka directory
```
$ sudo tar xvzf kafka_2.11-2.0.0.tgz -C /opt/Kafka

```
To view, the kafka Path use the following command
```
$  ls /opt/Kafka 
```
To add Kafka path to the system path, open /etc/profile using nano
```
$ sudo nano /etc/profile
```
Add the following lines to the end of the file, and then press ctrl+o to save and then press ctrl+x to exit
```
export KAFKA_PATH="/opt/Kafka/kafka_2.11-2.0.0"
export PATH="$PATH:${KAFKA_PATH}/bin"
```
Finally, reboot your system
```
$  sudo reboot
```
After the system starts, make a link to the Kafka server.properties file where configurations for the Kafka server  are stored
```
$ sudo -i ln -s $KAFKA_PATH/config/server.properties /etc/kafka.properties
 ```
Finally to run kafka server run the following command
```
$  sudo -i kafka-server-start.sh /etc/kafka.properties
```

##### Step 3- Testing Apache kafka using Terminal
To test Apache kafka, open a new terminal tab and create a test topic using the following command
```
$  sudo -i kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1   --topic testTopic --partitions 1
```
Now to publish messages, use the follwing command. After running the command an arrow(>) will appear
```
$  sudo -i kafka-console-producer.sh --broker-list localhost:9092 --topic testTopic
```
To Consume messages, Open a new terminal tab and  use the following command
```
$  sudo -i kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic testTopic --from-beginning
```
Now type 'Hello' on the producer Terminal tab and you will also be able to see it on the consumer terminal tab.

#### 2. Install and configure PostgreSQL on Debian/Ubuntu
 Install PostgreSQL using apt-get
```
$  sudo apt-get install postgresql postgresql-contrib
```
Creat user for etl
```
$  sudo -u postgres createuser --interactive --pwprompt
```
```
Output:
Enter name of role to add: etluser
Enter password for new role: 
Enter it again: 
Shall the new role be a superuser? (y/n) y
```
Create Database under etl user

```
$  sudo -u postgres createdb -O etluser etldb


```
Add the same user to your Unix system
```
$  sudo adduser etluser
```
Login to etldb using user etluser
```
$   sudo -u etluser psql etldb
```
A prompt(etldb=#) will appear, create the following tables
```
 CREATE TABLE Activity (
    equip_id serial PRIMARY KEY,
    type varchar (50) ,
    name varchar (50) ,
    role varchar (50) ,
    time TIMESTAMP ,
    id INT );
```
```
CREATE TABLE login (
    equip_id serial PRIMARY KEY,
    name varchar (50) ,
    role varchar (50) ,
    time TIMESTAMP ,
    id INT,
    age INT
    );
```
To check the schemas, use the following command at the prompt
```
 \dt
```
```
Output:
           List of relations
 Schema |   Name   | Type  |  Owner  
--------+----------+-------+---------
 public | activity | table | etluser
 public | login    | table | etluser
(2 rows)
```
#### 2. Install Maven, to bulid a copy of your own
Open a new terminal and run:
```
$ sudo apt-get install maven
```

## Deployment--Building and Running

To clone this project use git
```
$  git clone https://github.com/attaakkhan/ETL.git

```
To build Producer with maven navigate to Producer.
```
$  cd ETL/Producer/
```
Now build the project with maven
```
$ mvn package
```
Run the producer jar to genrate and send log to kafka servers
```
$ java -jar target/Producer.jar
```
Following is the sample of log generated and sent to kafka servers
```
Logs:`
{"time":"2018-09-15 10:25:02.249","type":"delete","Person":"{\"name\":\"Name101\",\"role\":\"user\",\"id\":101,\"age\":50}"}
{"time":"2018-09-15 10:25:02.249","type":"delete","Person":"{\"name\":\"Name102\",\"role\":\"user\",\"id\":102,\"age\":50}"}
{"time":"2018-09-15 10:25:02.25","type":"delete","Person":"{\"name\":\"Name103\",\"role\":\"user\",\"id\":103,\"age\":50}"}
{"time":"2018-09-15 10:25:02.25","type":"delete","Person":"{\"name\":\"Name104\",\"role\":\"user\",\"id\":104,\"age\":50}"}
{"time":"2018-09-15 10:25:02.25","type":"delete","Person":"{\"name\":\"Name105\",\"role\":\"user\",\"id\":105,\"age\":50}"}
{"time":"2018-09-15 10:25:02.251","type":"delete","Person":"{\"name\":\"Name106\",\"role\":\"user\",\"id\":106,\"age\":50}"}
{"time":"2018-09-15 10:25:02.251","type":"delete","Person":"{\"name\":\"Name107\",\"role\":\"user\",\"id\":107,\"age\":50}"}
{"time":"2018-09-15 10:25:02.251","type":"delete","Person":"{\"name\":\"Name108\",\"role\":\"user\",\"id\":108,\"age\":50}"}
{"time":"2018-09-15 10:25:02.252","type":"delete","Person":"{\"name\":\"Name109\",\"role\":\"user\",\"id\":109,\"age\":50}"}
{"time":"2018-09-15 10:25:02.252","type":"delete","Person":"{\"name\":\"Name110\",\"role\":\"user\",\"id\":110,\"age\":50}"}
{"time":"2018-09-15 10:25:02.252","type":"delete","Person":"{\"name\":\"Name111\",\"role\":\"user\",\"id\":111,\"age\":50}"}
{"time":"2018-09-15 10:25:02.253","type":"delete","Person":"{\"name\":\"Name112\",\"role\":\"user\",\"id\":112,\"age\":50}"}
{"time":"2018-09-15 10:25:02.253","type":"delete","Person":"{\"name\":\"Name113\",\"role\":\"user\",\"id\":113,\"age\":50}"}
{"time":"2018-09-15 10:25:02.253","type":"delete","Person":"{\"name\":\"Name114\",\"role\":\"user\",\"id\":114,\"age\":50}"}
{"time":"2018-09-15 10:25:02.254","type":"delete","Person":"{\"name\":\"Name115\",\"role\":\"user\",\"id\":115,\"age\":50}"}
............

```

Log are genrated and sent to kafka servers, Its time to build the etl consumer to transform these logs and load to psql run:

```
$  cd ../ConsumerETL/
$  mvn package

```
Run the ETL Consumer

```
$ java -jar target/ConsumerETL.jar
```
Now the log has been tarnformed and loaded to psql Database.

Here is the activity table in Psql
```
 equip_id |  type  |  name   | role  |          time           | id  
----------+--------+---------+-------+-------------------------+-----
     1769 | login  | Name0   | admin | 2018-09-15 10:45:00.906 |   0
     1770 | login  | Name1   | admin | 2018-09-15 10:45:01.031 |   1
     1771 | login  | Name2   | admin | 2018-09-15 10:45:01.032 |   2
     1772 | login  | Name3   | admin | 2018-09-15 10:45:01.033 |   3
     .
     .
     .
        1904 | update | Name135 | user  | 2018-09-15 10:45:01.124 | 135
     1905 | update | Name136 | user  | 2018-09-15 10:45:01.124 | 136
     1906 | update | Name137 | user  | 2018-09-15 10:45:01.125 | 137
     1907 | update | Name138 | user  | 2018-09-15 10:45:01.126 | 138
     1908 | update | Name139 | user  | 2018-09-15 10:45:01.126 | 139
(140 rows)
```
And the login table in Psql
```
 equip_id |  name  | role  |          time           | id | age 
----------+--------+-------+-------------------------+----+-----
      335 | Name0  | admin | 2018-09-15 10:43:15.486 |  0 |  50
      336 | Name1  | admin | 2018-09-15 10:43:15.609 |  1 |  50
      337 | Name2  | admin | 2018-09-15 10:43:15.061 |  2 |  50
      338 | Name3  | admin | 2018-09-15 10:43:15.061 |  3 |  50
      339 | Name4  | admin | 2018-09-15 10:43:15.611 |  4 |  50
    .
    .
    .
    .
    .
    
      363 | Name28 | admin | 2018-09-15 10:43:15.627 | 28 |  50
      364 | Name29 | admin | 2018-09-15 10:43:15.628 | 29 |  50
(30 rows)

```
