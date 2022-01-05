<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="models.Category"%>
<%@page import="models.Book"%>
<%@page import="models.Author"%>
<%@page import="models.Cart"%>
<%@page import="models.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
<%@page import="java.text.DecimalFormat"%>
<!doctype html>
<html class="no-js" lang="zxx">
<head>
	<meta charset="utf-8">
	<meta http-equiv="x-ua-compatible" content="ie=edge">
	<title>Bookshop</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!-- Favicons -->
	<link rel="shortcut icon" href="images/favicon.ico">
	<link rel="apple-touch-icon" href="images/icon.png">

	<!-- Google font (font-family: 'Roboto', sans-serif; Poppins ; Satisfy) -->
	<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800" rel="stylesheet"> 
	<link href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,500,600,600i,700,700i,800" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">

	<!-- Stylesheets -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/plugins.css">
	<link rel="stylesheet" href="style.css">

	<!-- Cusom css -->
   <link rel="stylesheet" href="css/custom.css">

	<!-- Modernizer js -->
	<script src="js/vendor/modernizr-3.5.0.min.js"></script>
</head>
<body>
	<!--[if lte IE 9]>
		<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="https://browsehappy.com/">upgrade your browser</a> to improve your experience and security.</p>
	<![endif]-->

	<!-- Main wrapper -->
	<div class="wrapper" id="wrapper">
		
		<!-- Header -->
		<jsp:include page="Header" />
		<!-- //Header -->
		
        <!-- cart-main-area start -->
        <div class="cart-main-area section-padding--lg bg--white">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 col-sm-12 ol-lg-12">
                        <form action="#" method="post">               
                            <div class="table-content wnro__table table-responsive">
                                <table>
                                    <thead>
                                        <tr class="title-top">
                                            <th class="product-thumbnail">Image</th>
                                            <th class="product-name">Product</th>
                                            <th class="product-price">Price</th>
                                            <th class="product-quantity">Quantity</th>
                                            <th class="product-subtotal">Total</th>
                                            <th class="product-remove">Remove</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <% 
                                    DecimalFormat decimalF = new DecimalFormat("#.##");
                                    Cart cart = (Cart) session.getAttribute("cart"); 
                                    for (CartItem item : cart.items) {
                                    %>
                                        <tr>
                                            <td class="product-thumbnail"><a href="#"><img width="90" height="150" src="<%=item.getBook().getImage() %>" alt="product img"></a></td>
                                            <td class="product-name"><a href="#"><%= item.getBook().getTitle() %></a></td>
                                            <td class="product-price"><span class="amount">$<%=Float.parseFloat(item.getBook().getPrice() + "")%></span></td>
                                            <td class="product-quantity"><input type="number" name="quantity" value="<%=item.getQuantity()%>"></td>
                                            <td class="product-subtotal"><%= decimalF.format(item.getBook().getPrice() * item.getQuantity()) %></td>
                                            <td class="product-remove"><a href="Cart_S?delete=<%= item.getBook().getId()%>"><i
																class="zmdi zmdi-delete"></i></a></td>
                                        </tr>
                                        <% }  session.setAttribute( "cart", cart ); %>
                                    </tbody>
                                </table>
                            </div>
                            <div class="cartbox__btn">
                            <ul class="cart__btn__list d-flex flex-wrap flex-md-nowrap flex-lg-nowrap justify-content-end">
                               
                                <li class="ml-3"><a href="#"><button  class="btn btn-primary-outline bg-transparent" name="update" type="submit">Update Cart</button></a></li>
                                
                                <% 
                                if(session.getAttribute("userId") != null && ((boolean) session.getAttribute("isConnected"))  == true){ %>
                               <li class="ml-3" ><a href="Cart_S?valider=ok">Check Out</a></li>
                                <% } else { %>
                                  <li class="ml-3"><a href="Login?cart=ok">Login</a></li>
                                 <% } %>
                            </ul>
                        </div>
                        </form> 
                        
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6 offset-lg-6">
                        <div class="cartbox__total__area">
                            
                            <div class="cart__total__amount">
                                <span>Grand Total</span>
                                <span><%= decimalF.format(cart.totalAmount()) %></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>  
        </div>
        <!-- cart-main-area end -->
		<!-- Footer Area -->
		<jsp:include page="Footer" />
		<!-- //Footer Area -->

	</div>
	<!-- //Main wrapper -->

	<!-- JS Files -->
	<script src="js/vendor/jquery-3.2.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/plugins.js"></script>
	<script src="js/active.js"></script>
	
</body>
</html>