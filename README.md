# Rest API for TO-DO Application 

This repository is for the Rest API of To-Do Application

## Installation

The Docker Image for the running application is available [here](https://cloud.docker.com/u/jajodiatushar/repository/docker/jajodiatushar/todorestapi). To Run the Image locally in docker Hub Use:

```bash
docker run -d -p 8080:8080 jajodiatushar/todorestapi
```

## How to Use

```javascript
var url = "http://localhost:8080/api/to-do/tasks";
fetch(url,{
            method: 'GET',
            headers : { "content-type" : "application/json;" }
        })
        .then((res) => res.json())
        .then((data)=> resolve(data))
        .catch((data) => reject(data))

// With POST Method

let method = "POST";

let data = let data = {
        id : "1",
        data : "My First Task"
    }

fetch(url,{
        method: method,
        headers : { "content-type" : "application/json;" },
        body:JSON.stringify(data)
    })
    .then((res) => res.json())
    .then((data)=> {
        if(data["message"] == "Success"){
            resolve("Success")
        }
        else if(data["message"] == "failed")
            reject(data["description"])
    })
    .catch((data) => {
        console.log(data);
    })
    });
```



## Exposed End Points
## Hit the API with Get method at /api/to-do/tasks to get all the tasks list.
```
/api/to-do/tasks 

```

## Hit the API with POST method  passing the json format in the format below to add a new task in the list

```
let data = {
      id : "1",
      data : "Your Data"
}
```

## Hit the API with PUT method to update the existing task. The Parameter should exactly contain two object in Array.
```
let data = [
      {
          id : "1",
          data : "Your Old Data"
      },
      {
          id : "1",
          data : "Your New Data"
      }
]
```

## Hit the API with DELETE method to delete the existing task. The Parameter should contain one object to be deleted.
```
let data = {
      id : "1",
      data : "Your Data to Be deleted"
}
```


# Here are the List of the Responses and the Description

## The JSON returned by GET

```
[
    {
        "id": "0",
        "data": "BootStrapCoding"
    },
    {
        "id": "1",
        "data": "Java Tutorials"
    }
]
```

## JSON return if the list is empty
```
{
    "message": "failed",
    "description": "There is No Content in Database"
}
```

## JSON returned If the Task is Successfully Added with POST
```
{
    "message": "Success",
    "description": "Added"
}
```

## JSON returned If the Parameter Passes with Post Method is Empty
```
{
    "message": "failed",
    "description": "Task Cannot Be Empty"
}
```

## JSON returned If the task passed with the POST method has some conflicts with the existing task
```
{
    "message": "failed",
    "description": "Some Conflict with previous Data"
}
```

## JSON returned If there are not exactly two parameter in the PUT method
```
{
    "message": "failed",
    "description": "Put accepts exactly two parameters"
}
```

## JSON returned If the Task is updated
```
{
    "message": "Success",
    "description": "Modified"
}
```

## JSON returned If the Task is DElETED
```
{
    "message": "Success",
    "description": "DELETED"
}
```

##Contribution Notice

Any Changes are welcomed. Just Fork and raise the Pull Request.
