# 4. Container Virtualization

Date: 2023-09-13

## Status

Accepted

## Context

A way to deploy these virtual PLCs is needed.

## Decision

We will use containers in the form of Kubernetes pods to orchestrate instances and components which handle the compute and networking needed for the virtual PLC engines and state transfer service.

## Consequences

An decrease in perforance in terms of jitter is possible with Docker/Kubernetes pods. SWAP limitations are a concern in embedded environments.
