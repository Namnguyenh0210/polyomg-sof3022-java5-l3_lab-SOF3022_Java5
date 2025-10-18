#!/bin/zsh
# Kill any process running on port 8080
PID=$(lsof -ti:8080)
if [ -n "$PID" ]; then
  echo "Killing process on port 8080 (PID: $PID)"
  kill -9 $PID
fi

# Start the Spring Boot application
./mvnw spring-boot:run &
APP_PID=$!

# Wait for the app to start (adjust sleep if needed)
sleep 10

# Open the browser to the index page
open http://localhost:8080/

echo "Lab6 started. To stop, run: kill $APP_PID"

