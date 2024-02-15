# Card-Auth-Server-Extensions

Card Auth Server Extensions is an open-source repository providing extension modules to enhance the functionality of the Card Auth Server (ACS), 
a 3D Secure Access Control Server designed to add an extra layer of security to online credit and debit card transactions. 
These extensions facilitate integration with various systems, offering additional features and security measures.

## Overview
The 3D Secure protocol, utilized by major credit card companies like Visa, Mastercard, and American Express, 
aims to combat card-not-present (CNP) fraud by introducing an additional layer of security. 
The Access Control Server (ACS) is a vital component enabling issuing banks to participate in Verified by Visa, 
MasterCard SecureCode, JCB J/Secure, and American Express Safekey 3D Secure programs. 
Card Auth Server Extensions extends the capabilities of ACS, making it more adaptable and feature-rich for seamless integration with diverse systems in the banking and fintech sectors.


### 1. Crypto Package

The `crypto` package includes encryption-related classes:

- **AES256Encryption:**
    - Implementation of the IEncryption interface for AES-256 encryption.
    - Provides methods for encrypting and decrypting data.

- **AES256EncryptionConfig:**
    - Configuration class for AES-256 encryption.
    - Stores password and salt values required for encryption.

- **EncryptionException:**
    - RuntimeException thrown for encryption-related errors.

- **IEncryption:**
    - Interface defining methods for encryption and decryption.

- **NoOpEncryption:**
    - Implementation of IEncryption that performs no encryption or decryption.

### 2. External Libs Package

The `externalibs` package includes classes related to external libraries:

- **RequestInterceptorConfig:**
    - Intercepts HTTP requests and processes them.
    - Checks for the presence of SecurityModuleAWS and verifies requests.

### 3. Security Package

The `security` package includes a class related to security functionality for AWS:

- **SecurityModuleAWS:**
    - Component in a Spring Boot application providing security functionality for AWS.
    - Conditionally enabled based on the value of the external-libs.security.SecurityModuleAWS.enabled property.

### 4. HSM Package

The `hsm` package contains classes related to Hardware Security Module (HSM) functionality.

### 5. Command Package

The `command` package includes classes related to handling commands.

### 6. CVV Package

The `cvv` package contains classes related to Card Verification Value (CVV) processing.

### 7. Exception Package

The `exception` package includes generic exception classes.

### 8. Luna Package

The `luna` package includes classes related to Luna hardware security module functionality.

### 9. Message Package

The `message` package contains classes related to message processing.

### 10. NoOp Package

The `noop` package includes classes that perform no operations.

### 11. Notification Package

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

### 12. Scheduled Task Package

The `scheduled-task` package includes classes related to scheduled tasks:

- **Config:**
    - Configuration classes for scheduled tasks.

- **Exception:**
    - Exception classes related to scheduled tasks.

- **TaskSchedularWrapper:**
    - Wrapper class for task scheduling.

### 13. State Machine Package

The `state-machine` package includes classes related to state machines:

- **InvalidStateTransactionException:**
    - Exception class for invalid state transactions.

- **State:**
    - Interface defining states for state machines.

- **StateMachine:**
    - Class representing a state machine.

- **StateMachineEntity:**
    - Interface for entities in state machines.

### 14. Timer Package

The `timer` package includes classes related to timers:

- **ScheduledExecutorTimerServiceImpl:**
    - Implementation of the TimerService interface using ScheduledExecutorService.

- **TimerService:**
    - Interface defining timer-related methods.

### 15. Util Package

The `util` package includes utility classes.

### 16. Validation Package

The `validation` package includes classes related to validation:

- **Enum:**
    - Enumerations related to validation.

- **Exception:**
    - Exception classes related to validation.

- **Validator:**
    - Validation utility classes.
