<h1>Software Engineering - Eye-Tracking-Tool</h1>

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
![Lines of code](https://img.shields.io/tokei/lines/github.com/xsoophx/SoftwareEngineering)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/xsoophx/SoftwareEngineering/Build?color=%231dbf8e&label=Build)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/xsoophx/SoftwareEngineering/CI?label=CI)
<h2>What is this about:question: </h2>
This is an assignment project for Software Engineering class at TU-Chemnitz.

## Table of contents :book:

- [Our toolbox](#which-tools-will-we-use)
- [Milestone 01](#milestone-one)
    - [Enumeration of Volere Snowcard IDs](#enumeration-of-volere-snowcard-ids)
- [Milestone 02](#milestone-two)
- [Milestone 03](#milestone-three)
- [Milestone 04](#milestone-four)
    - [UML-sequence diagrams](#uml-sequence-diagrams)
        - [Definition of sequence diagrams](#definition-uml-sequence-diagram-bookmark_tabs)
    - [UML-state machines](#uml-state-machines)
        - [Definition of state machines](#definition-uml-state-machine-bookmark_tabs)
- [Milestone 05](#milestone-five)
    - [Part 1](#part-fiveone)
        - [Project Language](#project-language)
        - [Unit Testing](#which-kind-of-tests-are-we-using?)
        - [Documentation and Coding Conventions](#how-are-we-documenting-our-code-and-what-are-our-code-conventions?)
    - [Part 2](#part-fivetwo)
        - [Overview](#overview)
        - [The database](#the-database-floppy_disk)
- [Milestone 06](#milestone-six)
    - [Overview](#overview-codecharts-chart_with_upwards_trend)
    - [String Handler](#stringhandler)
    - [Code Charts Data Values](#codechartsdatavalues)
    - [Code Charts Sub Application](#calling-the-codecharts-sub-application)
    - [Code Charts View](#codecharts-view)
    - [Picture View](#picture-view)
    - [Grid-View](#grid-view)
    - [Input Validator View](#inputvalidator-view)
    - [Thankful- and Retry-View](#thankful-view-and-retry-view)
    - [Overview ZoomMaps](#overview-zoommaps-mag)
- [Milestone 07](#milestone-seven)
    - [Connection of database to tools](#part-sevenone)
    - [Data Analyst](#part-seventwo)

For a thorough documentation of the code please follow
this [link](https://suffro.cc/se-dokka/software-engineering-exercise/).

## Which tools will we use?

The toolbox for capturing and evaluating of visual attention consists of multiple, different parts:

- Config File :wrench:
- Tool: Bubble View :wind_chime:
- Tool: Zoom Maps :mag:
- Tool: Code Charts :chart_with_upwards_trend:
- Tool: Webcam based Eyetracking :eyes:
- Storage for collected data :floppy_disk:
- Client for analysing of the data :microscope:

<br />

## Milestone :one:

_Deadline: 05.11.2020_

The first task was to describe the requirements of the different parts, with the help of Volere Snow Cards. For each
tool, a functional as well as a non-functional requirement needs to be described.

#### Enumeration of Volere Snowcard IDs

| **Req-ID's**  |                   functional                    |                   non-functional                    |
| ------------- | :---------------------------------------------: | :-------------------------------------------------: |
| Config File   | [M01-01F](docs/m01/snowcards/functional/01F.md) | [M01-01N](docs/m01/snowcards/non-functional/01N.md) |
| Bubble View   | [M01-02F](docs/m01/snowcards/functional/02F.md) | [M01-02N](docs/m01/snowcards/non-functional/02N.md) |
| Zoom Maps     | [M01-03F](docs/m01/snowcards/functional/03F.md) | [M01-03N](docs/m01/snowcards/non-functional/03N.md) |
| Code Charts   | [M01-04F](docs/m01/snowcards/functional/04F.md) | [M01-04N](docs/m01/snowcards/non-functional/04N.md) |
| Eyetracking   | [M01-05F](docs/m01/snowcards/functional/05F.md) | [M01-05N](docs/m01/snowcards/non-functional/05N.md) |
| Data storage  | [M01-06F](docs/m01/snowcards/functional/06F.md) | [M01-06N](docs/m01/snowcards/non-functional/06N.md) |
| Data analysis | [M01-07F](docs/m01/snowcards/functional/07F.md) | [M01-07N](docs/m01/snowcards/non-functional/07N.md) |

<br />

## Milestone :two:

_Deadline: 12.11.2020_

The second task was to analyze all the components of the toolbox with the help of CRC Cards. All classes, dependencies,
responsibilities, collaborations and relations, but also hierarchies (and inheritance) are supposed to be found. Also,
the results need some reasoning for the chosen arrangements.

|     Tool      |                 ID                 |
| :-----------: | :--------------------------------: |
|  Config File  | [M02-01](docs/m02/crc_cards/01.md) |
|  Bubble View  | [M02-02](docs/m02/crc_cards/02.md) |
|   Zoom Maps   | [M02-03](docs/m02/crc_cards/03.md) |
|  Code Charts  | [M02-04](docs/m02/crc_cards/04.md) |
|  Eyetracking  | [M02-05](docs/m02/crc_cards/05.md) |
| Data storage  | [M02-06](docs/m02/crc_cards/06.md) |
| Data analysis | [M02-07](docs/m02/crc_cards/07.md) |

<br />

## Milestone :three:

_Deadline: 19.11.2020_

The third task was to create a UML - class diagram based on the class hierarchy of our **CRC-Cards** ([_old
version_](docs/m02/crc_cards) vs. [**new version**](docs/m03/crc_cards)). We were supposed to give the exact interfaces
of the classes, as well as attributes, which have to be managed by these classes. The decision for this design should be
describable with only a few words.
[Our finished UML - class diagram.](https://tuc.cloud/index.php/s/FcfBdMmGcRcMam7)

## Milestone :four:

_Deadline: 26.11.2020_

The fourth milestone was to create UML-sequence diagrams, as well as UML state machines.

### UML-sequence diagrams

We were supposed to create a sequence diagram for the act of starting a tool, as well as for using and closing it. At
least one tool should be described with a sequence diagram. Moreover, a diagram for the data analysis client was wanted.
Starting of this tool, but also importing data, and the analysis itself should be depicted in at least one way.

#### definition UML-sequence diagram :bookmark_tabs:

_The sequence diagram shows a scenario, by depicting interactions between objects within a certain time span._

### UML-state machines

We also had to create a state machine for at least one of the tools. Suitable states and transitions (also with possible
conditions and inputs) needed to be defined, to find a model for the behaviour of the tool. Also, the state machine of
the data client was wanted.

#### definition UML-state-machine :bookmark_tabs:

_The state machine is describing the development of an object over time. Dependencies of interactions with other
objects, which the system does not contain, are depicted._

## Milestone :five:

### Part :five:.:one:

_Deadline: 08.12.2020_

### Project Language

Why are we using **Kotlin**?

- It's easy and completely interoperable with Java. So in case, we want to code some parts in Java, it's easy to add
  those code parts.
- We will be able to solve problems with a fewer amount of lines of code.
- The code is more reliable, easier to maintain, easier to read, and it's also easier to apply changes when they're
  needed.
- The compiler is smarter and safer. It detects errors at compile time and performs a lot of checks.
- Kotlin has a good support for Functional Programming.
- Kotlin has Null Safety, which means it eliminates the danger of null references.

### Which kind of tests are we using?

We decided for Kotlin Unit Tests to build our code faster and more reliable. Even though, we will have a lot more code
because of the tests, it will be easier to find bugs and to solve them. With *Test Driven Development*, our requirements
will be turned into specific test cases, which will not pass in the first run. After that, our main code will be written
or refactored, so the tested code will actually pass the tests. Thus, a lot of time will be saved, even though we're
writing more code. Less or even no more debugging is needed, because we already know, which part of our code is failing.
Also, build-in Unit Tests are providing documentation for the code, because it's easy to get a basic picture of the
logic of the tested class or module just by looking at the tests. Moreover, we can see how much code is actually
covered, just by running the tests with coverage. More coverage means fewer lines of code which could contain bugs. Our
tests will be mainly built with [JUnit5](https://junit.org/junit5/docs/current/user-guide/)
and [assertk](https://github.com/willowtreeapps/assertk).

In a nutshell, our tests will ensure that we have a reliable engineering environment and that our code will meet quality
standards. See more info about TDD [*
here*](https://fortegrp.com/the-importance-of-unit-testing/#:~:text=Developers%20write%20unit%20tests%20for,%2Ddriven%20development%20(TDD).)
and [*here*](https://blog.cleancoder.com/uncle-bob/2013/03/06/ThePragmaticsOfTDD.html).

### How are we documenting our code and what are our code conventions?

**Documentation and comments:**

- For the documentation, we are using description of parameters, just like in the Kotlin standard documentation (KDoc).
  All public classes and methods will be commented by using ```/**...*/```. Instead of using *@param*, *@return*, *
  @throws* and so on, we will be using *[paramname]* plus a description, and an automated API-Documentation can be
  created.
- "Normal" comments should be very short and precise, if needed. Comments will be written by ```/*...*/```
  or ```//...```. They won't be used for documentation purposes.

**Naming Conventions and types**

- *Language* - Everything in this project will have english naming. It's important, especially for e.g. interfaces. By
  providing english naming, international stakeholders, clients and so on, can understand the methods better.

- *Import directives* - All imports will be in alphabetical order. Imports will always be precise, no ```.*```
  annotation for imports will be used. Moreover, unused imports will be removed.

- *Classes and Interfaces* - Class- and Interface names will start with an uppercase letter, followed by lowercase
  letters. If the name consists out of multiple words assembled together, each word is starting with an uppercase
  letter (also known as camel-case).

- *Methods* - All Methods will start with a lowercase letter, followed by lowercase letters. If the name consists out of
  multiple words assembled together, each word is starting with an uppercase letter (also known as camel-case), except
  of the first one.

- *Attributes and Variables* - This is the same as for Methods. Variables and Attributes will be starting with a
  lowercase letter, followed by lowercase letters. If the name consists out of multiple words assembled together, each
  word is starting with an uppercase letter (also known as camel-case), except of the first one.

- *Constant parameters* - All constants will be written in Uppercase. If the name consists out of multiple words
  assembled together, each word is separated through a "```_```".

- *Trailing commas* - We will be using trailing commas to make version control diffs cleaner, to easily add and reorder
  elements in enumerations or to simplify code generation. This can also be found in
  the [Kotlin coding-conventions](https://kotlinlang.org/docs/reference/coding-conventions.html).

  *See more info [here](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html)
  and [here](https://kotlinlang.org/docs/reference/coding-conventions.html).*

### Summary

In the first part of this milestone, we were supposed to write automated tests, which are testing our methods. At least
all public methods should already be known, since we created UML diagrams, which are showing a huge part of the classes
and methods already. We agreed, that we will create all classes and public methods we already know, but also all tests,
which are related to those classes. Also tests which could test private methods, of which we know the functionality, are
written down in our code. To sum this up, after the first part of this milestone, we will have test declarations, which
are supposed to test at least all public methods we know already. Moreover, they need the declaration of their
corresponding class and the methods, so we created them too. Less to no functionality is built in here. Tests without
any functionality, but only the declarations are looking like this:

    class ConfigManagerTest {
      private val manager = ConfigManager() //declaration of corresponding class
      private val someResult = "result" //some dummy result

      // description of functionality or function which is tested in the test
      @Test
      fun `comparing db and file content should work`(){ 

       assertThat(manager.compare()).isEqualTo(someResult)
       /* assertion that expected argument is being returned, 
       however currently no functionality is implemented, 
       which will cause the test to fail */
      }

*\*comments in this example only for explanation reasons, they cannot be found in the actual code*

Another task of this part of the milestone was to agree on code conventions and on how we will do our documentation,
which you can see [above](#how-are-we-documenting-our-code-and-what-are-our-code-conventions?).

### Part :five:.:two:

_Deadline: 12.12.2020_

### Overview

The task for our second part of the 5th milestone was not given. That means, we could decide what the second part should
be. We came to the result, that the second task would be the completion of our database and the corresponding tests.
Whoever felt like starting with the implementation for a tool, was free to actually start with that and sync up and
explain what he/she did in our regular meetings.

### The database :floppy_disk:

We decided to use [Mongo DB](https://www.mongodb.com/) as a database. It is a document database, which is very useful
for future implementations. More reasons where the following:

- Mongo is saving its documents in a JSON-like format, which is great for object-oriented programming and conversion of
  data models.
- The database accepts JSON-Documents as well as values, like arrays or even objects and datatypes. It's supporting
  flexible and dynamic schemes.
- Queries are very powerful, even though the data models can be very nested. Aggregations and more modern use cases are
  supported.
- Queries do have the JSON format and there's no need to chain them to create SQL-queries
- It is easy to install and use locally, additionally you can use a UI to see what is happening with the data.

**See our Database and its code [in this directory](/src/main/code/de/tuchemnitz/se/exercise/persist).**
**All tests are [here](/src/test/code/de/tuchemnitz/se/exercise/persist).**

*See more information on why to use Mongo
DB [here](https://www.mongodb.com/why-use-mongodb#:~:text=Companies%20and%20development%20teams%20of,of%20both%20data%20and%20traffic.)
.*

## Milestone :six:

*Deadline: 07.01.2021*

### Overview CodeCharts :chart_with_upwards_trend:

The task for this milestone was to implement the basics of one tool and to save the collected values of it. We
implemented Code Charts mostly by using Pair-Programming. To get the GUI working, we used a framework
called [TornadoFX](https://edvin.gitbooks.io/tornadofx-guide/content/).

### StringHandler

This class is not supposed to provide any GUI elements, but it is able to generate random Strings with a certain length
in a reasonable time. For this functionality we used an equation to calculate this length:
<img src="https://render.githubusercontent.com/render/math?math=\frac{\ln{(input)}}{\ln{(alphabetLength)}}.roundToInt() %2B 1">
. We added 1 in this equation to accelerate the randomization process. An example:
If we want to generate 26 random strings, consisting out of lowercase letters only, we are able to achieve this with 26
strings (length one). In this case however, generating the strings gives us a probability of 1/26 for the last string (
assuming we already have the other 25). This is very low and would take too much time.

The StringHandlerClass is also able to store the generated strings in a list and to order them by natural order, using
the `orderList()` method.

### CodeChartsDataValues

Objects of this class are able to store all the relevant data for CodeCharts and provide the corresponding setters and
getters. In addition to this they are also able to store the eye position, which is important for the data analysis
client.

### Calling the CodeCharts-Sub application

Currently, the CC sub-application named CodeChartsTool is called in the `ToolSelectionView.kt`.
`editData()` is a function, which retrieves the needed values from the *ConfigManager*. Afterwards it calls
the `setStrings` method of our StringHandler, to generate strings, which are following the rules given in a data class
object of `StringCharacters`. This is defined in the file `CodeChartsDataValues.kt`.

### CodeCharts-View

This class is used for starting the experiment by pressing a button. Some information about the conduction of the
experiment can easily be added by using text fields, or other graphical elements of TornadoFX. After pressing the "
START"-Button, the conduction of the experiment starts, and the Dialog-View is replaced by CodeChartsPictureView.

### Picture-View

This View is used to display an image specified in the "path"-value of our config file. We implemented an algorithm, to
scale the image to the screen size of the user - if the image width and/or image height (of the image which is supposed
to be loaded) are too big, the image is being scaled to the users screen size, while maintaining the aspect ratio. The
same procedure applies to images that are too small. This algorithm is implemented in the method called `scaleImageSize`
.

### Grid-View

After the delay set in Picture-View this View is shown. It contains a data grid with the strings generated by
the `setStrings`-Method of the StringHandler-Class. In the data grid we used a stackpane to set some styling values for
the grid cells (rectangles) and write the label where each one contains a string from the generated String list of our
StringHandler Object. The width and height of the cells in pixels are calculated using the scaled size of our image
which was shown in PictureView and the gridDimensions.

### InputValidator-View

This view is shown after the delay, set in Grid-View. It contains a textfield, to give users the opportunity to input
strings. After the button "Abschicken" is pressed by the user, a method `validateInput` is called. This function uses
the getter function the StringHandler provides for the generated Strings list and checks whether this list contains
the `inputString`. If so, the InputValidator-View is replaced by the Thankful-View. However, if the inputString is equal
to `""` nothing happens, otherwise it is replaced by the Retry-View. If the user provided a correct String, the
eye-Position of the user on his/her screen is being calculated as an Interval2D value, which is a data class, defined in
the file `CodeChartsDataValues.kt`. This value contains an x- and a y- Interval in pixels, based on the scaled picture:

    private fun calculateEyePosition(userInput: String) {
            val listPosition = codeChartsStringHandler.getStrings().indexOf(userInput)
            val xFieldNumber = listPosition % (codeChartsData.gridDimension.x)
            val yFieldNumber = (listPosition / (codeChartsData.gridDimension.y).toInt())
            val cellWidth = codeChartsData.scaledImageSize.y / codeChartsData.gridDimension.x
            val cellHeight = codeChartsData.scaledImageSize.y / codeChartsData.gridDimension.y
            val xMinPos = xFieldNumber * cellWidth
            val yMinPos = yFieldNumber * cellHeight
            val xMaxPos = xMinPos + cellWidth
            val yMaxPos = yMinPos + cellHeight
    
            // interval will later be used for data analysis
            val eyePos = Interval2D(xMin = xMinPos, xMax = xMaxPos, yMin = yMinPos, yMax = yMaxPos)
            codeChartsData.eyePos = eyePos
    
            logger.info("${codeChartsData.eyePos.xMin}, ${codeChartsData.eyePos.xMax}, ${codeChartsData.eyePos.yMin}, ${codeChartsData.eyePos.yMax}")
            logger.info("${codeChartsData.scaledImageSize.x}, ${codeChartsData.scaledImageSize.y}")
        }

### Thankful-View and Retry-View

The Thankful-View appears after the InputValidator-View in case the user provided a String, which is a member of the
generated String list. The Retry-View appears if the String, provided by the user, is not part of the list of generated
Strings. Both Views give the user the opportunity to retry or to leave the program.

### Overview ZoomMaps :mag:

To implement the zoom maps-tool we created a class called `ZoomMapsView`. This view implements the functionality of
zooming in and out on a picture. We also addressed the required functionalities. With the injected property
`scaleProperty` the user can choose a zoom speed and from the configuration file we read the user assigned zoom key. To
detect an intended zoom we added a
`EventFilter` which detects when the assigned key is being pressed and when it is released. To store the location of the
zoom, we initialize the variable `zoomPosition`. When the zoom key is being triggered, the value is being saved.
Beforehand we have to adjust the position in the picture to the applied zoom.

## Milestone :seven:

*Deadline: 20.01.2021*

### Part :seven:.:one:

For the interaction with the database we created a class named `AbstractCollection`. It contains methods to allow
finding elements matching one or multiple filters, to save or delete one or multiple entries. To ensure that each tool
gets its settings we also have created a `ConfigManager`. This class covers everything related with the config files of
the tools.

### Part :seven:.:two:

To implement the Data Analyst, we created multiple views and classes. In the `ClientDataIntialView` we gather user
inputs for filterparameters. This data is being stored in the `DataClientQueryModel`. When the user presses the start
button, the class `DataAnalyst` is being called with the passed parameters. This class instantiates a query and returns
the data form the database back to the initial view. The `Query` class searches the datasets in the database for the
given filters. To do that we divide the filter into multiple subfilters und return the combined results. In
the `DataProcessorHeatMap` the datasets are being processed to return a list of point coordinates. We need these
coordinates in the `HeatMapView`
to draw a circle around each pair of coordinates to visualize the areas that had been viewed.
