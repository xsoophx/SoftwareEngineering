<h1>Software Engineering - Eye-Tracking-Tool</h1>
<h2> What is this about:question: </h2>
This is an assignment project for Software Engineering class at TU-Chemnitz.

## Table of contents :book:

-   [Our toolbox](#which-tools-will-we-use?)
-   [Milestone 01](#milestone-one)
    -   [Enumeration of Volere Snowcard IDs](#enumeration-of-volere-snowcard-ids)
-   [Milestone 02](#milestone-two)
-   [Milestone 03](#milestone-three)
-   [Milestone 04](#milestone-four)
    -   [UML-sequence diagrams](#uml-sequence-diagrams)
        -   [Definition of sequence diagrams](#definition-uml-sequence-diagram-bookmark_tabs)
    -   [UML-state machines](#uml-state-machines)
         -   [Definition of state machines](#definition-uml-state-machine-bookmark_tabs)
-   [Milestone 05](#milestone-five)
    -   [Part 1](#part-one)
        -   [Project Language](#project-language)
        -   [Unit Testing](#which-kind-of-tests-are-we-using?)
        -   [Documentation and Coding Conventions](#how-are-we-documenting-our-code-and-what-are-our-code-conventions?)
    -   [Part 2](#part-two)
        -   [Overview](#overview)
        -   [The database](#the-database-floppy_disk)
        -   [The graphics framework](#the-graphics-framework-art)

## Which tools will we use?

The toolbox for capturing and evaluating of visual attention consists of multiple, different parts:

-   Config File :wrench:
-   Tool: Bubble View :wind_chime:
-   Tool: Zoom Maps :mag:
-   Tool: Code Charts :chart_with_upwards_trend:
-   Tool: Webcam based Eyetracking :eyes:
-   Storage for collected data :floppy_disk:
-   Client for analysing of the data :microscope:

<br />

## Milestone :one:

_Deadline: 05.11.2020_

The first task was to describe the requirements of the different parts, with the help of Volere Snow Cards.
For each tool, a functional as well as a non-functional requirement needs to be described.

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

The second task was to analyze all of the components of the toolbox with the help of CRC Cards. All classes, dependencies, responsibilities, collaborations and relations, but also hierarchies (and inheritance) are supposed to be found. Also, the results need some reasoning for the chosen arrangements.

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

The third task was to create a UML - class diagram based on the class hierarchy of our **CRC-Cards** ([_old version_](docs/m02/crc_cards) vs. [**new version**](docs/m03/crc_cards)). We were supposed to give the exact interfaces of the classes, as well as attributes, which have to be managed by these classes. The decision for this design should be describable with only a few words.
[Our finished UML - class diagram.](https://tuc.cloud/index.php/s/FcfBdMmGcRcMam7)
## Milestone :four:
_Deadline: 26.11.2020_

The fourth milestone was to create UML-sequence diagrams, as well as UML state machines. 

### UML-sequence diagrams

We were supposed to create a sequence diagram for the act of starting a tool, as well as for using and closing it. At least one tool should be described with a sequence diagram. Moreover, a diagram for the data analysis client was wanted. Starting of this tool, but also importing data, and the analysis itself should be depicted in at least one way.

#### definition UML-sequence diagram :bookmark_tabs:
_The sequence diagram shows a scenario, by depicting interactions between objects within a certain time span._

### UML-state machines

We also had to create a state machine for at least one of the tools. Suitable states and transitions (also with possible conditions and inputs) needed to be defined, to find a model for the behaviour of the tool. Also, the state machine of the data client was wanted.

#### definition UML-state-machine :bookmark_tabs:
_The state machine is describing the development of an object over time. Dependencies of interactions with other objects, which the system does not contain, are depicted._

## Milestone :five:

### Part :one:
_Deadline: 08.12.2020_

### Project Language
Why are we using **Kotlin**?
-   It's easy and completely interoperable with Java. So in case, we want to code some parts in Java, it's easy to add those code parts.
-   We will be able to solve problems with a fewer amount of lines of code.
-   The code is more reliable, easier to maintain, easier to read, and it's also easier to apply changes when they're needed.
-   The compiler is smarter and safer. It detects errors at compile time and performs a lot of checks.
-   Kotlin has a good support for Functional Programming.
-   Kotlin has Null Safety, which means it eliminates the danger of null references.

### Which kind of tests are we using?

We decided for Kotlin Unit Tests to build our code faster and more reliable. Even though, we will have a lot more code because of the tests,
it will be easier to find bugs and to solve them. With *Test Driven Development*, our requirements will be turned into specific 
test cases, which will not pass in the first run. After that, our main code will be written or refactored, so the tested code will actually
pass the tests. Thus, a lot of time will be saved, even though we're writing more code. Less or even no more debugging is needed, 
because we already know, which part of our code is failing. Also, build-in Unit Tests are providing documentation for the code, 
because it's easy to get a basic picture of the logic of the tested class or module just by looking at the tests.
Moreover, we can see how much code is actually covered, just by running the tests with coverage. More coverage means
fewer lines of code which could contain bugs. Our tests will be mainly built with [JUnit5](https://junit.org/junit5/docs/current/user-guide/) and [assertk](https://github.com/willowtreeapps/assertk).

In a nutshell, our tests will ensure that we have a reliable engineering environment and that our code will meet quality standards. See more info about TDD [*here*](https://fortegrp.com/the-importance-of-unit-testing/#:~:text=Developers%20write%20unit%20tests%20for,%2Ddriven%20development%20(TDD).) and [*here*](https://blog.cleancoder.com/uncle-bob/2013/03/06/ThePragmaticsOfTDD.html).


### How are we documenting our code and what are our code conventions?

**Documentation and comments:** 
-   For the documentation, we are using description of parameters, just like in the Kotlin standard documentation (KDoc). All public classes and methods will be commented by using ```/**...*/```. Instead of using *@param*, *@return*, *@throws* and so on, we will be using *[paramname]* plus a description, and an automated API-Documentation can be created.
-   "Normal" comments should be very short and precise, if needed. Comments will be written by ```/*...*/``` or ```//...```. They won't be used for documentation purposes.

**Naming Conventions and types**
-    *Language* - Everything in this project will have english naming. It's important, especially for e.g. interfaces. By providing english naming, international stakeholders, clients and so on, can understand the methods better.  

-   *Import directives* - All imports will be in alphabetical order. Imports will always be precise, no ```.*``` annotation for imports will be used. Moreover, unused imports will be removed.

-   *Classes and Interfaces* - Class- and Interface names will start with an uppercase letter, followed by lowercase letters. If the name consists out of multiple words assembled together, each word is starting with an uppercase letter (also known as camel-case).

-   *Methods* - All Methods will start with a lowercase letter, followed by lowercase letters. If the name consists out of multiple words assembled together, each word is starting with an uppercase letter (also known as camel-case), except of the first one.

-   *Attributes and Variables* - This is the same as for Methods. Variables and Attributes will be starting with a lowercase letter, followed by lowercase letters. If the name consists out of multiple words assembled together, each word is starting with an uppercase letter (also known as camel-case), except of the first one.

-   *Constant parameters* - All constants will be written in Uppercase. If the name consists out of multiple words assembled together, each word is separated through a "```_```".

-   *Trailing commas* - We will be using trailing commas to make version control diffs cleaner, to easily add and reorder elements in enumerations or to simplify code generation. This can also be found in the [Kotlin coding-conventions](https://kotlinlang.org/docs/reference/coding-conventions.html).

    *See more info [here](https://www.oracle.com/java/technologies/javase/codeconventions-namingconventions.html) and [here](https://kotlinlang.org/docs/reference/coding-conventions.html).*

### Summary

In the first part of this milestone, we were supposed to write automated tests, which are testing our methods. At least all public methods should already be known, since we created UML diagrams, which are showing a huge part of the classes and methods already. We agreed, that we will create all classes and public methods we already know, but also all tests, which are related to those classes. Also tests which could test private methods, of which we know the functionality, are written down in our code. To sum this up, after the first part of this milestone, we will have test declarations, which are supposed to test at least all public methods we know already. Moreover, they need the declaration of their corresponding class and the methods, so we created them too. Less to no functionality is built in here. Tests without any functionality, but only the declarations are looking like this:

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

Another task of this part of the milestone was to agree on code conventions and on how we will do our documentation, which you can see [above](#how-are-we-documenting-our-code-and-what-are-our-code-conventions?).

### Part :two:
_Deadline: 12.12.2020_

### Overview

The task for our second part of the 5th milestone was not given. That means, we could decide what the second part should be. We came to the result, that the second task would be the completion of our database and the corresponding tests. Whoever felt like starting with the implementation for a tool, was free to actually start with that and sync up and explain what he/she did in our regular meetings.

### The database :floppy_disk:

We decided to use [Mongo DB](https://www.mongodb.com/) as a database. It is a document database, which is very useful for future implementations. More reasons where the following:
-   Mongo is saving its documents in a JSON-like format, which is great for object-oriented programming and conversion of data models.
-   The database accepts JSON-Documents as well as values, like arrays or even objects and datatypes. It's supporting flexible and dynamic schemes.
-   Queries are very powerful, even though the data models can be very nested. Aggregations and more modern use cases are supported.
-   Queries do have the JSON format and there's no need to chain them to create SQL-queries
-   It is easy to install and use locally, additionally you can use a UI to see what is happening with the data.

**See our Database and its code [in this directory](/src/main/code/de/tuchemnitz/se/exercise/persist).**
**All tests are [here](/src/test/code/de/tuchemnitz/se/exercise/persist).**

*See more information on why to use Mongo DB [here](https://www.mongodb.com/why-use-mongodb#:~:text=Companies%20and%20development%20teams%20of,of%20both%20data%20and%20traffic.).*

