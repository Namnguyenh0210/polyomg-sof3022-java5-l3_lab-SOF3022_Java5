#!/bin/bash
PORT=8080
PID=$(lsof -ti tcp:$PORT)
if [ -n "$PID" ]; then
  echo "Đang kill tiến trình chiếm port $PORT: PID $PID"
  kill -9 $PID
else
  echo "Port $PORT đang rảnh, không cần kill."
fi

echo "Đang khởi động ứng dụng Lab4..."
./mvnw spring-boot:run &

sleep 5
open http://localhost:8080

