# JAVA LEARNING
Some inportant and useful basic Java Code

## Getting Started

Download and open in Netbean 8.xx IDE or any ID
SETUP path ENV variable to jdk bin directory

compilation run in cmd prompt : javac <java_file>.java
To run java <java_file_class>

### To Run MYSQL JDBC File
how to run  : 1) Download Connector/J Platform Independent from https://dev.mysql.com/downloads/connector/j/
              2) Extract and put mysql-connector-java-5.1.45-bin.jar  in the same folder
              3) for compilation run in cmd prompt : javac -cp  mysql-connector-java-5.1.45-bin.jar;. <java_file>.java
              4) To run java -cp  mysql-connector-java-5.1.45-bin.jar;. <java_file_class>

### Prerequisites
* Java SDK 8.xx
* Connector/J  https://dev.mysql.com/downloads/connector/j/
* JDBC CONNECTOR
* MYSQL Database Server https://dev.mysql.com/downloads/mysql/
* XAMPP Server https://www.apachefriends.org/index.html

```

//////////CREATE DATABASE  ////////////
CREATE DATABASE `java_test`;--

//////////  IMPORT for MySQL  /////////////////////

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//// and others


 
```


## Built With

* [Netbeans](https://netbeans.org/downloads/) - Netbeans IDE
* [Java SDK ](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - as per your OS

## Contributing & Issue

Please read [CONTRIBUTING.md](https://github.com/dodobasu/java_swing_mysql_crud/issues) for details on our code of conduct, and the process for submitting pull requests to us.


## Authors

* **Prativas Basu** 


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* includehelp.com

