# Spring Cloud Config Server client
This repository is for creating config server and client using various file source such as GitHub, file system and Vault

Microservice need various configuration data to serve different busineeds. Placing configuration details within the application is not a good idea because it requires service restart every time you update the configuration. So it requires solution to keep all the configuration data at a centralized place and that can be updated at any point of time and those values should be availabe to clients without restarting/restaging the services. Spring Cloud provides Config Server and Client APIs to resolve this issue.

Pros:
1. All configuration related each service would be in centralized secure place
2. No need of service restart/restage to modify the configuration details
3. Offers great level security by encrypting the config details
4. Flexibility to use configuration based on specific spring profile
5. Supports .property files and .yml files

Cons:
1. Causes services to malfunction in case of config server itself is down
2. Client services take more time to load config data first time the service is started

<b>Note:</b> All the instructions based on STS(Spring Tool Suite) IDE.

<b>Creating Spring Cloud Config Server</b>
------------------------------------

Step1: Create new Spring Boot project. Name the project SpringCloudConfigServer. select packaging as jar and latest version of Java. Select config server dependency

Step2: Open SpringCloudConfigServerApplication.java add @EnableConfigServer annotation

Step3: Create a new repository named SpringConfigData on GitHub





