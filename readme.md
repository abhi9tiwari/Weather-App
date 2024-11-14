## Weather info 

A Spring Boot application providing weather information for a given pincode and date through a REST API. The application utilizes the OpenWeather API to fetch weather data and caches the results in a relational database (H2) for optimization.


---

## Features

- **RESTful API**: Provides weather information for a given pincode and date.
- **Data Caching**: Caches pincode coordinates and weather data in an H2 database to minimize external API calls.
- **External API Integration**: Utilizes OpenWeather APIs for geocoding and weather data.
- **Optimized Calls**: Checks the database before making external API requests to optimize performance.
- **Testable**: Can be tested using Postman or Swagger UI.

---

## Technology Stack

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **H2 Database**
- **Lombok**
- **RestTemplate**

---

## Prerequisites

- **Java Development Kit (JDK) 17** or higher
- **Maven** (for building the project)
- **An OpenWeather API Key**

---

## Getting Started

## Clone the Repository

git clone https://github.com/yourusername/weather-info-api.git
cd weather-info-api

---

## Features

    Obtain an OpenWeather API Key:
        Sign up at OpenWeather to get a free API key.

    Set the API Key:

        Option 1: Environment Variable

        Set the environment variable OPENWEATHER_API_KEY with your API key.

export OPENWEATHER_API_KEY=your_openweather_api_key

Option 2: Application Properties

Open src/main/resources/application.properties and set your API key:

    openweather.api.key=your_openweather_api_key

---

## Using the API
API Endpoint

GET /weather

Request Parameters

    pincode (required): The postal code for which weather data is requested.
    for_date (required): The date in YYYY-MM-DD format for which weather data is requested.
    country_code (optional): The country code (e.g., IN for India). Defaults to IN if not provided.

Example Requests

    Get Weather Data for Pincode 411014 on 2023-11-12

GET http://localhost:8080/weather?pincode=411014&for_date=2023-11-12

Get Weather Data with Country Code

    GET http://localhost:8080/weather?pincode=10001&for_date=2023-11-12&country_code=US

Sample Response

{
  "pincode": "411014",
  "date": "2023-11-12",
  "weather": {
    "temperature": 28.5,
    "humidity": 60,
    "description": "clear sky",
    "wind_speed": 3.6
  }
}

---

## Testing the API
Using Postman

    Open Postman.

    Create a new GET request.

    Enter the request URL:

    http://localhost:8080/weather?pincode=411014&for_date=2023-11-12

    Send the Request.

    View the Response in the Body section.
	
---

## Database Access
Accessing H2 Console

    Ensure H2 Console is Enabled:

    In application.properties:

spring.h2.console.enabled=true

Access the H2 Console:

http://localhost:8080/h2-console

JDBC URL:

    For in-memory database: jdbc:h2:mem:testdb
	
---

##Troubleshooting

    Error 404 Not Found from Geocoding API:
        Ensure the country code is included in the Geocoding API request.
        Verify the API key is correct and has access to the Geocoding API.

    Error 401 Unauthorized from Weather API:
        Confirm that your OpenWeather API key is valid.
        Historical data access requires a paid subscription.

    Application Fails to Start:
        Ensure all dependencies are correctly specified in pom.xml.
        Check for typos or misconfigurations in application.properties.