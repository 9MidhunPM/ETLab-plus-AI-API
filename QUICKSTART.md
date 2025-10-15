# Quick Start

## Local Development

### 1. Setup
Create `.env` file with your Gemini API key:
```
GEMINI_API_KEY=your-api-key-here
```

### 2. Run with Maven
```bash
mvn spring-boot:run
```

### 3. Or Run with Docker
```bash
docker build -t ai-api .
docker run -p 8080:8080 -e GEMINI_API_KEY=your-key ai-api
```

### 4. Test
```bash
curl http://localhost:8080/api/health
```

## Deploy to Render

See [DEPLOY_STEPS.md](DEPLOY_STEPS.md) for detailed instructions.

**Quick version:**
1. Push to GitHub
2. Create Web Service on Render (select Docker runtime)
3. Add `GEMINI_API_KEY` environment variable
4. Deploy!

## Endpoints
- `POST /api/ai-query` - Query AI with data
- `GET /api/health` - Health check
- `GET /api/models` - List models

## Security
✅ `.env` file is in `.gitignore` and won't be uploaded to GitHub
