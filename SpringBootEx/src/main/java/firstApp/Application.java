package firstApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@RequestMapping(value="/redis")
	public String redis() {
		int counter = 0;
		ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
		String key = "redis.counter";
	if(!this.redisTemplate.hasKey(key)) {
		ops.set(key, Integer.toString(counter));
		System.out.println("Key Not Found");
	}else {
		counter = Integer.parseInt(ops.get(key));
		counter++;
		ops.set(key, Integer.toString(counter));
		System.out.println("Key Found" + counter);
		
	}
	
	return ops.get(key);
	}
	
	@RequestMapping(value="/")
	public String Demo() {
		return "Hello World";
	}
}
