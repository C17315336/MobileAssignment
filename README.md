# Mobile Software Development CA - Android App Assignment [![CodeFactor](https://www.codefactor.io/repository/github/c17315336/mobileassignment/badge)](https://www.codefactor.io/repository/github/c17315336/mobileassignment)

Completed by Eoghan Byrne (C17315336)  
10th December 2019  
[c17315336@mytudublin.ie](mailto:c17315336@mytudublin.ie)

## Index

- [Overview](#overview)
- [Classes](#classes)
- [Approach](#approach)
- [Reasoning](#reasoning)
- [Tests](#tests)
- [Challenges](#challenges)
- [Interesting Elements](#interesting-elements)
- [Brief](#brief)

## Overview

The app is designed to allow a user to add individuals to the app under the headings of 4 possible options. Once inserted the listenings can be updated or deleted. The app is also designed to be localised for Ireland.

[Link to Github](https://github.com/C17315336/MobileAssignment/)

## Approach

I approached each aspect of the assignment is multiple different ways as with dyslexia this tends to be the most common case.

1. Storing the data in a SQLlite Database. This one was handy enough in the sense that I knew we had to use the `RoomDatabase` alongside a `DAO`.

2. Input screen composed of the need to take in:

   - Category - through the use of a Dropdown menu
   - Name - `EditText` allowed the capturing of this data
   - Details - Again `EditText` allowed the capturing of this data

3. Listing the content I knew I would have to use a *RecyclerView* to facilitate the functionality correctly.

4. The underlying operations all took place within the DAO as seen below;

   ```java
   @Dao
   public interface MemberListDAO {
       @Insert
       public void insert(MemberList... memberLists);

       @Update
       public void update(MemberList... memberLists);

       @Delete
       public void delete(MemberList... memberList);

       @Query("SELECT * FROM member")
       public List<MemberList> getMemberLists();

       @Query("SELECT * FROM member WHERE id = :number")
       public MemberList getMemberListWithId(int number);
   }
   ```

5. The element of having at least one of the listed screens I choose the do all three through **input** of a new member, the **displaying** of the members & finally the extra screen was that of the Splash screen on launch.

6. Localised for one other language I choose that of Irish :ireland:

7. Two Android Features I choose that of the previously mentions Splash screen on launch and the  second I attempted to have a search field available which can been seen in some areas of the code.

## Reasoning

All of the above statements have been reasoned behind the material provided throughout the module as that is the real outlet for information. Granted there is the whole [Android Developers](https://developer.android.com/) website that is an unbelievable asset and resource however with having Dyslexia the shear volume of text was and still remains a huge turn off.

As a general overview all of the above features should cater for a simple UI so thus should form an enjoyable and simple UX for the end-user.

## Tests

Testing was limted in that I personally don't own an Android device, so as such I was limited to only the use of the Emulator. Generally throughout the use of the app no issues were found, e.g. the repeated tapping of a button.

## Challenges

No major challenges with regards to the project other than the element of adding 2 external features. This was difficult as we haven't had alot of exposure to Android Studio nor infact to that of Java as we only sat a single semester in Year 2. In general any and all challenges were overcome by just keeping the head down until personal health & wellbeing aswell as that of the deadline are empossed.

## Interesting Elements

Interesting Elements from the project, unfortunatly there wasn't really any, other than how once correctly implamented the Strings can be very easily localied for the user experience.

___

## Brief

___

#### Mobile Software Development CA

#### Android App Assignment

Please find below the details for your Android assignment. Students are advised to **carefully read**
all four pages of this document before beginning work on the assignment. Any queries related to it
should be emailed to david.leonard@tudublin.ie

##### IMPORTANT DEADLINES

| What                       | Where       | When                                  |
| -------------------------- | ----------- | ------------------------------------- |
| Report and Code Submission | BrightSpace | Monday December 9th at 23:55          |
| Demo                       | Lab         | Wednesday December 11th 09:00 – 11:00 |

##### SUBMISSION AND DEMO DETAILS

These two items should be uploaded to **BrightSpace** by the deadline (December 9th at 23:55):

1. **Report** a word processing document e.g. word or pdf
2. Project files necessary to run **app** i.e. source code, resources, manifest, gradle files etc. Just use the **Export to Zip File**... command (screenshot below) in Android studio:

##### Demo details

Students will be expected to smoothly demonstrate their app and **answer any questions** about their
code posed during the **allotted time** for their demo.

##### REGULATIONS / LATE SUBMISSIONS

1. Assignments will **not** be corrected unless **demoed**.
2. A 10% penalty will be applied for **each day late**.

The app must be your own work. Assignments that are copied or written by someone else will receive
**Zero marks** and the plagiarism escalated as per TUD assessment regulations.

##### EXTERNAL CODE / CODE SNIPPETS

If you use code snippets that you obtained from an online or book example, you MUST reference with
an opening AND closing comment around the code block itself in the .java file and/or XML file e.g. for
java

```java
// Reference: The following code is from Android example @www / wherever
Code snippet here...
// Reference complete
```

If you don’t reference code snippets and the code is not yours, it is technically plagiarised code. It is not
practical to prevent students from using code snippets, but marks **will reduce** the more you rely on
code written by someone else i.e. ultimately it is **your programming skills** that are being examined.

##### PERCENTAGE OF OVERALL GRADE

The app development assignment is worth **30%** of your overall mark in Mobile Software development.
It is marked as follows:

1. **App** – 70 out of 100 marks ( **conditional on successful demo** )
2. **Report** – 30 out of 100 marks

##### SPECIFICATION

The purpose of this assignment is to develop an Android app. The app can be about anything that you
want, but has to include the following **skeleton features** :

1. The app stores data in a local **SQLlite** database.
2. It contains an **Input** screen where the user enters data.
3. It has a **List** (populated with data from the SQLlite database).
4. Has underlying **operations** on the database - i.e. all of `INSERT`, `UPDATE`, `SELECT`, `DELETE` usage on the database (user login functionality does not count towards this).
5. At least one of each of the following: List screen, Input screen, Extra screen.
6. Localised for one other **language** e.g. Irish, French etc.
7. Use at least two Android features outside of the above specification. The purpose of
   this is to demonstrate your ability at using the API to discover new features, which you
   can then implement because you should have built up enough general familiarity with
   Android to be able to do this. Examples of features might be using using the Camera
   API, Location services, more creative GUIs using features such as Touch input.

##### What sort of app should you develop

It’s up to you, if it matches your interests then it should be fun to do. Given the specification, the idea
is to develop an app that captures information about something (e.g. a sports team, news items,
college work, social media,... etc. ). You need to make sure that you include the functionality requested so your app will have at least four or five screens in total.

##### REPORT

The report should be two pages covering the following topics:

1. How you approached the development of each component of your app.
2. Reasons behind design decisions made e.g. efficiency, user experience etc.
3. Tests that were carried out to ensure the app works correctly.
4. Challenges faced during the development and how these were overcome.
5. What interesting things you learned about Android during the course of developing the
   app.

##### MARKING SCHEME

Marks will be awarded under the following categories:

**Report** (30%)
The report will be marked based on the depth of coverage of the five topics and how this
corresponds to a realistic development process for the delivered app.

**App** (70%)

1. **Correctness / Completeness** (28%)

   Working commented code that meets the deliverables listed in the **specifications** above to a high standard. Standard coding conventions that provide a useful guide are available at this [link](https://google.github.io/styleguide/javaguide.html).

2. **Quality of the user interface** (14%)
       Usability and Coherence of UI screens i.e. the extent to which a real user or client's
       needs have been considered.

3. **Overall quality and complexity** (28%)
       Does the app function well and robustly? To what extent is it original? Are there
       creative aspects to it? How complex is it?