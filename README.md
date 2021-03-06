# Spring Cloud Config Server client
This repository is for creating config server and client using various file sources such as GitHub, file system and Vault

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
--------------------------------------

Step1: Create new Spring Boot project. Name the project SpringCloudConfigServer. select packaging as jar and latest version of Java. Select config server dependency

Step2: Open SpringCloudConfigServerApplication.java add @EnableConfigServer annotation

Step3: Create a new repository named SpringConfigData on GitHub

Step4: Go back to spring boot application in STS and open application.properties under src/main/resources and add below properties
        spring.cloud.config.server.git.uri=https://github.com/SatyaVannemreddi/SpringConfigData
        spring.cloud.config.server.native.searchLocations: classpath:offline-repository/
        server.port=8001
Step5: Run the application by right clicking on the project and select Run->Spring Boot App. open below url in browser to view the configuration data as JSON format.
       http://localhost:8001/Config-Data/default/
       
<b>Creating Spring Cloud Config Client</b>
--------------------------------------

Step1: Create new separate Spring Boot Project and name the project SpringCloudConfigClient. select packaging as jar and latest version of Java. Select Config client and Spring web dependencies

Step2: Create bootstrap.properties file under src/main/resources foler and place below properties
        spring.application.name=SpringCloudConfigClient</br>
        spring.cloud.config.uri=http://localhost:8001</br>
        server.port=8002

Step3: Create a rest service to access the properties defined in the Config Server.

@RestController
public class ConfigClientRService {
	@Value("${FirstName}")
	String firstName;
	
	@Value("${LastName}")
	String lastName;
	
	@GetMapping("/hello")
	public String sayHello(){
		return "Hello "+firstName+" "+lastName;
	}

}

Step4: run the app and access url http://localhost:8002/hello. This will give the result Hello Satyanarayana Vannemreddi

<b>Creating Spring Cloud Config Client with refresh without restart app</b>

Step1: Create new separate Spring Boot Project and name the project SpringCloudRefreshConfigData. select packaging as jar and latest version of Java. Select Config client, actuator and Spring web dependencies

Step2: Create bootstrap.properties file under src/main/resources foler and place below properties. this is to specify the config server url
        spring.application.name=personDetails</br>
        spring.cloud.config.uri=http://localhost:8001</br>
        server.port=8002

Step3: Add file named personDetails-PROD.properties with below values in git hub. here PROD is the profile name.

person.firstName=Satyanarayana
person.lastName=Vannemreddi
person.city=Irving
person.state=TX

Step4: Create a bean to access properties to access the properties defined in property file.

@ConfigurationProperties(prefix="person")
@Component

public class PersonDetails {
	String firstName;
	String lastName;
	String city;
	String state;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}

Step5: Create a controller to test the properties values

@RestController
@RefreshScope
public class PersonDetailRestController {
	
	@Autowired
	PersonDetails person;
	
	@GetMapping("/getPersonDetail")
	public PersonDetails getPersonDetail(){
		return person;
	}

}

Step6: Run the app and access url http://localhost:8002/getPersonDetail. It should return properties defined in git repository

Step7: Change the propeties and try the above url again, but properties won't be refreshed this time.

Step8: Call url http://localhost:8002/refresh url to update the properties and try above service url to see the latest property values


