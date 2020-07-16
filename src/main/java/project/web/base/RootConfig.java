package project.web.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages= {"config"})
@ComponentScan(basePackages= {"com.kosmo.ver2"})
@ComponentScan(basePackages= {"mvc.board"})
@ComponentScan(basePackages= {"android.mvc"})
public class RootConfig {

}
