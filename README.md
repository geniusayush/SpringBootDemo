# library Demo

demo for article service

## Getting Started

Though it can be counted as featutures I have over engineered a bit and focussed on quality of the architecture and the design of the code incorporating patterns to make sure the code is future ready and easily extendible and follows good principles.
I am focussing on making the code more architecturally solid and scalable.

Some corner cases in terms of inputs might have been overlooked.

The api details are given with swagger. the location <root>/swagger-ui.html will give an interactive guide to the API. The API has been annotated wuth apropriate text to make the experience authentic
Only 200(201) , 400  and 500 are supported error codes.

## Things left in the pipeline:
   Setting up Exception handler to wrap up Exception messages.
   Currently in case of error the code will give 
   Setting up Logging capabilities for every functions

## Architecture:
    a)The data arrives in ArticleCreationInput/ArticleUpdationInput, The apropriate article is created/updated
    DTO & responseFactory :While the service returns the entity the controller converts it into something that can be shown to the user(ArticleDTO).
        This data conversion is handled by ArticleResponseFactory .
    b)  To create an article only some features are needed Hence a builder pattern is being used here
    c) The authors and article API are independent, do not share any data and can be deployed as seperate microservices.
    d) Business logic is handled wiuthin the service layer, repository saves the data while the controller presents  the data for the public user
    e) Due to time constrains The erroir messages to be shown to the users are hardcoded witghin the application. and not read from message file
    f) in next cycle The exception thrown can be wrapped up in a Exception Handler and a neat message can be thrown
    h) The actual creation of entity is handled at the persistence layer (This code can be a part of service layer as well)

## Technical Insights:
    The input validations are done at service layer. The persistence will not check unless it will cause exceptions
    a) Create API - Authors are independent entity in my system .
        Thus to create an article corresponding authors have to be created in the system.
        hence seperate author creation api has been created.
    b) Create API -  To create an article header , date, authors are must. The authors array should contaimn atleast one author.
    c) Udate API - To update tags,authors ony the changes have to be passed.
    d) Since object was asked to be in memory In place of the database the respositorImpl classes implement the interface by  uses hashmap to store the data.
    e) List articles API - It is assumed that max. one of the filters woule be provided. if multiple passed only tbhe first passed woulf be take care into.Chainig of thee filters can be taken up later.
    f)If the article is not found on the server we are sending 400 bad request in place of 404 Not Found.
    g)Date input is in millisecond .
    h)while removing authors , atleast one has to be left in the system.


## Testing:
Testing is done as per components. Persistence layer tests are donew to see how it handles data.Service Lyer test are done to check the business logic.
Due to time constraints i have been able to check and write testr case only for some scenarios .
"# SpringBootDemo" 
