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

The second task was to analyze all the components of the toolbox with the help of CRC Cards. All classes, dependencies, responsibilities, collaborations and relations, but also hierarchies (and inheritance) are supposed to be found. Also, the results need some reasoning for the chosen arrangements.

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

