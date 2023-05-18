package com.example.recommendpharmacy

import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.GenericContainer
import spock.lang.Specification

@SpringBootTest
abstract class AbstractIntegrationContainerBaseTest extends Specification {

    static final GenericContainer MY_REDIS_CONTAINER

    static {
        MY_REDIS_CONTAINER = new GenericContainer<>("redis:6")
                .withExposedPorts(6379) // 도커 컨테이너 포트를 지정, HOST 포트는 임의의 사용하지 않는 포트를 지정해준다.

        MY_REDIS_CONTAINER.start()

        System.setProperty("spring.redis.host", MY_REDIS_CONTAINER.getHost()) // 스프링에게 HOST 포트를 알려줬다.
        System.setProperty("spring.redis.port", MY_REDIS_CONTAINER.getMappedPort(6379).toString())
        // 스프링에게 host 포트와 연결된 컨테이너 포트를 알려준다.

    }
}
