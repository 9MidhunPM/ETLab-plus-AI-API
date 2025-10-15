# Quick Start Guide for Render Deployment

## Step 1: Prepare Your Repository

```bash
git add .
git commit -m "Add Docker support for Render deployment"
git push origin master
```

## Step 2: Deploy to Render

1. Go to https://dashboard.render.com/
2. Click **"New +"** → **"Web Service"**
3. Connect your GitHub repository: `9MidhunPM/ETLab-plus-AI-API`
4. Configure:
   - **Name:** `ai-api` (or any name)
   - **Runtime:** **Docker**
   - **Branch:** `master`
   - **Instance Type:** Free

5. **Add Environment Variable:**
   - Click **"Environment"**
   - Add variable:
     - Key: `GEMINI_API_KEY`
     - Value: `AIzaSyB8Hs2YIHHVabLdzYHfNCP5Bc7oObTaAqU`

6. Click **"Create Web Service"**

## Step 3: Wait for Deployment

Render will:
1. Clone your repository
2. Build the Docker image
3. Deploy the container
4. Provide a URL like: `https://ai-api-xxxx.onrender.com`

## Step 4: Test Your API

```bash
curl https://your-app-name.onrender.com/api/health
```

Example POST request:
```bash
curl -X POST https://your-app-name.onrender.com/api/ai-query \
  -H "Content-Type: application/json" \
  -d '{
    "data": [{"subject_code": "SC3", "attendance_percentage": "100%"}],
    "query": "How is my attendance?"
  }'
```

## Important Notes

⚠️ **Free Tier Limitations:**
- Service sleeps after 15 minutes of inactivity
- First request after sleep takes ~30 seconds to wake up
- 750 hours/month free

✅ **Benefits:**
- Automatic HTTPS
- Auto-deploy on git push
- Free subdomain
- Easy environment variable management

## Your Deployed URLs

Once deployed, your endpoints will be:
- Health: `https://your-app.onrender.com/api/health`
- Query: `https://your-app.onrender.com/api/ai-query`
- Models: `https://your-app.onrender.com/api/models`

## Mobile Access

Use the Render URL in your mobile app:
```
https://your-app.onrender.com/api/ai-query
```

No need to worry about local IPs anymore! 🎉
