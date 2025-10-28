#!/bin/bash

# Script chạy Lab 7 - JpaRepository 2
# Tự động kill port cũ và mở web browser

echo "=========================================="
echo "   LAB 7: JPAREPOSITORY 2"
echo "   Ts00855 - Nguyễn Hải Nam"
echo "=========================================="
echo ""

# Tìm và kill process đang chạy trên port 8080
echo "🔍 Đang kiểm tra port 8080..."
PORT=8080
PID=$(lsof -ti:$PORT)

if [ ! -z "$PID" ]; then
    echo "⚠️  Phát hiện process đang chạy trên port $PORT (PID: $PID)"
    echo "🔪 Đang dừng process..."
    kill -9 $PID
    sleep 2
    echo "✅ Đã dừng process cũ"
else
    echo "✅ Port $PORT đang trống"
fi

echo ""
echo "🧹 Đang clean project..."
mvn clean -q

echo ""
echo "🚀 Đang khởi động ứng dụng Lab 7..."
echo "⏳ Vui lòng đợi..."
echo ""

# Chạy ứng dụng trong background
mvn spring-boot:run > /dev/null 2>&1 &
APP_PID=$!

echo "📝 Application PID: $APP_PID"
echo ""

# Đợi ứng dụng khởi động (kiểm tra port 8080)
echo "⏳ Đang đợi ứng dụng khởi động..."
counter=0
max_attempts=30

while [ $counter -lt $max_attempts ]; do
    if lsof -Pi :8080 -sTCP:LISTEN -t >/dev/null 2>&1 ; then
        echo "✅ Ứng dụng đã khởi động thành công!"
        break
    fi

    # Kiểm tra xem process còn chạy không
    if ! ps -p $APP_PID > /dev/null 2>&1; then
        echo "❌ Lỗi: Ứng dụng không thể khởi động!"
        echo "📋 Kiểm tra log để biết chi tiết lỗi:"
        echo "    mvn spring-boot:run"
        exit 1
    fi

    counter=$((counter + 1))
    echo -n "."
    sleep 1
done

if [ $counter -eq $max_attempts ]; then
    echo ""
    echo "❌ Timeout: Ứng dụng mất quá nhiều thời gian để khởi động"
    echo "🔪 Dừng process..."
    kill -9 $APP_PID
    exit 1
fi

echo ""
echo ""
echo "=========================================="
echo "✅ Lab 7 đã sẵn sàng!"
echo "=========================================="
echo ""
echo "🌐 Đang mở trình duyệt..."
sleep 2

# Mở trình duyệt (macOS)
open http://localhost:8080

echo ""
echo "📋 DANH SÁCH BÀI TẬP:"
echo "   • Trang chủ: http://localhost:8080"
echo "   • Bài 1 (@Query): http://localhost:8080/product/search"
echo "   • Bài 2 (@Query + Phân trang): http://localhost:8080/product/search-and-page"
echo "   • Bài 3 (Tổng hợp): http://localhost:8080/report/inventory-by-category"
echo "   • Bài 4 (DSL): http://localhost:8080/product/search-dsl"
echo "   • Bài 5 (DSL + Phân trang): http://localhost:8080/product/search-and-page-dsl"
echo ""
echo "🛑 Để dừng ứng dụng, nhấn Ctrl+C hoặc chạy lệnh:"
echo "   kill -9 $APP_PID"
echo ""
echo "=========================================="

# Giữ script chạy và hiển thị log
echo "📊 LOG (Nhấn Ctrl+C để thoát):"
echo "=========================================="
tail -f /dev/null

