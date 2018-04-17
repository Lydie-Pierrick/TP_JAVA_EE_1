## Start project

    mvn clean package wildfly:run

## Debug SQL content

To deploy h2 console in application server. Once the server is running, do the following : 

    mvn wildfly:deploy-only -Dwildfly.deployment.filename=../src/lib/h2console.war

Then you can access h2console at http://localhost:8080/h2console:

 * select "Generic JNDI Data Source"
 * JDBC URL : "java:jboss/datasources/ExampleDS"
 * User : "sa"
 * Password : "sa"
