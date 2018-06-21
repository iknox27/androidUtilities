function androidUtilities(){}

androidUtilities.prototype.enableLayoutFlags = function(type){
  return new Promise(function(resolve,reject){
    cordova.exec(resolve,reject,"androidUtilities","enableLayoutFlags",[type])
  });
}

androidUtilities.prototype.changeKeyboardType = function(type){
  return new Promise(function(resolve,reject){
    cordova.exec(resolve,reject,"androidUtilities","changeKeyboardType",[type])
  });
}

androidUtilities.prototype.setColorNavigationBar = function(type){
  return new Promise(function(resolve,reject){
    cordova.exec(resolve,reject,"androidUtilities","setColorNavigationBar",[type])
  });
}

androidUtilities.prototype.setTranslucent = function(type){
  return new Promise(function(resolve,reject){
    cordova.exec(resolve,reject,"androidUtilities","setTranslucent",[type])
  });
}

androidUtilities.install = function() {
    if (!window.plugins) {
        window.plugins = {};
    }

    window.plugins.androidUtilities = new androidUtilities();
    return window.plugins.androidUtilities;
};

cordova.addConstructor(androidUtilities.install);
