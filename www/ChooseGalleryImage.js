var ChooseImageGallery = function() {
};
ChooseImageGallery.prototype.chooseImage=function(successCallback,errorCallback,base64String, params){
  
    cordova.exec(successCallback, errorCallback, "ChooseImageGallery", "ChooseImage", [base64String,params]);
};


if(!window.plugins) {
	window.plugins = {};
}
if (!window.plugins.ChooseImageGallery) {
	window.plugins.ChooseImageGallery = new ChooseImageGallery();
}

