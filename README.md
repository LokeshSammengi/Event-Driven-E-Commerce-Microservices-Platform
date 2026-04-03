# 🛒 Kafka-Based Asynchronous E-Commerce Microservices

## 📌 Overview

This project demonstrates an **event-driven microservices architecture** using **Apache Kafka** for asynchronous communication.

It simulates a real-world e-commerce workflow:

* Order creation
* Inventory validation
* Shipping processing

Each service communicates using Kafka events, ensuring **loose coupling and scalability**.

---

## 🏗️ Architecture

```
Client → Order Service → Kafka → Inventory Service → Kafka → Shipping Service
```

---

## 🔄 Flow Explanation

1. Client sends request to **Order Service**
2. Order Service publishes `OrderCreated` event to Kafka
3. **Inventory Service** consumes the event

   * Checks product availability
   * If available → publishes `InventoryConfirmed`
4. **Shipping Service** consumes `InventoryConfirmed`

   * Initiates shipping process
   * Updates shipping status

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

* Creates orders
* Publishes `OrderCreated` event

---

### 2. Inventory Service

* Consumes `OrderCreated`
* Validates stock
* Publishes `InventoryConfirmed` / `InventoryFailed`

---

### 3. Shipping Service

* Consumes `InventoryConfirmed`
* Handles shipment processing
* Logs or updates shipping status

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

### 3. Create Topics

```bash
bin/kafka-topics.sh --create \
--topic order-topic \
--bootstrap-server localhost:9092

bin/kafka-topics.sh --create \
--topic inventory-topic \
--bootstrap-server localhost:9092
```

---

## ▶️ Run Services

Start services in this order:

1. Inventory Service
2. Shipping Service
3. Order Service

---

## 📬 API Endpoint

### Create Order

```
POST /orders
```

### Sample Request

```json
{
  "productId": "P101",
  "quantity": 2
}
```

---

## 🔄 Kafka Topics & Events

| Topic           | Producer          | Consumer          | Event              |
| --------------- | ----------------- | ----------------- | ------------------ |
| order-topic     | Order Service     | Inventory Service | OrderCreated       |
| inventory-topic | Inventory Service | Shipping Service  | InventoryConfirmed |

---

## 📊 Key Concepts Demonstrated

* Event-driven architecture
* Asynchronous communication
* Kafka Producer & Consumer
* Microservices decoupling
* Event chaining between services

---

## ⚠️ Limitations

* No Saga orchestration implemented
* No Outbox pattern
* No distributed transactions
* Basic error handling

---

## 📈 Future Enhancements

* Implement **Saga Pattern (Choreography/Orchestration)**
* Add **Payment Service**
* Introduce **Retry & DLQ (Dead Letter Queue)**
* Add **Database persistence**
* Implement **Outbox Pattern**

---

## 💡 Why This Project?

This project clearly shows:

* How microservices communicate asynchronously
* How Kafka enables event flow between services
* Practical understanding of distributed systems

---

## 👨‍💻 Author

**Sai Lokesh Sammengi**
Backend Developer | Java | Spring Boot


