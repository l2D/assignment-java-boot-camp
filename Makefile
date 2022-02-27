build-docker:
	docker build -t l2d-assignment-week-1:1.0.0 ./assignments/week1/

run-image:
	docker run -d -p 8080:8080 l2d-assignment-week-1:1.0.0

kill-port:
	npx fkill -f :8080

check:
	mvn clean test package -f ./assignments/week1/

check-and-build: check build-docker

check-flow: check build-docker kill-port run-image


