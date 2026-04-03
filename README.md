# 🛒 Kafka-Based Asynchronous E-Commerce Microservices

## 📌 Overview

This project demonstrates an **event-driven microservices architecture** using **Apache Kafka** for asynchronous communication.

It simulates a simple e-commerce flow:

* Order Service places an order
* Inventory Service validates stock
* Services communicate via Kafka events

The goal is to showcase:

* Event-driven architecture
* Kafka producer/consumer
* Microservices communication without tight coupling

---

## 🏗️ Architecture

```
Client → Order Service → Kafka → Inventory Service
```

### Flow:

1. User sends order request
2. Order Service publishes event to Kafka
3. Inventory Service consumes event
4. Inventory checks stock and processes request

---

## 🚀 Tech Stack

* Java
* Spring Boot
* Apache Kafka
* REST APIs
* Maven

---

## 📂 Microservices

### 1. Order Service

* Accepts order requests
* Publishes events to Kafka topic

### 2. Inventory Service

* Consumes Kafka events
* Checks product availability
* Sends response/logs status

---

## ⚙️ Setup Instructions

### 1. Start Zookeeper

```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
```

### 2. Start Kafka

```bash
bin/kafka-server-start.sh config/server.properties
```

### 3. Create Topic

```bash
bin/kafka-topics.sh --create \
--topic order-topic \
--bootstrap-server localhost:9092 \
--partitions 1 \
--replication-factor 1
```

---

## ▶️ Run Services

Start services in this order:

1. Inventory Service
2. Order Service

---

## 📬 API Endpoint

### Create Order

```http
POST /orders
```

#### Sample Request:

```json
{
  "productId": "P101",
  "quantity": 2
}
```

---

## 🔄 Kafka Flow

* **Producer:** Order Service
* **Topic:** `order-topic`
* **Consumer:** Inventory Service

---

## 📊 Key Concepts Demonstrated

* Asynchronous communication
* Event-driven architecture
* Loose coupling between services
* Kafka producer & consumer
* Real-time message processing

---

## ⚠️ Limitations

* No Saga orchestration implemented
* No Outbox pattern
* Basic error handling
* No database consistency management

*(This project focuses on understanding Kafka and async communication rather than full production design.)*

---

## 📈 Future Enhancements

* Implement Saga pattern (orchestration/choreography)
* Add Outbox pattern
* Add payment service
* Add database persistence
* Add retry & dead-letter queue (DLQ)

---

## 💡 Why This Project?

This project is built to:

* Demonstrate real-world microservices communication
* Show practical Kafka usage
* Explain async processing in interviews

---

## 👨‍💻 Author

**Sai Lokesh Sammengi**

* Backend Developer (Java + Spring Boot)


