# CSCI 656
## Assignment 1
## Matthew Collins

### 1) A one paragraph description, in your own words, of the problem that the architecture addresses
The architecture described in the paper “Koziolek et al. - 2021 - Dynamic Updates of Virtual Programmable Logic Controllers (PLCs) Deployed as Kubernetes Microservices”, introduces a dynamic updating strategy for Virtual PLCs in K8s environments, leveraging a specialized K8s Operator. Their method permits seamless application updates without disrupting production. Notably, their tests indicate that this mechanism can transfer internal states of sizable control applications (with 100,000 state variables) utilizing just 15% of the available cycle slack time, opening doors for real-time updates and cloud-native migrations.

### 2)  A discussion of the requirements for the architecture including, but not limited to, constraints such as performance, security, and scalability
The internal state transfer is a critical aspect of the architecture. For instance, for a control algorithm that requires 10% of the cycle time for computation (i.e., 10 ms), a 400 KByte internal state needs to be transferred in less than 90 ms. This demands a minimum transfer rate of about 4.5 MByte/sec. While typical RAM, bus, and network bandwidths can handle this, the main bottleneck might be the CPU and possibly the network latency. The controller, which is responsible for the state transfer, is implemented as a separate POSIX thread with high real-time priorities. This ensures deterministic execution of the state transfer, especially when deployed in a real-time capable operating system. 

This paper doesn’t dive deeply into security, but it's implied that the architecture relies on established technologies and standards like OPC UA, which has inherent security features. Additionally, the use of Kubernetes and containerization also brings in security practices inherent to these technologies.

The architecture is designed to handle large industrial control applications. However, there are some limitations. For instance, the approach is not meant for fast machine control applications in discrete automation that require sub-millisecond cycle times.
The system was tested on comparably fast server hardware, which might not be available in many smaller production plants. However, such hardware is essential for running non-trivial container workloads in an orchestration system.Overall, the scalability of this solution for plants with the capability of hosting a sizable server room, is better than the alternative of having remote PLCs throughout the plant/manufacturing facility.

The architecture detailed in this paper is not suitable for applications requiring extremely fast cycle times (sub-millisecond). The experiments were conducted on fast server hardware, which might not be universally available. The experiments included only simulated IO devices and not real devices communicating with the OpenPLC runtime.
The prototypical implementation relied solely on open-source components, meaning results might vary with different software components, whether open-source or commercial.

### 3)  A set of Architectural Decision Records (ADRs) documenting the major decisions made in the design
The ADRs were done using adr-tools and merged with pandoc. See the attached ADRs at the end of this paper.You can also find the ADRs and files for this assignment on my github repository: https://github.com/mcollinsece/CSCI-656/tree/main/doc/adr/assignment1

### 4)  Your opinion on whether the architecture meets the customer needs and why you believe it does or does not
This design is still in a very early prototype phase. All tests were performed in simulation. However, given the design constraints of 100 ms detailed by a standard IEC 61131 PID controller, the architecture presented in this paper performed well within the constraints. The worst case test runs presented in this paper were a state size of 500,000 variables, and the longest run took less than 70ms.
