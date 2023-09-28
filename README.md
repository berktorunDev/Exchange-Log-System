# Exchange Log System

## Overview

This technical documentation provides an in-depth understanding of the Exchange Log System project. The project encompasses a web application that allows users to create and manage text-based posts. Additionally, it captures user interactions such as creating, updating, and deleting posts and logs them using a message queuing system (RabbitMQ) to store them in Elasticsearch.

## Scenario

The Exchange Log System is designed to achieve the following main scenarios:

1. **Managing Posts**: Users can create, read, update, and delete text-based posts. These posts include a title, content, creation date, update date, and the user who created them.

2. **Logging User Interactions**: The system logs user interactions with posts, such as creating, updating, and deleting posts. These interactions are captured using a message queuing system and stored in Elasticsearch.

## Project Components

### Models

#### Post Model
- **id**: A unique identifier for a post.
- **title**: The title of the post.
- **content**: The content of the post.
- **createdAt**: The date and time when the post was created.
- **updatedAt**: The date and time when the post was last updated.
- **user**: The user who created the post.

#### User Model
- **id**: A unique identifier for a user.
- **username**: The username of the user.
- **email**: The email address of the user.
- **role**: The role of the user.
- **posts**: The list of posts created by the user.

### Repositories

#### PostRepository
The PostRepository interface provides CRUD (Create, Read, Update, Delete) operations for the Post model. It extends the JpaRepository interface.

#### UserRepository
The UserRepository interface provides CRUD operations for the User model. It also extends the JpaRepository interface.

### Services

#### PostService
The PostService class contains business logic related to posts. It provides methods for:
- Retrieving a list of all posts.
- Retrieving a post by ID.
- Creating a new post.
- Updating an existing post.
- Deleting a post.

#### UserService
The UserService class contains business logic related to users. It provides methods for:
- Retrieving a list of all users.
- Retrieving a user by ID.
- Creating a new user.
- Updating an existing user.
- Deleting a user.

#### ElasticSearchService
The ElasticSearchService class is responsible for sending log messages to Elasticsearch. It communicates with Elasticsearch using HTTP requests and has methods for sending log messages.

#### RabbitMQService
The RabbitMQService class is responsible for sending log messages to RabbitMQ. It uses the RabbitMQ template to send log messages to a queue named "logQueue."

### Controllers

#### PostController
The PostController class defines a RESTful API for managing posts. It includes endpoints for:
- Retrieving a list of all posts.
- Retrieving a post by ID.
- Creating a new post.
- Updating an existing post.
- Deleting a post.

#### UserController
The UserController class defines a RESTful API for managing users. It includes endpoints for:
- Retrieving a list of all users.
- Retrieving a user by ID.
- Creating a new user.
- Updating an existing user.
- Deleting a user.

### Configuration

#### ModelMapperConfig
The ModelMapperConfig class is a Spring configuration class that defines a ModelMapper bean, which is used for mapping between entities and DTOs.

#### RestTemplateConfig
The RestTemplateConfig class is a Spring configuration class that defines a RestTemplate bean, which is used for making HTTP requests.

#### SecurityConfig
The SecurityConfig class is a Spring configuration class that configures security settings. In this example, it disables CSRF protection.

#### SpringFoxConfig
The SpringFoxConfig class is a Spring configuration class for Swagger documentation. It configures Swagger to document the RESTful APIs.

### Logging

#### Log
The Log class represents a log object that captures details of an operation.

## How Components Work Together

**Managing Posts**:

- Users interact with the system through the PostController, which exposes RESTful endpoints.
- The PostController communicates with the PostService to perform CRUD operations on posts.
- The PostService interacts with the PostRepository to access post data in the database.

**Logging User Interactions**:

- User interactions with posts are logged using the RabbitMQService.
- When a user creates, updates, or deletes a post, a log message is sent to RabbitMQ.
- The ElasticSearchService consumes log messages from RabbitMQ and stores them in Elasticsearch for analysis.
