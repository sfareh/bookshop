
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
		<div class="mt-5 pb-3"></div>
		
		<!-- End main Content -->
		<section class="wn__newsletter__area m-5 pb-5">
			<div class="container">
				<div class="row">
					<div class="col-12 ">
						<div class="section__title text-center">
							<h2 class="title__be--2">Orders</h2>
						</div>
						<div class="alert alert-success" role="alert">Thank you for
							your order.</div>
					</div>

				</div>
			</div>
	</div>
	</section>

	<!-- Start NEwsletter Area -->
	<section class="wn__newsletter__area bg-image--2">
		<div class="container">
			<div class="row"></div>
		</div>
	</section>
	<!-- End NEwsletter Area -->


	<!-- Footer Area -->
	<jsp:include page="Footer" />
	<!-- //Footer Area -->

	<!-- QUICKVIEW PRODUCT -->
	<div id="quickview-wrapper">
		<!-- Modal -->
		<div class="modal fade" id="productmodal" tabindex="-1" role="dialog">
			<div class="modal-dialog modal__container" role="document">
				<div class="modal-content">
					<div class="modal-header modal__header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="modal-product">
							<!-- Start product images -->
							<div class="product-images">
								<div class="main-image images">
									<img alt="big images" src="images/product/big-img/1.jpg">
								</div>
							</div>
							<!-- end product images -->
							<div class="product-info">
								<h1>Simple Fabric Bags</h1>
								<div class="rating__and__review">
									<ul class="rating">
										<li><span class="ti-star"></span></li>
										<li><span class="ti-star"></span></li>
										<li><span class="ti-star"></span></li>
										<li><span class="ti-star"></span></li>
										<li><span class="ti-star"></span></li>
									</ul>
									<div class="review">
										<a href="#">4 customer reviews</a>
									</div>
								</div>
								<div class="price-box-3">
									<div class="s-price-box">
										<span class="new-price">$17.20</span> <span class="old-price">$45.00</span>
									</div>
								</div>
								<div class="quick-desc">Designed for simplicity and made
									from high quality materials. Its sleek geometry and material
									combinations creates a modern look.</div>
								<div class="select__color">
									<h2>Select color</h2>
									<ul class="color__list">
										<li class="red"><a title="Red" href="#">Red</a></li>
										<li class="gold"><a title="Gold" href="#">Gold</a></li>
										<li class="orange"><a title="Orange" href="#">Orange</a></li>
										<li class="orange"><a title="Orange" href="#">Orange</a></li>
									</ul>
								</div>
								<div class="select__size">
									<h2>Select size</h2>
									<ul class="color__list">
										<li class="l__size"><a title="L" href="#">L</a></li>
										<li class="m__size"><a title="M" href="#">M</a></li>
										<li class="s__size"><a title="S" href="#">S</a></li>
										<li class="xl__size"><a title="XL" href="#">XL</a></li>
										<li class="xxl__size"><a title="XXL" href="#">XXL</a></li>
									</ul>
								</div>
								<div class="social-sharing">
									<div class="widget widget_socialsharing_widget">
										<h3 class="widget-title-modal">Share this product</h3>
										<ul
											class="social__net social__net--2 d-flex justify-content-start">
											<li class="facebook"><a href="#" class="rss social-icon"><i
													class="zmdi zmdi-rss"></i></a></li>
											<li class="linkedin"><a href="#"
												class="linkedin social-icon"><i
													class="zmdi zmdi-linkedin"></i></a></li>
											<li class="pinterest"><a href="#"
												class="pinterest social-icon"><i
													class="zmdi zmdi-pinterest"></i></a></li>
											<li class="tumblr"><a href="#"
												class="tumblr social-icon"><i class="zmdi zmdi-tumblr"></i></a></li>
										</ul>
									</div>
								</div>
								<div class="addtocart-btn">
									<a href="#">Add to cart</a>
								</div>
							</div>
							<!-- .product-info -->
						</div>
						<!-- .modal-product -->
					</div>
					<!-- .modal-body -->
				</div>
				<!-- .modal-content -->
			</div>
			<!-- .modal-dialog -->
		</div>
		<!-- END Modal -->
	</div>
	<!-- END QUICKVIEW PRODUCT -->

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