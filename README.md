DemoTask

Overview

DemoTask is a Spring Boot application designed to manage a book borrowing system. The application allows users to borrow and manage books with a set of defined constraints and rules.

Features

	•	Member Management: Add, update, and retrieve member details.
	•	Book Management: Add, update, and retrieve book information.
	•	Borrowing Rules: Each member can borrow up to 10 books. Books have an availability status, and cannot be borrowed if not available.


Configuration

The application allows configuration of the maximum number of books a member can borrow through the application.properties file.

Dependencies

	•	Spring Boot
	•	Hibernate
