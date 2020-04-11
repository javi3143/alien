*Alien Race App*
Author: Javier Ruiz Perez
-----------------------------------
Requirements:

_Front_

Go to alien-front folder
sudo npm i -g -y create-react-app
npm i --save reactstrap react react-dom
npm i --save bootstrap
npm i axios
npm i d3
npm install
npm start

_Spring Boot API_

- Go to Eclipse, File Tab, Import, Existing Maven Project, select Pom File from RestAPI folder.
- Right-click on the project, Build Path and go to Configure Build Path
- Click the libraries tab. If Maven dependencies are not in the list, you need to add it.
- To add it: Right-click on the project, Maven → Disable Maven Nature Right-click on the project, Configure → Convert to Maven Project.
- Close the dialog.
- Download lombok.jar from https://projectlombok.org/download
- Double-click lombok.jar. This starts the eclipse installer which will find eclipse and offers to install lombok into these eclipse installations.
- You can check if your eclipse installation is lombok-enabled in eclipse's about dialog. The lombok version will be listed at the end of the copyright text.
- Right Click on the project, Run As -> Spring Boot App

_Comments_

The API allows to create, retrieve, update and delete an alien through the front interface. 

- To create an Alien, you must specify a name, type, birth planet and alien id of its parent (if any, otherwise leave it empty).

- To retrieve Aliens there are several options via get request using Postman. You can request http://localhost:8080/api/v1/alien/{id} to get an individual alien showing its id, name, type, planet and children (if any). You can also get an alien id siblings by doing a get request to http://localhost:8080/api/v1/alien/{id}/siblings. Get all the aliens without taking into consideration family relationships http://localhost:8080/api/v1/alien/aliens or get all aliens in the db including their children http://localhost:8080/api/v1/alien/all. 

There is a manyToOne - oneToMany relationship between parent and children columns that given an alien with a parent id 'x', then that alien appears as children of Alien 'x'.
You can see that by accesing h2 database directly through http://localhost:8000/h2-console, you'll see that only name, type, planet and parent_id (id of its parent) are present on the db. However, when a get request is sent to the API, it delivers the alien info + its children.

- Deleting aliens is simply done by passing the ID

- Put requests only allow to update name and birth planet values.

Regarding the D3 visualization, I had to introduce the data manually to the svg as I couldn't transform the array of JSONS to a one hierarchical-nested JSON object.

What I got:

[
    {
        "id": 1,
        "name": "William",
        "type": "Alpha",
        "planet": "Earth",
        "children": [
            {
                "id": 2,
                "name": "Henry",
                "type": "Alpha",
                "planet": "Earth"
            }
        ]
    },
    {
        "id": 2,
        "name": "Henry",
        "type": "Alpha",
        "planet": "Earth",
        "children": [
            {
                "id": 3,
                "name": "Matt",
                "type": "Beta",
                "planet": "Mars"
            },
            {
                "id": 4,
                "name": "Alisa",
                "type": "Gamma",
                "planet": "Mars"
            }
        ]
    },
    {......

    What I wanted:

    {
    "id": 1,
    "name": "William",
    "type": "Alpha",
    "planet": "Earth",
    "children": [
        {
            "id": 2,
            "name": "Henry",
            "type": "Alpha",
            "planet": "Earth",
            "children": [
                            id,
                            name,......
                            ]
    }

Unfortunately, this means that the tree visualization is not updated whenever a change to the dB is made.