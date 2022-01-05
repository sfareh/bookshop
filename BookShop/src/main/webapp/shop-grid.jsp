<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="models.Category"%>
<%@page import="models.Book"%>
<%@page import="models.Author"%>
<%@page import="models.Cart"%>
<%@page import="models.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Collections"%>
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
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,500,600,600i,700,700i,800"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900"
	rel="stylesheet">

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

		<!-- Start NEwsletter Area -->
		<div class="mt-5 pb-3"></div>
		<section class="wn__newsletter__area bg-image--2 mt-5 mb-3">
			<div class="container">
				<div class="row">
					<div class="col-lg-7 offset-lg-5 col-md-12 col-12 ptb--150">
						<div class="section__title text-center">
							<h2 class="text-white">
								<!-- ? -->
							</h2>
						</div>
						<div class="newsletter__block text-center">
							<p class="text-white">
								<!-- ? -->
							</p>
							<form action="#">
								<!-- <div class="newsletter__box">
									<input type="email" placeholder="">
									<button class="text-white"></button>
								</div> -->
							</form>
						</div>
					</div>
				</div>
			</div>
		</section>
		<!-- End NEwsletter Area -->


		<!-- Start Shop Page -->
		<div
			class="page-shop-sidebar left--sidebar bg--white section-padding--lg">
			<div class="container">
				<div class="row">
					<div class="col-lg-3 col-12 order-2 order-lg-1 md-mt-40 sm-mt-40">
						<div class="shop__sidebar">
							<aside class="wedget__categories poroduct--cat">
								<h3 class="wedget__title">Product Categories</h3>
								<ul>
									<%
									ArrayList<Category> categories = (ArrayList) request.getAttribute("categories");

									for (Category cat : categories) {
									%>
									<li><a href="CategoryS?catId=<%=cat.getId()%>"><%=cat.getName()%>
											<span>(<%=cat.getBook_nbr()%>)
										</span></a></li>

									<%
									}
									%>
								</ul>
							</aside>
						</div>
					</div>
					<div class="col-lg-9 col-12 order-1 order-lg-2">
						<div class="row">
							<div class="col-lg-12">
								<div
									class="shop__list__wrapper d-flex flex-wrap flex-md-nowrap justify-content-end">

									<div class="orderby__wrapper ">
										<span>Sort By</span> <select class="shot__byselect">
											<option></option>
											<option><a href="ShopGrid?sortAsc=<%="asc"%>">Lower
													price</a></option>
											<option>Higher price</option>
										</select>
									</div>
								</div>
							</div>
						</div>
						<div class="tab__container">
							<div class="shop-grid tab-pane fade show active" id="nav-grid"
								role="tabpanel">
								<div class="row">
									<%
									ArrayList<Book> booksPagination = (ArrayList) request.getAttribute("booksPagination");
									System.out.println("booksPagination jsp : " + booksPagination.size());
									ArrayList<Author> authors = (ArrayList) request.getAttribute("authors");
									for (int i = 0; i < booksPagination.size(); i++) {
									%>
									<!-- Start Single Product -->
									<div class="col-lg-4 col-md-4 col-sm-6 col-12">
										<div class="product">
											<div class="product__thumb">
												<a class="first__img"
													href="SingleProduct?id=<%=booksPagination.get(i).getId()%>">
													<img class="gridForm"
													src="<%=booksPagination.get(i).getImage()%>"
													alt="product image">
												</a>
												<ul class="prize position__right__bottom d-flex">
													<li>$<%=Float.parseFloat(booksPagination.get(i).getPrice() + "")%></li>
												</ul>
												<div class="action">
													<div class="actions_inner">
														<ul class="add_to_links">
															<li><a class="cart" href="cart.html"></a></li>
															<li><a class="wishlist" href="wishlist.html"></a></li>
															<li><a class="compare" href="compare.html"></a></li>
															<li><a data-toggle="modal" title="Quick View"
																class="quickview modal-view detail-link"
																href="#productmodal"></a></li>
														</ul>
													</div>
												</div>
											</div>
											<div class="product__content">
												<h4>
													<a
														href="SingleProduct?id=<%=booksPagination.get(i).getId()%>"><%=booksPagination.get(i).getTitle()%></a>
												</h4>
												<h4>
													<a
														href="SingleProduct?id=<%=booksPagination.get(i).getId()%>"><%=authors.get(booksPagination.get(i).getAuthor_id() - 1).getName()%></a>
												</h4>
											</div>
										</div>
									</div>
									<!-- End Single Product -->
									<%
									}
									%>
								</div>

								<nav aria-label="Page navigation example">
									<ul class="pagination">
										<%
										int nbrTotalPage = (int) request.getAttribute("nbrPage");
										for (int i = 1; i < nbrTotalPage; i++) {
										%>
										<li class="page-item"><a class="page-link"
											href="ShopGrid?pagination=<%=i%>"><%=i%></a></li>
										<%
										}
										%>
									</ul>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End Shop Page -->

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