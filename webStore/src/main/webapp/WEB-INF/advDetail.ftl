<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Adv Detail</title>
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
		<section id="shop-single-product" class="ptb-60">
			<div class="container">
				<div class="row">
					<div class="col-md-9">
                        <div class="product-single-content">
                            <div class="row">
                                <div class="col-md-5 col-sm-5">
                                    <div class="product-image">
                                        <img class="img-responsive" src="assets/images/products/laptop.png" alt="">
                                    </div>
                                </div>
                                <div class="col-md-7 col-sm-7">
                                    <div class="product-content-right">
                                        <h3>${adv.titel}</h3>
                                        <div class="single-product-review">
                                            <ul>
                                                <li>
                                                    <p>since ${adv.erstellungsdatum} from <a href="/user?userName=${adv.ersteller}&currentUser=${currentUser}">${adv.ersteller}</a> </p>
                                                </li>
                                            </ul>
                                        </div>

                                        <h5>${adv.preis}€</h5>

                                        <p>
                                        	Description:<p>${adv.text} </p>
                                        </p>
                                    </div>

                                    <div class="product-quantity">
                                        <ul>
                                            <li>

<#if currentUser == adv.ersteller>
                                                <a href="/delete?currentUser=${currentUser}&advId=${adv.id}" class="btn cart-button">DELETE</a>
                                                <a href="/advEdit?currentUser=${currentUser}&advId=${adv.id}" class="btn cart-button">Edit</a>
<#else>
    <a href="javascript:void(0);" onclick="checkStAndBuy()" class="btn cart-button">Buy Now</a>
    <#--<a href="/buy?currentUser=${currentUser}&advId=${adv.id}" class="btn cart-button">Buy Now</a>-->
</#if>


                                            </li>

                                        </ul>
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
									  <li class="active"><a href="#eightteen" data-toggle="tab">Comment </a></li>
									</ul>

								</div>
							</div>
						</div>

<#list commentList as comment>
            <div class="comments--news comments--default comments--article"  data-author-id="1030000014576408 " data-is-admin="null" id="goToReplyArea" style="margin-top: 10px;">
                <div class="comments-container">
                    <div class="comments-list">
                        <div class="comments-item" data-id="1050000018007863">
                            <div class="comments-content">
                                <div class="comment-trigger">
                                    <strong><a target="_blank" href="">${comment.BENUTZERNAME} </a> said: </strong>
                                </div>

                                <div class="fmt mb10"><p> ${comment.TEXT}</p></div>

                            </div>
                        </div>
                    </div>



                </div>
            </div>
</#list>
                        <form class="form contact-us-form" name="contactform" id='contact_form' method="post" action='/comment'>
                            <div class="col-xs-12" class="form-group inner-addon left-addon" style="margin-top:10px;">
                                <textarea rows="10" name="commentData" class="form-control gardener-reply-area" id="exampleInputEmail4"></textarea>
                            </div>

                            <input  type="hidden" name="currentUser" value="${currentUser}"></input>
                            <input  type="hidden" name="advId" value="${adv.id}"></input>
                            <div class="col-xs-12" style="margin-top:10px;">
                                <!-- Send Button -->
                                <button class="btn btn-default">Add Comment</button>
                            </div>
                        </form>


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

            <script>
                function checkStAndBuy() {
                    $.ajax({
                        url:"/buy?currentUser=${currentUser}&advId=${adv.id}",//要发送的地址
                        type: "GET",//发送的类型
                        dataType:"json",//预期响应的数据类型
                        success: function (data) {
                            alert(data.msg);
                        }
                    })
                }

                
                

            </script>
			
			
	</body>
</html>

