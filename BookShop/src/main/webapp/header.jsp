<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="models.Category"%>
<%@page import="models.Book"%>
<%@page import="models.Cart"%>
<%@page import="models.CartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>

<header id="wn__header" class="header__area header__absolute">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-6 col-sm-6 col-6 col-lg-2">
				<div class="logo">
					<a href="Index"> <img src="images/logo/logo.png"
						alt="logo images">
					</a>
				</div>
			</div>
			<div class="col-lg-8 d-none d-lg-block">
				<nav class="mainmenu__nav">
					<ul class="meninmenu d-flex justify-content-start">
						<li class="drop with--one--item"><a href="Index">Home</a></li>
						<%
						ArrayList<Category> categories = (ArrayList) request.getAttribute("categories");
						for (int i = 0; i < 4; i++) {
						%>
						<li class="drop"><a
							href="CategoryS?catId=<%=categories.get(i).getId()%>"><%=categories.get(i).getName()%></a></li>
						<%
						}
						%>
						<li class="drop with--one--item"><a href="ShopGrid">Plus</a></li>
					</ul>

				</nav>
			</div>
			<div class="col-md-6 col-sm-6 col-6 col-lg-2">
				<ul
					class="header__sidebar__right d-flex justify-content-end align-items-center">
					<%
					Cart cart = (Cart) session.getAttribute("cart");
					DecimalFormat decimalF = new DecimalFormat("#.##");
					%>
					<li class="shopcart"><a class="cartbox_active" href="#"><span
							class="product_qun">${ cart.count() }</span></a> <!-- Start Shopping Cart -->
						<div class="block-minicart minicart__active">
							<div class="minicart-content-wrapper">
								<div class="micart__close">
									<span>close</span>
								</div>

								<div class="items-total d-flex justify-content-between">
									<span> ${ cart.count() } items</span> <span>Cart
										Subtotal</span>
								</div>
								<div class="total_amount text-right">
									<span>$ <%=decimalF.format(cart.totalAmount())%></span>
								</div>
								<div class="mini_action checkout">
									<a class="checkout__btn" href="Cart_S">Go to Checkout</a>
								</div>
								<div class="single__items">
									<div class="miniproduct">
										<%
										for (CartItem item : cart.items) {
										%>
										<div class="item01 d-flex">
											<div class="thumb">
												<a href="product-details.html"><img
													src="<%=item.getBook().getImage()%>" alt="product images"></a>
											</div>
											<div class="content">
												<h6>
													<a href="SingleProduct?id=<%=item.getBook().getId()%>"><%=item.getBook().getTitle()%></a>
												</h6>
												<span class="prize">$<%=Float.parseFloat(item.getBook().getPrice() + "")%></span>
												<div class="product_prize d-flex justify-content-between">
													<span class="qun">Qty: <%=item.getQuantity()%></span>
													<ul class="d-flex justify-content-end">
														<li><a
															href="Header?delete=<%=item.getBook().getId()%>"><i
																class="zmdi zmdi-delete"></i></a></li>
													</ul>
												</div>
											</div>
										</div>
										<%
										}
										%>
									</div>
								</div>
							</div>
						</div> <!-- End Shopping Cart --></li>
					<%
					if ((boolean) session.getAttribute("isConnected") == false) {
					%>
					<li class="setting__bar__icon"><a class="setting__active"
						href="#"></a>
						<div class="searchbar__content setting__block">
							<div class="content-inner">
								<div class="switcher-currency">
									<strong class="label switcher-label"> <span>My
											Account</span>
									</strong>
									<div class="switcher-options">
										<div class="switcher-currency-trigger">
											<div class="setting__menu">
												<span><a href="Login">Sign In</a></span> <span><a
													href="Register">Create An Account</a></span> <span><a
													href="Cart_S">View and edit cart</a></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div></li>
					<%
					} else {
					%>
					<li class="setting__bar__icon"><a class="setting__active"
						href="#"></a>
						<div class="searchbar__content setting__block">
							<div class="content-inner">
								<div class="switcher-currency">
									<strong class="label switcher-label"> <span>Welcome
											${ userName }</span>
									</strong>
									<div class="switcher-options">
										<div class="switcher-currency-trigger">
											<div class="setting__menu">
												<span><a href="Cart_S">View and edit cart</a></span> <span><a
													href="Logout">Logout</a></span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div></li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
		<!-- Start Mobile Menu -->
		<div class="row d-none">
			<div class="col-lg-12 d-none">
				<nav class="mobilemenu__nav">
					<ul class="meninmenu">
						<li class="drop with--one--item"><a href="Index">Home</a></li>
						<%
						for (Category item : categories) {
						%>
						<li class="drop"><a href="#"><%=item.getName()%></a></li>
						<%
						}
						%>
						<li class="drop with--one--item"><a href="ShopGrid">Plus</a></li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- End Mobile Menu -->
		<div class="mobile-menu d-block d-lg-none"></div>
		<!-- Mobile Menu -->
	</div>
</header>
<!-- Start Search Popup -->
<div
	class="brown--color box-search-content search_active block-bg close__top">
	<form id="search_mini_form" class="minisearch" action="#">
		<div class="field__search">
			<input type="text" placeholder="Search entire store here...">
			<div class="action">
				<a href="#"><i class="zmdi zmdi-search"></i></a>
			</div>
		</div>
	</form>
	<div class="close__wrap">
		<span>close</span>
	</div>
</div>
<!-- End Search Popup -->
