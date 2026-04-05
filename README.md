# Advanced Tuition Centre (ATC) Management System

### 🚀 Overview
A standalone Java application designed for tuition center operations, featuring a **custom-built data persistence engine**. This project was developed to explore low-level data handling and object serialization without relying on external relational databases.

### 🛠️ Tech Stack
* **Language:** Java (JDK 8+)
* **Persistence:** Custom Flat-file Database (.txt based)
* **API:** Native `java.awt.print` for receipt generation

### 🏗️ Key Engineering Features
* **Custom Persistence Engine:** Implemented CRUD operations using `BufferedReader` and `BufferedWriter` to manage state across multiple comma-separated text files.
* **Cascading Deletion Algorithm:** Engineered a logic-based data integrity system that automatically purges associated records (enrollments, invoices) when a student record is deleted.
* **Auto-incrementing ID Parser:** Developed a string-parsing utility to dynamically generate unique primary keys (e.g., ST001 -> ST002) to prevent data collisions.

### 📂 File Structure
* `/src`: Source code (.java files)
* `/attendance`: Log files for student attendance
* `/*.txt`: Local text files acting as the persistent relational database
