# Quick Start

## 1. Setup
Create `.env` file with your Gemini API key:
```
GEMINI_API_KEY=your-api-key-here
```

## 2. Run
```bash
mvn spring-boot:run
```

## 3. Test
```bash
curl -X POST http://localhost:8080/api/ai-query \
  -H "Content-Type: application/json" \
  -d @examples/exam-results-query.json
```

## Endpoints
- `POST /api/ai-query` - Query AI with data
- `GET /api/health` - Health check
- `GET /api/models` - List models

## Security
✅ `.env` file is in `.gitignore` and won't be uploaded to GitHub
