# Nearby Places Finder

## UI

![Screenshot 1](https://github.com/bedirhanbalci/nearby-places-backend/assets/61194064/1fdae6f1-679b-42db-9b84-475a4969589f)

![Screenshot 2](https://github.com/bedirhanbalci/nearby-places-backend/assets/61194064/08ad6e50-65e4-4851-87f8-0d39e227cf19)

![Screenshot 3](https://github.com/bedirhanbalci/nearby-places-backend/assets/61194064/6adb811d-13ae-49a2-8474-0fdaf9b35f80)

## Features

- **Real-time Map Update**: Inputting new coordinates updates the map in real-time.
- **Location Details**: Shows the list of places below the map, each with a button to focus on the selected location.
- **Submit and Reset**: Allows users to submit new coordinates and reset the map and inputs to the initial state.
- **Responsive Design**: The interface is clean and user-friendly, with a responsive design for better usability.

## Technologies Used

- **Spring Boot**: For building the backend REST API.
- **Spring Boot Starter Data JPA**: For ORM and database interaction.
- **Spring Boot Starter Web**: To build web, including RESTful, applications using Spring MVC.
- **Springdoc OpenAPI Starter WebMvc UI**: For generating API documentation.
- **Spring Boot DevTools**: Provides fast application restarts, LiveReload, and configurations for enhanced development experience.
- **PostgreSQL**: For storing location data.
- **Spring Data JPA**: For data persistence.
- **Lombok**: For reducing boilerplate code.
- **OpenAPI (Swagger)**: For API documentation.
- **RestTemplate**: For making HTTP requests to the external Google Places API.
- **ObjectMapper**: A class from the Jackson library to handle JSON serialization and deserialization.
- **WebConfig**: Custom web configuration for the Spring application.

## Setup

1. **Clone the repository:**

    ```bash
    git clone https://github.com/bedirhanbalci/nearby-places-backend.git
    cd nearby-places-backend
    ```

2. **Configure the database:**
    - Create a PostgreSQL database.
    - Update the `application.properties` file with your database credentials:
      ```properties
      server.port=your-server-port
      spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
      spring.datasource.username=your-username
      spring.datasource.password=your-password
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true

      google.api.key=your-google-api-key
      google.api.url=https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=%f,%f&radius=%f&key=%s
      ```

3. **Run the backend application:**
    ```bash
    ./mvnw spring-boot:run
    ```

## API Endpoints

### Get Nearby Places
- **URL:** `/api/place`
- **Method:** `GET`
- **Query Parameters:**
    - `longitude` (required): The longitude of the location.
    - `latitude` (required): The latitude of the location.
    - `radius` (required): The radius to search for places.
- **Response:**

  ```json
    {
    "place_id": "some-place-id",
    "vicinity": "some-vicinity",
    "name": "some-name",
    "nearbyPlaceList": [
    {
        "id": 1,
        "name": "some-place-name",
        "longitude": 139.6917,
        "latitude": 35.6895,
        "radius": 1000,
        "placeId": "some-place-id",
        "vicinity": "some-vicinity"
      }
    ]
  }

## Usage

1. **Open the application in your browser (default: `http://localhost:3000`).**
2. **Enter the `longitude`, `latitude`, and `radius` values in the input fields.**
3. **Click the **Submit** button to view the map and nearby places.**
4. **The map will update in real-time to reflect the new coordinates.**
5. **Below the map, a list of places will be displayed. Click the **Show on Map** button next to a place to zoom in and highlight that specific location on the map.**
6. **Click the **Reset** button to clear the input fields, map, and place list.**

## Deployment

[https://bedirhanbalci.github.io/nearby-places-frontend/](https://bedirhanbalci.github.io/nearby-places-frontend/)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For questions or support, contact [bedirhanbalci@outlook.com](mailto:your-email@example.com).
