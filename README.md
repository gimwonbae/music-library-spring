# Music Web Library RESTful API


## Prerequisities

### Docker

* [Windows](https://docs.docker.com/windows/started)
* [macOS](https://docs.docker.com/mac/started/)
* [Linux](https://docs.docker.com/linux/started/)

### Docker Compose

### Windows and macOS

Docker Compose is included in
[Docker Desktop](https://www.docker.com/products/docker-desktop)
for Windows and macOS.

### Linux

[release page](https://github.com/docker/compose/releases)

## Usage

(1) Clone Repo
```bash
git clone https://github.com/waristo/music-library-spring.git
```

(2) Start Docker Containers (MariaDB, phpmyadmin, Spring'
```bash
docker-compose -f docker-compose.yml up
```

## Build

(1) build jar
```bash
./gradlew clean build
```

(2) build docker image
```bash
docker build -t waristo/mwl:latest .
```

(3) upload to docker hub
```bash
docker push waristo/mwl:latest

```
## API Document (after start docker-compose)

http://localhost:8080/swagger-ui.html
![스크린샷 2021-08-12 오후 11 32 48](https://user-images.githubusercontent.com/22341324/129215476-29aa6754-3726-48e1-b586-88c8db3cef67.png)

## phpMyAdmin (after start docker-compose)

http://localhost:8000
![스크린샷 2021-08-12 오후 11 34 18](https://user-images.githubusercontent.com/22341324/129215678-8621b162-11d0-4c49-ae7c-8013bc588c8e.png)
