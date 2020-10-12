<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Shop</title>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
		<meta name="description" content="">
		<meta name="author" content="">


				
		
		<!-- Main css -->
		<link href="assets/css/main-style.css" rel="stylesheet" >
		
	</head>

	<body>
         

		
		<!-- Navigation Area
        ===================================== -->
		<div class="navbar-sticky">
			<nav class="navbar navbar-index-top shadow">
				<div class="container header-container">
					<div id="main-menu-wrap"><a href="#" class="menu-trigger"></a></div>
					<!-- Main Menu -->
					<div id="main-menu" class="main-nav mega_menu_wrapper ">
					
						<ul id="custom-menu" class="main-menu mega_menvu">
							<li><a href="/shop?currentUser=${currentUser}">All Adv Over View</a></li>
                            <li><a href="/createAd?currentUser=${currentUser}">Create a Adv Now!</a></li>
                            <li><a href="/user?userName=${currentUser}&currentUser=${currentUser}">My Profile</a></li>
						</ul>
					</div>

				</div>

			</nav>
		</div>
		
		<div id="page_wrapper"></div>

		
		<!-- Page Content Area
        ===================================== -->
		<section id="shop-col-3" class="ptb-100">
			<div class="container">
                <div class="row">
					<div class="col-md-6 col-sm-6 col-xs-6">
                        <form class="form contact-us-form" name="contactform" id='contact_form' method="get" action='/sort'>
                            <div class="col-md-3 col-sm-3 col-xs-3">
                                <div class="btn-group">
                                    <select class="form-control" name="sortBy">
                                        <option>Titel</option>
                                        <option>Create Date</option>
                                    </select>
                                </div>

							</div>
                            <div class="col-md-3 col-sm-3 col-xs-3">
                                    <button class="btn btn-default" style="position:absolute;top:50%;margin-top:-button;" >Go</button>
							</div>

                            <input  type="hidden" name="currentUser" value="${currentUser}"></input>

                        </form>
					</div>

                    <div class="col-md-6 col-sm-6 col-xs-6" style="margin-bottom: 30px">
                        <form class="form contact-us-form" name="contactform" id='contact_form' method="get" action='/filter'>
                            <div class="col-md-3 col-sm-3 col-xs-3">
                                <div class="btn-group">
                                    <select class="form-control" name="cate">
                                        <option>Digitale Waren</option>
                                        <option>Haus & Garten</option>
                                        <option>Mode & Kosmetik</option>
                                        <option>Multimedia & Elektronik</option>
                                    </select>
                                </div>

                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3">
                                <button class="btn btn-default" style="position:absolute;top:50%;margin-top:-button;" >Go</button>
                            </div>

                            <input  type="hidden" name="currentUser" value="${currentUser}"></input>
                        </form>
                    </div>

				</div>


				<div class="row">
					    <#list advList as adv>

                            <div class="col-md-4 col-sm-6 col-xs-6">
                                <div class="product-style-2">
                                    <figure>
                                        <img class="img-responsive" src="assets/images/products/laptop.png" alt="">
                                        <figcaption>
                                            <#--<ul>-->
                                                <#--<li>-->
                                                    <#--<a class="btn product-button" href="#"> ADD TO WISHLIST </a>-->
                                                <#--</li>-->
                                                <#--<li>-->
                                                    <#--<a class="btn product-button" href="#"> ADD TO CART </a>-->
                                                <#--</li>-->
                                            <#--</ul>-->
                                        </figcaption>
                                    </figure>

                                    <div class="product-detail-content-2">
                                        <div class="product-detail-top-content">
                                            <h6>
                                                <span>${adv.PREIS}€</span>
                                                <!--UPPER CASE-->
												<a href="/advDetail?advId=${adv.ID}&currentUser=${currentUser}">${adv.TITEL}<a>

                                            </h6>
                                        </div>
                                        <div class="product-detail-bottom-content">
                                            <div class="like-count">
                                                <ul>
                                                   <a href="/user?userName=${adv.ERSTELLER}&currentUser=${currentUser}"> ${adv.ERSTELLER}</a>
                                                </ul>
                                            </div>
                                            <i>${adv.ERSTELLUNGSDATUM}</i>
                                        </div>
                                    </div>
                                </div>
                            </div>

						</#list>

					
				</div>
			</div>
		</section>
		<!-- End Section Image Content  -->

		
		
		<!-- ============ Js Files ============ -->
		    <!-- Placed at the end of the document so the pages load faster -->
			
			<!-- Core js -->
			<script src="assets/js/jquery-3.1.1.min.js"></script>
			<script src="assets/js/bootstrap.min.js"></script>	
			
			<script src="assets/js/menu.js"></script>	
			<script src="assets/js/jquery.sticky.js"></script>	
			<script src="assets/js/owl.carousel.min.js"></script>	
			<script src="assets/js/appear.js"></script>	
			<script src="assets/js/jquery.dcjqaccordion.min.js"></script>	
			<!--<script src="assets/js/instagram.js"></script>-->	
			<!-- Google Map -->
			<script type="text/javascript" src="http://ditu.google.cn/maps/api/js?key=AIzaSyAvpnlHRidMIU374bKM5-sx8ruc01OvDjI"></script>
			<script src="assets/js/gmap3.min.js"></script>	
			
			<!-- Custom Script -->		
			<script src="assets/js/main.js"></script>

			
	</body>
</html>

