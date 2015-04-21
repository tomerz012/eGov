/*#-------------------------------------------------------------------------------
	# eGov suite of products aim to improve the internal efficiency,transparency, 
	#    accountability and the service delivery of the government  organizations.
	# 
	#     Copyright (C) <2015>  eGovernments Foundation
	# 
	#     The updated version of eGov suite of products as by eGovernments Foundation 
	#     is available at http://www.egovernments.org
	# 
	#     This program is free software: you can redistribute it and/or modify
	#     it under the terms of the GNU General Public License as published by
	#     the Free Software Foundation, either version 3 of the License, or
	#     any later version.
	# 
	#     This program is distributed in the hope that it will be useful,
	#     but WITHOUT ANY WARRANTY; without even the implied warranty of
	#     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	#     GNU General Public License for more details.
	# 
	#     You should have received a copy of the GNU General Public License
	#     along with this program. If not, see http://www.gnu.org/licenses/ or 
	#     http://www.gnu.org/licenses/gpl.html .
	# 
	#     In addition to the terms of the GPL license to be adhered to in using this
	#     program, the following additional terms are to be complied with:
	# 
	# 	1) All versions of this program, verbatim or modified must carry this 
	# 	   Legal Notice.
	# 
	# 	2) Any misrepresentation of the origin of the material is prohibited. It 
	# 	   is required that all modified versions of this material be marked in 
	# 	   reasonable ways as different from the original version.
	# 
	# 	3) This license does not grant any rights to any user of the program 
	# 	   with regards to rights under trademark law for use of the trade names 
	# 	   or trademarks of eGovernments Foundation.
	# 
	#   In case of any queries, you can reach eGovernments Foundation at contact@egovernments.org.
#-------------------------------------------------------------------------------*/
$(document).ready(function () {
	
	var openedWindows = [];
	var addfav_li;
	var menuheight = ($( window ).height() - 63);
	var ulheight =(menuheight -93);
	
	$('#menu').multilevelpushmenu({
		menuWidth: '250px',
		mode: 'cover',
		menu:menuItems,
		onItemClick: function (event) {
			var e = arguments[0],$item = arguments[2];
			$('#menu_multilevelpushmenu ul li').removeClass('li-active');
			$(e.target).parent().addClass('li-active');
			if ($(e.target).prop('tagName').toLowerCase() == 'input') {
				$(e.target).focus();
				$(e.target).val('');
				$(e.target).unbind('');
				$(e.target).blur(function(){
					$(e.target).val('');
				});
			}
			
			if($(e.target).hasClass('remove-favourite'))
			{
				bootbox.confirm("Do you wish to remove this from Favourite ?", function(result) {
					if(result){
						removeFromFavourites(e);
						$(e.target).parent().parent().remove();
					}
				}); 
				}else if($(e.target).hasClass('added-as-fav')){
				bootbox.confirm("Do you wish to remove this from Favourite ?", function(result) {
					if(result){
						removeFromFavourites(e);
						$(e.target).removeClass('added-as-fav');
					}
				}); 
				}else if($(e.target).hasClass('add-to-favourites')){
				$('.favourites').modal('show', {backdrop: 'static'});
				favouriteName = $(e.target).parent().text();
				favouriteURL = $item.find( 'a:first' ).attr( 'href' ).toString();
				contextRoot = favouriteURL.split("/")[1];
				actionId = $item.attr('id');
				$('#fav-name').val(favouriteName);
				}else{
				var itemHref = $item.find( 'a:first' ).attr( 'href' );
				var windowObjectReference = window.open(itemHref, ''+$item.attr('id')+'', 'width=900, height=700, top=300, left=150,scrollbars=yes'); 
				openedWindows.push(windowObjectReference);
			}
		},
		onGroupItemClick: function () {
			menuheight = ($( window ).height() - 63);
			ulheight =(menuheight -93);
			$('#menu').height(''+menuheight+'px');
			$('#menu_multilevelpushmenu').height(''+menuheight+'px');
			$('#menu, #menu_multilevelpushmenu').css('min-height', ''+menuheight+'px');
			var e = arguments[2];
			if(e.children('div').children('ul').height() > ulheight){
				$('#menu_multilevelpushmenu ul').height(''+ulheight+'px');
				$('#menu_multilevelpushmenu ul').css('overflow-y','scroll');
				}else{
				$('#menu_multilevelpushmenu ul').css('overflow-y','auto');
			}
		},
		onBackItemClick : function () {
			$('#menu_multilevelpushmenu ul').css('overflow-y','auto');
		},
		onCollapseMenuEnd : function () {
			var w = $('#menu.homepage').width()+'px';
			$('.inline-main-content').css('width', 'calc(100% - '+w+')');
		},
		onExpandMenuEnd : function () {
			var w = $('#menu.homepage').width()+'px';
			$('.inline-main-content').css('width', 'calc(100% - '+w+')');
		}
	});
	
	var actionId='';
	var favouriteName='';
	var favouriteURL='';
	var contextRoot='';
	
	$(window).on('resize', function () {
		setmenuheight();
	}).trigger('resize');
	
	function setmenuheight(){
		menuheight = ($( window ).height() - 63);
		$('#menu').height(''+menuheight+'px');
		$('#menu_multilevelpushmenu').height(''+menuheight+'px');
		$('#menu, #menu_multilevelpushmenu').css('min-height', ''+menuheight+'px');
	}
	
	function removeFromFavourites(e) {
		$.ajax({
			type: "GET",
			url: "home/remove-favourite",
			data:{'actionId' : $(e.target).parent().parent().attr('id')}
			}).done(function(value) {
			if(value === true) {
				bootbox.alert("Removed from Favourite");
				} else {
				bootbox.alert("Could not delete it from Favourite");
			}
		});
	}
	
	$('.add-fav').click(function(){
		$('.favourites').modal('hide');
		$.ajax({
			type: "GET",
			url: "home/add-favourite",
			data:{'actionId' : actionId,'name':favouriteName,'contextRoot':contextRoot}
			}).done(function(value) {
			if(value) {
				$('#'+actionId+' a i').addClass('added-as-fav');
				$('#favMenu ul').append('<li id="'+actionId+'"> <a href="'+favouriteURL+'" class="open-popup"><i class="floatRight fa fa-times-circle remove-favourite"></i>'+favouriteName+'</a> </li>')
				} else {
				bootbox.alert("Could not add to Favourite");
			}
		});
		
	});
	
	$(document).on('click', '.remove-favourite',function(e) {
		bootbox.confirm("Do you wish to remove this from Favourite ?", function(result) {
			if(result){
				//$('id-of-li i').removeClass('added-as-fav'); Removing color of the star
				//$(e.target).parent().remove(); Removing li element
			}
		}); 
	});
	
	$("a.open-popup").click(function(e) {
		// to open in good size for user
		var width = window.innerWidth /0.66 ; 
		// define the height in 
		var height = width * window.innerWidth / window.innerHeight; 
		// Ratio the hight to the width as the user screen ratio
		var windowObjectReference = window.open(this.href, ''+$(this).attr('data-strwindname')+'', 'width=900, height=700, top=300, left=150,scrollbars=yes'); 
		openedWindows.push(windowObjectReference);
		return false;
	});
	
	$(document).on('click', 'a.open-popup', function() {
		// to open in good size for user
		var width = window.innerWidth /0.66 ; 
		// define the height in 
		var height = width * window.innerWidth / window.innerHeight; 
		// Ratio the hight to the width as the user screen ratio
		var windowObjectReference = window.open(this.href, ''+$(this).attr('data-strwindname')+'', 'width=900, height=700, top=300, left=150,scrollbars=yes'); 
		openedWindows.push(windowObjectReference);
		return false;
	});
	
	$('.signout').click(function(){
		$.each( openedWindows, function( i, val ) {
			var window = val;
			window.close();
		});
	});
	
});	
