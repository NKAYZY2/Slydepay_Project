<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Purchase Order</title>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>


<style>


/* Full-width input fields */
input[type=text], input[type=password] , input[type=email] , input[type = number] {
    width: 100%;
    padding: 15px;
    margin: 5px 0 22px 0;
    display: inline-block;
    border: none;
    background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
    background-color: #ddd;
    outline: none;
}

hr {
    border: 1px solid #f1f1f1;
    margin-bottom: 25px;
}

/* Set a style for all buttons */
button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 100%;
    opacity: 0.9;
}

button:hover {
    opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
    padding: 14px 20px;
    background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn, .signupbtn {
  float: right;
  width: 25%;
}

.submitbtn{
  float: right;
  width: 25%;
      background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    cursor: pointer;
    width: 25%;
    opacity: 0.9;
}

/* Add padding to container elements */
.container {
    padding: 16px;
}

/* Clear floats */
.clearfix::after {
    content: "";
    clear: both;
    display: table;
}

/* Change styles for cancel button and signup button on extra small screens */
@media screen and (max-width: 300px) {
    .cancelbtn, .signupbtn {
       width: 100%;
    }
}
</style>


<body>
	
<form action="Controller" method="post" style="float:center;">
  <div class="container">
    <h1> Order page </h1>
    <p>Please fill in this form with your Order.</p>
    <hr>

    <label for="email"><b>Merchant Email</b></label><br>
   
     <input type="email" name="merchantEmail" placeholder="Your Merchant Email..." required>
    <br>
    
   <label for="email"><b>Merchant Key</b></label><br>
   
     <input type="number" name="merchantKey" placeholder="Your merchant key" required> 
    <br>
   

    <label for="psw"><b> Order name </b></label> <br>
    <input type="text" name="orderName" placeholder="Your Order Name" required> 
    <br>

    <label for="psw"><b> Item name </b></label> <br>
    <input type="text" name="item" placeholder="Item Name" required> 
    <br>
    
 
 <label for="psw"><b>  Quantity </b></label> <br>
    <input type="number" name="quantity" placeholder="Quantity" required>
    <br>
 		 
 
    <div class="clearfix">
        <input type="submit" class="submitbtn" name="submit" value="Purchase" />
        
      <button onClick="location.href='Order.jsp'" type="button" class="cancelbtn">Cancel</button>
   
    </div>
  </div>
  
   <input type="hidden" name="action" value="restPost">    <!-- hidden value set in form for servlet processing -->
  
</form>

</body>

</html>