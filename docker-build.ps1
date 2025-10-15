# Docker Build and Test Script

Write-Host "Building Docker image..." -ForegroundColor Cyan
docker build -t ai-api .

if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Docker image built successfully!" -ForegroundColor Green
    Write-Host ""
    Write-Host "To run the container:" -ForegroundColor Yellow
    Write-Host "docker run -p 8080:8080 -e GEMINI_API_KEY=your-key ai-api" -ForegroundColor White
    Write-Host ""
    Write-Host "Or run it now with your API key from .env:" -ForegroundColor Yellow
    
    $apiKey = (Get-Content .env | Select-String "GEMINI_API_KEY").ToString().Split("=")[1]
    
    Write-Host "docker run -p 8080:8080 -e GEMINI_API_KEY=$apiKey ai-api" -ForegroundColor White
} else {
    Write-Host "❌ Docker build failed!" -ForegroundColor Red
}
