# Final Challenge

In this final challenge, you have been tasked with creating an application from scratch!

This application should be finance related.

## Technologies

You may use any technology you like that is used within the firm.

Your application can be data related or web based or a back end of some kind, or a simple mix of all three, or something completely different!

If you require data for your application, you can use the Gen AI tools to generate some data for you.

## Mark Scheme
Your instructors will be looking for the following:
- A well-written README file that explains the application and how to run it
- A relevant and useful application
- A good set of unit tests
- Some consideration of deployment, observability, and security
- A good understanding of the code and how it works
- A good understanding of the Gen AI tools used

## Points
Your application can score up to 1000 points!

## Project Overview

Our application is an AI-powered expense tracker designed to help users gain insights into their spending habits. The core feature is a pluggable chatbot component that analyzes expense data and provides personalized financial insights.

### Key Features
- **Expense Tracking:** Users can log and categorize their expenses.
- **AI Chatbot Insights:** The chatbot answers questions and offers suggestions based on the user's expense data.
- **H2 Database:** All data is stored securely in an in-place H2 database.
- **Pluggable Component:** The chatbot is designed as a modular component, making it easy to integrate into other applications.

### Deployment

Sigilium Money can be easily deployed on any Java-supported platform. For containerized deployment, a Dockerfile is provided. The application can also be hosted on cloud platforms such as Azure or AWS.

### Observability

- The application includes logging for key actions and errors using standard logging frameworks.
- Logs can be monitored in real time to track user activity and diagnose issues.
- Health check endpoints are available to monitor application status.
- **Swagger UI** is integrated for interactive API documentation and easy testing of endpoints, improving observability and developer experience. localhost::8080/swagger-ui/index.html

### Security

- All data is stored locally in the H2 database, minimizing external exposure.
- Basic authentication is implemented to restrict access to sensitive endpoints.
- User input is validated to prevent SQL injection and other common vulnerabilities.
- Sensitive information is never logged or exposed in error messages.


### How to Run

1. Clone the repository.
2. Ensure Java and H2 are installed.
3. Start the application (`./gradlew bootRun` or `mvn spring-boot:run`).
4. Access the chatbot via the provided UI.

### Gen AI Usage

We use Gen AI to:
- Summarize spending patterns.
- Suggest saving opportunities.
- Answer natural language questions about expenses.


