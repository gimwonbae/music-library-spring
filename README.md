# Music Web Library REST API

## User

`POST /user`

### Request
    
    {
    "username" : String,
    "password" : String,
    "email" : String
    }

### Response (example)
    
    {
    "id": 1,
    "username": "waristo",
    "password": "$2a$10$qMJjK/aVZ3c7dt3n5F8kTOnsGbgjHlUj96oYyWd69mpuzQ/zwj8X.",
    "email": "abc@naver.com",
    "createdAt": "2021-07-31",
    "modifiedAt": "2021-07-31",
    "enabled": true,
    "authorities": [
        {
            "authority": "ROLE_USER"
        }
    ],
    "accountNonExpired": true,
    "accountNonLocked": true,
    "credentialsNonExpired": true
    }

`POST /artist`

### Request

    Request Body (Json)
    
    {
        "username" : String,
        "password" : String
    }
    
### Response (example, Json Web Token)

    eyJ0eXAiOiJKVxMiJ9.eyJzdWIiOiJ3YXJpc3AiOjE2Mjc3OTY1MDN9.aVw1ywRlxYykiETs4USH0EHIBkFpHHzuu7xny3hA

## Library

`POST /user`

### Request (example)
    
    Authroization Request Header : Bearer eyJ0eXAiOiJKVxMiJ9.eyJzdWIiOiJ3YXJpc3AiOjE2Mjc3OTY1MDN9.aVw1ywRlxYykiETs4USH0EHIBkFpHHzuu7xny3hA
    {
        "rateInt" : 0 to 2
        "albumId" : int
        "comment" : String
    }

### Response (example)
    
    {
    "id": 2,
    "user": {
        "id": 1,
        "username": "waristo",
        "password": "$2a$10$qMJjK/aVZ3c7dt3n5F8kTOnsGbgjHlUj96oYyWd69mpuzQ/zwj8X.",
        "email": "abc@naver.com",
        "createdAt": "2021-07-31",
        "modifiedAt": "2021-07-31",
        "enabled": true,
        "authorities": [
            {
                "authority": "ROLE_USER"
            }
        ],
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true
    },
    "rate": "LIKE",
    "album": {
        "id": 11,
        "albumName": "hiphop",
        "artist": {
            "id": 11,
            "name": "biggie1",
            "born": "2020-01-01",
            "died": "2020-01-21"
        },
        "genre": "ready to die1",
        "releaseDate": "2020-01-21",
        "rate": "LIKE"
    },
    "createdAt": "2021-07-31T14:43:50.392721",
    "modifiedAt": "2021-07-31T14:43:50.392721",
    "comment": "wow i love it!"
    }

`GET /user`

### Request (example)

    Authroization Request Header : Bearer eyJ0eXAiOiJKVxMiJ9.eyJzdWIiOiJ3YXJpc3AiOjE2Mjc3OTY1MDN9.aVw1ywRlxYykiETs4USH0EHIBkFpHHzuu7xny3hA
    
    
### Response (example, Json Web Token)

    [{
    "id": 2,
    "user": {
        "id": 1,
        "username": "waristo",
        "password": "$2a$10$qMJjK/aVZ3c7dt3n5F8kTOnsGbgjHlUj96oYyWd69mpuzQ/zwj8X.",
        "email": "abc@naver.com",
        "createdAt": "2021-07-31",
        "modifiedAt": "2021-07-31",
        "enabled": true,
        "authorities": [
            {
                "authority": "ROLE_USER"
            }
        ],
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true
    },
    "rate": "LIKE",
    "album": {
        "id": 11,
        "albumName": "hiphop",
        "artist": {
            "id": 11,
            "name": "biggie1",
            "born": "2020-01-01",
            "died": "2020-01-21"
        },
        "genre": "ready to die1",
        "releaseDate": "2020-01-21",
        "rate": "LIKE"
    },
    "createdAt": "2021-07-31T14:43:50.392721",
    "modifiedAt": "2021-07-31T14:43:50.392721",
    "comment": "wow i love it!"
    },
    {
    "id": 2,
    "user": {
        "id": 1,
        "username": "waristo",
        "password": "$2a$10$qMJjK/aVZ3c7dt3n5F8kTOnsGbgjHlUj96oYyWd69mpuzQ/zwj8X.",
        "email": "abc@naver.com",
        "createdAt": "2021-07-31",
        "modifiedAt": "2021-07-31",
        "enabled": true,
        "authorities": [
            {
                "authority": "ROLE_USER"
            }
        ],
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true
    },
    "rate": "LIKE",
    "album": {
        "id": 11,
        "albumName": "hiphop",
        "artist": {
            "id": 11,
            "name": "biggie1",
            "born": "2020-01-01",
            "died": "2020-01-21"
        },
        "genre": "ready to die1",
        "releaseDate": "2020-01-21",
        "rate": "LIKE"
    },
    "createdAt": "2021-07-31T14:43:50.392721",
    "modifiedAt": "2021-07-31T14:43:50.392721",
    "comment": "wow i love it!"
    }
    ]

## Album

`GET /album`

### Request

    Query Param
    ?name= : contain name, default = ""
    ?genre= : contain genre, default = ""
    ?startYear= : default=1000
    ?endYear= : default=2000
    ?startRate= : GOOD or LIKE or LOVE, default = GOOD
    ?endRate = : GOOD or LIKE or LOVE, default = LOVE

### Response (example)

    [
    {
        "id": 11,
        "albumName": "hiphop",
        "artist": {
            "id": 11,
            "name": "biggie1",
            "born": "2020-01-01",
            "died": "2020-01-21"
        },
        "genre": "ready to die1",
        "releaseDate": "2020-01-21",
        "rate": "LIKE"
    },
    {
        "id": 12,
        "albumName": "hiphop",
        "artist": {
            "id": 11,
            "name": "biggie1",
            "born": "2020-01-01",
            "died": "2020-01-21"
        },
        "genre": "ready to die2",
        "releaseDate": "2021-01-21",
        "rate": "LOVE"
    }
    ]

    
`GET /album/{id}`

### Response (example)

    {
        "id": 11,
        "albumName": "hiphop",
        "artist": {
            "id": 11,
            "name": "biggie1",
            "born": "2020-01-01",
            "died": "2020-01-21"
        },
        "genre": "ready to die1",
        "releaseDate": "2020-01-21",
        "rate": "LIKE"
    }
    
`POST /album`

### Request

    Request Body (Json)
    
    {
    "albumName" : String,
    "artistId" : int,
    "genre" : String,
    "releaseDate" : "yyyy-MM-dd",
    "rate": 0 to 2
    }
    
### Response (example)

    {
        "id": 11,
        "albumName": "hiphop",
        "artist": {
            "id": 11,
            "name": "biggie1",
            "born": "2020-01-01",
            "died": "2020-01-21"
        },
        "genre": "ready to die1",
        "releaseDate": "2020-01-21",
        "rate": "LIKE"
    }

## Artist

`GET /artist`

### Request

    Query Param
    ?name= : contain name, default = ""
    
### Response (example)

    [
    {
        "id": 11,
        "name": "biggie1",
        "born": "2020-01-01",
        "died": "2020-01-21"
    },
    {
        "id": 12,
        "name": "biggie2",
        "born": "2020-01-01",
        "died": "2020-01-21"
    }
    ]

`POST /artist`

### Request

    Request Body (Json)
    
    {
    "name" : String,
    "born" : "yyyy-MM-dd",
    "died" : "yyyy-MM-dd"
    }
    
### Response (example)

    {
        "id": 12,
        "name": "biggie2",
        "born": "2020-01-01",
        "died": "2020-01-21"
    }


