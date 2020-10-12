<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
<head>
<title>Create New Adv</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<meta name="description" content="">
<meta name="author" content="">
<!-- Main css -->
<link href="../assets/css/main-style.css" rel="stylesheet" >

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
                    <li><a href="/user?userName=${currentUser}&currentUser=${currentUser}">My Profile</a></li>
                </ul>
            </div>

        </div>

    </nav>
</div>

<!-- Page Content Area
===================================== -->
<section id="contact-page"  style="margin-top:10px;">
	<div class="container">
		
		<div class="row">
            <div class="organix-header" style="margin-bottom: 20px;padding-bottom: 0px;">
                <h1>Editing Adv</h1>
            </div>
			<div class="col-md-6">
				<div class="reply-form">
								
					<!-- Post Form -->
					<form class="form contact-us-form" name="contactform" id='contact_form' method="post" action='/saveAd'>
						<div class="clearfix">
                            <div class="col-xs-6">
                                <!-- Name -->
                                <div class="form-group inner-addon left-addon">
                                    <p>Titel</p>
                                    <input type="text" name="titel" value="${adv.titel}" id="titelInput" class="form-control gardener-reply-form" id="exampleInputEmail1" pattern=".{2,100}" required>
                                </div>
                            </div>


                            <div class="col-xs-12" style="margin-top:10px;">
                                <p >Kategorie</p>
                            </div>

                            <div class="col-xs-12" style="margin-top:10px;">
                                <div >
									<label>
										<input type="radio" name ="kategorie" checked value="Digitale Waren">Digitale Waren</input>
                            </label>

                            <label>
                                <input type="radio" name ="kategorie" value="Haus & Garten">Haus & Garten</input>
                            </label>
                        </div>

                        <div>
                            <label>
                                <input type="radio" name ="kategorie" value="Mode & Kosmetik">  Mode & Kosmetik</input>
                            </label>

                            <label>
                                <input type="radio" name ="kategorie" value="Multimedia & Elektronik">  Multimedia & Elektronik</input>
                            </label>
                        </div>
                </div>




                <div class="col-xs-6" style="margin-top:10px;">
                                <!-- Name -->
                                <div class="form-group inner-addon left-addon" >
                <p >Pries</p>
                <input type="text" name="price" value="${adv.preis}" class="form-control gardener-reply-form" id="priceInput" id="exampleInputEmail1" pattern=".{2,100}" required>
            </div>
        </div>
        <input  type="hidden" name="currentUser" value="${currentUser}"></input>
        <input  type="hidden" name="advId" value="${adv.id}"></input>



        <div class="col-xs-12" class="form-group inner-addon left-addon" style="margin-top:10px;">
                                <p >Description</p>
        <textarea rows="10" name="description"  class="form-control gardener-reply-area" id="myTextArea"></textarea>
    </div>


    <div class="col-xs-12" style="margin-top:10px;">
        <!-- Send Button -->
        <button class="btn btn-default" type="button" onclick="checkAndSubmit()">Save</button>
    </div>


						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- End Section Image Content  -->




<!-- ============ Js Files ============ -->
	<!-- Placed at the end of the document so the pages load faster -->
	
	<!-- Core js -->
	<script src="../assets/js/jquery-3.1.1.min.js"></script>
	<script src="../assets/js/bootstrap.min.js"></script>
	
	<script src="../assets/js/menu.js"></script>
	<script src="../assets/js/jquery.sticky.js"></script>
	<script src="../assets/js/owl.carousel.min.js"></script>
	<script src="../assets/js/appear.js"></script>
	<script src="../assets/js/appear.js"></script>
	<script src="../assets/js/jquery.dcjqaccordion.min.js"></script>
	<!--<script src="assets/js/instagram.js"></script>-->	
	<script src="../assets/js/jquery.ytplayer.js"></script>
	
	<!-- Google Map -->
	<script type="text/javascript" src="http://ditu.google.cn/maps/api/js?key=AIzaSyAvpnlHRidMIU374bKM5-sx8ruc01OvDjI"></script>
	<script src="../assets/js/gmap3.min.js"></script>
	
	<!-- Custom Script -->		
	<script src="../assets/js/main.js"></script>

<script>
    document.getElementById("myTextArea").value="${adv.text}"
</script>

<script>
    function checkAndSubmit() {
        //check all the field
        var titel = document.getElementById("titelInput");
        if (titel.value == null || titel.value.replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("Titel can  not be null");
            return;
        }
        else {
            if(titel.value.length > 100){
                alert("Titel length can not larger than 100 characters !");
                return;
            }
        }

        var price = document.getElementById("priceInput");
        var reg = /(^[0-9]{1,3}(.[0-9]{2})?$)/;
        if(! reg.test(price.value)){
            alert("Price can only be positive real number with 2 decimal like 333.00");
            return;
        }
        if (price.value == null || price.value.replace(/(^s*)|(s*$)/g, "").length == 0){
            alert("Price can  not be null");
            return;
        }
        else {
            if(price.value >= 1000 || price.value <= 0){
                alert("Price must > 0 and < 1000");
                return;
            }
        }

        var textContent = document.getElementById("myTextArea");
        if (textContent.value == null || textContent.value.replace(/(^s*)|(s*$)/g, "").length <= 10){
            alert("Description can  not be null and must more than 10 charaters");
            return;
        }

        contactform.submit();
    }

    // function validate_required(field,alerttxt)
    // {
    //     with (field)
    //     {
    //         if (value==null||value=="")
    //         {alert(alerttxt);return false}
    //         else {return true}
    //     }
    // }
    //
    // function validate_form(thisform)
    // {
    //     with (thisform)
    //     {
    //         if (validate_required(titel,"titel must be filled out!")==false)
    //         {titel.focus();return false}
    //     }
    // }
</script>
	
	
</body>
</html>

