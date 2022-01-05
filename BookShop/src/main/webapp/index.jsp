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
	<div class="wrapper wrapper-boxed-layout" id="wrapper">
		<!-- Header -->
		<jsp:include page="HeaderHome" />
		<!-- //Header -->

		<!-- Start BEst Seller Area -->
		<section class="wn__product__area brown--color pb--30">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="section__title text-center">
							<h2 class="title__be--2">
								New <span class="color--theme">Products</span>
							</h2>
							<p>Discover the best new book releases and find your next
								read. If you’re looking for the best new releases, you’ve come
								to the right place. Our expert booksellers put together this
								curated collection of the top rated new releases and soon-to-be
								trending titles</p>
						</div>
					</div>
				</div>
				<!-- Start Single Tab Content -->
				<div
					class="furniture--4 border--round arrows_style owl-carousel owl-theme row mt--50">
					<%
					ArrayList<Author> authors = (ArrayList) request.getAttribute("authors");
					ArrayList<Category> categories = (ArrayList) request.getAttribute("categories");
					ArrayList<Book> books = (ArrayList) request.getAttribute("books");
					for (int i = 0; i < 6; i++) {
					%>
					<!-- Start Single Product -->
					<div class="product product__style--3" data-toggle="tooltip"
						data-placement="bottom" title="<%=books.get(i).getTitle()%>">
						<div class="col-lg-3 col-md-4 col-sm-6 col-12">
							<div class="product__thumb">
								<h4>
									<a href="SingleProduct?id=<%=books.get(i).getId()%>"></a>
								</h4>
								<a class="first__img"
									href="SingleProduct?id=<%=books.get(i).getId()%>"><img
									src="<%=books.get(i).getImage()%>" alt="product image"></a>
							</div>
							<div class="product__content content--center">
								<span class="h6"> <a
									href="SingleProduct?id=<%=books.get(i).getId()%>"><%=books.get(i).getTitle()%></a>
								</span><br> <span class="h6"><%=books.get(i).getName()%></span>
								<ul class="prize d-flex">
									<li>$<%=Float.parseFloat(books.get(i).getPrice() + "")%></li>
								</ul>
							</div>
						</div>
					</div>
					<%
					}
					%>

					<!-- End Single Product -->
				</div>
				<!-- End Single Tab Content -->
			</div>
		</section>
		<!-- Start BEst Seller Area -->
		<!-- Start NEwsletter Area -->
		<section class="wn__newsletter__area bg-image--2">
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
		<!-- Start Best Seller Area -->
		<section class="wn__bestseller__area bg--white pt--80  pb--30">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="section__title text-center">
							<h2 class="title__be--2">
								All <span class="color--theme">Products</span>
							</h2>
							<p>There are many variations of passages of Lorem Ipsum
								available, but the majority have suffered lebmid alteration in
								some ledmid form</p>
						</div>
					</div>
				</div>
				<div class="tab__container mt--60">
					<!-- Start Single Tab Content -->


					<div class="row single__tab tab-pane fade show active" id="all"
						role="tabpanel">
						<div
							class="product__indicator--4 arrows_style owl-carousel owl-theme">
							<%
							Collections.shuffle(books);
							%>
							<%!int j = 0;%>
							<%
							for (int i = 0; i < 7; i++) {
							%>
							<div class="single__product">
								<!-- Start Single Product -->
								<div class="col-lg-3 col-md-4 col-sm-6 col-12">
									<div class="product product__style--3">
										<div class="product__thumb">
											<a class="first__img gridForm"
												href="SingleProduct?id=<%=books.get(j).getId()%>"><img
												src="<%=books.get(j).getImage()%>" alt="product image"></a>
										</div>
										<div class="product__content content--center content--center">
											<h4>
												<a href="SingleProduct?id=<%=books.get(j).getId()%>"><%=books.get(j).getTitle()%></a>
											</h4>
											<span class="h6"><%=books.get(j).getName()%></span>
											<ul class="prize d-flex">
												<li>$<%=Float.parseFloat(books.get(j).getPrice() + "")%></li>
											</ul>
										</div>
									</div>
									<%
									j++;
									%>
									<%
									System.out.println("produit  (j) : " + j + " de la boucle (i) : " + i);
									%>
								</div>

								<!-- Start Single Product -->
								<!-- Start Single Product -->
								<div class="col-lg-3 col-md-4 col-sm-6 col-12">

									<div class="product product__style--3">
										<div class="product__thumb">
											<a class="first__img gridForm"
												href="SingleProduct?id=<%=books.get(j).getId()%>"><img
												src="<%=books.get(j).getImage()%>" alt="product image"></a>
										</div>
										<div class="product__content content--center content--center">
											<h4>
												<a href="SingleProduct?id=<%=books.get(j).getId()%>"><%=books.get(j).getTitle()%></a>
											</h4>
											<span class="h6"><%=books.get(j).getName()%></span>
											<ul class="prize d-flex">
												<li>$<%=Float.parseFloat(books.get(j).getPrice() + "")%></li>
											</ul>
										</div>
									</div>
									<%
									j++;
									%>
									<%
									System.out.println("produit  (j) : " + j + " de la boucle (i) : " + i);
									%>
								</div>
								<!-- Start Single Product -->
							</div>
							<%
							}
							%>
						</div>
					</div>

					<!-- End Single Tab Content -->
				</div>
			</div>
		</section>
		<!-- End BEst Seller Area -->
		<!-- Best Sale Area -->
		<section class="best-seel-area pt--80 pb--60">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="section__title text-center pb--50">
							<h2 class="title__be--2">
								Best <span class="color--theme">Seller </span>
							</h2>
							<p>Looking for what book to read next? Explore the Barnes &
								Noble Top 100 Best Sellers list to discover all the current top
								books from your favorite authors and genres. Browse a large
								variety of books on topics you love or the best new books to
								discover!</p>
						</div>
					</div>
				</div>
			</div>
			<div class="slider center">

				<%
				Collections.shuffle(books);
				for (int i = 0; i < 7; i++) {
				%>
				<!-- Single product start -->
				<div class="product product__style--3">
					<div class="product__thumb">
						<a width="300" height="355" class="first__img"
							href="SingleProduct?id=<%=books.get(i).getId()%>"><img
							src="<%=books.get(i).getImage()%>" alt="product image"></a>
					</div>
				</div>
				<!-- Single product end -->
				<%
				}
				%>
			</div>
		</section>
		<!-- Best Sale Area Area -->

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