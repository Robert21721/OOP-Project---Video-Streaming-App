
<p align="center">
  <img src="https://ocw.cs.pub.ro/courses/lib/exe/fetch.php?hash=2597cd&media=https%3A%2F%2Fi.imgur.com%2FS1ROjQr.gif" />
</p>


## *The purpose of the Project*

> This project is a simplified movie app that can log in existent users or register
> new ones. After this, you will be able to search or filter the movies in the
> database, you can buy tokens, movies or switch to premium account. You can watch
> purchased movies, rate and give them a like.
> More than that, now we included a back option, notifications for users, subscribe
> for specific genres and the possibility to add and delete movies form database.
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
> * **AppLogic** - class that contains the current page, the current user, current movie list and
> an editor object
> * **Credentials** - class that stores personal data for a user
> * **Factory** - class with a static method that return a page instance depending on its name
> * **Movie** - class that contains all the information about a movie and 2 copy constructors
> * **MovieRate** - class that contains a movie and the rating provided by a user 
> * **Notification** - class that represent a notification
> * **Print** - class that has 3 methods for printing a successful action, an error or the final recommendation for
> a premium user
> * **command/** - a package that contains files used for the Command design pattern that was used
> to perform the back action between pages
> * **comparators/** - a package that contains 4 comparators depending on the method of filtration
> * **observer/** - a package which include the database, the Observer interface and the user that implements
> the Observer interface
> * **pages/** - a package with all the pages available in the app
> * **premiumUserNotification/** - a package that contains 2 comparators, a class that stores a genre and its
> number of likes and the PremiumUserRecommendation class

### command/
> In the command/ package we have the ChangePageCommand that implements the Command interface and the Editor class
> * **Command** - interface with execute and undo methods
> * **ChangePageCommand**  - class that implements Command interface and override the two methods:
>  * execute method change the current page (and print if it is necessary)
>  * undo method change the current page to the
> previous one (and print if it is necessary)
> * **Editor** - class that has a execute method that receive a command, execute it and added it on the list of commands
> and an undo method that get the last command and call the undo method for that command


### comparators/
> A package that contains 4 comparators used in filter method

### observer/
> A package that contains the database class and the user class that implements the Observer interface
> * **database** - a class that contains the user list, the movie list and 3 methods: 
addUser method that add a new user to database, removeUser that remove a user from database and
> setNotification that update the database and notify all the observers (users)
> * **User** - class that implements Observer interface contains all the information about a user

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
> * **Movies** - when the user enter this page, the currentMovie list will be fill with all the
        movies allowed in user's country.
>  * this page is a singleton
>  * for change page to "see details", if the specified movie exists, il will be put in the
       current movie list by itself, otherwise the program throw an error
>  * search action try to find a movie by its name. If the name starts with the given input,
       the program will add the movie in currentMovie list by itself
>  * search action verify what fields has received and use the right comparator( or a lambda expression)
       and the sort method
>  * logout action - same as above
> * **SeeDetails** - on this page you have only one movie on the currentMovie list,
        and you purchase, watch, like and rate the movie
>   * this page is a singleton
>   * for the 4 actions mentioned above, we verify if we respect the chronological order
        of events using 4 arrayLists, and we check if the movie exists in the previous list.
>   * on this page we also have the subscribe method where the current user can subscribe one of the
>   movie genres
>   * logout and change to movies page are the same as above
> * **Upgrades** - the user can buy tokens or switch to premium account on this page
>   * this page is a singleton
>   * verify if the user has enough balance or tokens before making the action
>   * logout and change to movies page are the same as above

!! All the pages have in common the Page interface methods but each page has its own private
methods that are called from executeChangePage and executeOnPage.
### premiumUserNotification/
> A Package that stores 4 classes that have the role to give the right recommendation to the premium user
> * **CompareGenreByNrOfLikes and CompareMoviesByNrOfLikes** - comparator classes
> * **GenreAndLikes** - class that stores a genre together with its number of likes
> * **PremiumUserRecommendation** - class that has giveRecommendation method. This method create an ArrayList with
> the genres and its number of likes and sorts it. After this, it creates an ArrayList with all the movies from database
> that the user is allowed to watch and has not seen them yet. Then, this array is sorted and the search begins
