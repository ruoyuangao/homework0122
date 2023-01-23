API Doc

1. create new user

HTTP Method: POST

Endpoint: /userinfo

request body:
{
    "firstName": "Kelly",
    "lastName": "Gao",
    "middleName":"M",
    "dateOfBirth":"1999-06-26"
}

Http Status: CREATED

2. get all users

HTTP Method: GET

Endpoint: /userinfo

HttpStatus: OK

Response Body:

[
    {
        "id": 1,
        "firstName": "Kelly",
        "lastName": "Gao",
        "middleName": "M",
        "dateOfBirth": "1999-06-26T00:00:00.000+00:00"
    }
]



