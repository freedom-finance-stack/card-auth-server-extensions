# Card-Auth-Server-Extensions

Card Auth Server Extensions is an open-source repository providing extension modules to enhance the functionality of the Card Auth Server (ACS), 
a 3D Secure Access Control Server designed to add an extra layer of security to online credit and debit card transactions. 
These extensions facilitate integration with various systems, offering additional features and security measures.
For more detailed explaination and feature [visit here](https://github.com/freedom-finance-stack/card-auth-server).

### 1. Crypto Package

The `crypto` package provides a comprehensive set of interfaces and implementations related to encryption. It encompasses various cryptographic algorithms and functionalities, allowing developers to secure sensitive data through encryption and related techniques. This package is often used in software development to ensure the confidentiality and integrity of information, providing a robust foundation for cryptographic operations in applications.

There are following Interfaces:
- **IEncryption:**
  - The IEncryption interface outlines a contract for encryption and decryption operations within a software system. It likely includes method signatures such as encrypt and decrypt, providing a standardized interface for incorporating different encryption algorithms. Implementing classes or modules can adhere to this interface, promoting a consistent and interchangeable approach to data security within the application architecture.

There are pre-existing implementations of the above interface
- **AES256Encryption:**
    - Implementation of the IEncryption interface for AES-256 encryption.
    - Provides methods for encrypting and decrypting data.

- **AES256EncryptionConfig:**
    - Configuration class for AES-256 encryption.
    - Stores password and salt values required for encryption.
  

- **NoOpEncryption:**
    - Implementation of IEncryption that performs no encryption or decryption.
>Feel free to customize them as per your requirements and don't forget to put it in the application.yml file for the application to determine appropriate config for Encryption.

### 2. External Libs Package

The `externalLibs` package contains essential components designed to enhance the functionality and security of the open-source project. These libraries are integral to the system's core operations, offering features like request interception configuration and seamless integration with security modules.

- **RequestInterceptorConfig:**
  - The `RequestInterceptorConfig` class plays a crucial role in the project's request processing workflow. It serves as a customizable entry point that is invoked before any request is processed. This class allows developers to configure and call custom modules as specified in the properties file. By offering a versatile method, RequestInterceptorConfig accommodates diverse use cases for custom modules, providing the flexibility to tailor request processing based on specific project requirements. Developers can modify this function to seamlessly integrate and execute custom logic as needed.


- **SecurityModuleAWS Class** 
   - The `SecurityModuleAWS` class is a fundamental component within the ExternalLibs package, focused on bolstering the security aspects of the project. This module is specifically designed for seamless integration with Amazon Web Services (AWS) security features. It facilitates robust security implementations, ensuring the confidentiality and integrity of sensitive data. Developers can leverage the capabilities of SecurityModuleAWS to enhance the overall security posture of the project, especially in scenarios where AWS services play a vital role.
  

### 3. HSM Package

The `hsm` package contains classes related to Hardware Security Module (HSM) functionality.
  1. **command**: The `command` package includes classes related to handling commands.
    - **HSMCommandType** :
       - The `HSMCommandType` enum encapsulates various types of commands for Hardware Security Modules (HSMs). 
       This enumeration serves as a comprehensive set of predefined values, representing distinct HSM command types. 
       Its primary functionalities include not only defining and organizing these command types but also providing a mechanism to convert a string representation of a command type into its corresponding enum value, enhancing flexibility and ease of use in HSM-related operations.
     - **HSMCommandFactory**
       - The HSMCommandFactory interface, within the HSM package, defines a contract for creating instances of HSMCommand. It declares a method, getHSMCommand, responsible for instantiating specific HSMCommand implementations based on the provided HSMCommandType. This factory design pattern allows for a flexible and extensible approach to constructing HSM commands, promoting modularity and ease of maintenance within the HSM package.
     - **HSMCommandModule**
       - The `HSMCommandModule` class in the HSM package serves as a configuration class with the primary role of orchestrating the creation of beans related to Hardware Security Module (HSM) command functionality in a Spring application. This class, through code analysis, undertakes the task of scanning a specified package for components to be included in the application context. It defines two essential beans: hsmCommandFactory, implemented as a ServiceLocatorFactoryBean adhering to the HSMCommandFactory interface, and hsmCommandTypeEnabled, which retrieves the enabled HSM gateway from application properties and converts it into the corresponding HSMCommandType enum value. These defined beans play a pivotal role in configuring and enhancing the HSM-related functionalities within the Spring application.
     - **HSMCommand**
       - The abstract class HSMCommand in the HSM module defines a template for interacting with Hardware Security Modules (HSMs). It includes methods for initializing, serializing, sending requests, and processing responses. The class encapsulates common functionality for HSM communication and introduces a template method, processHSMMessage, orchestrating the sequence of actions required to handle an HSM message, promoting modularity and ease of extension for concrete implementations.
  2. **cvv** 
   - The cvv package serves as an example implementation for Card Verification Value (CVV) generation using a Hardware Security Module (HSM). The CVVFacade interface defines a method for generating CVV values from an HSM message. The accompanying CVVFacadeImpl class demonstrates the process of validating the input HSM message, utilizing the appropriate HSM command, and returning the generated CVV. Users have the flexibility to create their implementations and update the application.yml configuration file to seamlessly integrate their custom CVV generation logic into the application. This modular structure encourages user customization and adaptability to specific requirements.
  3. **Luna** 

- **config** 
  - The `config` package within the Luna subpackage of the HSM module is integral to configuring and initializing communication with a Luna Hardware Security Module (HSM). It includes two key classes, HSMGatewayConfig and HSMInitialisationConfig, responsible for establishing the HSM gateway. HSMGatewayConfig sets up essential components such as the client connection factory, sending message handler, and receiving channel adapter. On the other hand, HSMInitialisationConfig configures channels, handlers, and messaging gateways for sending and receiving messages to and from the Luna HSM, demonstrating a comprehensive approach to HSM initialization and communication setup.
- **gateway**
  - 
- **message**
  - 
- **utils**
  - 
- **LunaEE0802HSMCommandImpl**
  - The `LunaEE0802HSMCommandImpl` class, an implementation of the `HSMCommand` abstract class within the Luna subpackage, specializes in sending requests to a Luna Hardware Security Module (HSM) and handling responses, particularly for the generation of Card Verification Value (CVV) codes. Key functionalities include initializing the HSM command, serializing it with modified CVV index and padded data, sending the request to the Luna HSM via HSMGatewayService, fetching and processing the response, and updating the HSM message accordingly. It's worth noting that this is a general implementation, providing users with the flexibility to customize it as needed and incorporate it into the application configuration (`application.yml`).

### 8. Message Package

The `message` package contains classes related to message processing.

### 9. NoOp Package

The `noop` package includes classes that perform no operations.

### 10. Notification Package

The `notification` package includes classes related to notification services:

- **DTO:**
    - Data Transfer Objects for notifications.

- **Enums:**
    - Enumeration types related to notifications.

- **Exceptions:**
    - Custom exceptions for notification services.

- **Factory:**
    - Factory classes for creating notification services.

- **Impl:**
    - Implementation classes for notification services.

- **Interfaces:**
    - Interface definitions for notification services.

### 11. Scheduled Task Package

The `scheduled-task` package includes classes related to scheduled tasks:

- **Config:**
    - Configuration classes for scheduled tasks.

- **Exception:**
    - Exception classes related to scheduled tasks.

- **TaskSchedularWrapper:**
    - Wrapper class for task scheduling.

### 12. State Machine Package

The `state-machine` package includes classes related to state machines:

- **InvalidStateTransactionException:**
    - Exception class for invalid state transactions.

- **State:**
    - Interface defining states for state machines.

- **StateMachine:**
    - Class representing a state machine.

- **StateMachineEntity:**
    - Interface for entities in state machines.

### 13. Timer Package

The `timer` package includes classes related to timers:

- **ScheduledExecutorTimerServiceImpl:**
    - Implementation of the TimerService interface using ScheduledExecutorService.

- **TimerService:**
    - Interface defining timer-related methods.

### 14. Util Package

The `util` package includes utility classes.

### 15. Validation Package

The `validation` package includes classes related to validation:

- **Enum:**
    - Enumerations related to validation.

- **Exception:**
    - Exception classes related to validation.

- **Validator:**
    - Validation utility classes.
