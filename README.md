# WebSystem I2oT

## Versions

- Java 8

## Getting Started

In this section I will introduce the minimum configuration you need to do to run the project locally. In some cases I wrote some links to videos where you can watch and understand better the configuration you need to do. Most of these videos is from [this Java programming playlist](https://www.youtube.com/playlist?list=PL_GwGUsBlNyfI0W3ggfffhBdJUqB4981Z), this is where, in this period, I acquired the knowledge to build this project. The playlist is in portuguese, but you can use the translator legend that the YouTube allows you to use. I confess that today I recommend to use Javascript and Node.js to build applications like that :), but, let's get started.

### Installing Dependences

#### Installing Java

`If you already have the Java JDK version 8, you can skip this section.`

Well, the first thing we need to do is to install Java. To do so, we can go to the [oracle page](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) and install the `Java for Developers`, the JDK. The version I'm using is the openjdk version "1.8.0_265", that is, Java 8. You can also follow [this video](https://www.youtube.com/watch?v=JGDiGDPIbnM&list=PL_GwGUsBlNyfI0W3ggfffhBdJUqB4981Z&index=2&ab_channel=S%C3%A9rgioRobertoDelfino) to download and install the Java JDK.

#### Installing Eclipse

To install the Eclipse you can follow [this video](https://www.youtube.com/watch?v=vT6l-3iNpNE&list=PL_GwGUsBlNyfI0W3ggfffhBdJUqB4981Z&index=3&ab_channel=S%C3%A9rgioRobertoDelfino).
You can go to [this page](https://www.eclipse.org/downloads/packages/) and download and install the Eclipse IDE for Enterprise Developers, The one with this symbol:

<div align="center">
    <img src="https://i.imgur.com/mhlALwe.png" alt="Image"/>
</div>

After downloading it, you will descompact and start the application. While starting the application, you will need to setup the "workspace" folder, that is the folder where the Eclipse will save all the projects. You can also create this folder before starting the Eclipse and paste this WebSystem I2oT project there.

#### Installing the MySQL database

For this project I used the MySQL workbench to manage the MySQL database. To install it, you can go to [this page](https://www.mysql.com/products/workbench/), or, if you use linux, as I do, you can install it from the store, search for "MySQL Workbench" and install it. I also recommend to follow [this video](https://www.youtube.com/watch?v=YNhhf_N_qZU&list=PL_GwGUsBlNyfI0W3ggfffhBdJUqB4981Z&index=5&ab_channel=S%C3%A9rgioRobertoDelfino), it explains all the steps to install and configure the MySQL Workbench.

In the video you will see how to create a connection. After creating and accessing the connection instance, you will be able to create databases. I already included a dump (backup) of the database that I was using, so you can import the file and automatically create a new database populated. To do that, you enter the instance:

<div align="center">
    <img src="https://i.imgur.com/3WGlFqj.png" alt="Image"/>
</div>

Then you access the "Data Import/Restore":

<div align="center">
    <img src="https://i.imgur.com/B9lBE1K.png" alt="Image"/>
</div>

Then you choose `"Import from Self-Contained File"` and browse the right .sql file that is in `[...]/I2oT/src/main/java/arquivosSQL/databaseBackup.sql`. You can create a new schema in the `"New..."` button and name as `'i2ot'`. Then you can confirm the importing at "Start Import" button:

<div align="center">
    <img src="https://i.imgur.com/TzQbpnR.png" alt="Image"/>
</div>
