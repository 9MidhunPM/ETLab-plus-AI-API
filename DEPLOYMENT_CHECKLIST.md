# Render Deployment Checklist

## ✅ Pre-Deployment Checklist

- [ ] `.env` file is in `.gitignore` ✅
- [ ] API key removed from `application.properties` ✅
- [ ] `Dockerfile` created ✅
- [ ] `.dockerignore` configured ✅
- [ ] Code committed to Git
- [ ] Code pushed to GitHub

## 🚀 Deployment Steps

### 1. Push to GitHub
```bash
git status
git add .
git commit -m "Add Docker support for Render deployment"
git push origin master
```

### 2. Create Render Account
- Go to https://render.com/
- Sign up with GitHub

### 3. Create Web Service
1. Dashboard → **"New +"** → **"Web Service"**
2. Connect your repository: `9MidhunPM/ETLab-plus-AI-API`
3. Configure:
   - **Name:** `etlab-ai-api` (or your choice)
   - **Region:** Select closest to you
   - **Branch:** `master`
   - **Runtime:** **Docker** ⚠️ IMPORTANT!
   - **Instance Type:** Free

### 4. Add Environment Variable
⚠️ **CRITICAL STEP:**
1. Scroll to **"Environment Variables"**
2. Click **"Add Environment Variable"**
3. Add:
   - **Key:** `GEMINI_API_KEY`
   - **Value:** `AIzaSyB8Hs2YIHHVabLdzYHfNCP5Bc7oObTaAqU`

### 5. Deploy
1. Click **"Create Web Service"**
2. Wait 5-10 minutes for initial build
3. Watch the logs for any errors

## 📊 Monitor Deployment

### Build Logs
- Check "Logs" tab in Render dashboard
- Look for:
  ```
  ==> Building...
  ==> Deploying...
  ==> Service is live 🎉
  ```

### Test Deployment
```bash
# Replace with your actual URL
curl https://etlab-ai-api.onrender.com/api/health

# Should return: AI API is running
```

## 🎯 Post-Deployment

### Get Your URL
Your service URL will be: `https://your-app-name.onrender.com`

### Update Mobile App
Update your mobile app to use the new URL:
```
https://your-app-name.onrender.com/api/ai-query
```

### Test Full Query
```bash
curl -X POST https://your-app-name.onrender.com/api/ai-query \
  -H "Content-Type: application/json" \
  -d '{
    "data": [
      {
        "subject_code": "SC3",
        "attendance_percentage": "100%",
        "present_hours": "7",
        "total_hours": "7"
      }
    ],
    "query": "How is my attendance?"
  }'
```

## 🔧 Troubleshooting

### Build Failed?
- Check Dockerfile syntax
- Verify all files are committed
- Check build logs in Render dashboard

### Service Won't Start?
- Verify `GEMINI_API_KEY` is set
- Check application logs
- Ensure port 8080 is used

### 502 Bad Gateway?
- Service is waking up from sleep (wait 30 seconds)
- Free tier sleeps after 15 min inactivity

### Environment Variable Not Working?
- Check spelling: `GEMINI_API_KEY` (exact match)
- Re-deploy after adding variables
- Verify in Render → Service → Environment

## 🎉 Success Indicators

✅ Build completes without errors  
✅ Service shows "Live" status  
✅ Health endpoint returns 200 OK  
✅ Can query the AI endpoint  
✅ Mobile app can connect  

## 📱 Share Your API

Once deployed, you can share:
- Postman collection with your Render URL
- API documentation
- Mobile app with deployed endpoint

---

**You're all set!** Your AI API is now running in the cloud! 🚀
