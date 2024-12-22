@echo off
REM Windows Batch script to add sample students

curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d "{\"name\":\"John Smith\",\"email\":\"john.smith@example.com\",\"course\":\"Mathematics\",\"grade\":92}"

curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d "{\"name\":\"Emma Wilson\",\"email\":\"emma.w@example.com\",\"course\":\"Physics\",\"grade\":88}"

curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d "{\"name\":\"Michael Brown\",\"email\":\"michael.b@example.com\",\"course\":\"Chemistry\",\"grade\":95}"

curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d "{\"name\":\"Sarah Davis\",\"email\":\"sarah.d@example.com\",\"course\":\"Biology\",\"grade\":78}"

curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d "{\"name\":\"James Wilson\",\"email\":\"james.w@example.com\",\"course\":\"Computer Science\",\"grade\":89}"

curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d "{\"name\":\"Emily Clark\",\"email\":\"emily.c@example.com\",\"course\":\"English\",\"grade\":85}"

curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d "{\"name\":\"Daniel Lee\",\"email\":\"daniel.l@example.com\",\"course\":\"History\",\"grade\":76}"

curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d "{\"name\":\"Sophia Chen\",\"email\":\"sophia.c@example.com\",\"course\":\"Art\",\"grade\":98}"

curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d "{\"name\":\"Oliver White\",\"email\":\"oliver.w@example.com\",\"course\":\"Music\",\"grade\":91}"

curl -X POST http://localhost:8080/api/students -H "Content-Type: application/json" -d "{\"name\":\"Ava Martinez\",\"email\":\"ava.m@example.com\",\"course\":\"Spanish\",\"grade\":87}"

echo All sample students have been added!
pause