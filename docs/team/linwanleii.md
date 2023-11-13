---
layout: page
title: Lin Wan Lei's Project Portfolio Page
---

### Project: PharmHub

PharmHub - PharmHub is an order and patient tracking application for small remote pharmacist to implement an all-in-one application to improve precision and efficiency. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to undo/redo previous commands.
  * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*

* **New Feature**: Added A Detail information display to GUI.
  * What it does: Allows user to view more detailed information on order `viewo` and person `viewp`. Displays the information of most recent added/edited/deleted order or person.
  * Justification: This feature improves the product significantly because the user can view and analysis more in-depth information easy. Furthermore, displaying the orders or person after an action is done on them helps them check and rectify their actions.
  * Highlights: This enhancement affects how the whole application works and displays itself. It enabled a lot of future implementation that could be build upon to further enhance the application. The challenging part of the implementation was to determine the characteristic and the look of the display.
  * Credits: NIL

* **New Feature**: Added A Status for Orders.
  * What it does: 
  * Justification: 
  * Highlights: 
  * Credits: NIL

* **New Feature**: Added A find order command.
  * What it does:
  * Justification:
  * Highlights:
  * Credits: NIL

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=linwanleii&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=LinWanLeii&tabRepo=AY2324S1-CS2103T-W08-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
  * Team lead for the group.
  * Managed meetings, discussion and task allocation for the team throughout the project.

* **Enhancements to existing features**:
  * Updated the GUI color scheme (Pull requests [\#33](), [\#34]())
  * Wrote additional tests for existing features to increase coverage from 88% to 92% (Pull requests [\#36](), [\#38]())

* **Documentation**:
  * User Guide:
    * Added documentation for the features `addo`, `deleteo`, `updates` and `findo` [\#124](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/124/files)
    * In charge of `Table of Content`, `Quick Start` and `Trouble Shooting`
    * Added new category called `Definition` and `Navigation`
  * Developer Guide:
    * Added implementation details of the `findo` and `addo` feature.
    * Managed the Table of content.
    * Updated the storage component.
    * Updated and added all use cases.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [Add medicine and update UI in DG\#184](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/184), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())

