# 1. Transfer Mechanism

Date: 2023-09-13

## Status

Accepted

## Context

A transfer mechanism i sneeded to enable reliable, flexible, and fast state transfer..

## Decision

Open Platform Communications Unified Architecture (OPC UA) will be used to provide a middleware for transfering state quickly and reliably. OPC UA  is designed for monitoring industrial devices from workstations, but was lately extended to also support fast, deterministic controller-to-field device communication. OPC UA includes a client/server protocol on top of TCP/IP, as well as a publish/subscribe mechanism on top of UDP. OPC UA address spaces may hold both configuration and sensor data. The Open Process Automation Forum (OPCAF) has identified OPC UA as the core communication mechanism in future open and interoperable industrial control systems. Controllers and certain field devices shall be equipped with OPC UA clients and servers, while legacy field buses shall be integrated via OPC UA gateways.

## Consequences

The only downside is OPC UA is niche protocol. Less open source libraries will support OPC UA. Fewer developers and engineers will know the protocol. More custom code will need to be written and less off the shelf support will be expected. 
