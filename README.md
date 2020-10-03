# WebSystem I2oT

## Versions

- Java 8
- Tomcat 8.0.53

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

After downloading it, you will descompact and start the application. While starting the application, you will need to setup the `"workspace" folder`, that is the folder where the Eclipse will save all the projects. You can also create this folder before starting the Eclipse and paste this WebSystem I2oT project there.

#### Opening the project

If the project doesn't appear in the `Eclipse project Explorer` (in the left side of the IDE), you can go to the menu `File/Open Projects From Filesystem...`, browse your file, select it and click in `"Finish"`.

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

Then you choose `"Import from Self-Contained File"` and browse the right .sql file inside the project in your workspace that is in `[...]/I2oT/src/main/java/arquivosSQL/databaseBackup.sql`. You can create a new schema in the `"New..."` button and name as `'i2ot'`. Then you can confirm the importing at "Start Import" button:

<div align="center">
    <img src="https://i.imgur.com/TzQbpnR.png" alt="Image"/>
</div>

After importing the database, you'll be able to iteract with it, accessing the tab `Schemas`.

<div align="center">
    <img src="https://i.imgur.com/qUqzBxY.png" alt="Image"/>
</div>

#### Configuring the database in the project

In the project `src/main/resources/` folder you will see that we have the `hibernate.cfg.xml` file, this is the project configuration file. You will open it and configure with your database credentials you created in the MySQL Workbench.

```
<property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
<property name="connection.url">jdbc:mysql://127.0.0.1:[port]]/[databasename]]?useTimezone=true&amp;serverTimezone=UTC</property>
<property name="connection.username"> [user] </property>
<property name="connection.password"> [password] </property>
```

Update the following information:

- \[port\]: the mysql port, usually 3306;
- \[databasename\]: the database name that you created when imported the .sql file and created the schema;
- \[user\]: the database user;
- \[password\]: the user password;

After configuring the file, it should be like this:

```
<property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>
<property name="connection.url">jdbc:mysql://127.0.0.1:3306/i2ot?useTimezone=true&amp;serverTimezone=UTC</property>
<property name="connection.username">root</property>
<property name="connection.password">123</property>
```

#### Configuring the ontology path

Inside the file `SistemaWeb-I2oT/src/main/java/com/uesc/lif/i2ot/util/OntologyManager.java` you will see the following code lines:

<div align="center">
    <img src="https://i.imgur.com/1vOs2PJ.png" alt="Image"/>
</div>

Here you will update the **filePath** variable to the right path of the ontology. The ontology is located in `SistemaWeb-I2oT/src/main/java/ontology/i2otology.owl`, so you can just change the fist part of the string `"home/levy/eclipse-workspace/"` and put your path.

#### Configuring the Apache Tomcat

[This video](https://www.youtube.com/watch?v=aJ7kdjzIVcw&list=PL_GwGUsBlNyfI0W3ggfffhBdJUqB4981Z&index=32&ab_channel=S%C3%A9rgioRobertoDelfino) explains all the proccess of downloading and configuring the Tomcat inside the Eclipse, but I will explain breafly here too. In this project I used the Tomcat version 8.0.53.
The Tomcat is the server I used to run the project. To install it, you will access the [Tomcat download page](https://archive.apache.org/dist/tomcat/tomcat-8/v8.0.53/bin/) and choose one of the following compacted packages:

<div align="center">
    <img src="https://i.imgur.com/BLhZ8bp.png" alt="Image"/>
</div>

After downloading, you will unzip in some folder. Then inside the eclipse, you will access the `Server` tab and inside the block, click with the right button of the mouse, then "New/Server".

<div align="center">
    <img src="https://i.imgur.com/IFq2uTl.png" alt="Image"/>
</div>

A new modal will open, then you click to expand the Apache option:

<div align="center">
    <img src="https://i.imgur.com/0keiJRt.png" alt="Image"/>
</div>

Scroll down and select the Tomcat version 8, then click "Next":

<div align="center">
    <img src="https://i.imgur.com/HScxqS3.png" alt="Image"/>
</div>

Here you will browse the Tomcat that you extracted then click "Next":

<div align="center">
    <img src="https://i.imgur.com/Bg5psNa.png" alt="Image"/>
</div>

Now you need to add the project to the server clicking at the project name, then "Add", then "Finish".

<div align="center">
    <img src="https://i.imgur.com/5kLlrgp.png" alt="Image"/>
</div>

Now you should be able to run the server by selecting the server and clicking the green button to run:

<div align="center">
    <img src="https://i.imgur.com/uT51qLI.png" alt="Image"/>
</div>

If everything went well, you should be able to access the smart objects page at http://localhost:8080/I2oT/pages/objectsPage.xhtml
