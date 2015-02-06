# jVoiD

## Introduction 

jVoiD is an open-source extensible ecommerce architecture for the enterprise using J2EE, Spring MVC, Spring Security with OAUTH2, Hibernate, Bootstrap/AngularUI, AngularJS that can run in TomCat or JBoss Servers.

## Getting Started

### Prerequisites

> Java 7 or Java 8 Development Kit (JDK 7 or JDK 8).
> Apache Maven 3.1 and above.
> Eclipse IDE or any other Java IDE.
> Spring 4.0 and above.

## Key Features and Technologies

### Java J2EE

Java J2EE technology aims to simplify the design and implementation of enterprise applications. It is highly scalable to meet variations in demand and can be integrated with existing information systems. It also provides complete web services support along with various choices of servers, tools and components with flexible security.

### Spring MVC

jVoiD is based on Spring MVC. Spring is the most popular application development framework for enterprise Java. It is a powerful yet lightweight container. It is not server dependant and does not require special deployment steps. Use of Dependency Injection along with AOP(Aspect Oriented Programming) makes object creation and dependency management easy.

### Spring OAuth2

Spring provides a highly flexible OAuth2 implementation for security. It is possible to write OAuth client, OAuth resource server and also OAuth authorization server. It enables Spring to integrate and inherit the feature of OAuth2 security with almost complete configurability.

### Hibernate ORM

Hibernate is an ORM(Object Relational Mapping) implementation. It is database independent. It will work with all kind of databases like Oracle, MySQL, NoSQL etc. It has a layered architecture and supports inheritence, associations and collections. SQL knowledge is not necessary to work with it. It also provides caching mechanism for better performance. This also controls the persistence in jVoiD.

### Bootstrap AngularUI

Bootstrap is an increasingly popular front-end development framework. It enables the use of ready-made blocks of code to help speedy development. Its fluid grid layout provides proper responsiveness without much effort. It is highly consistent and customizable with the ability to choose features that are needed.

### AngularJS

AngularJS is a framework for single page applications which uses proper MVC. Two way data binding and saving to the server is made easy in this. Applications developed using AngularJS are more maintainable and provides good testability. Context aware communication is another important feature provided by AngularJS.

### REST JSON API

REST JSON API's can be consumed everywhere including front-end applications, mobile applications or any other devices that have access to internet. It is a lot smaller that other API's resulting in less data transfer over the network. It is highly efficient and provides caching to improve response times. Moreover, REST JSON interfaces are easy to design and implement.

### Maven Build Tool

Maven is a tool, that can be used to build and manage any java-based project. It makes dependecy management for complex projects easy. Apart from that, it also makes the build process easy, by providing a uniform build system. Transparent migration to new features is another important feature of Maven, which makes it a powerful tool.

### CDN Hosted

CDN(Content Delivery Network) is a revolution in web hosting. It distributes the files and load of a website across multiple systems rather than hosting it in a single server. It boosts performance, providing high speed serving of web pages by distribting the load. It can save a lot of bandwidth and hosting costs.

### Server Independent

jVoiD can be deployyed on JBoss or TomCat. Whatever suits your needs. This will work!

### Technology Independent Frontend

Requests and responses in jVoiD are in JSON. In fact the front end can even be a native mobile app!

### Loosely Coupled Logical Blocks

Each logical block in jVoiD can be managed and even hosted separately. Host frontend blocks (like catalog) on faster servers.

### Secure Communication

jVoiD makes communication between blocks highly secure as the REST-JSON calls between block happens via the Core. Inter-block communication restricted to local IPs!

### External Inventory Management

Configure the shop to use an external inventory system. Prevent duplicacy of inventory management.

### White-Label Shops

Easily create white-label shops and manage them via a single login.

## Local Framework Development

jVoiD recommends JDK 1.7, but will build/run with JDK 1.6 or 1.7.

If you want to fix a bug or contribute to jVoiD, see our [CONTRIBUTING.md](CONTRIBUTING.md) guidelines.

The easiest way to make changes to the jVoiD framework (not typical) is to clone this repository and execute a clean install via Maven from the jVoidBundle folder:

```sh
mvn clean install
```

If you want to temporarily skip our integration tests:

```sh
mvn clean install -DskipTests
```

> Note: all contributed code must have passing tests via Maven

If you need to use a specific version of jVoiD, simply check out that version using either the branch or tag. All releases are tagged 'jVoiD-<version>'.

```sh
git clone git@github.com:schogini/jVoiD

# use 1.0.x-SNAPSHOT
git checkout jVoiD-1.0.x
mvn clean install
```

## License

jVoiD is released under the terms of the Apache Software License 2 (see LICENSE.txt).

We also offer various levels of [enterprise support options](http://www.jvoid.com). Please [contact us](http://www.jvoid.com) for detailed information.


