# Demo application for testing and showcasing SpringBoot features

This application is meant to serve as a demo for testing and showcasing various features of SpringBoot. It includes examples of RESTful APIs, database integration, security configurations, and more.

The idea was a very simple application where a user can register what media he (has) consumed or is consuming. Therefore I've made a simple data model, where you have an Actor, a Media and the connecting entity is the MediaConsumption. In my eyes this gave an interesting example of a relational database, but the implementation is still very rudimentary.

## Features

### RESTful API Endpoints
I've added several RESTful API endpoints to demonstrate how to create and manage resources using SpringBoot, however this is mostly concentrated in the ActorController. In a real application, the controllers would be more extensive, including for example PATCH and DELETE operations in the ActorController and more extended operations than the current in the other controllers.

#### OpenApi Documentation/Swagger
I've made a very basic implementation of the OpenAPI documentation that is in my eyes crucial for any application of this kind. Ideally, I would choose to work actual spec-first, meaning that the OpenAPI specifications should be written and be used to generate interfaces and transfer objects for the implementations. Ideally this should be done in a seperate module in a multimodule maven project, where the implementation of the APIs would be done in another module. This would have several other advantages such as being able to use maven caching.

Also, obviously, OpenAPI documentation should be implemented for all endpoints and transfer objects. For now I've just implemented it for the ActorController.

### Database Integration
For the sake of simplicity I used a simple H2 database in file as a database, with a very simple schema. I think for the sake of a demo this works fine, but obviously should never be used in any production application.

### Flyway
Since I had previous experience with Flyway a long time ago, I wanted to try it out again for this demo. For now it's consisting of 4 simple scripts: 1 to create the tables and 3 to insert some sample data. I know JPA could technically do the same and create the tables from entities, but in my previous application we felt like that was a bit too risky, since it's such a blackbox and we weren't sure how it would behave with existing data.

### JPA/Hibernate
For this application I played around with JPA, using different styles of calls to the database. I'm still not sure which style I prefer most and I think it depends on the application and the complexity of the query. For example, I think JPA queries can be really powerful and intuitive for simple queries, but the method `getMediaConsumptionEntitiesByActor_IdAndMedia_TypeInAndMediaConsumptionStatusIn` in the `MediaConsumptionRepository` is in my eyes a bit too complex to be really readable and maintainable. In that case I would probably prefer to use either a JPQL query or a native query.

You could even argue that you should keep the repository interfaces really simple and only use them for basic CRUD operations, to then do filtering and enhancing of the data in the service layer.


### Spring Security
The implementation of the Spring Security is really rudimentary and to be honest is the first time I've worked with it. I've used online resources to implement it and although I do understand the general gist of it, I certainly am no expert on the details and there are parts that I need extra studying for to fully understand it.

However, I do have experience and understanding of the workings of JWT tokens and basic knowledge of OAuth2 in general, so I do understand the concepts behind the implementation.

### Spring validation
As for Spring Security, this was my first time diving into Spring validation. In contrast to the Spring Security I do feel like I do understand the Spring validation library now. I've mostly focused on the validation for the ActorController once again, so the concepts are not applied consistently in the rest of the application.


## Further needed enhancements

### Package structure
I'm not happy with how I structured the current project, but in my experience this is something you can easily lose yourself in. Therefore, I chose to focuse on actually implementing features, rather than spend too much time on the package structure. However, I do think this is quite a crucial aspect of maintenance. In that regard I think it kind of is like your codestyle: it's better to agree on a style/structure that you don't like and stick to it, than constantly applying a style or structure that you personally prefer at that moment of time.

### Logging
I did not add any logging, but obviously logging is crucial for any real application. What to log and when to log it is a topic of endless discussions however in which I haven't found the perfect answer yet.

### Unit/Integration tests
I think unit and integration tests are crucial for the maintainability of any project. However, due to time constraints I did not add any tests to this demo. Generally I do believe in high code-coverage, however, I do think code-coverage is a dangerous metric to base your tests on.

For Unit tests I generally prefer to really look at a method as a unit and Mock anything outside of that unit using Mockito. This feels tedious at times, but in the long run adds to the maintainability of the code, since it generally is easier to understand what a unittest is actually trying to test.

As for integration tests in my current project I have very good experience with TestContainers (https://java.testcontainers.org/). I've not worked on the implementation myself, but I'm impressed on how smoothly it runs and it really makes end-to-end testing/integration testing a lot better, since you can easily create a database with sample data. I think this would be ideal also for testing the (overly) complex JPA queries.

### JavaDoc
I've written minimal JavaDoc for the demo application and there should be more documentation. However, I tend to have a love/hate relationship with JavaDoc, because I feel like a lot of documentation in the JavaDoc should be redundant with good naming conventions and therefore could be considered "noise". Therefore, I think JavaDoc should be used only if it adds extra information or can explain a concpet for a complex method or class.

In the same line I tend to not write any code commentary, since I'm of the opinion that if you need code commentary to understand a piece of code, the code is not written well enough.

### ErrorHandling
Other than introducing the `GlobalExceptionHandler` for translating exceptions to proper HTTP responses, I did not add any extra error handling.

### Pom
I've paid little attention to the pom.xml, using it purely to add dependencies. I'm a firm believer that the pom should be well structured for maintainability, especially in larger projects.