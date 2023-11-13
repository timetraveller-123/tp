---
layout: page
title: Vijayaraghavan Vishnuprasath's Project Portfolio Page
---

### Project: Pharmhub

PharmHub is an order and patient tracking application for small remote pharmacist to implement an all-in-one application to improve precision and efficiency.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

**New Feature**: CRUD for medicine  ,`addm`, `listm`, `findm`, `deletem`, `sfm`

* What it does: It allows the user to add a medicine, delete a medicine, list all medicine, find a medicine and update a medicine with its short form. Medicines are also stored in same storage file as persons.

* Justification: This feature allows the application to maintain medicines as a fundamental type rather than string. As such, it allows for better invalid medicine
                  detection and duplicate detection. It also allows for the user to enter short form for a medicine which will save a signficant time when entering
                  the full form of the medicine over and over again.

* Highlights: None.

* Credits: Medicine features was modelled closely to the Person feature in AB-3.


**New Feature**: Add and delete order, `addo`, `deleteo`

* What it does: Allows the user to add and delete orders from the system.Orders are also stored in same storage file as person.

* Justification:  This allows the user to add an order for a person and delete it if cancelled.

* Highlights: None

* Credits: Order was also modelled closely to the Person feature in AB-3.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=timetraveller-123&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)



* **Enhancements to existing features**:

Allow name to take in non-alphanumeric characters[#120](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/120)  
Ignore extra spaces and allow inputting / in parameters. [#116](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/116)   
Fix JavaDocs comments throughout the file. [#211](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/211)  


* **Documentation**:

***User Guide***  
Add documentation for features `addm`, `listm`, `findm`, `deletem`, `sfm`  
Add glossary

***Developer Guide***  
Add implementation for `sfm`.  
Add instruction for manual testing of `addm`, `listm`, `findm`, `deletem`, `sfm`  
Add Non-Function Requirements  
Add Effort in Appendix.  
Update UI class diagram



* **Community**:

PRs reviewed &#40;with non-trivial review comments&#41;: [\#54](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/54) , [\#88](https://github.com/AY2324S1-CS2103T-W08-4/tp/pull/88 );





