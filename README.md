This is Spring boot Circuit breaker simulation project.

Circuit breaker URL: http://localhost:9003/actuator/circuitbreakers
Valid REST endpoint: http://localhost:9003/weather?city=Zurich
This is show circuit breaker state as CLOSED

----

Rest service call error: http://localhost:9003/weather?city=error
This is show circuit breaker state as OPEN --> HALF-OPEN

Rest service call slow response timeout:
http://localhost:9003/weather?city=timeout
