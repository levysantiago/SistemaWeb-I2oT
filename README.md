# WebSystem I2oT

## Getting Started

### Installing Dependences

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

Then you choose "Import from Self-Contained File" and browse the right .sql file that is in `I2oT/src/main/java/arquivosSQL/databaseBackup.sql`. You can create a new schema in the "New..." button and name as `'i2ot'`. Then you can confirm the importing at "Start Import" button:

<div align="center">
    <img src="https://i.imgur.com/TzQbpnR.png" alt="Image"/>
</div>
