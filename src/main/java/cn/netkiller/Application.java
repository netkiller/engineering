package cn.netkiller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{
    public static void main( String[] args )
    {
        System.out.println( "Netkiller bottleneck tool!" );
        SpringApplication.run(Application.class, args);
    }
}
