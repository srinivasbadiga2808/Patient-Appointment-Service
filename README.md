# Patient Appointment Service

## 📌 Overview  
The **Patient Appointment Service** is a Spring Boot–based microservice designed to handle the complete lifecycle of doctor-patient appointment scheduling. It supports creating, updating, canceling, and retrieving appointments, while integrating with external services for patient records, notifications, and doctor availability.

This service is built for **enterprise-scale healthcare systems** and follows modern architecture best practices for security, scalability, and maintainability.

---

## 🚀 Features  
✅ **Book Appointments** – Patients or staff can create new appointments with doctors.  
✅ **Update/Cancel Appointments** – Modify or delete scheduled appointments.  
✅ **Doctor Availability Check** – Prevents booking during unavailable slots.  
✅ **Search & Filter** – Retrieve appointments by patient, doctor, or date range.  
✅ **Event-driven Notifications** – Publishes Kafka events for reminders.  
✅ **Role-based Access Control** – Supports different user roles (Admin, Doctor, Receptionist).  
✅ **Swagger API Documentation** – Auto-generated API docs for developers.


**Tech Stack:**  
- Java 17, Spring Boot, Spring Data JPA, Spring Security  
- PostgreSQL/MySQL  
- Apache Kafka (for notifications)  
- Redis (optional caching)  
- Swagger/OpenAPI

---
