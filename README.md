# MovieStorage

## Architecture

The application consists in 2 main parts:

1. API: A simple SpringBoot application that creates some endpoints to manipulate data (see API Enpoints section).
2. Database: An in-memory H2 database is used to store data.

## Data model

![image info](model.png)

## API Endpoints

### Get movie
- URL: /getMovies
- Params: N/A
- HTTP Method: GET
- Description: Returns the movies saved

### Save movie
- URL: /saveMovie
- Params: N/A
- HTTP Method: POST
- Description: Saves the movie defined as JSON in the request body

### Update movie
- URL: /updateMovie
- Params: id -> ID of the movie you want to update
- HTTP Method: PUT
- Description: Updates the movie which ID is specified as parameter.

### Delete movie
- URL: /deleteMovie
- Params: id -> ID of the movie you want to delete
- HTTP Method: DELETE
- Description: Deletes the movie which ID is specified as parameter.

### List
- URL: /list
- Params:
  - fields -> List of fields to order by.
  - title -> Title filter.
  - page -> Number of the page to return.
  - size -> Size of the page to return.
- HTTP Method: GET
- Description: Returns a filtered, sorted and paginated list of movies.

## Examples

### getMovies
- URL: http://localhost:8080/moviestorage/getMovies
- Response body:
```JSON
  [
    {
        "id": 3,
        "title": "C99",
        "rating": 9.2,
        "yearPremiered": 1991,
        "genres": [
            {
                "id": 1,
                "name": "Comedy"
            },
            {
                "id": 2,
                "name": "SciFi"
            }
        ]
    },
    {
        "id": 4,
        "title": "Python2",
        "rating": 8.0,
        "yearPremiered": 2007,
        "genres": [
            {
                "id": 1,
                "name": "Comedy"
            },
            {
                "id": 2,
                "name": "SciFi"
            }
        ]
    },
    {
        "id": 5,
        "title": "Python2.7",
        "rating": 8.9,
        "yearPremiered": 2008,
        "genres": [
            {
                "id": 1,
                "name": "Comedy"
            },
            {
                "id": 2,
                "name": "SciFi"
            }
        ]
    },
    {
        "id": 6,
        "title": "Python3.10",
        "rating": 7.55,
        "yearPremiered": 2019,
        "genres": [
            {
                "id": 1,
                "name": "Comedy"
            },
            {
                "id": 2,
                "name": "SciFi"
            }
        ]
    }
]
```

### saveMovie
- URL: http://localhost:8080/moviestorage/saveMovie
- Request body:
```JSON
{
    "title": "Python3.10",
    "rating": 7.55,
    "yearPremiered": 2019,
    "genres":
        [
            {
                "name": "Comedy"
            },
            {
                "name": "SciFi"
            }
        ]
    
}
```
- Response body:
```JSON
{
    "id": 6,
    "title": "Python3.10",
    "rating": 7.55,
    "yearPremiered": 2019,
    "genres": [
        {
            "id": 1,
            "name": "Comedy"
        },
        {
            "id": 2,
            "name": "SciFi"
        }
    ]
}
```

### filter
- URL: http://localhost:8080/moviestorage/list?fields=yearPremiered,rating&size=4&page=0&title=Py
- Response body:
```json
[
    {
        "id": 6,
        "title": "Python3.10",
        "rating": 7.55,
        "yearPremiered": 2019,
        "genres": [
            {
                "id": 1,
                "name": "Comedy"
            },
            {
                "id": 2,
                "name": "SciFi"
            }
        ]
    },
    {
        "id": 5,
        "title": "Python2.7",
        "rating": 8.9,
        "yearPremiered": 2008,
        "genres": [
            {
                "id": 1,
                "name": "Comedy"
            },
            {
                "id": 2,
                "name": "SciFi"
            }
        ]
    },
    {
        "id": 4,
        "title": "Python2",
        "rating": 8.0,
        "yearPremiered": 2007,
        "genres": [
            {
                "id": 1,
                "name": "Comedy"
            },
            {
                "id": 2,
                "name": "SciFi"
            }
        ]
    }
]
```

### updateMovie
- URL: http://localhost:8080/moviestorage/updateMovie?id=6
- Request body:
```json
{
    "id": 6,
    "title": "Python2",
    "rating": 7.00,
    "yearPremiered": 1995,
    "genres":
        [
            {
                "name": "Comedy"
            },
            {
                "name": "SciFi"
            }
        ]
    
}
```
- Response body:
```json
{
    "id": 6,
    "title": "Python2",
    "rating": 7.00,
    "yearPremiered": 1995,
    "genres":
        [
            {
                "name": "Comedy"
            },
            {
                "name": "SciFi"
            }
        ]
    
}
```

### deleteMovie
- URL: http://localhost:8080/moviestorage/deleteMovie?id=6