# Spring Boot starter for Java 11
This is a starter including:
* Spring Boot Web starter
* Spring Boot Data JPA starter
    * Using MySQL
* Spring Boot Devtools and Annotation Processor
* A sample REST controller

## MySQL in Docker
`application.yml` already contains connection information to this DB.
```shell script
docker-compose up -d
```

Good luck!


# Running the application

This app uses basic authentication, two users are added on start up:

###Admin
username: admin

password: admin

###Customer
username: customer

password: customer

##
To build the application:

```shell script
mvn install
```

To verify APIs:

Add product

```shell script
curl -X POST -i --user admin:admin --data '{ "currency": "NOK", "name": "test", "description": "test desc", "price": 12.00, "stock": 20, "status": "AVAILABLE"}' --header "Content-Type: application/json"  http://localhost:8080/api/v1/product-catalogue
```

Get products:

```shell script
curl -i --user customer:customer "http://localhost:8080/api/v1/product-catalogue?page=0&size=10"
```

Get products with search query:

```shell script
curl -i --user customer:customer "http://localhost:8080/api/v1/product-catalogue?page=0&size=10&q=test"
```

Add item to card:

```shell script
curl -X POST --user customer:customer --header "Content-Type: application/json" --data '{ "productId": "{{PRODUCT_ID}}", "quantity": 1 }' "http://localhost:8080/api/v1/customer-card"
```


Submit order:
```shell script
curl -X POST --user customer:customer --header "Content-Type: application/json"  "http://localhost:8080/api/v1/orders"
```

