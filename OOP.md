Object Oriented Programming Concept Questions

As you should know by now, there are 4 pillars of Object Oriented Programming.

********************
1. Encapsulation

Encapsulation is the binding of data and functions (that can manipulate the data) together, and keeping them both safe from any outside interference.

Say we have a class called CellPhone, and we have some public functions (functions that can be called from outside the class) such as:

openCamera()
openMaps()
openNotes()

The function openCamera() is only used to open the camera on your CellPhone. One day the developers decided to implement some new functions, called zoomIn() and zoomOut(). To implement these functions, the developers would just need to update the function openCamera().

This is encapsulation, the mechanism of implementing class and functions and bundling things together.

********************
2. Inheritance

Inheritance allows new objects to take on the properties of existing objects. 

Say you have class called Parent class which has some properties and methods. You can create a sub class called Child Class that can both inherit the properities and methods from the Parent class AND have additional properities or methods.

Example of Parent class:

class Call {
    private String Contact;
    private Stringe CallLog;

    private Call(String c, cl) {
        Contact = c;
        CallLog = cl;
    }

    public void startCall() {
        //StartCall
    }
}

Example of Child class:

class GroupCall extends Call {
    private String GroupName;

    private GroupCall(String c, String cl) {
        super(c, cl);
        GroupName = gn;
    }
}

public void startGroupCall() {
    //StartGroupCall
}

********************
3. Abstraction

Abstraction is the process of only showing necessary (or essential) information while hiding anything else that is irrelevant. Abstraction is mostly used to reduce code complexity, and at the same time it can make your code look organized and neat.

Take any ride sharing app like UBER or LYFT for example:

1. When you first want to book a ride, you have to enter a pick up location.
2. After you are done entering a pick up location, you now have to enter a drop off location.
3. After you are done enetering a drop off location, you now have your estimated time of arrival and the cost.

This is a perfect example of abstraction because at each step, these apps hide the information that isn't applicable to you anymore, and only show what you need. This both reduces clutter while making the app appear really organized and neat.

Therefore, abstraction is the abstraction of data or the hiding of information, and only shows what the user is concerned about.

********************
4. Polymorphism

Polymorphism is the ability to redefine methods for derived class, and be able to take on different forms. So what does this mean? Say you have a subclass, it is able to define its own unique functionalities or behavior AND still share its some of its functionalities or behavior from the parent class. However, it doesn't work the other way around, so the parent class won't be able to have some of the unique functionalities or behaviors created in the subclass.

Take the Google search engine for example:

Say you type something into the search bar like "Chihuahua". By default the function SearchAll() will be called.

class Search {
    public SearchItems(String item) {
        //Search Chihuahua
    }
}

class ImageSearch {
    public searchAll(String item) {
        public SearchItems(String item) {
            //Search All Chihuahua
        }
    }
}

class ImageSearch {
    public SearchItems(String item) {
        public SearchItems(String item) {
            //Search Image Chihuahua
        }
    }
}

class VideoSearch {
    public SearchItems(String item) {
        public SearchItems(String item) {
            //Search Video Chihuahua
        }
    }
}

Therefore, with polymorphism we are able to redefine a function and that the object of the Search class can be appeared in many forms.

********************

Please write a 1-3 paragraphs explaining these 4 concepts further.  Please provide a sufficient enough explanation about these pillars, as well as some examples to illustrate the practical use cases of these principles.  

