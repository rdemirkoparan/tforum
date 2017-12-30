Tech Forum by rdemirkoparan
===========================
 
This is a very basic Forum model. The project created using Spring-boot and Thymeleaf.

# STACK
- Spring Security
- Spring Boot
- Spring Data JPA
- Thymeleaf
- Maven
- H2DB

# LICENSE

This software is licensed under the [Apache License 2.0] (http://www.apache.org/licenses/LICENSE-2.0.html).

# RUN

	mvn clean spring-boot:run
	
# TEST

	mvn test
	
# STRUCTURE

	├── doc
	│   ├── allclasses-frame.html
	│   ├── allclasses-noframe.html
	│   ├── constant-values.html
	│   ├── deprecated-list.html
	│   ├── help-doc.html
	│   ├── index-files
	│   │   ├── index-10.html
	│   │   ├── index-11.html
	│   │   ├── index-12.html
	│   │   ├── index-13.html
	│   │   ├── index-14.html
	│   │   ├── index-15.html
	│   │   ├── index-16.html
	│   │   ├── index-17.html
	│   │   ├── index-18.html
	│   │   ├── index-19.html
	│   │   ├── index-1.html
	│   │   ├── index-2.html
	│   │   ├── index-3.html
	│   │   ├── index-4.html
	│   │   ├── index-5.html
	│   │   ├── index-6.html
	│   │   ├── index-7.html
	│   │   ├── index-8.html
	│   │   └── index-9.html
	│   ├── index.html
	│   ├── org
	│   │   └── rdemirkoparan
	│   │       └── forum
	│   │           ├── AnswerTest.html
	│   │           ├── class-use
	│   │           │   ├── AnswerTest.html
	│   │           │   ├── ForumApp.html
	│   │           │   ├── TopicTest.html
	│   │           │   └── UserTest.html
	│   │           ├── configuration
	│   │           │   ├── class-use
	│   │           │   │   └── SecurityConfig.html
	│   │           │   ├── package-frame.html
	│   │           │   ├── package-summary.html
	│   │           │   ├── package-tree.html
	│   │           │   ├── package-use.html
	│   │           │   └── SecurityConfig.html
	│   │           ├── controller
	│   │           │   ├── AnswersController.html
	│   │           │   ├── class-use
	│   │           │   │   ├── AnswersController.html
	│   │           │   │   ├── TopicsController.html
	│   │           │   │   └── UserController.html
	│   │           │   ├── package-frame.html
	│   │           │   ├── package-summary.html
	│   │           │   ├── package-tree.html
	│   │           │   ├── package-use.html
	│   │           │   ├── TopicsController.html
	│   │           │   └── UserController.html
	│   │           ├── ForumApp.html
	│   │           ├── helper
	│   │           │   ├── AnswerHelper.html
	│   │           │   ├── class-use
	│   │           │   │   ├── AnswerHelper.html
	│   │           │   │   └── TopicHelper.html
	│   │           │   ├── package-frame.html
	│   │           │   ├── package-summary.html
	│   │           │   ├── package-tree.html
	│   │           │   ├── package-use.html
	│   │           │   └── TopicHelper.html
	│   │           ├── model
	│   │           │   ├── Answer.html
	│   │           │   ├── class-use
	│   │           │   │   ├── Answer.html
	│   │           │   │   ├── Topic.html
	│   │           │   │   └── User.html
	│   │           │   ├── package-frame.html
	│   │           │   ├── package-summary.html
	│   │           │   ├── package-tree.html
	│   │           │   ├── package-use.html
	│   │           │   ├── Topic.html
	│   │           │   └── User.html
	│   │           ├── package-frame.html
	│   │           ├── package-summary.html
	│   │           ├── package-tree.html
	│   │           ├── package-use.html
	│   │           ├── repository
	│   │           │   ├── AnswerRepository.html
	│   │           │   ├── class-use
	│   │           │   │   ├── AnswerRepository.html
	│   │           │   │   ├── TopicRepository.html
	│   │           │   │   └── UserRepository.html
	│   │           │   ├── package-frame.html
	│   │           │   ├── package-summary.html
	│   │           │   ├── package-tree.html
	│   │           │   ├── package-use.html
	│   │           │   ├── TopicRepository.html
	│   │           │   └── UserRepository.html
	│   │           ├── service
	│   │           │   ├── AnswerService.html
	│   │           │   ├── class-use
	│   │           │   │   ├── AnswerService.html
	│   │           │   │   ├── TopicService.html
	│   │           │   │   └── UserService.html
	│   │           │   ├── impl
	│   │           │   │   ├── AnswerServiceImpl.html
	│   │           │   │   ├── class-use
	│   │           │   │   │   ├── AnswerServiceImpl.html
	│   │           │   │   │   ├── TopicServiceImpl.html
	│   │           │   │   │   ├── UserDetailsServiceImpl.html
	│   │           │   │   │   └── UserServiceImpl.html
	│   │           │   │   ├── package-frame.html
	│   │           │   │   ├── package-summary.html
	│   │           │   │   ├── package-tree.html
	│   │           │   │   ├── package-use.html
	│   │           │   │   ├── TopicServiceImpl.html
	│   │           │   │   ├── UserDetailsServiceImpl.html
	│   │           │   │   └── UserServiceImpl.html
	│   │           │   ├── package-frame.html
	│   │           │   ├── package-summary.html
	│   │           │   ├── package-tree.html
	│   │           │   ├── package-use.html
	│   │           │   ├── TopicService.html
	│   │           │   └── UserService.html
	│   │           ├── TopicTest.html
	│   │           ├── UserTest.html
	│   │           ├── util
	│   │           │   ├── class-use
	│   │           │   │   ├── DateTimeUtil.html
	│   │           │   │   ├── GlobalConstants.html
	│   │           │   │   └── StringUtil.html
	│   │           │   ├── DateTimeUtil.html
	│   │           │   ├── GlobalConstants.html
	│   │           │   ├── package-frame.html
	│   │           │   ├── package-summary.html
	│   │           │   ├── package-tree.html
	│   │           │   ├── package-use.html
	│   │           │   └── StringUtil.html
	│   │           └── validation
	│   │               ├── class-use
	│   │               │   └── UserValidator.html
	│   │               ├── package-frame.html
	│   │               ├── package-summary.html
	│   │               ├── package-tree.html
	│   │               ├── package-use.html
	│   │               └── UserValidator.html
	│   ├── overview-frame.html
	│   ├── overview-summary.html
	│   ├── overview-tree.html
	│   ├── package-list
	│   ├── script.js
	│   └── stylesheet.css
	├── LICENSE.txt
	├── pom.xml
	├── README.md
	├── src
	│   ├── main
	│   │   ├── java
	│   │   │   └── org
	│   │   │       └── rdemirkoparan
	│   │   │           └── forum
	│   │   │               ├── configuration
	│   │   │               │   └── SecurityConfig.java
	│   │   │               ├── controller
	│   │   │               │   ├── AnswersController.java
	│   │   │               │   ├── TopicsController.java
	│   │   │               │   └── UserController.java
	│   │   │               ├── ForumApp.java
	│   │   │               ├── helper
	│   │   │               │   ├── AnswerHelper.java
	│   │   │               │   └── TopicHelper.java
	│   │   │               ├── model
	│   │   │               │   ├── Answer.java
	│   │   │               │   ├── Topic.java
	│   │   │               │   └── User.java
	│   │   │               ├── repository
	│   │   │               │   ├── AnswerRepository.java
	│   │   │               │   ├── TopicRepository.java
	│   │   │               │   └── UserRepository.java
	│   │   │               ├── service
	│   │   │               │   ├── AnswerService.java
	│   │   │               │   ├── impl
	│   │   │               │   │   ├── AnswerServiceImpl.java
	│   │   │               │   │   ├── TopicServiceImpl.java
	│   │   │               │   │   ├── UserDetailsServiceImpl.java
	│   │   │               │   │   └── UserServiceImpl.java
	│   │   │               │   ├── TopicService.java
	│   │   │               │   └── UserService.java
	│   │   │               ├── util
	│   │   │               │   ├── DateTimeUtil.java
	│   │   │               │   ├── GlobalConstants.java
	│   │   │               │   └── StringUtil.java
	│   │   │               └── validation
	│   │   │                   └── UserValidator.java
	│   │   └── resources
	│   │       ├── application.properties
	│   │       ├── static
	│   │       │   ├── css
	│   │       │   │   ├── bootstrap.min.css
	│   │       │   │   └── main.css
	│   │       │   ├── fonts
	│   │       │   │   ├── glyphicons-halflings-regular.eot
	│   │       │   │   ├── glyphicons-halflings-regular.svg
	│   │       │   │   ├── glyphicons-halflings-regular.ttf
	│   │       │   │   ├── glyphicons-halflings-regular.woff
	│   │       │   └── js
	│   │       │       ├── bootstrap.min.js
	│   │       │       └── jquery.min.js
	│   │       └── templates
	│   │           ├── answers.html
	│   │           ├── fragments
	│   │           │   ├── footer.html
	│   │           │   └── header.html
	│   │           ├── homeNotSignedIn.html
	│   │           ├── homeSignedIn.html
	│   │           ├── login.html
	│   │           ├── signup.html
	│   │           ├── topic.html
	│   │           └── topics.html
	│   └── test
	│       └── java
	│           └── org
	│               └── rdemirkoparan
	│                   └── forum
	│                       ├── AnswerTest.java
	│                       ├── TopicTest.java
	│                       └── UserTest.java
	└── TODO


# ROADMAP

 - Add password confirm, status, email, role and picture attributes to user
 - Implement custom validators
 - Add notification to user when topic answered or when answer found helpful
 - Detailed messages to user
 - I18n support
 - User type and permission change according to reputation
 - Add keywords and category to topics
 - Add overview to users
 - Improve helpful mechanism, implement voting model
 - Add enchanced ordering features to topics screen
