<!DOCTYPE html>
<html lang="en">
<head>
    <title>Chat with ${chatWith}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
    <meta name="description" content="">
    <meta name="author" content="">


    <!-- Main css -->
    <link href="assets/css/main-style.css" rel="stylesheet" >
    <link href="assets/css/chat.css" rel="stylesheet" >

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
            <div class="organix-header" style="margin-bottom: 20px;padding-bottom: 0px;">
                <h1>Chat with ${chatWith}</h1>
            </div>
            <#if (chatList)?? && (chatList?size > 0)>
                <#list chatList as chat>
                    <#if chat.ABSENDER == currentUser>
                     <div class="col-md-12" style="min-height:65px;background-color: #C1FFC1;margin-bottom: 10px;font-family:'Montserrat', sans-serif;font-size: 16px;border-radius:15px 15px 0px 15px"">
                        <div class="tab-horizontal" style="line-height: 65px;">
                            ${chat.TEXT}
                        </div>
                     </div>
                    <#else>
                        <div class="col-md-12" style="min-height:65px;background-color: #F0FFFF;margin-bottom: 10px;font-family:'Montserrat', sans-serif;font-size: 16px;border-radius:0px 15px 15px 15px">
                            <div class="tab-horizontal" style="line-height: 65px;">
                               ${chat.TEXT} msg from (${chatWith})
                            </div>
                        </div>
                    </#if>
                </#list>

            <#else>
                <div class="col-md-12" style="min-height:65px;background-color: #feecef;margin-bottom: 10px;font-family:'Montserrat', sans-serif;font-size: 16px;">
                    <div class="tab-horizontal" style="line-height: 65px;">
                        None yet Conversation available!
                    </div>
                </div>
            </#if>
                          
            <!-- Product Row Two -->
                <div class="row pt-50">
                    <div class="col-md-12">
                        <div class="tab-horizontal">
                            <!-- Nav tabs -->
                            <ul class="nav nav-tabs product-navtabs">
                                <li class="active"><a href="#" data-toggle="tab"> </a></li>
                            </ul>

                        </div>
                    </div>
                </div>


                <form class="form contact-us-form" name="contactform" id='contact_form' method="post" action='/chat'>
                    <div class="col-xs-12" class="form-group inner-addon left-addon" style="margin-top:10px;">
                        <textarea rows="10" name="chatData" class="form-control gardener-reply-area" id="exampleInputEmail4"></textarea>
                    </div>

                    <input  type="hidden" name="currentUser" value="${currentUser}"></input>
                    <input  type="hidden" name="chatWith" value="${chatWith}"></input>
                    <div class="col-xs-12" style="margin-top:10px;">
                        <!-- Send Button -->
                        <button class="btn btn-default">Send Msg</button>
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


</body>
</html>

