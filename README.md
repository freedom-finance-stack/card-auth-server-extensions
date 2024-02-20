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
       - The `gateway` package within the `Luna` subpackage is pivotal for establishing communication with a Hardware Security Module (HSM). At its core is the `HSMGateway` class, providing methods for managing connection status and accessing components crucial for message exchange over TCP/IP. Additionally, the package includes key interfaces such as `HSMGatewayAsyncReply`, `HSMGatewayCorrelationStrategy`, and `HSMGatewayService`, defining asynchronous reply handling, correlation strategies, and service methods for sending requests and fetching responses. Users are encouraged to contribute their implementations of these interfaces and seamlessly update the configuration in `application.yml` to tailor the HSM communication behavior according to specific requirements.
     - **message**
       - The message subpackage under the hsm module encompasses critical classes facilitating communication with a Hardware Security Module (HSM). The HSMBarrierHandlerWithLateGoodResponse class serves as a barrier for processing request messages, allowing only one thread at a time and incorporating a timeout mechanism for thread release. Additionally, the HSMByteArraySerializer class provides methods for efficient serialization and deserialization of byte arrays tailored for HSM usage. Completing the package, the HSMTransactionMessage class acts as a comprehensive wrapper for HSM transactions, offering functionalities such as constructing request headers, handling responses, and converting messages to string representations. Users have the flexibility to integrate their implementations of these classes and tailor the behavior via updates in application.yml.
     - **utils**
       -  The `util` subpackage under the hsm module encompasses essential utility classes facilitating various operations related to hexadecimal data and constants for a Luna Hardware Security Module (HSM). The `HexDump` class offers functionalities for converting byte arrays to hexadecimal strings and printing them in a hex dump format. In parallel, the `HexUtil` class provides a suite of utility methods, including conversions between hexadecimal and other data types, integer-to-byte array conversions, exception stack trace retrieval, and more. Complementing these, the `LunaConstants` class serves as a centralized repository for constant values pertinent to Luna HSM, along with a synchronized method for dynamically setting the echo time. Users can seamlessly integrate their custom implementations into the application's configuration (`application.yml`) to tailor these utilities according to their specific needs.
     - **LunaEE0802HSMCommandImpl**
       - The `LunaEE0802HSMCommandImpl` class, an implementation of the `HSMCommand` abstract class within the Luna subpackage, specializes in sending requests to a Luna Hardware Security Module (HSM) and handling responses, particularly for the generation of Card Verification Value (CVV) codes. Key functionalities include initializing the HSM command, serializing it with modified CVV index and padded data, sending the request to the Luna HSM via HSMGatewayService, fetching and processing the response, and updating the HSM message accordingly. It's worth noting that this is a general implementation, providing users with the flexibility to customize it as needed and incorporate it into the application configuration (`application.yml`).

### 8. Message Package
- The `message` subpackage under the `hsm` module encapsulates essential classes for handling messages sent to and from a Hardware Security Module (HSM). The `HSMMessage` class provides a structured representation of an HSM message, including key check value (KCV), data, and the HSM response. The accompanying `HSMMessageValidator` class ensures the validity of `HSMMessage` objects by checking for blank or null values in the KCV and data fields, throwing an exception if either is detected. Users are encouraged to contribute their own implementations of these classes and seamlessly update the application's behavior by modifying the configuration in `application.yml`.

### 9. NoOp Package
  The `noop` subpackage within the `hsm` module contains the `NoOpHSMCommandImpl` class, which serves as an implementation of the `HSMCommand` abstract class. Specifically designed for No-Op (No Operation) HSM operations, this class acts as a placeholder or mock implementation, abstaining from actual HSM operations. Its main functionalities include representing a No-Op HSM command and setting the HSM response of the associated `HSMMessage` object to a predefined value. Users are encouraged to contribute their custom implementations of these classes and easily adjust the application's behavior by modifying the configuration in `application.yml`.

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
