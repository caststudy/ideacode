var PROXY = {}
Object.defineProperty(PROXY, "webcontext", {
  	value : "http://localhost:8080",
	writable : false,
	configurable : false
});
Object.defineProperty(PROXY, "images", {	
	value : "http://localhost:8080/images/",
	writable : false,
	configurable : false
});