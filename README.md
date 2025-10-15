# AI API for Gemini

A Spring Boot REST API that integrates with Google's Gemini AI models to provide intelligent responses based on structured data queries.

## Features

- Single POST endpoint `/api/ai-query` for querying Gemini AI
- Supports multiple Gemini models with automatic fallback
- Accepts structured JSON data with custom queries
- Returns AI-generated responses based on the provided data

## Supported Gemini Models

The API automatically tries the following models in order:
1. `gemini-2.0-flash`
2. `gemini-2.0-flash-lite`
3. `gemini-1.5-flash`

## API Endpoints

### 1. Query AI
**Endpoint:** `POST /api/ai-query`  
**Content-Type:** `application/json`

The API accepts flexible data formats. You can send any JSON structure in the `data` array.

**Example 1: Exam Results Format**
```json
{
  "data": [
    {
      "subjectName": "OBJECT ORIENTED PROGRAMMING",
      "subjectCode": "24CSR304",
      "semester": "IIIrd Semester",
      "marksObtained": "29",
      "maximumMarks": "30",
      "exam": "1"
    },
    {
      "subjectName": "THEORY OF COMPUTATION",
      "subjectCode": "24CST302",
      "semester": "IIIrd Semester",
      "marksObtained": "30",
      "maximumMarks": "30",
      "exam": "1"
    }
  ],
  "query": "Analyze my exam performance"
}
```

**Example 2: Attendance Format**
```json
{
  "data": [
    {
      "subject_code": "24EST315",
      "attendance_percentage": "95%",
      "present_hours": "35",
      "total_hours": "37"
    },
    {
      "subject_code": "24MAT311",
      "attendance_percentage": "94%",
      "present_hours": "33",
      "total_hours": "35"
    },
    {
      "subject_code": "SGA24",
      "attendance_percentage": "71%",
      "present_hours": "5",
      "total_hours": "7"
    }
  ],
  "query": "Where can I improve my attendance?"
}
```

**Response:**
```json
{
  "response": "AI-generated response based on your data and query",
  "model": "gemini",
  "success": true,
  "error": null
}
```

### 2. Health Check
**Endpoint:** `GET /api/health`

**Response:**
```
AI API is running
```

### 3. Get Available Models
**Endpoint:** `GET /api/models`

**Response:**
```json
[
  "gemini-2.0-flash",
  "gemini-2.0-flash-lite",
  "gemini-1.5-flash"
]
```

## Prerequisites
- Java 21
- Maven
- Gemini API Key ([Get one here](https://makersuite.google.com/app/apikey))

## Setup

1. **Create `.env` file** in the project root:
   ```
   GEMINI_API_KEY=your-api-key-here
   ```

2. **Build and run:**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. **API will be available at:**
   - Local: `http://localhost:8080`
   - Network: `http://YOUR_LOCAL_IP:8080`



## Testing

Use the example JSON files in the `examples/` folder with curl or Postman:

```bash
curl -X POST http://localhost:8080/api/ai-query \
  -H "Content-Type: application/json" \
  -d @examples/exam-results-query.json
```

## Configuration

The API key is loaded from the `.env` file. Other settings can be configured in `application.properties`:

```properties
gemini.api.base-url=https://generativelanguage.googleapis.com/v1beta
gemini.api.model=gemini-2.0-flash
server.port=8080
server.address=0.0.0.0
```

## Project Structure

```
src/
├── main/
│   ├── java/com/API/AI/
│   │   ├── config/
│   │   │   └── GeminiConfig.java          # Gemini API configuration
│   │   ├── controller/
│   │   │   └── AiController.java          # REST API endpoints
│   │   ├── dto/
│   │   │   ├── AiQueryRequest.java        # Request DTO
│   │   │   ├── AiQueryResponse.java       # Response DTO
│   │   │   └── SubjectData.java           # Subject data model
│   │   ├── service/
│   │   │   └── GeminiService.java         # Gemini API integration
│   │   └── AiApplication.java             # Main application class
│   └── resources/
│       └── application.properties          # Configuration
```

## Error Handling

The API includes comprehensive error handling:
- **400 Bad Request:** When data or query fields are missing/empty
- **500 Internal Server Error:** When all Gemini models fail to respond

## Security

⚠️ **Important:** Never commit the `.env` file to Git. It's already in `.gitignore`.
