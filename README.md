# Project: bills-manager
#### OVERVIEW: 
    Bill Manager is a application design to keep a track of one's bills in a simple way.

#### USED TECHNOLOGIES:
    Java(17), Spring Boot(3.0.6), Spring Validation, Spring Data JPA, MySQL, Spring MVC, Thymeleaf, Bootstrap, AJAX, JQuery, Maven

## iNTERFACES:
    Bills-manager is an application composed of 2 interfaces:
        => Register Interface: to register a new bill
        => Search Interface: to list/edit the bills already registered.

  ## HOW DOES IT WORK?
    To register a bill, the following inputs are required:
      Description - Title defined by the user to identify the bill
      Due Date - Bill's due date to help the user to follow it up.
      Amount - Value of the bill.
      Status - Situation of the bill: PENDING or PAID

    After the bill is registered, the user can find it in the list of all bills or use the description filter to search for a specific one.
    On the Search Interface, it is also possible to delete a bill or to simply chaging its status (from pending to Paid) with only one click.
    For edition of others bill's information, such as description, amount, due date or status (from 'PAID' to 'PENDING'), the user can use the Edit button.
 
