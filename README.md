# WebSystem I²oT (Inventory IoT)

This repository holds a prototype of the project I²oT, an inventory system that aims to monitor and track the patrimonial gods of an institution. The web system works like a social media where the smart objects are your friends and you can see information about them. We also created an Ontology called I²oTology, which is an extension of the [IoT-Lite](https://www.w3.org/Submission/iot-lite/), a light ontology for IoT devices.

## Links

To understand more about the project, there are some articles and reports you can access:

**Articles**

- [I²oTegrator: a Service-Oriented IoT Middleware for Intelligent Object Management](https://ieeexplore.ieee.org/document/8538541) (English)

- [I²oTology - Tracking-Oriented Ontology](http://ceur-ws.org/Vol-2228/short5.pdf) (English)

**Reports**

- [InventoryIoT (I2oT): uma plataforma de gerenciamento automatizado de inventário](https://drive.google.com/file/d/152A3dqGNh61SnI3yNWC_NEChE7sum07i/view?usp=sharing) (Portuguese)

## Technologies used

- Java
- Eclipse
- Hibernate
- Tomcat Server
- [Apache Jena](https://jena.apache.org/)
- Protégé

## Documentation improvements

Currently I'm improving the documentation of this project to help others to understand what I did and how you can contribute with the community in this field. This table shows the goals and tasks I have reached and what are the ones I still need to complete:

| Goal                                   | Description                                                                                            | Status                   | Date     |
| -------------------------------------- | ------------------------------------------------------------------------------------------------------ | ------------------------ | -------- |
| Update Repository                      | Structure the project and update the GitHub repository.                                                | :heavy_check_mark:       | 10/03/20 |
| Write Getting Started                  | Write a getting started readme with the configuration needed to run the project locally.               | :heavy_check_mark:       | 10/03/20 |
| Write Architecture readme              | Write about the architecture/layers I used in this project.                                            | :heavy_check_mark:       | 10/04/20 |
| Write a table of tasks                 | Write about all tasks I have done and what must be done to continue improve the project documentation. | :heavy_check_mark:       | 10/04/20 |
| Write a project overview               | Write about how the project should work, what are the pages, what is done until now.                   | :hourglass_flowing_sand: |          |
| Write Ontology readme                  | Write a readme about the ontology and how to get started using the Protégé.                            | :hourglass_flowing_sand: |          |
| Write a Contributing readme            | Write about how someone can contribute to this project using this repository.                          | :hourglass_flowing_sand: |          |
| List all the service routes            | List the routes created in the servers showing the HTTP method and what is used for.                   | :hourglass_flowing_sand: |          |
| Improve the code documentation/javadoc | Improve the documentation of all the code.                                                             | :hourglass_flowing_sand: |          |

## The project

## Project Architecture

Basically the system architecture diagram flows like this:

<div align="center">
    <img src="https://i.imgur.com/MsFHP42.png" alt="Image"/>
</div>

Here I will explain each layer breafly:

- **Model:**
  The model is the definition of the objects that will be persisted to the database and also used by the system as a normal object manipulation. In this layer I created a Generic Model where were created all the common caracteristics that all objects will have (e.g. ID and some set and get functions), and all the other models inherit from this Generic Model these characteristics, so that we can access the inherited caracteristics and also create others that are specific for this object/model.

- **Database:**
  The database I used for this this project is the MySQL, but it could be anyone, it was a choice based in the popularity and the highly use of this database by enterprises. To manage the database, the MySQL Workbench was used, To understand more about configuration, see the [Getting Started page](https://github.com/Levysantiago/SistemaWeb-I2oT/blob/master/GETTING_STARTED.md).

- **DAO:**
  The DAO (Data Access Object) is a pattern for data persistence. This pattern allow us to uncouple the application from the data persistence layer. It's also good for code management, since a layer does not "disturb" the other when trying to update a code from a layer. This pattern allows us to configure the rules to persist a model to the database. Similar to the Model Layer, I created a GenericDAO which holds all the rules that are common to all the other DAOs that I created, so all of them will inherit from this GenericDAO and eventually can also implements specific rules.

- **Service:**
  The service is a layer created in order to uncouple the application to the database and server responsibilities, so that the layers could be divided and configured in different hosts. The service uses the Model and the DAO layers to create routes that can be triggered through RESTful HTTP requests.

- **Controller:**
  The controller layer in this case is used to call the Service routes and then update the view when requested by it. It needs to use the Model to "understand" how to interact with the objects returned by the services. It also connects to the Ontology Service and Ontology Model that will work similarly to the Service and Model layers.

  `OBS: In some frameworks the concept of the Controller changes. Some of them abstracts the Service layer and uses the Controller as the routes definer. So in this case the Controller offer the RESTful routes and the View layer interact with the Controller through the RESTful API. For example, in javascript you can use the Adonis.js as the server to offer the Service through the Controller layer, and use the React.js to construct the View layer and the Axios.js to make requests to the Adonis server.`

- **View:**
  The view is the frontend HTML tags that will communicate with the Controller to get the updated data received by the requests to the services. The view pages are located at SistemaWeb-I2oT/src/main/webapp/pages. When you run the Tomcat server, it will serve the services and also the view pages, and you are able to access the routes through some RESTful API caller (as [Insomnia](https://insomnia.rest/)) to make GETs and POSTs HTML requests and use the browser to access the pages.

- **Ontology:**
  The ontology is where some objects are configured and classified in order to infer new information about them and return to the system which will execute some decision. This ontology is an extension of the [IoT-Lite](https://www.w3.org/Submission/iot-lite/), a light ontology for IoT devices, we took this ontology and created some more classes that we needed to use within this project. The ontology is located in SistemaWeb-I2oT/src/main/java/ontology/i2otology.owl.

- **Ontology Model:**
  It's similar to the Model layer, the difference is that we define the caracteristics thinking about the Ontology and not the Database, because of that, these models cannot inherits from the GenericModel once the Ontology model caracteristics are different from the Database model caracteristics.

- **[Jena Library:](https://jena.apache.org/)**
  Is the library created by the Apache used to communicate with the Ontology file. [Here](https://jena.apache.org/getting_started/index.html) you can see more information about the documentation. This library also offers some methods where we can activate the reasoner and make some inferences inside the ontology.

- **Ontology DAO:**
  It's similar to the DAO layer, but it will not use the database to persist the data, it will use the library Jena to communicate with the ontology file (i2otology.owl).

- **Ontology Service:**
  Similar to the Service, but will serve the ontology routes using the Ontology DAO layer and the Ontology Model layer.

## Getting Started

Go to the [Getting Started page](https://github.com/Levysantiago/SistemaWeb-I2oT/blob/master/GETTING_STARTED.md)

## Authors

- Jauberth Abijaude
- Péricles Sobreira
- Fabiola Greve
- Levy Santiago

You can ask me anything, here is my email: **levyssantiago@gmail.com**, I have done this project for a little while so I don't remmember much everything I did, but I'm willing to help with any problems you might have about configuring the project to your machine.
