# 3. State Transfer Service as K8s Extension

Date: 2023-09-13

## Status

Accepted

## Context

A state transfer service is needed to implement the OPC UA client and Virtual PLC Controller.

## Decision

A K8 operator (Virtual PLC Operator) running in a pod will be the state transfer service.

## Consequences

There is not out of the box K8 operator for this task. A custom pod will need to be developed with OPC UA, open62541, and a webserver.
