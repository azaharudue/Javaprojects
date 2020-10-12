 <!DOCTYPE html>
<html lang="en">
	<head>
		<title>User Profile</title>
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

		
		<!-- Page Content Area
        ===================================== -->
		<section id="shop-single-product" class="ptb-100">
			<div class="container">
				<div class="row">
					<div class="col-md-9">
						<div class="product-single-content">
							<div class="row">
								<div class="col-md-5 col-sm-5">
									<div class="product-image">
										<img class="img-responsive" src="assets/images/products/david.png" alt="">
									</div>
								</div>
								<div class="col-md-7 col-sm-7">
									<div class="product-content-right">
										<h3>Profile of ${user.benutzername}</h3>
										<div class="single-product-review">
											<ul>
												<li>
													<strong>Entry date:</strong><p>${user.eintrittsdatum}</p>
												</li>
											</ul>
										</div>

                                        <div class="single-product-review">
                                            <ul>
                                                <li>
													<strong>Names:</strong>
                                                    <p> ${user.name}</p>
                                                </li>
                                            </ul>
                                        </div>


                                        <div class="single-product-review">
                                            <ul>
                                                <li>
                                                    <strong>Number of Sold Articles:</strong>
                                                    <p> ${soldNum}</p>
                                                </li>
                                            </ul>
                                        </div>

                                        <div class="product-quantity">
                                            <ul>
											<#if currentUser == user.benutzername>

											<#else>
												<li>
													<a href="/chat?currentUser=${currentUser}&chatWith=${user.benutzername}" class="btn cart-button">Send Msg</a>
												</li>
											</#if>

                                            </ul>
                                        </div>
									</div>

								</div>
							</div>

						</div>
						
						<!-- Product Row Two -->
						<div class="row pt-50">
							<div class="col-md-12">
								<div class="tab-horizontal">
									<!-- Nav tabs -->
									<ul class="nav nav-tabs product-navtabs">
									  <#--<li class="active"><a href="#eightteen" data-toggle="tab"> </a></li>-->
									</ul>

									<!-- Tab panes -->
									<div class="tab-content product-tab-content">
										<div class="tab-pane fade in active" id="eightteen">
										</div>
										
										<div class="tab-pane fade" id="seventeen">	
											<div class="row">
												<div class="col-md-3 col-sm-6 col-xs-12">
													<div class="product-info">
														<ul>
															<li>
																Color: <span>Yellow</span>
															</li>
															<li>
																Weight: <span>2 kg</span>
															</li>
															<li>
																Origin: <span>Mexico</span>
															</li>
														</ul>
													</div>
												</div>
												<div class="col-md-4 col-sm-6 col-xs-12">
													<div class="product-info">
														<ul>
															<li>
																Month of Production: <span>March</span>
															</li>
															<li>
																Availability: <span>Available</span>
															</li>
															<li>
																Free shipping: <span>USA</span>
															</li>
														</ul>
													</div>
												</div>
											</div>
										</div>
										
										<div class="tab-pane fade" id="nineteen">
											<div class="">
												
												<div class="comment-holder">
													<ul class="media-list comment-list">
														<li class="media single-authors-content pt-40 pb-30"> 
															<div class="media-left"> 
																<a href="#"> 
																	<img class="media-object" src="assets/images/blog/author-1.jpg" alt=""> 
																</a> 
																<h5 class="media-heading">Jona Doe</h5> 
																<p>Customer</p>
															</div> 
															<div class="media-body"> 
																<i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i>
																<p>
																	Duis autem vel eum irure dolor n hendrer it invul uputate velt esse molestie <br>
																	conseq uat. Duis suautem vel eupm irure dolor in hendrerit.vel eum irure d <br>
																	olor n hendrer it invul uputate velt esse 
																</p>
																<a href="#leave-comment"><i class="fa fa-reply-all" aria-hidden="true"></i> Reply</a>
															</div> 
														</li>
														
														<li class="media single-authors-content pt-25 pb-30"> 
															<div class="media-left"> 
																<a href="#"> 
																	<img class="media-object" src="assets/images/blog/author-2.jpg" alt=""> 
																</a> 
																<h5 class="media-heading">John Doe</h5> 
																<p>Customer</p>
															</div> 
															<div class="media-body"> 
																<i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i>
																<p>
																	Duis autem vel eum irure dolor n hendrer it invul uputate velt esse molestie <br>
																	conseq uat. Duis suautem vel eupm irure dolor in hendrerit.vel eum irure d <br>
																	olor n hendrer it invul uputate velt esse 
																</p>
																<a href="#leave-comment"><i class="fa fa-reply-all" aria-hidden="true"></i> Reply</a>
															</div> 
														</li>
													</ul>
												</div>
												
												<div id="leave-comment" class="page-left-header pt-60">
													<h5>Your review</h5>
													
													<div class="form-group">
														<label class="pt-40">Your Rating :</label>
														<i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i><i class="fa fa-star-o" aria-hidden="true"></i>
													</div>
												</div>
												
												<div class="">
																
													<!-- Post Form -->                            
													<form class="form reply-form pt-25">
														<div class="clearfix">
															<div id="contact_message"></div>
															
															
															<div class="col-xs-12">
																<!-- Message -->
																<div class="form-group">
																	<textarea rows="8" class="form-control organix-reply-form" placeholder="Message"></textarea>
																</div>
																
															</div>
															
															<div class="col-xs-6">
																<!-- Name -->
																<div class="form-group">
																	<input type="text" class="form-control organix-reply-form" placeholder="Name*" pattern=".{2,100}" required>
																</div>
															</div>
															<div class="col-xs-6">
																<!-- Email -->
																<div class="form-group">
																	<input type="email" class="form-control organix-reply-form" placeholder="Email*"  required>
																</div>
															</div>
															
															
														</div>
														
														<div class="col-xs-12">
															<!-- Send Button -->
															<button class="btn btn-default">Submit</button>
														</div>
														
														
													</form>
												</div>
										</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- End Product Row Two -->
						
						<div class="row  pt-70">
							<div class="col-md-12">
								<div class="related-item">
									<h3>Offers</h3>
								</div>
							</div>
						</div>
                        <div class="row pt-25">
						<#list offerList as offer>

                                    <div class="col-md-4 col-sm-6 col-xs-6">
                                        <div class="product-style-1 text-center">
                                            <img class="img-responsive" src="assets/images/products/laptop.png" alt="">

                                            <div class="product-detail-content">
                                                <h6>${offer.TITEL}</h6>
                                                <div class="product-rating-with-border">
                                                    <p>${offer.ERSTELLUNGSDATUM}</p>
                                                </div>
                                                <h5>${offer.PREIS}€</h5>
                                                <p>${offer.STATUS}</p>

                                            </div>
                                        </div>
                                    </div>

						</#list>
                    </div>




                            <div class="tab-content product-tab-content">
                                <div class="tab-pane fade in active" id="eightteen">
                                </div>
                            </div>

                            <div class="row  pt-70">
                                <div class="col-md-12">
                                    <div class="related-item">
                                        <h3>Purchased</h3>
                                    </div>
                                </div>
                            </div>

                    <div class="row pt-25">
						<#list purchaseList as purche>

									<div class="col-md-4 col-sm-6 col-xs-6">
										<div class="product-style-1 text-center">
											<img class="img-responsive" src="assets/images/products/laptop.png" alt="">

											<div class="product-detail-content">
												<h6>${purche.TITEL}</h6>
												<div class="product-rating-with-border">
													<p>${purche.ERSTELLUNGSDATUM}</p>
												</div>
                                                <div class="product-rating-with-border">
                                                    <p>Bought From: ${purche.ERSTELLER}</p>
                                                </div>
												<h5>${purche.PREIS}€</h5>
											</div>
										</div>
									</div>
						</#list>

					</div>


					</div>

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

