FROM golang:1.20-alpine
WORKDIR /app
COPY main.go .
RUN go mod init backend && go build -o main .
EXPOSE 8080
CMD ["./main"]