# 🐳 Docker & Render Deployment Ready!

## ✅ What's Been Added

### Docker Support
- ✅ **Dockerfile** - Multi-stage build (Maven + JRE)
- ✅ **.dockerignore** - Optimized Docker builds
- ✅ **render.yaml** - Render Blueprint configuration

### Documentation
- ✅ **RENDER_DEPLOY.md** - Complete Render deployment guide
- ✅ **DEPLOY_STEPS.md** - Quick step-by-step instructions
- ✅ **docker-build.ps1** - Local Docker testing script

## 🚀 How to Deploy to Render

### Method 1: Quick Deploy (Recommended)

1. **Push to GitHub:**
   ```bash
   git add .
   git commit -m "Add Docker support for Render"
   git push origin master
   ```

2. **Deploy on Render:**
   - Go to https://dashboard.render.com/
   - New + → Web Service
   - Connect repository: `9MidhunPM/ETLab-plus-AI-API`
   - Select **Docker** runtime
   - Add environment variable: `GEMINI_API_KEY=AIzaSyB8Hs2YIHHVabLdzYHfNCP5Bc7oObTaAqU`
   - Click "Create Web Service"

3. **Done!** Your API will be live at: `https://your-app.onrender.com`

### Method 2: Using Blueprint (render.yaml)

1. Push to GitHub
2. Render Dashboard → Blueprints → New Blueprint Instance
3. Select your repository
4. Render auto-detects `render.yaml` and configures everything
5. Add the `GEMINI_API_KEY` in environment settings

## 🧪 Test Docker Locally

```bash
# Build the image
docker build -t ai-api .

# Run with your API key
docker run -p 8080:8080 -e GEMINI_API_KEY=AIzaSyB8Hs2YIHHVabLdzYHfNCP5Bc7oObTaAqU ai-api

# Test
curl http://localhost:8080/api/health
```

Or use the PowerShell script:
```powershell
.\docker-build.ps1
```

## 📦 What the Dockerfile Does

1. **Build Stage:**
   - Uses Maven to compile your Java code
   - Creates executable JAR file
   - Optimized with dependency caching

2. **Runtime Stage:**
   - Lightweight JRE image (Alpine Linux)
   - Only includes the built JAR
   - Small final image size (~200MB)

## 🌐 After Deployment

Your API will be available at:
```
https://your-app-name.onrender.com
```

**Endpoints:**
- `POST https://your-app.onrender.com/api/ai-query`
- `GET https://your-app.onrender.com/api/health`
- `GET https://your-app.onrender.com/api/models`

## 📱 Mobile Access

Now you can access from anywhere:
```javascript
fetch('https://your-app.onrender.com/api/ai-query', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    data: [...],
    query: "Your question"
  })
})
```

## 💡 Render Free Tier

- ✅ 750 hours/month free (one service 24/7)
- ✅ Automatic HTTPS
- ✅ Auto-deploy on git push
- ⚠️ Sleeps after 15 min inactivity
- ⚠️ First request takes ~30 sec to wake

## 🔒 Security

✅ API key is set as environment variable on Render  
✅ `.env` file is NOT included in Docker image  
✅ `.dockerignore` excludes sensitive files  
✅ Safe to push to GitHub  

## 📂 Files Added

```
├── Dockerfile              # Docker container definition
├── .dockerignore           # Files to exclude from Docker
├── render.yaml             # Render configuration
├── RENDER_DEPLOY.md        # Detailed deployment guide
├── DEPLOY_STEPS.md         # Quick deployment steps
└── docker-build.ps1        # Local Docker test script
```

---

**Your application is now ready to deploy to Render!** 🚀

See [DEPLOY_STEPS.md](DEPLOY_STEPS.md) for the complete deployment guide.
