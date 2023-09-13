# 2. Serialization Mechanism

Date: 2023-09-13

## Status

Accepted

## Context

A way to store the state and memory structure before the state is transfered is needed.

## Decision

Binary large objects (BLOB) will be used to transfer the application state. A BLOB allows to abstract the internal application memory structure, which is important as the internal memory state structure might be vendor-specific. Hence, using a BLOB instead of structured data, allows to keep the interface of the state transfer service stable for different virtual PLCs. Additionally, it gives the ability to use data compression techniques or encryption

## Consequences

Storing data as BLOBs may result in reduced data integrity because there are no constraints or validations applied to the binary data. Structured data can have rules and constraints to maintain data consistency.
