# Card-Auth-Server-Extensions

Card Auth Server Extensions is an open-source repository providing extension modules to enhance the functionality of the Card Auth Server (ACS), 
a 3D Secure Access Control Server designed to add an extra layer of security to online credit and debit card transactions. 
These extensions facilitate integration with various systems, offering additional features and security measures.
For more detailed explanation and feature [visit here](https://github.com/freedom-finance-stack/card-auth-server).

### 1. Crypto Package

The `crypto` package provides a comprehensive set of interfaces and implementations related to encryption. It encompasses various cryptographic algorithms and functionalities, allowing developers to secure sensitive data through encryption and related techniques. This package is often used in software development to ensure the confidentiality and integrity of information, providing a robust foundation for cryptographic operations in applications.

There are following Interfaces:
- **IEncryption:**
  - The IEncryption interface outlines a contract for encryption and decryption operations within a software system. It likely includes method signatures such as encrypt and decrypt, providing a standardized interface for incorporating different encryption algorithms. Implementing classes or modules can adhere to this interface, promoting a consistent and interchangeable approach to data security within the application architecture.

There are pre-existing implementations of the above interface
- **AES256Encryption:**
    - The `AES256Encryption` class is an implementation of the IEncryption interface using the AES-256 encryption algorithm. It provides methods for encrypting and decrypting text using a specified configuration.

- **NoOpEncryption:**
    - The `NoOpEncryption` class is an implementation of the IEncryption interface that performs no encryption or decryption. It simply returns the input text as is.

- **AES256EncryptionConfig:**
    - The AES256EncryptionConfig class is a configuration class for AES-256 encryption. It is used to store the password and salt values required for encryption.
  
> Feel free to customize them as per your requirements and don't forget to put it in the application.yml file for the application to determine appropriate config for Encryption.

### 2. External Libs Package

The externalLibs package houses essential components to augment the functionality and security of the open-source project. It includes RequestInterceptorConfig for customizable request processing and SecurityModuleAWS for seamless integration with Amazon Web Services (AWS) security features. Developers can leverage these libraries to enhance project capabilities and strengthen security measures.

### 3. HSM Package

The `HSM` module provides a comprehensive framework for Hardware Security Module (HSM) interactions, encompassing diverse functionalities such as command handling, CVV generation, Luna HSM communication configuration, and message processing. With abstract templates, interfaces, and example implementations.

- **Luna Implementation**
  - The `Luna` implementation within the HSM module facilitates communication with Luna HSMs. It includes comprehensive configurations such as HSM gateway setup, initialization, and communication with Luna HSMs. The `LunaEE0802HSMCommandImpl` class offers an example implementation for generating Card Verification Value (CVV) codes using Luna HSM. Users can further customize or contribute their implementations, allowing seamless integration into the application by updating the configuration in application.yml.
- **NoOp Implementation**
  - The `NoOp` (No Operation) implementation provides a mock HSM command operation, designed for scenarios where actual HSM operations are not required. The `NoOpHSMCommandImpl` class acts as a placeholder, abstaining from real HSM operations and serving as a template for users to create their custom NoOp implementations. Users can easily configure this implementation in the `application.yml` file.
- **CVV Module**
  - The `CVV` module in conjunction with the HSM module offers a seamless solution for Card Verification Value (CVV) generation. The `CVVFacade` interface defines a method for generating CVV values using HSM commands. The example implementation, `CVVFacadeImpl`, demonstrates the validation of input HSM messages, utilizing the appropriate HSM command for CVV generation. Users are encouraged to implement their CVV generation logic, updating the `application.yml` configuration for effortless integration.

- **Configuring HSM Implementations**
  - Users have the flexibility to configure and add more HSM implementations based on their security and operational needs. The `HSMCommandModule` class serves as a central configuration hub, orchestrating the creation of beans related to HSM command functionality in a Spring application. The inclusion of additional implementations involves updating the application context with the desired configurations.

- **Adding Custom HSM Implementations**
  1. Create a new implementation of the `HSMCommand` abstract class.
  2. Implement the required methods for initialization, serialization, sending requests, and processing responses.
  3. Configure the new implementation in the application.yml file by updating the HSM module configuration.

```yaml
hsm:
  implementations:
    - type: luna
      enabled: true
    - type: custom
      enabled: true
      # Add additional custom configuration options as needed

```
> Users can seamlessly customize HSM-related behavior and adapt the application to specific security requirements by configuring parameters in the `application.yml file`.


### 10. Notification Package

The `notification` package serves as the central hub for a comprehensive notification system, housing packages such as `DTO`, `Enums`, `Exceptions`, `Factory`, and `Impl`. It provides a modular and extensible framework for handling email and SMS notifications, offering structured DTOs, customizable enums, exception handling, and factory classes. Users can tailor the notification system by extending and customizing these components, with configurations managed conveniently in `application.yml`. 
#### Interfaces

1. `EmailNotificationService`

    This interface defines common functionalities for email notification services. Users can implement this interface to create custom email notification services. The core methods include:

  - `getEmailChannelType()`: Returns the type of email channel used for notification.
  - `send(EmailNotificationDto notificationDto)`: Sends an email notification based on the provided `EmailNotificationDto`.

2. `SMSNotificationService`
   
    Similar to the EmailNotificationService, this interface defines common functionalities for SMS notification services. Users can implement this interface to create custom SMS notification services. Key methods include:

- `getSMSAPIType()`: Returns the type of SMS channel used for notification.
- `send(SMSNotificationDto smsNotificationDto)`: Sends an SMS notification based on the provided `SMSNotificationDto`.

#### Implementations
1. `EmailNotificationFactory`

    This factory class is general implementation of the  `EmailNotificationService` interface based on the configuration. Users can extend or replace this implementation to customize email notification services. 

  Configuration in `application.yml` looks like:
```yaml
notification:
  email:
    enabledChannel: "custom-email-server"
    customSMTP:
      host: your-custom-smtp-server.com
      port: your-custom-port-number
```
2.  `SMSNotificationFactory`
      Similar to the `EmailNotificationFactory`, this class  is general implementation of the  `SMSNotificationService` based on the configuration. Users can extend or replace this implementation to customize SMS notification services. 

Configuration example:
```yaml
notification:
  sms:
    enabledChannel: "custom-sms-server"
```

Custom Implementations
Users can leverage the example implementations provided in the factory package as a reference for creating their custom notification services. By implementing new classes that adhere to the EmailNotificationService and SMSNotificationService interfaces, users can seamlessly integrate their services into the application. Configuration in application.yml allows users to specify their custom implementations:
```yaml
notification:
  email:
    enabledChannel: "custom-email"
    customSMTP:
      host: your-custom-smtp-server.com
      port: 587
```

### 11. Scheduled Task Package

The `scheduled-task` package provides a robust module for efficient management of scheduled tasks within an application. It encompasses configuration classes for tuning task scheduler properties, exception handling for task-related errors, and a versatile `TaskSchedulerWrapper` class for seamless task scheduling and cancellation using a custom `ScheduledExecutorService`. Users can easily integrate and extend these components, tailoring the scheduling behavior to their specific needs by adjusting configurations in the `application.yml` file.

### 12. State Machine Package

The `state-machine` package constitutes a cohesive module for implementing and managing state machines within an application. It encompasses essential components such as a custom exception class (`InvalidStateTransactionException`) for handling invalid state transitions, a generic `State` interface for defining states and transitions, the `StateMachine` class providing methods for triggering transitions, and the `StateMachineEntity` interface serving as a foundation for state machine entities. Users can seamlessly integrate, extend, and configure these components according to their specific state machine requirements, with customization options conveniently available in the `application.yml` file.

- **State:**
    - The `State` interface plays a pivotal role in facilitating state transitions within a state machine. Implement this interface in your state classes, allowing them to compute the next state dynamically based on the provided event. This approach provides a flexible and extensible foundation for building state machines that can adapt to different types of events. Ensure to handle potential state transition exceptions to maintain the integrity of the state machine.
- **StateMachineEntity:**
    - The `StateMachineEntity` interface defines a standard set of methods for managing the state of entities within a state machine. Implement this interface in your entity classes to seamlessly integrate them into the state machine framework. Use EntityName() to uniquely identify entities, SetState() to transition between states, and GetState() to query the current state. Customize the interface to match the specific needs of your entities, offering a cohesive and structured approach to state management.

### 13. Timer Package

The `timer` package includes classes related to timers:

- **ScheduledExecutorTimerServiceImpl:**
    - Implementation of the TimerService interface using ScheduledExecutorService.

- **TimerService:**
    - The `StateMachineEntity` interface, defining methods for retrieving the entity name, setting the state, and getting the current state. This interface serves as the foundation for entities within a state machine. Users can implement this interface to create custom state machine entities and configure their behavior in `application.yml`.

### 15. Validation Package

the `Validation` package offers a robust set of tools for handling and customizing validation operations within an application, with configurations conveniently managed in the `application.yml` file.

- **Rule:**
    - Introduces the `Validatable` and `Validator` interfaces, allowing users to define custom validation rules. The `Validation` class provides a mechanism for applying multiple validation rules to a value, logging errors, and rethrowing a consolidated exception. Users can implement their validation logic and update configurations in `application.yml`.

```yaml
validation:
  customRule:
    enabled: true
```