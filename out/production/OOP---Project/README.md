
<p align="center">
  <img src="https://ocw.cs.pub.ro/courses/lib/exe/fetch.php?hash=2597cd&media=https%3A%2F%2Fi.imgur.com%2FS1ROjQr.gif" />
</p>


## *The purpose of the Project*

> This project is a simplified movie app that can log in existent users or register
> new ones. After this, you will be able to search or filter the movies in the
> database, you can buy tokens, movies or switch to premium account. You can watch
> purchased movies, rate and give them a like.
> 
> All the data is read form a json file and write in another json file to simplify
> the whole process.

## *Implementation*

### src/
> The implementation of the program can be found in src/ directory. Here we have:
> * **Main**  - a class that handle reading and writing from and in json files
> * **input.files/** - a package that stores the classes used to read data from the
 input files.
> * **solution/** - a package that stores the classes used in the implementation of the
> program

### input.files/
> In the input.files/ package we can find the following classes:
> * **ActionsInput** - contains all commands and information that can be received in a action
    made by the current user
> * **ContainsInput** - this class contains 2 arrayLists of actors and genres and is used for filter
> * **CredentialsInput** - contains user data
> * **FiltersInput** - contains 2 objects (SortInput and ContainsInput) and receive the filter criteria
> * **Input** - class that contains all the information from input file
> * **MoviesInput** - class that contains the information about a movie
> * **SortInput** - class that set the filter order (increasing or decreasing) depending on rating and duration
> * **UsersInput** - class that contains user credentials

### solution/
> In the solution/ package we have:
> * **ActionFunctions** - class with a static method that return a page instance depending on its name
> * **AppLogic** - class that contains the current page, the current user and the current movie list
> * **Movie** - class that contains all the information about a movie and 2 copy constructors
> * **User** - class that contains all the information about a user and 2 copy constructors
> * **comparators/** - a package that contains 4 comparators depending on the method of filtration
> * **data/** - a package which include the database and the user credentials
> * **pages/** - a package with all the pages available in the app

### pages/
> In the pages/ package are 7 classes that implements Page plus Page Interface:
> * **Page** - an interface that has 2 boolean methods: executeChangePage and executeOnPage
> * **HomePageNeautentificat** - the starting page of the app. From here you can switch to
   Login or Register Page.
>   * this page is a singleton
>   * the actionChangePage method modify the current page
> * **Login** - the login page
>   * this page is a singleton
>   * you can only execute the login action
>   * executeOnPage method is calling the private method for login that verify if the user
    exists in the database and if exists, he will be authenticated
> * **Register** - the register page
>   * this page is a singleton
>   * you can only execute the login action
>   * if the user already exist in the database, the function will return false (error),
        otherwise it will add the user to database and change the current page to HomePageAutentificat
> * **HomePageAutentificat** - after the user is successfully authenticated (login or register),
        he is sent on this page.
>   * this page is a singleton
>   * from this page, he can log out (the current user is set to null, the movie list is clear
      and the current page is set to HomePageNeautentificat)
>   * he can change page to movies or upgrades and the movie list and current page are updated
> * **Movies** - 
> * **SeeDetails** - 
> * **Upgrades** - 