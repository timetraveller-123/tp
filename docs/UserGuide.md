---
layout: page
title: User Guide
---

Pharmhub is a **desktop app for managing patients and their medication orders, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, PharmHub can get your patient/ order management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `pharmhub.jar` from [here](https://github.com/AY2324S1-CS2103T-W08-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your PharmHub.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar pharmhub.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all people.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a person named `John Doe` to PharmHub.

   * `delete 3` : Deletes the 3rd patient shown in the current list.

   * `clear` : Deletes all people.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `addp`

Adds a person to PharmHub.

Format: `addp n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [no/ALLERGY]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags or allergies (including 0)
</div>

Examples:
* `addp n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 no/paracetamol no/aspirin`
* `addp n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 t/criminal`

### Listing all persons : `listp`

Shows an interactive list of all persons in PharmHub.

Format: `listp`

### Viewing a person `[v1.3]`

Displays the specified person with more details in the info panel.

Format: `viewp INDEX`

Examples:
* `listp` followed by `viewp 2` opens the 2nd person in the list into the info panel.

### Editing a person : `editp`

Edits an existing person in the PharmHub.

Format: `editp INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG] [no/ALLERGY]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* This command will not be able to add/delete orders to this person
* Existing values will be updated to the input values.
* When editing tags or allergies, the existing tags or allergies of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.
* You can remove all the person’s allergies by typing `no/` without
    specifying any allergies after it.

Examples:
*  `editp 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `editp 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
*  `editp 3 no/` Edits the allergies of the 3rd person to be empty.

### Locating persons by name: `findp`

Finds persons whose names contain any of the given keywords.

Format: `findp KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `findp John` returns `john` and `John Doe`
* `findp alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find alex david'](images/findAlexDavidResult.png)

### Deleting a person : `deletep`

Deletes the specified person from PharmHub.

Format: `deletep INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `listp` followed by `deletep 2` deletes the 2nd person in the address book.
* `findp Betsy` followed by `deletep 1` deletes the 1st person in the results of the `find` command.

### Listing all orders : `listo` `[v1.2]`

Shows an interactive list of all orders in PharmHub.

Format: `listo`

### Viewing an order : `viewo` `[v1.3]`

Shows the order in the info panel.

Format: `viewo`

### Adding a new order : `addo` `[v1.2]`

Adds a new order of medication corresponding to a patient into the system.

Format: `addo INDEX o/ORDER_NUMBER m/MEDICINE_NAME [/ia]`

* Orders are created automatically having a `status` of `pending`.
* Orders can only be created for a person in the index range, and for a known `medicine`
* Orders created for persons with an allergy to any of the medications in the order will raise a warning.
* The warning can be overridden by adding the `/ia` to the command

Parameters:
* `INDEX` - index of patient who is ordering the medicine as shown in the patient list.
* `ORDER_NUMBER` - the order number of this order specified by the invoice.
* `MEDICINE_NAME` - the name of medicine being ordered.

Examples:
* `addo 1 o/618457 m/panadol`
* `addo 3 o/438756 m/claritin`


### Updating the status of an order : `updates` `[v1.3]`

Updates the status of the order to the designated status.

Format: `updates INDEX s/STATUS`

* Statuses: `Pending (pd) -> Preparing (pr) -> Completed (cp) -> Cancelled (cc)`, in increasing hierarchy
* Statuses can only be updated upwards. Once the status of an order progresses to the next stage, it cannot go back.
* Statuses can be updated by skipping the hierarchy. `Pending -> Cancelled`
* Shorthands can be used in replacement of the full names of the statuses

Example: 
* `updates 1 s/completed`
* `updates 1 s/COMPLETED`
* `updates 1 s/cp`
* `updates 1 s/CP`

### Filtering/Finding Order by status and medicines: `findo`

Finds orders whose status and medicine satisfies both inputs.

Format: `findo s/STATUS m/MEDICINE_NAME [m/MEDICINE_NAME]...`

* The search is case-insensitive. e.g `PANADOL` will match `Panadol`, `COMPLETED` or `CP` will match `Completed`.
* User input can find orders base on either status or medicines or both(but both will have to be satisfied).
* Status can only be `Pending/PD Preparing/PR Completed/CP Cancelled/CC`, any other inputs will be invalid.
* Medicine can be written both in their short form and full form. `pan` will match `Panadol`.
* Multiple Medicine can be used as input separated by a blank space but only one status can be used.
* Orders that contain any one of the medication/Status will be shown.

Examples:
* `findo m/Panadol Ibuprofen` returns any orders with either `Panadol` or `Ibuprofen`.
* `findo s/pd m/Panadol` returns any orders that is both `Pending` and contains `Panadol`.
  ![result for 'findo s/pd m/Panadol'](images/findOrder2Input.png)

### Deleting an order : `deleteo` `[v1.2]`

Deletes the specified order from PharmHub.

Format: `deleteo INDEX`

Example: `deleteo 2`


### Undoing an action : `undo` `[v1.3]`

Undoes the last data-modifying action.

Format: `undo`

* Limited to last 30 actions.
* Does not undo Ui views (eg. find, view commands). 

Example: 
* `addp` -> `listp` -> `undo` will undo the `addp` command

### Redoing an action (after an undo) : `redo` `[v1.3]`

Negates the effect of the last undo.

Format: `redo`

* The undo command must have been the latest data-modifying command.
* Once a non-`undo` data-modifying command is executed, redoing undoes before that non-`undo` command is no longer possible

Example:
* `addp` -> `undo` -> `addp` -> `redo`  will throw an error
* `addp` -> `undo` -> `listp` -> `redo` will redo the `addp` command successfully



### Clearing all entries : `clear`

Clears all entries from PharmHub.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

PharmHub data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

PharmHub data are saved automatically as a JSON file `[JAR file location]/data/pharmhub.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, PharmHub will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous PharmHub home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                  | Format, Examples                                                                                                                                                                                |
|-------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **List People**         | `listp`                                                                                                                                                                                         |
| **Find Person**         | `findp KEYWORD [MORE_KEYWORDS]`<br> e.g., `findp James Jake`                                                                                                                                    |
| **View Person**         | `viewp INDEX` <br> e.g., `viewp 1`                                                                                                                                                              |
| **Add Person**          | `addp n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG] [no/ALLERGY]…​` <br> e.g., `addp n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague no/aspirin` |
| **Edit Person**         | `editp INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG] [no/allergy]…​`<br> e.g.,`editp 2 n/James Lee e/jameslee@example.com`                                                      |
| **Delete Person**       | `deletep INDEX`<br> e.g., `deletep 3`                                                                                                                                                           |
| **List Orders**         | `listo`                                                                                                                                                                                         |
| **Find Order**          | `findo [s/STATUS] [m/MEDICINE_NAME]...`<br> e.g., `findo s/cp m/pen`                                                                                                                            |
| **View Order**          | `viewo ORDER_NUMBER` <br> e.g., `viewo 12345`                                                                                                                                                   |
| **Add Order**           | `addo INDEX o/ORDER_NUMBER m/MEDICINE_NAME [m/MEDICINE_NAME]...` <br> e.g., `addorder 3 o/438756 m/claritin`                                                                                    |
| **Update Order Status** | `updates INDEX s/STATUS`<br> e.g., `updates s/cancelled`                                                                                                                                        |
| **Delete Order**        | `deleteo INDEX`<br> e.g., `deleteo 3`                                                                                                                                                           |
| **Undo**                | `undo`                                                                                                                                                                                          |
| **Redo**                | `redo`                                                                                                                                                                                          |
| **Clear**               | `clear`                                                                                                                                                                                         |
| **Help**                | `help`                                                                                                                                                                                          |
| **Exit**                | `exit`                                                                                                                                                                                          |
