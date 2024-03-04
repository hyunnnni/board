package com.green.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

@EnableJpaAuditing //jpa는 auditing기능을 사용할 것이다 날짜값이 자동으로 들어가게 해쥬는 기능
@ConfigurationPropertiesScan //yaml에 있는 기능을 객체화해서 쓸 때 필요한 애노테이션
@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardApplication.class, args);
    }

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customizer(){ //1부터 시작하는 페이지 번호 사용 ( 0,1 둘 다 1페이지로 처리 )
        return p -> p.setOneIndexedParameters(true);
    }

}
