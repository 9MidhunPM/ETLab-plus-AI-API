# Deploy to Render

This application is containerized with Docker and ready to deploy to Render.

## Quick Deploy to Render

### Option 1: Using Render Dashboard (Recommended)

1. **Push your code to GitHub:**
   ```bash
   git add .
   git commit -m "Add Docker support"
   git push origin master
   ```

2. **Create a Web Service on Render:**
   - Go to [Render Dashboard](https://dashboard.render.com/)
   - Click "New +" → "Web Service"
   - Connect your GitHub repository
   - Configure:
     - **Name:** `ai-api` (or your choice)
     - **Runtime:** `Docker`
     - **Branch:** `master`
     - **Build Command:** (leave empty - Docker handles it)
     - **Start Command:** (leave empty - Docker handles it)

3. **Add Environment Variable:**
   - In the service settings, add:
     - **Key:** `GEMINI_API_KEY`
     - **Value:** `AIzaSyB8Hs2YIHHVabLdzYHfNCP5Bc7oObTaAqU`

4. **Deploy!** Render will automatically build and deploy your Docker container.

### Option 2: Using Render Blueprint (render.yaml)

1. Push your code to GitHub
2. Go to Render Dashboard → "Blueprints" → "New Blueprint Instance"
3. Connect your repository
4. Render will detect `render.yaml` and set up automatically
5. Add the `GEMINI_API_KEY` environment variable in the dashboard

## Local Docker Testing

Test your Docker container locally before deploying:

```bash
# Build the Docker image
docker build -t ai-api .

# Run the container
docker run -p 8080:8080 -e GEMINI_API_KEY=your-api-key-here ai-api

# Test the API
curl http://localhost:8080/api/health
```

## Environment Variables on Render

**Required:**
- `GEMINI_API_KEY` - Your Gemini API key

Set this in Render Dashboard → Your Service → Environment → Add Environment Variable

## Accessing Your API

Once deployed, Render will provide a URL like:
```
https://ai-api-xxxx.onrender.com
```

Your endpoints will be:
- `POST https://ai-api-xxxx.onrender.com/api/ai-query`
- `GET https://ai-api-xxxx.onrender.com/api/health`
- `GET https://ai-api-xxxx.onrender.com/api/models`

## Free Tier Notes

Render's free tier:
- ✅ Automatically sleeps after 15 minutes of inactivity
- ✅ First request after sleep takes ~30 seconds to wake up
- ✅ 750 hours/month free (enough for one service running 24/7)
- ✅ Automatic HTTPS

## Troubleshooting

**Container won't start?**
- Check logs in Render Dashboard
- Ensure `GEMINI_API_KEY` is set
- Verify Dockerfile builds locally first

**502 Bad Gateway?**
- Service is waking up from sleep (wait 30 seconds)
- Check if environment variables are set correctly

**Health check failing?**
- Render auto-detects port 8080
- Make sure `server.port=8080` in `application.properties`
