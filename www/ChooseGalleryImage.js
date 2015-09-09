var ChooseGalleryImage = function() {
};
ChooseGalleryImage.prototype.chooseImage=function(successCallback,errorCallback,base64String, params){
  
    cordova.exec(successCallback, errorCallback, "ChooseGalleryImage", "ChooseImage", [base64String,params]);
};


if(!window.plugins) {
	window.plugins = {};
}
if (!window.plugins.ChooseGalleryImage) {
	window.plugins.ChooseGalleryImage = new ChooseGalleryImage();
}

