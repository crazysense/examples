package examples.neo4j;

import java.util.Collection;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import examples.neo4j.domain.User;
import examples.neo4j.service.Neo4JService;

@ImportResource("classpath:/META-INF/spring/app-config.xml")
@ComponentScan(basePackages = "examples.neo4j")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class Neo4jApplication {

	public static void main(String[] args) {
		ApplicationContext context = new SpringApplicationBuilder(Neo4jApplication.class).web(false).run(args);

		Neo4JService service = context.getBean(Neo4JService.class);

		// Get all users.
		Collection<User> users = service.getAllUsers();

		for (User user : users) {
			System.out.println(String.format("User [%d] -> name : %s / gender : %s", 
					user.getId(), user.getName(), user.getGender().toString()));
		}
		
		// Get users by name.
		Collection<User> dylan = service.getUser("Dylan");
		for (User user : dylan) {
			System.out.println(String.format("User [%d] -> name : %s / gender : %s", 
					user.getId(), user.getName(), user.getGender().toString()));
		}
	}
}
