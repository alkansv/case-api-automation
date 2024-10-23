Pet Endpoint Test Cases
=====================

Find pets by status  - Positive
---------------------------
* Get pets by status : "available"
* Check status code "200"
* Check response "body": "status[0]","available"

Find pets by status  - Negative
---------------------------
* Get pets by status : "test123"
* Check status code is not "200"

Add a new pet to the store
---------------------------
* Add a new pet to the store
* Check status code "200"
* Check response "body": "id","111"
* Check response "body": "status","available"
* Check response "body": "name","fish"

Update pet
---------------------------
* Update pet
* Check status code "200"
* Check response "body": "id","111"
* Check response "body": "name","fish 2"
* Check response "body": "status","sold"

Update pet with form data
---------------------------
* Update pet with form data
* Check status code "200"
* Check response "body": "code","200"
* Check response "body": "type","unknown"
* Check response "body": "message","111"

Update pet image with file
---------------------------
* Update pet image with file
* Check status code "200"
* Check response "body": "code","200"
* Check response "body": "type","unknown"
* Check response "body": "message","additionalMetadata: sevgi\nFile uploaded to ./pet.json, 215 bytes"

Find pets by id  - Positive
---------------------------
* Get pets by id : "5"
* Check status code "200"
* Check response "body": "id","5"

Delete pet
---------------------------
* Delete pet : "1"
* Check status code "200"
* Check response "body": "code","200"